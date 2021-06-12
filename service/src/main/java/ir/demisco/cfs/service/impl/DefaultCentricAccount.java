package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.response.CentricAccountDto;
import ir.demisco.cfs.model.dto.response.CentricAccountNewResponse;
import ir.demisco.cfs.model.entity.CentricAccount;
import ir.demisco.cfs.service.api.CentricAccountService;
import ir.demisco.cfs.service.repository.CentricAccountRepository;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import ir.demisco.cloud.core.middle.service.business.api.core.GridFilterService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class DefaultCentricAccount implements CentricAccountService {
    private final GridFilterService gridFilterService;
    private final CentricAccountListGridProvider financialPeriodListGridProvider;
    private final CentricAccountRepository centricAccountRepository;

    public DefaultCentricAccount(GridFilterService gridFilterService, CentricAccountListGridProvider financialPeriodListGridProvider, CentricAccountRepository centricAccountRepository) {
        this.gridFilterService = gridFilterService;
        this.financialPeriodListGridProvider = financialPeriodListGridProvider;
        this.centricAccountRepository = centricAccountRepository;
    }

    @Override
    @Transactional
    public DataSourceResult getCentricAccountByOrganizationIdAndPersonAndName(DataSourceRequest dataSourceRequest) {
        return gridFilterService.filter(dataSourceRequest, financialPeriodListGridProvider);
    }

    @Override
    @Transactional
    public List<CentricAccountNewResponse> getCentricAccountByOrganizationIdAndCentricAccountTypeId(Long centricAccountTypeId, Long organizationId) {
        List<CentricAccount> centricAccountList = centricAccountRepository.findByCentricAccountAndOrganizationId(centricAccountTypeId, 1L);
        return centricAccountList.stream().map(e -> CentricAccountNewResponse.builder().id(e.getId())
                .name(e.getName())
                .code(e.getCode()).build()).collect(Collectors.toList());
    }

}
