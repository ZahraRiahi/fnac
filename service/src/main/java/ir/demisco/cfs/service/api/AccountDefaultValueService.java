package ir.demisco.cfs.service.api;

import ir.demisco.cfs.model.dto.request.AccountDefaultValueDtoRequest;
import ir.demisco.cfs.model.dto.request.AccountDefaultValueUpdateRequest;
import ir.demisco.cfs.model.dto.response.AccountDefaultValueDto;
import ir.demisco.cfs.model.dto.response.AccountDefaultValueOutPutResponse;

import java.util.List;

public interface AccountDefaultValueService {
    List<AccountDefaultValueDto> save(AccountDefaultValueDtoRequest accountDefaultValueDtoRequest);

    List<AccountDefaultValueOutPutResponse> updateAccountDefaultValueById(AccountDefaultValueUpdateRequest accountDefaultValueUpdateRequest);

}
