package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.response.CentricAccountDto;
import ir.demisco.cfs.model.entity.CentricAccount;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.service.business.api.core.GridDataProvider;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Selection;
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
                filterContext.getPath("activeFlag")
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
                    .activeFlag((Long) array[10])
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public Predicate getCustomRestriction(FilterContext filterContext) {
        DataSourceRequest dataSourceRequest = filterContext.getDataSourceRequest();
        String name;
        boolean isName = false;
        boolean nameEqualNull = false;
        for (DataSourceRequest.FilterDescriptor filter : dataSourceRequest.getFilter().getFilters()) {
            if ("name".equals(filter.getField())) {
                isName = true;
                name = (String) filter.getValue();
                if (name == null||name.equals("")) {
                    nameEqualNull = true;
                    filter.setDisable(true);
                }
            }
        }
        if (isName && nameEqualNull) {
            dataSourceRequest.getFilter().getFilters().add(DataSourceRequest.FilterDescriptor
                    .create("name", null, DataSourceRequest.Operators.EQUALS));
        }
        return null;
    }
}
