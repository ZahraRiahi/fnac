package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.FinancialAccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FinancialAccountTypeRepository extends JpaRepository<FinancialAccountType, Long> {

    //    @Query(value = "select fnat.id ,fnat.description,case when acrt.id is null then 0 else 1 end as flg_exists " +
//            " from  FinancialAccountType fnat left outer join AccountRelatedType acrt on fnat.id=acrt.financialAccountType.id and acrt.deletedDate is null " +
//            " where fnat.deletedDate is null ")
//    List<Object[]> findByFinancialAccountTypeListObject(Long financialAccountId);
//    List<Object[]> findByFinancialAccountTypeListObject(Long financialAccountId);

    @Query(value = "select fnat.code," +
            " fnat.description," +
            " case" +
            " when :financialAccountId is null or not exists " +
            " (select 1 " +
            " from fnac.account_related_type acrt " +
            " where (:financialAccount is null or " +
            " acrt.financial_account_id = :financialAccountId)" +
            " and acrt.financial_account_type_id = fnat.id" +
            " and acrt.deleted_date is null) then " +
            " 0 " +
            " else " +
            " 1 " +
            " end flg_exists " +
            " from fnac.financial_account_type fnat " +
            " where fnat.deleted_date is null "
            , nativeQuery = true)
    List<Object[]> findByFinancialAccountAndFinancialAccountId(Object financialAccount, Long financialAccountId);


    @Query(value = "select fnat.id,fnat.code," +
            " fnat.description," +
            " case" +
            " when :financialAccountId is null or not exists " +
            " (select 1 " +
            " from fnac.account_related_type acrt " +
            " where acrt.financial_account_type_id = fnat.id " +
            " and acrt.financial_account_id = :financialAccountId " +
            " and acrt.deleted_date is null) then " +
            " 0 " +
            " else " +
            " 1 " +
            " end flg_exists " +
            " from fnac.financial_account_type fnat " +
            " where fnat.deleted_date is null "
            , nativeQuery = true)
    List<Object[]> findByFinancialAccount(Long financialAccountId);

}

