package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.response.FinancialCodingTypeDto;
import ir.demisco.cfs.model.entity.FinancialCodingType;
import ir.demisco.cfs.service.api.FinancialCodingTypeService;
import ir.demisco.cfs.service.repository.FinancialCodingTypeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultFinancialCodingType implements FinancialCodingTypeService {
    private final FinancialCodingTypeRepository financialCodingTypeRepository;

    public DefaultFinancialCodingType(FinancialCodingTypeRepository financialCodingTypeRepository) {
        this.financialCodingTypeRepository = financialCodingTypeRepository;
    }

    @Override
    @Transactional
    public List<FinancialCodingTypeDto> getFinancialCodingTypeByOrganizationId(Long OrganizationId) {
        List<FinancialCodingType> financialPeriodTypeList = financialCodingTypeRepository.findByOrganizationId(OrganizationId);
        return financialPeriodTypeList.stream().map(e -> FinancialCodingTypeDto.builder().id(e.getId())
                .description(e.getDescription())
                .build()).collect(Collectors.toList());
    }
}
