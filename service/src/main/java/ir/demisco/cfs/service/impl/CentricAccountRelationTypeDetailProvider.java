package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.response.CentricAccountRelationDetailDto;
import ir.demisco.cfs.model.entity.CentricAccount;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.service.business.api.core.GridDataProvider;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CentricAccountRelationTypeDetailProvider implements GridDataProvider {
    @Override
    public Class<?> getRootEntityClass() {
        return CentricAccount.class;
    }

    @Override
    public Selection<?> getCustomSelection(FilterContext filterContext) {
        CriteriaBuilder criteriaBuilder = filterContext.getCriteriaBuilder();
        return criteriaBuilder.array(
                filterContext.getPath("accountDefaultValues.accountRelationTypeDetail.id"),
                filterContext.getPath("id"),
                filterContext.getPath("name"),
                filterContext.getPath("code"),
                filterContext.getPath("accountDefaultValues.accountRelationTypeDetail.accountRelationType.description"),
                filterContext.getPath("accountDefaultValues.accountRelationTypeDetail.accountRelationType.id"),
                filterContext.getPath("accountDefaultValues.accountRelationTypeDetail.sequence"),
                filterContext.getPath("accountDefaultValues.accountRelationTypeDetail.centricAccountType.id"),
                filterContext.getPath("accountDefaultValues.accountRelationTypeDetail.centricAccountType.description"),
                filterContext.getPath("deletedDate")
        );
    }

    @Override
    public List<Object> mapToDto(List<Object> resultList) {

        return resultList.stream().map(object -> {
            Object[] array = (Object[]) object;

            return CentricAccountRelationDetailDto.builder()
                    .accountDefaultValuesRelationTypeDetailsId((Long) array[0])
                    .id((Long) array[1])
                    .name((String) array[2])
                    .code((String) array[3])
                    .accountDefaultValuesRelationTypeDescription((String) array[4])
                    .accountRelationTypeId((Long) array[5])
                    .accountRelationTypeDetailSequence((Long) array[6])
                    .centricAccountTypeId((Long) array[7])
                    .centricAccountTypeName((String) array[8])
                    .deletedDate((LocalDateTime) array[9])
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public Predicate getCustomRestriction(FilterContext filterContext) {
        CriteriaBuilder criteriaBuilder = filterContext.getCriteriaBuilder();
        Root<Object> root = filterContext.getRoot();
        Join<Object, Object> centricAccountDefaultValues = root.join("accountDefaultValues", JoinType.LEFT);
        criteriaBuilder.equal(centricAccountDefaultValues.get("id"), root.get("id"));
        Join<Object, Object> accountRelationTypeDetail = centricAccountDefaultValues.join("accountRelationTypeDetail", JoinType.LEFT);
        criteriaBuilder.equal(accountRelationTypeDetail.get("id"), centricAccountDefaultValues.get("id"));
        Join<Object, Object> accountRelationType = accountRelationTypeDetail.join("accountRelationType", JoinType.LEFT);
        criteriaBuilder.equal(accountRelationType.get("id"), accountRelationTypeDetail.get("id"));


        DataSourceRequest dataSourceRequest = filterContext.getDataSourceRequest();
        for (DataSourceRequest.FilterDescriptor filter : dataSourceRequest.getFilter().getFilters()) {
            switch (filter.getField()) {
                case "accountDefaultValues.financialAccount.id":
                    if (filter.getValue() == null) {
                        filter.setDisable(true);
                    }
                    break;
            }
        }
        return null;
    }

}
