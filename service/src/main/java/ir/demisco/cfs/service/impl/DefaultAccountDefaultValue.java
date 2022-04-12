package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.request.AccountDefaultValueDtoRequest;
import ir.demisco.cfs.model.dto.request.AccountDefaultValueUpdateRequest;
import ir.demisco.cfs.model.dto.response.AccountDefaultValueDto;
import ir.demisco.cfs.model.dto.response.AccountDefaultValueOutPutResponse;
import ir.demisco.cfs.model.entity.AccountDefaultValue;
import ir.demisco.cfs.service.api.AccountDefaultValueService;
import ir.demisco.cfs.service.repository.AccountDefaultValueRepository;
import ir.demisco.cfs.service.repository.AccountRelationTypeDetailRepository;
import ir.demisco.cfs.service.repository.CentricAccountRepository;
import ir.demisco.cfs.service.repository.FinancialAccountRepository;
import ir.demisco.cloud.core.middle.exception.RuleException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultAccountDefaultValue implements AccountDefaultValueService {
    private final AccountDefaultValueRepository accountDefaultValueRepository;
    private final AccountRelationTypeDetailRepository accountRelationTypeDetailRepository;
    private final FinancialAccountRepository financialAccountRepository;
    private final CentricAccountRepository centricAccountRepository;

    public DefaultAccountDefaultValue(AccountDefaultValueRepository accountDefaultValueRepository, AccountRelationTypeDetailRepository accountRelationTypeDetailRepository, FinancialAccountRepository financialAccountRepository, CentricAccountRepository centricAccountRepository) {
        this.accountDefaultValueRepository = accountDefaultValueRepository;
        this.accountRelationTypeDetailRepository = accountRelationTypeDetailRepository;
        this.financialAccountRepository = financialAccountRepository;
        this.centricAccountRepository = centricAccountRepository;
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public List<AccountDefaultValueDto> save(AccountDefaultValueDtoRequest accountDefaultValueDtoRequest) {
        List<Long> accountDefaultValue = accountRelationTypeDetailRepository.findByAccountRelationTypeDetail(accountDefaultValueDtoRequest.getAccountRelationTypeId());
        Long typeDetailIdList = accountDefaultValueRepository.findByFinancialAccountIdAndAccountRelationTypeDetailIdList(accountDefaultValueDtoRequest.getFinancialAccountId(), accountDefaultValue);
        if (typeDetailIdList > 0) {
            throw new RuleException("fin.defaultValue.ruleException.save");
        }
        List<AccountDefaultValueDto> accountDefaultValueDtos = new ArrayList<>();
        accountDefaultValue.forEach(e -> {
            AccountDefaultValue defaultValue = new AccountDefaultValue();
            defaultValue.setAccountRelationTypeDetail(accountRelationTypeDetailRepository.getOne(e));
            defaultValue.setFinancialAccount(financialAccountRepository.getOne(accountDefaultValueDtoRequest.getFinancialAccountId()));
            defaultValue = accountDefaultValueRepository.save(defaultValue);
            AccountDefaultValueDto accountDefaultValueDto = convertAccountDefaultValueToDto(defaultValue);
            accountDefaultValueDtos.add(accountDefaultValueDto);
        });
        return accountDefaultValueDtos;
    }

    private AccountDefaultValueDto convertAccountDefaultValueToDto(AccountDefaultValue accountDefaultValue) {
        return AccountDefaultValueDto.builder()
                .accountRelationTypeDetailId(accountDefaultValue.getAccountRelationTypeDetail().getId())
                .accountRelationTypeDescription(accountDefaultValue.getAccountRelationTypeDetail().getAccountRelationType().getDescription())
                .accountRelationTypeId(accountDefaultValue.getAccountRelationTypeDetail().getAccountRelationType().getId())
                .sequence(accountDefaultValue.getAccountRelationTypeDetail().getSequence())
                .build();
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public List<AccountDefaultValueOutPutResponse> updateAccountDefaultValueById(AccountDefaultValueUpdateRequest accountDefaultValueUpdateRequest) {
        List<AccountDefaultValueOutPutResponse> accountDefaultValueDtos = new ArrayList<>();
        accountDefaultValueUpdateRequest.getAccountDefaultValueUpdateDtos().forEach(e -> {
            AccountDefaultValue accountDefaultValue = accountDefaultValueRepository.findByIdAndAccountRelationTypeDetailId(e.getId(), e.getAccountRelationTypeDetailId());

            if (e.getCentricAccountId() == null) {
                accountDefaultValue.setCentricAccount(null);
            } else {
                accountDefaultValue.setCentricAccount(centricAccountRepository.getOne(e.getCentricAccountId()));
            }

            accountDefaultValue = accountDefaultValueRepository.save(accountDefaultValue);
            accountDefaultValueDtos.add(convertAccountDefaultValueToUpdateDto(accountDefaultValue));
        });

        return accountDefaultValueDtos;
    }


    private AccountDefaultValueOutPutResponse convertAccountDefaultValueToUpdateDto(AccountDefaultValue accountDefaultValue) {
        return AccountDefaultValueOutPutResponse.builder()
                .accountRelationTypeDetailId(accountDefaultValue.getAccountRelationTypeDetail().getId())
                .centricAccountId(accountDefaultValue.getCentricAccount() == null ? 0L : accountDefaultValue.getCentricAccount().getId())
                .centricAccountName(accountDefaultValue.getCentricAccount() == null ? "" : accountDefaultValue.getCentricAccount().getName())
                .centricAccountCode(accountDefaultValue.getCentricAccount() == null ? "" : accountDefaultValue.getCentricAccount().getCode())
                .accountRelationTypeDescription(accountDefaultValue.getAccountRelationTypeDetail().getAccountRelationType().getDescription())
                .accountRelationTypeId(accountDefaultValue.getAccountRelationTypeDetail().getAccountRelationType().getId())
                .build();
    }
}
