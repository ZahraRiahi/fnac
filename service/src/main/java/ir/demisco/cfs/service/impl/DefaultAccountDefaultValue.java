package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.request.AccountDefaultValueDtoRequest;
import ir.demisco.cfs.model.dto.response.AccountDefaultValueDto;
import ir.demisco.cfs.model.entity.AccountDefaultValue;
import ir.demisco.cfs.service.api.AccountDefaultValueService;
import ir.demisco.cfs.service.repository.AccountDefaultValueRepository;
import ir.demisco.cfs.service.repository.AccountRelationTypeDetailRepository;
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

    public DefaultAccountDefaultValue(AccountDefaultValueRepository accountDefaultValueRepository, AccountRelationTypeDetailRepository accountRelationTypeDetailRepository, FinancialAccountRepository financialAccountRepository) {
        this.accountDefaultValueRepository = accountDefaultValueRepository;
        this.accountRelationTypeDetailRepository = accountRelationTypeDetailRepository;
        this.financialAccountRepository = financialAccountRepository;
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public List<AccountDefaultValueDto> save(AccountDefaultValueDtoRequest accountDefaultValueDtoRequest) {
        List<Long> accountDefaultValue = accountRelationTypeDetailRepository.findByAccountRelationTypeDetail(accountDefaultValueDtoRequest.getAccountRelationTypeId());
        Long typeDetailIdList = accountDefaultValueRepository.findByFinancialAccountIdAndAccountRelationTypeDetailIdList(accountDefaultValueDtoRequest.getFinancialAccountId(), accountDefaultValue);
        if (typeDetailIdList > 0) {
            throw new RuleException("برای این حساب مالی تعدادی از تمرکزها قبلا درج شده است");
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
//                .centricAccountId(accountDefaultValue.getCentricAccount().getId())
//                .centricAccountName(accountDefaultValue.getCentricAccount().getName())
//                .centricAccountCode(accountDefaultValue.getCentricAccount().getCode())
                .accountRelationTypeDescription(accountDefaultValue.getAccountRelationTypeDetail().getAccountRelationType().getDescription())
                .accountRelationTypeId(accountDefaultValue.getAccountRelationTypeDetail().getAccountRelationType().getId())
                .sequence(accountDefaultValue.getAccountRelationTypeDetail().getSequence())
                .build();
    }

}
