package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.response.CentricAccountDto;
import ir.demisco.cfs.model.entity.CentricAccount;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.service.business.api.core.GridDataProvider;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CentricAccountListGridProvider implements GridDataProvider {
    @Override
    public Class<?> getRootEntityClass() {
        return CentricAccount.class;
    }

    @Override
    public Selection<?> getCustomSelection(FilterContext filterContext) {
        CriteriaBuilder criteriaBuilder = filterContext.getCriteriaBuilder();
        return criteriaBuilder.array(
                filterContext.getPath("id"),
                filterContext.getPath("name"),
                filterContext.getPath("code"),
                filterContext.getPath("abbreviationName"),
                filterContext.getPath("latinName"),
                filterContext.getPath("centricAccountType.id"),
                filterContext.getPath("centricAccountType.description"),
                filterContext.getPath("organization.id"),
                filterContext.getPath("person.id"),
                filterContext.getPath("person.personName"),
                filterContext.getPath("activeFlag"),
                filterContext.getPath("parentCentricAccount.id"),
                filterContext.getPath("parentCentricAccount.code"),
                filterContext.getPath("parentCentricAccount.name"),
                filterContext.getPath("deletedDate")
        );
    }

    @Override
    public List<Object> mapToDto(List<Object> resultList) {

        return resultList.stream().map(object -> {
            Object[] array = (Object[]) object;

            return CentricAccountDto.builder()
                    .id((Long) array[0])
                    .name((String) array[1])
                    .code((String) array[2])
                    .abbreviationName((String) array[3])
                    .latinName((String) array[4])
                    .centricAccountTypeId((Long) array[5])
                    .centricAccountTypeDescription((String) array[6])
                    .organizationId((Long) array[7])
                    .personId((Long) array[8])
                    .personName((String) array[9])
                    .activeFlag((Boolean) array[10])
                    .parentCentricAccountId((Long) array[11])
                    .parentCentricAccountCode((String) array[12])
                    .parentCentricAccountName((String) array[13])
                    .deletedDate((LocalDateTime) array[14])
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public Predicate getCustomRestriction(FilterContext filterContext) {
        CriteriaBuilder criteriaBuilder = filterContext.getCriteriaBuilder();
        Root<Object> root = filterContext.getRoot();
        Join<Object, Object> centricAccountParent = root.join("parentCentricAccount", JoinType.LEFT);
        criteriaBuilder.equal(centricAccountParent.get("id"), root.get("id"));
        DataSourceRequest dataSourceRequest = filterContext.getDataSourceRequest();
        for (DataSourceRequest.FilterDescriptor filter : dataSourceRequest.getFilter().getFilters()) {
            switch (filter.getField()) {
                case "centricAccountType.id":
                case "organization.id":
                    if (filter.getValue() == null) {
                        filter.setDisable(true);
                    }
                    break;
                case "name":
                    if (filter.getValue() == null || filter.getValue() == "") {
                        filter.setDisable(true);
                    }
                    break;
            }
        }
        return null;
    }
}
