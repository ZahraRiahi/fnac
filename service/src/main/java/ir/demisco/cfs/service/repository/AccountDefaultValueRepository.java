package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.AccountDefaultValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountDefaultValueRepository extends JpaRepository<AccountDefaultValue, Long> {
    @Query("select count(adv.id) from AccountDefaultValue adv join adv.financialAccount fa " +
            "where fa.id=:financialAccountId  and adv.accountRelationTypeDetail.id=:accountRelationTypeDetailId  and adv.deletedDate is null")
    Long findByAccountDefaultAndfinancialAccountAndAccountRelationTypeDetailId(Long accountRelationTypeDetailId, Long financialAccountId);
}
