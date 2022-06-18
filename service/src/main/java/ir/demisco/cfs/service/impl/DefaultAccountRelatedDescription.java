package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.request.AccountRelatedDescriptionRequest;
import ir.demisco.cfs.model.dto.response.AccountRelatedDescriptionDto;

import ir.demisco.cfs.model.entity.AccountRelatedDescription;
import ir.demisco.cfs.model.entity.FinancialAccountDescription;
import ir.demisco.cfs.service.api.AccountRelatedDescriptionService;
import ir.demisco.cfs.service.repository.AccountRelatedDescriptionRepository;
import ir.demisco.cfs.service.repository.FinancialAccountDescriptionRepository;
import ir.demisco.cfs.service.repository.FinancialAccountRepository;
import ir.demisco.cloud.core.middle.exception.RuleException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class DefaultAccountRelatedDescription implements AccountRelatedDescriptionService {
    private final AccountRelatedDescriptionRepository accountRelatedDescriptionRepository;
    private final FinancialAccountDescriptionRepository financialAccountDescriptionRepository;
    private final FinancialAccountRepository financialAccountRepository;

    public DefaultAccountRelatedDescription(AccountRelatedDescriptionRepository accountRelatedDescriptionRepository, FinancialAccountDescriptionRepository financialAccountDescriptionRepository, FinancialAccountRepository financialAccountRepository) {
        this.accountRelatedDescriptionRepository = accountRelatedDescriptionRepository;
        this.financialAccountDescriptionRepository = financialAccountDescriptionRepository;
        this.financialAccountRepository = financialAccountRepository;
    }


    @Override
    @Transactional(rollbackOn = Throwable.class)
    public boolean deleteAccountRelatedDescriptionById(Long accountRelatedDescriptionId) {
        if (!accountRelatedDescriptionRepository.findById(accountRelatedDescriptionId).isPresent()) {
            throw new RuleException("fin.accountRelatedDescription.delete");
        }

        accountRelatedDescriptionRepository.deleteById(accountRelatedDescriptionId);
        return true;
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public AccountRelatedDescriptionDto save(AccountRelatedDescriptionRequest accountRelatedDescriptionRequest) {
        if (accountRelatedDescriptionRequest.getFinancialAccountDesId() == null) {
            FinancialAccountDescription financialAccountDescription = new FinancialAccountDescription();
            financialAccountDescription = saveFinancialAccountDescription(financialAccountDescription, accountRelatedDescriptionRequest);

            AccountRelatedDescription accountRelatedDescription = new AccountRelatedDescription();
            accountRelatedDescription.setFinancialAccountDescription(financialAccountDescription);
            if (accountRelatedDescriptionRequest.getFinancialAccountId() != null) {
                accountRelatedDescription.setFinancialAccount(financialAccountRepository.getOne(accountRelatedDescriptionRequest.getFinancialAccountId()));
            }
            accountRelatedDescription = accountRelatedDescriptionRepository.save(accountRelatedDescription);
            return convertAccountRelatedDescriptionDto(accountRelatedDescription);

        } else if (accountRelatedDescriptionRequest.getFinancialAccountDesId() != null) {
            Long accountRelatedDescriptionCount = accountRelatedDescriptionRepository.getAccountRelatedDescriptionByFinancialAccountDescriptionId(accountRelatedDescriptionRequest.getFinancialAccountDesId(), accountRelatedDescriptionRequest.getFinancialAccountId());
            if (accountRelatedDescriptionCount == null) {
                AccountRelatedDescription accountRelatedDescription = new AccountRelatedDescription();
                accountRelatedDescription.setFinancialAccountDescription(financialAccountDescriptionRepository.getOne(accountRelatedDescriptionRequest.getFinancialAccountDesId()));
                accountRelatedDescription.setFinancialAccount(financialAccountRepository.getOne(accountRelatedDescriptionRequest.getFinancialAccountId()));
                accountRelatedDescription = accountRelatedDescriptionRepository.save(accountRelatedDescription);
                return convertAccountRelatedDescriptionDto(accountRelatedDescription);
            } else {
                FinancialAccountDescription financialAccountDescription = financialAccountDescriptionRepository.getOne(accountRelatedDescriptionRequest.getFinancialAccountDesId());
                financialAccountDescription.setDescription(accountRelatedDescriptionRequest.getDescription());
                financialAccountDescription = financialAccountDescriptionRepository.save(financialAccountDescription);
                AccountRelatedDescription accountRelatedDescription = accountRelatedDescriptionRepository.findByFinancialAccountDescriptionId(financialAccountDescription.getId());
                return convertAccountRelatedDescriptionDto(accountRelatedDescription);
            }
        } else {
            throw new RuleException("fin.financialAccount.notInformation");
        }
    }


    private FinancialAccountDescription saveFinancialAccountDescription(FinancialAccountDescription financialAccountDescription, AccountRelatedDescriptionRequest accountRelatedDescriptionRequest) {
        financialAccountDescription.setDescription(accountRelatedDescriptionRequest.getDescription());
        return financialAccountDescriptionRepository.save(financialAccountDescription);
    }


    public AccountRelatedDescriptionDto convertAccountRelatedDescriptionDto(AccountRelatedDescription accountRelatedDescription) {
        return AccountRelatedDescriptionDto.builder()
                .financialAccountId(accountRelatedDescription.getFinancialAccount().getId())
                .accountRelatedDescriptionId(accountRelatedDescription.getId())
                .financialAccountDescription(accountRelatedDescription.getFinancialAccountDescription().getDescription())
                .financialAccountDescriptionId(accountRelatedDescription.getFinancialAccountDescription().getId())
                .build();

    }


}