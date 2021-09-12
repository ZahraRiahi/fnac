package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.response.FinancialAccountStructureDto;
import ir.demisco.cfs.model.entity.FinancialAccountStructure;
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
public class FinancialAccountStructureListGridProvider implements GridDataProvider {

    @Override
    public Class<?> getRootEntityClass() {
        return FinancialAccountStructure.class;
    }

    @Override
    public Predicate getCustomRestriction(FilterContext filterContext) {
        return null;
    }

    @Override
    public Selection<?> getCustomSelection(FilterContext filterContext) {
        CriteriaBuilder criteriaBuilder = filterContext.getCriteriaBuilder();
        return criteriaBuilder.array(
                filterContext.getPath("id"),
                filterContext.getPath("description"),
                filterContext.getPath("sequence"),
                filterContext.getPath("digitCount"),
                filterContext.getPath("sumDigit"),
                filterContext.getPath("color"),
                filterContext.getPath("financialCodingType.id"),
                filterContext.getPath("deletedDate")
        );
    }

    @Override
    public List<Order> getCustomSort(FilterContext filterContext) {
        return Collections.singletonList(filterContext.getCriteriaBuilder().asc(filterContext.getPath("sequence")));
    }

    @Override
    public List<Object> mapToDto(List<Object> resultList) {

        return resultList.stream().map(object -> {
            Object[] array = (Object[]) object;

            return FinancialAccountStructureDto.builder()
                    .id((Long) array[0])
                    .description((String) array[1])
                    .sequence((Long) array[2])
                    .digitCount((Long) array[3])
                    .sumDigit((Long) array[4])
                    .color((String) array[5])
                    .financialCodingTypeId((Long) array[6])
                    .deletedDate((LocalDateTime) array[7])
                    .build();
        }).collect(Collectors.toList());
    }
}
