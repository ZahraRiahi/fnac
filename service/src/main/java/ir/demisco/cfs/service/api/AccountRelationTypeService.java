package ir.demisco.cfs.service.api;

import ir.demisco.cfs.model.dto.response.AccountRelationTypeDto;

import java.util.List;

public interface AccountRelationTypeService {
    List<AccountRelationTypeDto> getAccountRelationType();
}
