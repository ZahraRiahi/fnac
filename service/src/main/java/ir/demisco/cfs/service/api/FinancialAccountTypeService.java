package ir.demisco.cfs.service.api;

import ir.demisco.cfs.model.dto.response.FinancialAccountTypeDto;

import java.util.List;

public interface FinancialAccountTypeService {
    List<FinancialAccountTypeDto> getFinancialAccountType(Long financialAccountId);

}
