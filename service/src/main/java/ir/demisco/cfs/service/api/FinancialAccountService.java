package ir.demisco.cfs.service.api;

import ir.demisco.cfs.model.dto.request.FinancialAccountNewRequest;
import ir.demisco.cfs.model.dto.request.FinancialAccountRequest;
import ir.demisco.cfs.model.dto.request.FinancialAccountStatusRequest;
import ir.demisco.cfs.model.dto.response.*;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;

import java.util.List;

public interface FinancialAccountService {
    DataSourceResult getFinancialAccount(DataSourceRequest dataSourceRequest);

    DataSourceResult getFinancialAccountLov(Long OrganizationId,DataSourceRequest dataSourceRequest);

    FinancialAccountOutPutResponse getFinancialAccountGetById(Long financialAccountId, Long organizationId);

    FinancialAccountOutPutDto save(FinancialAccountRequest financialAccountRequest);

    FinancialAccountOutPutDto update(FinancialAccountRequest financialAccountRequest);

    List<FinancialAccountAdjustmentResponse> getFinancialAccountAdjustmentLov(Long OrganizationId);

    Boolean deleteFinancialAccountById(Long financialAccount);

    Boolean getFinancialAccountByIdAndStatusFlag(FinancialAccountStatusRequest financialAccountStatusRequest, Long organizationId);

    List<FinancialAccountNewResponse> getFinancialAccountByFinancialAccountParentAndCodingAndStructure(FinancialAccountNewRequest financialAccountNewRequest);

    List<AccountPermanentStatusDto> getAccountPermanentStatusLov();

}
