package ir.demisco.cfs.service.api;

import ir.demisco.cfs.model.dto.response.FinancialAccountDescriptionDto;

import java.util.List;

public interface FinancialAccountDescriptionService {
    List<FinancialAccountDescriptionDto> getFinancialAccountDescription();

}
