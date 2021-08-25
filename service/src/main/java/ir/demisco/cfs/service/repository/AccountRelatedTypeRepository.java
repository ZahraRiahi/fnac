package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.AccountRelatedType;
import ir.demisco.cfs.model.entity.AccountStructureLevel;
import ir.demisco.cfs.model.entity.FinancialAccountStructure;
import ir.demisco.cfs.model.entity.FinancialAccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRelatedTypeRepository extends JpaRepository<AccountRelatedType, Long> {

//    @Query(value = "select  fnat.description,fnat.code,case when acrt.id is null then 0 else 1 end as flg_exists from  AccountRelatedType acrt left outer join acrt.financialAccountType fnat" +
//            " where acrt.deletedDate is null and  fnat.deletedDate is null")
//    List<Object[]> findByFinancialAccountTypeListObject();
//}

    List<AccountRelatedType> findByFinancialAccountId(Long financialAccountId);
}
