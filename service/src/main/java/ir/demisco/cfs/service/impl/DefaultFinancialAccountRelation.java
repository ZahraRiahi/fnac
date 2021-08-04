package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.service.api.FinancialAccountRelationService;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import ir.demisco.cloud.core.middle.service.business.api.core.GridFilterService;
import org.apache.http.util.Asserts;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class DefaultFinancialAccountRelation implements FinancialAccountRelationService {
    private final GridFilterService gridFilterService;
    private final FinancialAccountRelationTypeGridProvider financialAccountRelationTypeGridProvider;

    public DefaultFinancialAccountRelation(GridFilterService gridFilterService, FinancialAccountRelationTypeGridProvider financialAccountRelationTypeGridProvider) {
        this.gridFilterService = gridFilterService;
        this.financialAccountRelationTypeGridProvider = financialAccountRelationTypeGridProvider;
    }

    @Override
    @Transactional
    public DataSourceResult getFinancialAccountRelationTypeDetailByFinancialCodingTypeId(Long accountRelationTypeId, DataSourceRequest dataSourceRequest) {
        Asserts.notNull(accountRelationTypeId, "accountRelationTypeId is null");
        dataSourceRequest.getFilter().setLogic("and");
        dataSourceRequest.getFilter().getFilters().add(DataSourceRequest
                .FilterDescriptor.create("accountRelationType.id", accountRelationTypeId, DataSourceRequest.Operators.EQUALS));
        dataSourceRequest.getFilter().getFilters().add(DataSourceRequest.FilterDescriptor.create("deletedDate", null, DataSourceRequest.Operators.IS_NULL));
        dataSourceRequest.getFilter().getFilters().add(DataSourceRequest.FilterDescriptor.create("accountRelationType.deletedDate", null, DataSourceRequest.Operators.IS_NULL));
        dataSourceRequest.getFilter().getFilters().add(DataSourceRequest.FilterDescriptor.create("centricAccountType.deletedDate", null, DataSourceRequest.Operators.IS_NULL));
        return gridFilterService.filter(dataSourceRequest, financialAccountRelationTypeGridProvider);
    }
}
