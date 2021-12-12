package ir.demisco.cfs.service.api;

import ir.demisco.cfs.model.dto.request.*;
import ir.demisco.cfs.model.dto.response.*;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;

import java.util.List;

public interface FinancialAccountService {
    DataSourceResult getFinancialAccount(DataSourceRequest dataSourceRequest);

    DataSourceResult getFinancialAccountLov(DataSourceRequest dataSourceRequest);

    FinancialAccountOutPutResponse getFinancialAccountGetById(Long financialAccountId, Long organizationId);

    FinancialAccountOutPutDto save(FinancialAccountRequest financialAccountRequest);

    FinancialAccountOutPutDto update(FinancialAccountRequest financialAccountRequest);

    //    List<FinancialAccountAdjustmentResponse> getFinancialAccountAdjustmentLov(Long OrganizationId);
    DataSourceResult getFinancialAccountAdjustmentLov(Long OrganizationId, DataSourceRequest dataSourceRequest);

    Boolean deleteFinancialAccountById(Long financialAccount);

    Boolean getFinancialAccountByIdAndStatusFlag(FinancialAccountStatusRequest financialAccountStatusRequest, Long organizationId);

    List<FinancialAccountNewResponse> getFinancialAccountByFinancialAccountParentAndCodingAndStructure(FinancialAccountNewRequest financialAccountNewRequest);

    List<AccountPermanentStatusDto> getAccountPermanentStatusLov();

    Boolean getFinancialAccountGetInsertAllowControl(FinancialAccountAllowChildRequest financialAccountAllowChildRequest);

//    List<FinancialAccountGetByStructureResponse> getFinancialAccountByGetByStructure(Long organizationId, FinancialAccountGetByStructureRequest financialAccountGetByStructureRequest);

    DataSourceResult getFinancialAccountByGetByStructure(Long OrganizationId, DataSourceRequest dataSourceRequest);

}
