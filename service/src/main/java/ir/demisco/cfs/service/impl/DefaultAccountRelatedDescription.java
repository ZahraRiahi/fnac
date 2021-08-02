package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.entity.AccountRelatedDescription;
import ir.demisco.cfs.service.api.AccountRelatedDescriptionService;
import ir.demisco.cfs.service.repository.AccountRelatedDescriptionRepository;
import ir.demisco.cloud.core.middle.exception.RuleException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class DefaultAccountRelatedDescription implements AccountRelatedDescriptionService {
    private final AccountRelatedDescriptionRepository accountRelatedDescriptionRepository;

    public DefaultAccountRelatedDescription(AccountRelatedDescriptionRepository accountRelatedDescriptionRepository) {
        this.accountRelatedDescriptionRepository = accountRelatedDescriptionRepository;
    }


    @Override
    @Transactional(rollbackOn = Throwable.class)
    public boolean deleteAccountRelatedDescriptionById(Long accountRelatedDescriptionId) {
        AccountRelatedDescription accountRelatedDescription =
                accountRelatedDescriptionRepository.findById(accountRelatedDescriptionId).orElseThrow(() -> new RuleException("شرح مورد استفاده ی حساب مالی یافت نشد"));
        accountRelatedDescription.setDeletedDate(LocalDateTime.now());
        accountRelatedDescriptionRepository.save(accountRelatedDescription);
        return true;
    }
}
