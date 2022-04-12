package ir.demisco.cfs.service.api;

import ir.demisco.cfs.model.dto.response.FinancialCodingTypeDto;
import ir.demisco.cfs.model.dto.response.FinancialCodingTypeResponse;

import java.util.List;

public interface FinancialCodingTypeService {
    List<FinancialCodingTypeResponse> getFinancialCodingTypeByOrganizationId(Long organizationId);

    Long save(FinancialCodingTypeDto financialCodingTypeDto);

    FinancialCodingTypeDto update(FinancialCodingTypeDto financialCodingTypeDto);

    boolean deleteFinancialCodingTypeById(Long financialCodingType);
}
