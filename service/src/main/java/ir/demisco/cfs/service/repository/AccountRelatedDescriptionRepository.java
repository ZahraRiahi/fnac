package ir.demisco.cfs.service.repository;


import ir.demisco.cfs.model.entity.AccountRelatedDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRelatedDescriptionRepository extends JpaRepository<AccountRelatedDescription, Long> {
    @Query(value = "select fnad.id,fnad.description " +
            " from  AccountRelatedDescription acrd join acrd.financialAccountDescription fnad " +
            " where acrd.financialAccount.id=:financialAccountId" +
            " and acrd.deletedDate is null and fnad.deletedDate is null ")
    List<Object[]> findByAccountRelatedDescriptionListObject(Long financialAccountId);
}