package ir.demisco.cfs.service.repository;


import ir.demisco.cfs.model.entity.AccountRelatedDescription;
import ir.demisco.cfs.model.entity.CentricPersonRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AccountRelatedDescriptionRepository extends JpaRepository<AccountRelatedDescription, Long> {
    @Query(value = "select fnad.id,fnad.description " +
            " from  AccountRelatedDescription acrd join acrd.financialAccountDescription fnad " +
            " where acrd.financialAccount.id=:financialAccountId" +
            " and acrd.deletedDate is null and fnad.deletedDate is null ")
    List<Object[]> findByAccountRelatedDescriptionListObject(Long financialAccountId);


    @Query(value = "select 1" +
            " from fnac.account_related_description " +
            " where financial_account_des_id = :financialAccountDesId "
            , nativeQuery = true)
    Long getAccountRelatedDescriptionByFinancialAccountDescriptionId(Long financialAccountDesId);

    AccountRelatedDescription findByFinancialAccountDescriptionId(Long financialAccountDescriptionId);

}
