package ir.demisco.cfs.service.api;

import ir.demisco.cfs.model.dto.request.CentricAccountRequest;
import ir.demisco.cfs.model.dto.response.CentricAccountDto;
import ir.demisco.cfs.model.dto.response.CentricAccountOutPutResponse;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;


public interface CentricAccountService {

    DataSourceResult getCentricAccountByOrganizationIdAndPersonAndName(DataSourceRequest dataSourceRequest);

    CentricAccountDto save(CentricAccountRequest centricAccountRequest);

    Boolean deleteCentricAccountById(Long centricAccount);

    Boolean getCentricAccountByOrganIdAndPersonId(Long personId, Long organizationId);

    CentricAccountOutPutResponse getCentricAccountGetById(Long centricAccountId);

    DataSourceResult getCentricAccountByOrganizationIdAndCentricAccountTypeId(DataSourceRequest dataSourceRequest);

    DataSourceResult getCentricAccountByOrganIdAndCentricAccountTypeId(DataSourceRequest dataSourceRequest);

}

