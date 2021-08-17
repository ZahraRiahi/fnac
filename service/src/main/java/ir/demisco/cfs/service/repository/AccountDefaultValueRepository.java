package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.AccountDefaultValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountDefaultValueRepository extends JpaRepository<AccountDefaultValue, Long> {
    @Query("select count(adv.id) from AccountDefaultValue adv join adv.financialAccount fa " +
            "where fa.id=:financialAccountId  and adv.accountRelationTypeDetail.id=:accountRelationTypeDetailId  and adv.deletedDate is null")
    Long findByAccountDefaultAndfinancialAccountAndAccountRelationTypeDetailId(Long accountRelationTypeDetailId, Long financialAccountId);

    @Query("select count(adv.id)  from AccountDefaultValue adv where adv.financialAccount.id=:financialAccountId and adv.accountRelationTypeDetail.id in (:accountRelationTypeDetailIdList) ")
    Long findByFinancialAccountIdAndAccountRelationTypeDetailIdList(Long financialAccountId,List<Long> accountRelationTypeDetailIdList);


    AccountDefaultValue findByIdAndAccountRelationTypeDetailId(Long id,Long accountRelationTypeDetailId);


    @Query("select coalesce(COUNT(acd.id),0) from AccountDefaultValue acd join acd.financialAccount fa  join acd.accountRelationTypeDetail artd where fa.id=:financialAccountId and artd.id=:accountRelationTypeDetailId  and acd.deletedDate is null")
    Long getCountByAccountDefaultValuefinancialAccountAndAccountRelationTypeDetail(Long financialAccountId, Long accountRelationTypeDetailId);
}
