package ir.demisco.cfs.service.api;

import ir.demisco.cfs.model.dto.response.FinancialAccountStructureDto;
import ir.demisco.cfs.model.dto.response.FinancialAccountStructureResponse;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;

import java.util.List;

public interface FinancialAccountStructureService {
    DataSourceResult getFinancialAccountStructureByFinancialCodingTypeId(Long financialCodingTypeId, DataSourceRequest dataSourceRequest);

    List<FinancialAccountStructureResponse> getFinancialAccountStructureByFinancialCodingTypeIdLov(Long financialCodingTypeId);

    Long save(FinancialAccountStructureDto financialAccountStructureDto);

    FinancialAccountStructureDto update(FinancialAccountStructureDto financialAccountStructureDto);

}
