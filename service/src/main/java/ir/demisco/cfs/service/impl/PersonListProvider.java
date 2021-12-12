package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.response.PersonDto;
import ir.demisco.cloud.basic.model.entity.prs.Person;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.service.business.api.core.GridDataProvider;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonListProvider implements GridDataProvider {
    @Override
    public Class<?> getRootEntityClass() {
        return Person.class;
    }

    @Override
    public Selection<?> getCustomSelection(FilterContext filterContext) {
        CriteriaBuilder criteriaBuilder = filterContext.getCriteriaBuilder();
        return criteriaBuilder.array(
                filterContext.getPath("id"),
                filterContext.getPath("personName")
        );
    }

    @Override
    public List<Order> getCustomSort(FilterContext filterContext) {
        return Collections.singletonList(filterContext.getCriteriaBuilder().asc(filterContext.getCriteriaBuilder().trim(filterContext.getPath("personName"))));
    }

    @Override
    public Predicate getCustomRestriction(FilterContext filterContext) {
        DataSourceRequest dataSourceRequest = filterContext.getDataSourceRequest();
        dataSourceRequest.getFilter().setLogic("and");
        dataSourceRequest.getFilter().getFilters().add(DataSourceRequest.FilterDescriptor
                .create("disableDate", LocalDateTime.now(), DataSourceRequest.Operators.IS_NULL));
        dataSourceRequest.getFilter().getFilters().add(DataSourceRequest.FilterDescriptor
                .create("disableDate", null, DataSourceRequest.Operators.IS_NULL));

        for (DataSourceRequest.FilterDescriptor filter : dataSourceRequest.getFilter().getFilters()) {
            if ("personName".equals(filter.getField())) {
                if (filter.getValue() == null || filter.getValue() == "") {
                    filter.setDisable(true);
                }
            }
        }
        return null;
    }

    @Override
    public List<Object> mapToDto(List<Object> resultList) {
        return resultList.stream().map(object -> {
            Object[] array = (Object[]) object;
            return PersonDto.builder()
                    .id(Long.parseLong(array[0].toString()))
                    .personName(array[1].toString())
                    .build();
        }).collect(Collectors.toList());
    }
}