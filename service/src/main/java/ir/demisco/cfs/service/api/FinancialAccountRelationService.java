package ir.demisco.cfs.service.api;

import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;

public interface FinancialAccountRelationService {
    DataSourceResult getFinancialAccountRelationTypeDetailByFinancialCodingTypeId(Long accountRelationTypeId, DataSourceRequest dataSourceRequest);

}
