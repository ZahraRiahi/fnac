package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.MoneyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MoneyTypeRepository extends JpaRepository<MoneyType, Long> {
    @Query(value = "select mnty.id,mnty.description, case when acmt.id is null then 0 else 1 end as flg_exists" +
            " from  MoneyType mnty left outer join AccountMoneyType acmt on mnty.id=acmt.moneyType.id and acmt.deletedDate is null  " +
            " where acmt.financialAccount.id=:financialAccountId" +
            " and mnty.deletedDate is null ")
    List<Object[]> findByMonetTypeListObject(Long financialAccountId);


}
