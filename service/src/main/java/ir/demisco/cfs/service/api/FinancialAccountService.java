package ir.demisco.cfs.service.api;

import ir.demisco.cfs.model.dto.request.FinancialAccountAllowChildRequest;
import ir.demisco.cfs.model.dto.request.FinancialAccountNewRequest;
import ir.demisco.cfs.model.dto.request.FinancialAccountRequest;
import ir.demisco.cfs.model.dto.request.FinancialAccountStatusRequest;
import ir.demisco.cfs.model.dto.response.AccountPermanentStatusDto;
import ir.demisco.cfs.model.dto.response.FinancialAccountNewResponse;
import ir.demisco.cfs.model.dto.response.FinancialAccountOutPutDto;
import ir.demisco.cfs.model.dto.response.FinancialAccountOutPutResponse;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;

import java.util.List;

public interface FinancialAccountService {
    DataSourceResult getFinancialAccount(DataSourceRequest dataSourceRequest);

    DataSourceResult getFinancialAccountLov(DataSourceRequest dataSourceRequest);

    FinancialAccountOutPutResponse getFinancialAccountGetById(Long financialAccountId, Long organizationId);

    FinancialAccountOutPutDto save(FinancialAccountRequest financialAccountRequest);

    FinancialAccountOutPutDto update(FinancialAccountRequest financialAccountRequest);

    DataSourceResult getFinancialAccountAdjustmentLov(Long organizationId, DataSourceRequest dataSourceRequest);

    Boolean deleteFinancialAccountById(Long financialAccount);

    Boolean getFinancialAccountByIdAndStatusFlag(FinancialAccountStatusRequest financialAccountStatusRequest, Long organizationId);

    List<FinancialAccountNewResponse> getFinancialAccountByFinancialAccountParentAndCodingAndStructure(FinancialAccountNewRequest financialAccountNewRequest);

    List<AccountPermanentStatusDto> getAccountPermanentStatusLov();

    Boolean getFinancialAccountGetInsertAllowControl(FinancialAccountAllowChildRequest financialAccountAllowChildRequest);

    DataSourceResult getFinancialAccountByGetByStructure(Long organizationId, DataSourceRequest dataSourceRequest);

}
