package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.service.api.FinancialAccountService;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import ir.demisco.cloud.core.middle.service.business.api.core.GridFilterService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class DefaultFinancialAccount implements FinancialAccountService {
    private final GridFilterService gridFilterService;
    private final FinancialAccountListGridProvider financialAccountListGridProvider;

    public DefaultFinancialAccount(GridFilterService gridFilterService, FinancialAccountListGridProvider financialAccountListGridProvider) {
        this.gridFilterService = gridFilterService;
        this.financialAccountListGridProvider = financialAccountListGridProvider;
    }

    @Override
    @Transactional
    public DataSourceResult getFinancialAccount(DataSourceRequest dataSourceRequest) {
        return gridFilterService.filter(dataSourceRequest, financialAccountListGridProvider);
    }
}
