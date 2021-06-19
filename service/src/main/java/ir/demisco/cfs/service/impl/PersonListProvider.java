package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.response.PersonDto;
import ir.demisco.cloud.basic.model.entity.prs.Person;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.service.business.api.core.GridDataProvider;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Selection;
import java.time.LocalDateTime;
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
    public Predicate getCustomRestriction(FilterContext filterContext) {
        DataSourceRequest dataSourceRequest = filterContext.getDataSourceRequest();
        dataSourceRequest.getFilter().setLogic("or");
        dataSourceRequest.getFilter().getFilters().add(DataSourceRequest.FilterDescriptor
                .create("disableDate", LocalDateTime.now(), DataSourceRequest.Operators.GREATER_THAN_EQUAL));
        dataSourceRequest.getFilter().getFilters().add(DataSourceRequest.FilterDescriptor
                .create("disableDate", null, DataSourceRequest.Operators.IS_NULL));
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