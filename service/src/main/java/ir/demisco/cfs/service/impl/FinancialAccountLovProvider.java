package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.response.FinancialAccountResponse;
import ir.demisco.cfs.model.entity.FinancialAccount;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.service.business.api.core.GridDataProvider;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FinancialAccountLovProvider implements GridDataProvider {
    @Override
    public Class<?> getRootEntityClass() {
        return FinancialAccount.class;
    }

    @Override
    public Selection<?> getCustomSelection(FilterContext filterContext) {
        CriteriaBuilder criteriaBuilder = filterContext.getCriteriaBuilder();
        Join<Object, Object> accountRelationTypeJoin = (Join<Object, Object>) filterContext.getJoins().get("accountRelationTypeJoin");
        return criteriaBuilder.array(
                filterContext.getPath("id"),
                filterContext.getPath("code"),
                filterContext.getPath("description"),
                filterContext.getPath("referenceFlag"),
                filterContext.getPath("exchangeFlag"),
                accountRelationTypeJoin.get("id"),
                filterContext.getPath("disableDate")

        );
    }

    @Override
    public List<Order> getCustomSort(FilterContext filterContext) {
        return null;
    }

    @Override
    public List<Object> mapToDto(List<Object> resultList) {

        return resultList.stream().map(object -> {
            Object[] array = (Object[]) object;

            return FinancialAccountResponse.builder()
                    .id((Long) array[0])
                    .code((String) array[1])
                    .description((String) array[2])
                    .referenceFlag((Boolean) array[3])
                    .exchangeFlag((Boolean) array[4])
                    .accountRelationTypeId((Long) array[5])
                    .disableDate((Date) array[6])
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public Predicate getCustomRestriction(FilterContext filterContext) {

        DataSourceRequest dataSourceRequest = filterContext.getDataSourceRequest();
        dataSourceRequest.getFilter().getFilters().add(DataSourceRequest.FilterDescriptor
                .create("deletedDate", null, DataSourceRequest.Operators.IS_NULL));
        dataSourceRequest.getFilter().getFilters().add(DataSourceRequest.FilterDescriptor
                .create("disableDate", null, DataSourceRequest.Operators.IS_NULL));
        dataSourceRequest.getFilter().getFilters().add(DataSourceRequest.FilterDescriptor
                .create("financialAccountStructure.flgShowInAcc", Boolean.TRUE, DataSourceRequest.Operators.EQUALS));
        dataSourceRequest.getFilter().getFilters().add(DataSourceRequest.FilterDescriptor
                .create("organization.id", 100, DataSourceRequest.Operators.EQUALS));

        CriteriaBuilder criteriaBuilder = filterContext.getCriteriaBuilder();
        CriteriaQuery<FinancialAccount> financialAccountQuery = filterContext.getCriteriaQuery();
        Root<FinancialAccount> root = filterContext.getRoot();

        Join<Object, Object> accountRelationTypeJoin = root.join("accountRelationType", JoinType.LEFT);
        filterContext.getJoins().put("accountRelationTypeJoin", accountRelationTypeJoin);

        Subquery<FinancialAccount> financialAccountSubQuery = financialAccountQuery.subquery(FinancialAccount.class);
        Root<FinancialAccount> financialAccountRootSubQuery = financialAccountSubQuery.from(FinancialAccount.class);
        financialAccountSubQuery.select(financialAccountRootSubQuery);

        Join<Object, Object> financialAccountParent = financialAccountRootSubQuery.join("financialAccountParent");
        Join<Object, Object> organization = financialAccountRootSubQuery.join("organization");

        List<Predicate> subQueryPredicateList = new ArrayList<>();
        subQueryPredicateList.add(criteriaBuilder.equal(financialAccountParent.get("id"), root.get("id")));
        subQueryPredicateList.add(criteriaBuilder.equal(organization.get("id"), 100));
        subQueryPredicateList.add(criteriaBuilder.isNull(financialAccountRootSubQuery.get("deletedDate")));
        financialAccountSubQuery.where(subQueryPredicateList.toArray(new Predicate[]{}));

        return criteriaBuilder.and(criteriaBuilder.not(criteriaBuilder.exists(financialAccountSubQuery)));

    }

}
