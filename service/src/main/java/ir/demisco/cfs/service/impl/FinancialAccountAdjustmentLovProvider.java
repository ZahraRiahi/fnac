package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.response.FinancialAccountAdjustmentResponse;
import ir.demisco.cfs.model.entity.FinancialAccount;
import ir.demisco.cloud.core.middle.service.business.api.core.GridDataProvider;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Selection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FinancialAccountAdjustmentLovProvider implements GridDataProvider {
    @Override
    public Class<?> getRootEntityClass() {
        return FinancialAccount.class;
    }

    @Override
    public Selection<?> getCustomSelection(FilterContext filterContext) {
        CriteriaBuilder criteriaBuilder = filterContext.getCriteriaBuilder();
        return criteriaBuilder.array(
                filterContext.getPath("id"),
                filterContext.getPath("fullDescription"),
                filterContext.getPath("description"),
                filterContext.getPath("code")
        );
    }


    @Override
    public List<Object> mapToDto(List<Object> resultList) {
        return resultList.stream().map((Object object) -> {
            Object[] array = (Object[]) object;
            return FinancialAccountAdjustmentResponse.builder()
                    .id((Long) array[0])
                    .fullDescription((String) array[1])
                    .code((String) array[3])
                    .description((String) array[2])
                    .build();
        }).collect(Collectors.toList());
    }

}
