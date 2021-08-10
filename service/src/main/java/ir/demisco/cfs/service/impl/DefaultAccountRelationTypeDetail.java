package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.service.api.AccountRelationTypeDetailService;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import ir.demisco.cloud.core.middle.service.business.api.core.GridFilterService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class DefaultAccountRelationTypeDetail implements AccountRelationTypeDetailService {
    private final GridFilterService gridFilterService;
    private final CentricAccountRelationTypeDetailProvider centricAccountRelationTypeDetailProvider;

    public DefaultAccountRelationTypeDetail(GridFilterService gridFilterService, CentricAccountRelationTypeDetailProvider centricAccountRelationTypeDetailProvider) {
        this.gridFilterService = gridFilterService;
        this.centricAccountRelationTypeDetailProvider = centricAccountRelationTypeDetailProvider;
    }

    @Override
    @Transactional
    public DataSourceResult getFinancialAccountByFinancialAccountId(DataSourceRequest dataSourceRequest) {
        dataSourceRequest.getFilter().getFilters().add(DataSourceRequest.FilterDescriptor.create("deletedDate", null, DataSourceRequest.Operators.IS_NULL));
        dataSourceRequest.getFilter().getFilters().add(DataSourceRequest.FilterDescriptor.create("accountDefaultValues.deletedDate", null, DataSourceRequest.Operators.IS_NULL));
        dataSourceRequest.getFilter().getFilters().add(DataSourceRequest.FilterDescriptor.create("accountDefaultValues.accountRelationTypeDetail.deletedDate", null, DataSourceRequest.Operators.IS_NULL));
        dataSourceRequest.getFilter().getFilters().add(DataSourceRequest.FilterDescriptor.create("accountDefaultValues.accountRelationTypeDetail.accountRelationType.deletedDate", null, DataSourceRequest.Operators.IS_NULL));
        dataSourceRequest.getFilter().getFilters().add(DataSourceRequest.FilterDescriptor.create("centricAccountType.deletedDate", null, DataSourceRequest.Operators.IS_NULL));

        return gridFilterService.filter(dataSourceRequest, centricAccountRelationTypeDetailProvider);
    }

}
