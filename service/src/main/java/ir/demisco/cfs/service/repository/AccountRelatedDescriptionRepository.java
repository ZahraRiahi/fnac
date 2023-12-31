package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.AccountRelatedDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRelatedDescriptionRepository extends JpaRepository<AccountRelatedDescription, Long> {
    @Query(value = "select fnad.id,fnad.description,acrd.id " +
            " from  AccountRelatedDescription acrd join acrd.financialAccountDescription fnad " +
            " where acrd.financialAccount.id=:financialAccountId" +
            " and acrd.deletedDate is null and fnad.deletedDate is null ")
    List<Object[]> findByAccountRelatedDescriptionListObject(Long financialAccountId);

    @Query(value = " select 1 from  AccountRelatedDescription ard join ard.financialAccountDescription fad " +
            " join ard.financialAccount fa " +
            " where fad.id=:financialAccountDesId and  fa.id=:financialAccountId ")
    Long getAccountRelatedDescriptionByFinancialAccountDescriptionId(Long financialAccountDesId, Long financialAccountId);

    AccountRelatedDescription findByFinancialAccountDescriptionId(Long financialAccountDescriptionId);

    AccountRelatedDescription findByFinancialAccountIdAndFinancialAccountDescriptionIdAndDeletedDateIsNull(Long financialAccountId, Long financialAccountDescriptionId);

    List<AccountRelatedDescription> findByFinancialAccountId(Long financialAccountId);

}
