package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.response.FinancialAccountDto;
import ir.demisco.cfs.model.entity.FinancialAccount;
import ir.demisco.cloud.core.middle.service.business.api.core.GridDataProvider;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.*;
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
                filterContext.getPath("permanentFlag"),
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

        return resultList.stream().map(object -> {
            Object[] array = (Object[]) object;

            return FinancialAccountDto.builder()
                    .id((Long) array[0])
                    .organizationId((Long) array[1])
                    .description((String) array[2])
                    .code((String) array[3])
                    .activeFlag((Boolean) array[4])
                    .permanentFlag((Boolean) array[5])
                    .accountNatureTypeId((Long) array[6])
                    .accountNatureTypeDescription((String) array[7])
                    .accountRelationTypeId((Long) array[8])
                    .accountRelationTypeDescription((String) array[9])
                    .financialAccountParentId((Long) array[10])
                    .deletedDate((LocalDateTime) array[11])
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
        return null;
    }

}
