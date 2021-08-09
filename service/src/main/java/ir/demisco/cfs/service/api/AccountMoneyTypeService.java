package ir.demisco.cfs.service.api;

import ir.demisco.cfs.model.dto.request.AccountMoneyTypeRequest;
import ir.demisco.cfs.model.dto.response.AccountMoneyTypeDto;

import java.util.List;

public interface AccountMoneyTypeService {
    List<AccountMoneyTypeDto> getAccountMoneyType(AccountMoneyTypeRequest accountMoneyTypeRequest);
}
