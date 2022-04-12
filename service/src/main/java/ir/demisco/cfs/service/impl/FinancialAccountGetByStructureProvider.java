package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.response.FinancialAccountGetByStructureResponse;
import ir.demisco.cfs.model.entity.FinancialAccount;
import ir.demisco.cloud.core.middle.service.business.api.core.GridDataProvider;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FinancialAccountGetByStructureProvider implements GridDataProvider {
    @Override
    public Class<?> getRootEntityClass() {
        return FinancialAccount.class;
    }

    @Override
    public Selection<?> getCustomSelection(FilterContext filterContext) {
        CriteriaBuilder criteriaBuilder = filterContext.getCriteriaBuilder();
        return criteriaBuilder.array(
                filterContext.getPath("id"),
                filterContext.getPath("code"),
                filterContext.getPath("description"),
                filterContext.getPath("referenceFlag"),
                filterContext.getPath("exchangeFlag"),
                filterContext.getPath("accountRelationType.id"),
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

            return FinancialAccountGetByStructureResponse.builder()
                    .id((Long) array[0])
                    .code((String) array[1])
                    .description((String) array[2])
                    .referenceFlag((Long) array[3])
                    .exchangeFlag((Long) array[4])
                    .accountRelationTypeId((Long) array[5])
                    .disableDate((Date) array[6])
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public Predicate getCustomRestriction(FilterContext filterContext) {
        CriteriaBuilder criteriaBuilder = filterContext.getCriteriaBuilder();
        Root<FinancialAccount> root = filterContext.getRoot();
        Join<Object, Object> accountRelationType = root.join("accountRelationType", JoinType.LEFT);
        criteriaBuilder.equal(accountRelationType.get("id"), root.get("id"));
        return null;

    }

}

