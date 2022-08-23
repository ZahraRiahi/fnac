package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.response.AccountRelationTypeDetailDto;
import ir.demisco.cfs.model.entity.AccountRelationTypeDetail;
import ir.demisco.cloud.core.middle.service.business.api.core.GridDataProvider;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Selection;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FinancialAccountRelationTypeGridProvider implements GridDataProvider {
    @Override
    public Class<?> getRootEntityClass() {
        return AccountRelationTypeDetail.class;
    }

    @Override
    public Predicate getCustomRestriction(FilterContext filterContext) {
        return null;
    }

    @Override
    public List<Order> getCustomSort(FilterContext filterContext) {
        return Collections.singletonList(filterContext.getCriteriaBuilder().asc(filterContext.getPath("sequence")));
    }

    @Override
    public Selection<?> getCustomSelection(FilterContext filterContext) {
        CriteriaBuilder criteriaBuilder = filterContext.getCriteriaBuilder();
        return criteriaBuilder.array(
                filterContext.getPath("id"),
                filterContext.getPath("centricAccountType.id"),
                filterContext.getPath("centricAccountType.description"),
                filterContext.getPath("sequence"),
                filterContext.getPath("deletedDate")
        );
    }


    @Override
    public List<Object> mapToDto(List<Object> resultList) {

        return resultList.stream().map((Object object) -> {
            Object[] array = (Object[]) object;
            return AccountRelationTypeDetailDto.builder()
                    .id((Long) array[0])
                    .centricAccountTypeId((Long) array[1])
                    .centricAccountTypeDescription((String) array[2])
                    .sequence((Long) array[3])
                    .deletedDate((LocalDateTime) array[4])
                    .build();
        }).collect(Collectors.toList());
    }
}
