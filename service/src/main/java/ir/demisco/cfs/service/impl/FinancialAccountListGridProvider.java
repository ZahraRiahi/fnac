package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.response.FinancialAccountDto;
import ir.demisco.cfs.model.entity.FinancialAccount;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.service.business.api.core.GridDataProvider;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FinancialAccountListGridProvider implements GridDataProvider {
    @Override
    public Class<?> getRootEntityClass() {
        return FinancialAccount.class;
    }

    @Override
    public Selection<?> getCustomSelection(FilterContext filterContext) {
        CriteriaBuilder criteriaBuilder = filterContext.getCriteriaBuilder();
        return criteriaBuilder.array(
                filterContext.getPath("id"),
                filterContext.getPath("organization.id"),
                filterContext.getPath("description"),
                filterContext.getPath("code"),
                filterContext.getPath("activeFlag"),
                filterContext.getPath("accountNatureType.id"),
                filterContext.getPath("accountNatureType.description"),
                filterContext.getPath("accountRelationType.id"),
                filterContext.getPath("accountRelationType.description"),
                filterContext.getPath("financialAccountParent.id"),
                filterContext.getPath("deletedDate")
        );
    }

    @Override
    public List<Object> mapToDto(List<Object> resultList) {

        return resultList.stream().map((Object object) -> {
            Object[] array = (Object[]) object;

            return FinancialAccountDto.builder()
                    .id((Long) array[0])
                    .organizationId((Long) array[1])
                    .description((String) array[2])
                    .code((String) array[3])
                    .activeFlag((Long) array[4])
                    .accountNatureTypeId((Long) array[5])
                    .accountNatureTypeDescription((String) array[6])
                    .accountRelationTypeId((Long) array[7])
                    .accountRelationTypeDescription((String) array[8])
                    .financialAccountParentId((Long) array[9])
                    .deletedDate((LocalDateTime) array[10])
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public Predicate getCustomRestriction(FilterContext filterContext) {
        CriteriaBuilder criteriaBuilder = filterContext.getCriteriaBuilder();
        Root<Object> root = filterContext.getRoot();
        Join<Object, Object> financialAccountParent = root.join("financialAccountParent", JoinType.LEFT);
        Join<Object, Object> accountAdjustment = root.join("accountAdjustment", JoinType.LEFT);
        criteriaBuilder.equal(financialAccountParent.get("id"), root.get("id"));
        criteriaBuilder.equal(accountAdjustment.get("id"), root.get("id"));

        DataSourceRequest dataSourceRequest = filterContext.getDataSourceRequest();
        for (DataSourceRequest.FilterDescriptor filter : dataSourceRequest.getFilter().getFilters()) {
            switch (filter.getField()) {
                case "organization.id":
                case "centricAccountType.id":
                case "financialAccountStructure.financialCodingType.id":
                case "accountNatureType.id":
                case "accountRelationType.id":
                case "financialAccountStructure.id":
                    if (filter.getValue() == null) {
                        filter.setDisable(true);
                    }
                    break;
                case "description":
                    if (filter.getValue() == null || filter.getValue() == "") {
                        filter.setDisable(true);
                    }
                    break;
                default:
                    break;
            }
        }
        return null;
    }

}
