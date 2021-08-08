package ir.demisco.cfs.service.api;

import ir.demisco.cfs.model.dto.response.MoneyTypeDto;

import java.util.List;

public interface MoneyTypeService {
    List<MoneyTypeDto> getMoneyType(Long financialAccountId);
}
