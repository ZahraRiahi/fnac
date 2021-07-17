package ir.demisco.cfs.service.impl;


import ir.demisco.cfs.model.dto.response.AccountNatureTypeDto;
import ir.demisco.cfs.model.dto.response.FinancialAccountDescriptionDto;
import ir.demisco.cfs.service.api.FinancialAccountDescriptionService;
import ir.demisco.cfs.service.repository.FinancialAccountDescriptionRepozitory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultFinancialAccountDescription implements FinancialAccountDescriptionService {
    private final FinancialAccountDescriptionRepozitory financialAccountDescriptionRepozitory;

    public DefaultFinancialAccountDescription(FinancialAccountDescriptionRepozitory financialAccountDescriptionRepozitory) {
        this.financialAccountDescriptionRepozitory = financialAccountDescriptionRepozitory;
    }


    @Override
    public List<FinancialAccountDescriptionDto> getFinancialAccountDescription() {
        return financialAccountDescriptionRepozitory.findByFinancialAccountDescription().stream().map(e -> FinancialAccountDescriptionDto.builder()
                .id(e.getId())
                .description(e.getDescription())
                .build()
        ).collect(Collectors.toList());
    }

}
