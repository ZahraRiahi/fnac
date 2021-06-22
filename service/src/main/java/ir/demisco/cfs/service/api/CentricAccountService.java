package ir.demisco.cfs.service.api;

import ir.demisco.cfs.model.dto.request.CentricAccountPersonRequest;
import ir.demisco.cfs.model.dto.request.CentricAccountRequest;
import ir.demisco.cfs.model.dto.response.CentricAccountDto;
import ir.demisco.cfs.model.dto.response.CentricAccountNewResponse;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;

import java.util.List;

public interface CentricAccountService {
    DataSourceResult getCentricAccountByOrganizationIdAndPersonAndName(DataSourceRequest dataSourceRequest);

    List<CentricAccountNewResponse> getCentricAccountByOrganizationIdAndCentricAccountTypeId(Long centricAccountTypeId, Long organizationId);

    CentricAccountDto save(CentricAccountRequest centricAccountRequest);

    List<CentricAccountNewResponse> getCentricAccountByOrganIdAndPersonId(CentricAccountPersonRequest centricAccountPersonRequest);

//    CentricAccountDto update(CentricAccountRequest centricAccountRequest);


}
