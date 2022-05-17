package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.response.CentricAccountDto;
import ir.demisco.cfs.model.entity.CentricAccount;
import ir.demisco.cloud.core.middle.service.business.api.core.GridDataProvider;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Selection;
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
                filterContext.getPath("centricAccountType.code"),
                filterContext.getPath("organization.id"),
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
                    .centricAccountTypeCode((String) array[7])
                    .organizationId((Long) array[8])
                    .activeFlag((Boolean) array[9])
                    .parentCentricAccountId((Long) array[10])
                    .parentCentricAccountCode((String) array[11])
                    .parentCentricAccountName((String) array[12])
                    .deletedDate((LocalDateTime) array[13])
                    .build();
        }).collect(Collectors.toList());
    }

}
