package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.FinancialAccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FinancialAccountTypeRepository extends JpaRepository<FinancialAccountType, Long> {

    @Query(value = "select fnat.id ,fnat.description,case when acrt.id is null then 0 else 1 end as flg_exists " +
            " from  FinancialAccountType fnat left outer join AccountRelatedType acrt on fnat.id=acrt.financialAccountType.id and acrt.deletedDate is null " +
            " where fnat.deletedDate is null ")
    List<Object[]> findByFinancialAccountTypeListObject(Long financialAccountId);

}

