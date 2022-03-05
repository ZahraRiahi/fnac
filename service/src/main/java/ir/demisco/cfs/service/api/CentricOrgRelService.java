package ir.demisco.cfs.service.api;

import ir.demisco.cfs.model.dto.response.CentricAccountDto;

public interface CentricOrgRelService {
    void save(Long organizationId, CentricAccountDto centricAccountDto);
}
