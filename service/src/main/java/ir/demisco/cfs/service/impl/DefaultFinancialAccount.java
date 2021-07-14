package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.response.FinancialAccountResponse;
import ir.demisco.cfs.model.entity.FinancialAccount;
import ir.demisco.cfs.service.api.FinancialAccountService;
import ir.demisco.cfs.service.repository.FinancialAccountRepository;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import ir.demisco.cloud.core.middle.service.business.api.core.GridFilterService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultFinancialAccount implements FinancialAccountService {
    private final GridFilterService gridFilterService;
    private final FinancialAccountListGridProvider financialAccountListGridProvider;
    private final FinancialAccountRepository financialAccountRepository;

    public DefaultFinancialAccount(GridFilterService gridFilterService, FinancialAccountListGridProvider financialAccountListGridProvider, FinancialAccountRepository financialAccountRepository) {
        this.gridFilterService = gridFilterService;
        this.financialAccountListGridProvider = financialAccountListGridProvider;
        this.financialAccountRepository = financialAccountRepository;
    }

    @Override
    @Transactional
    public DataSourceResult getFinancialAccount(DataSourceRequest dataSourceRequest) {
        dataSourceRequest.getFilter().getFilters().add(DataSourceRequest.FilterDescriptor.create("deletedDate", null, DataSourceRequest.Operators.IS_NULL));
        return gridFilterService.filter(dataSourceRequest, financialAccountListGridProvider);
    }

    @Override
    @Transactional
    public List<FinancialAccountResponse> getFinancialAccountLov(Long OrganizationId) {
        List<FinancialAccount> financialAccount = financialAccountRepository.findByFinancialAccountByOrganizationId(OrganizationId);
        return financialAccount.stream().map(e -> FinancialAccountResponse.builder().id(e.getId())
                .description(e.getDescription())
                .code(e.getCode())
                .build()).collect(Collectors.toList());
    }

}

