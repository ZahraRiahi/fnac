package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.response.CentricAccountRelationDetailDto;
import ir.demisco.cfs.model.entity.CentricAccount;
import ir.demisco.cloud.core.middle.service.business.api.core.GridDataProvider;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Selection;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CentricAccountProvider implements GridDataProvider {
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

}
