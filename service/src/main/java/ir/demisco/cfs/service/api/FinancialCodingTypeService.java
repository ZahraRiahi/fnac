package ir.demisco.cfs.service.api;

import ir.demisco.cfs.model.dto.response.FinancialCodingTypeDto;

import java.util.List;

public interface FinancialCodingTypeService {
    List<FinancialCodingTypeDto> getFinancialCodingTypeByOrganizationId(Long OrganizationId);

    Long save(FinancialCodingTypeDto financialCodingTypeDto);

    FinancialCodingTypeDto update(FinancialCodingTypeDto financialCodingTypeDto);
}
