package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.AccountDefaultValue;
import ir.demisco.cfs.model.entity.AccountRelatedDescription;
import ir.demisco.cfs.model.entity.CentricPersonRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountDefaultValueRepository extends JpaRepository<AccountDefaultValue, Long> {
    @Query("select count(adv.id) from AccountDefaultValue adv join adv.financialAccount fa " +
            " where fa.id=:financialAccountId  and adv.accountRelationTypeDetail.id=:accountRelationTypeDetailId " +
            " and (:centricAccount is null or " +
            " adv.centricAccount.id = :centricAccountId)" +
            " and adv.deletedDate is null")
    Long findByAccountDefaultAndFinancialAccountAndAccountRelationTypeDetailId(Long accountRelationTypeDetailId, Long financialAccountId, Long centricAccountId, Object centricAccount);

    @Query("select count(adv.id)  from AccountDefaultValue adv where adv.financialAccount.id=:financialAccountId and adv.accountRelationTypeDetail.id in (:accountRelationTypeDetailIdList) and adv.deletedDate is null ")
    Long findByFinancialAccountIdAndAccountRelationTypeDetailIdList(Long financialAccountId, List<Long> accountRelationTypeDetailIdList);

    AccountDefaultValue findByIdAndAccountRelationTypeDetailId(Long id, Long accountRelationTypeDetailId);

    List<AccountDefaultValue> findByFinancialAccountIdAndDeletedDateIsNull(Long financialAccountId);

}
