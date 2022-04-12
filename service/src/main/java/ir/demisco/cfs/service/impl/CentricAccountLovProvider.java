package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.response.CentricAccountNewResponse;
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
public class CentricAccountLovProvider implements GridDataProvider {
    @Override
    public Class<?> getRootEntityClass() {
        return CentricAccount.class;
    }

    @Override
    public Selection<?> getCustomSelection(FilterContext filterContext) {
        CriteriaBuilder criteriaBuilder = filterContext.getCriteriaBuilder();
        return criteriaBuilder.array(
                filterContext.getPath("id"),
                filterContext.getPath("code"),
                filterContext.getPath("name")
        );
    }

    @Override
    public List<Object> mapToDto(List<Object> resultList) {
        return resultList.stream().map(object -> {
            Object[] array = (Object[]) object;
            return CentricAccountNewResponse.builder()
                    .id((Long) array[0])
                    .name((String) array[2])
                    .code((String) array[1])
                    .build();
        }).collect(Collectors.toList());
    }


    @Override
    public Predicate getCustomRestriction(FilterContext filterContext) {
        DataSourceRequest dataSourceRequest = filterContext.getDataSourceRequest();
        for (DataSourceRequest.FilterDescriptor filter : dataSourceRequest.getFilter().getFilters()) {
            switch (filter.getField()) {
                case "centricAccountType.id":
                case "parentCentricAccount.id":
                    if (filter.getValue() == null) {
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
