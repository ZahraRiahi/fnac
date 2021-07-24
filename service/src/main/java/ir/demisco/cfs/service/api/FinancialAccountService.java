package ir.demisco.cfs.service.api;

import ir.demisco.cfs.model.dto.response.FinancialAccountOutPutResponse;
import ir.demisco.cfs.model.dto.response.FinancialAccountResponse;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;

import java.util.List;

public interface FinancialAccountService {
    DataSourceResult getFinancialAccount(DataSourceRequest dataSourceRequest);

    List<FinancialAccountResponse> getFinancialAccountLov(Long OrganizationId);

   FinancialAccountOutPutResponse getFinancialAccountGetById(Long financialAccountId);


}
