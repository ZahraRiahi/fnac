package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.FinancialAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Pageable;

import java.util.List;


public interface FinancialAccountRepository extends JpaRepository<FinancialAccount, Long> {
//    @Query(value = " select fa from  FinancialAccount fa join fa.financialAccountStructure fs where fs.sequence = (select max(fs_inner.sequence) from  FinancialAccountStructure  fs_inner " +
//            " where fs_inner.financialCodingType.id=fs.financialCodingType.id) " +
//            " and fa.organization.id=:organizationId and fa.deletedDate is null")
//    List<FinancialAccount> findByFinancialAccountByOrganizationId(Long organizationId);

    @Query(value = " select fiac.id, " +
            "       fiac.code," +
            "       fiac.description," +
            "       fiac.reference_flag, " +
            "       fiac.exchange_flag," +
            "       fiac.account_relation_type_id," +
            "       fiac.disable_date, " +
            "       case " +
            "         when fiac.disable_date is not null then 0 else 1 " +
            "       end active_flag" +
            "  from fnac.financial_account fiac" +
            " inner join fnac.financial_account_structure fs" +
            "    on fiac.financial_account_structure_id = fs.id" +
            "   and fs.sequence = (select max(fs_inner.sequence)" +
            "                        from fnac.financial_account_structure fs_inner " +
            "                       where fs_inner.financial_coding_type_id = " +
            "                             fs.financial_coding_type_id) " +
            " where fiac.organization_id = :organizationId " +
            "   and fiac.deleted_date is null " +
            "   and fiac.disable_date is null "
            , nativeQuery = true)
    List<Object[]> findByFinancialAccountByOrganizationId(Long organizationId);


    @Query("select fa from  FinancialAccount fa where fa.financialAccountStructure.id=:financialAccountStructureId and fa.deletedDate is null")
    List<FinancialAccount> findByFinancialAccountStructureId(Long financialAccountStructureId);

    @Query(value = "select fiac.id," +
            "       fiac.organization_id," +
            "       fiac.code," +
            "       fiac.description," +
            "       fiac.account_nature_type_id," +
            "       fiac.permanent_flag," +
            "       fiac.account_relation_type_id," +
            "       fiac.financial_account_parent_id," +
            "       acnt.description                 as account_nature_type_Description," +
            "       acrt.description                 as account_relation_type_Description," +
            "       case " +
            "         when fiac.disable_date is not null then " +
            "          0 " +
            "         else " +
            "          1 " +
            "       end active_flag, " +
            "      case when  (exists(select 1 from fnac.financial_account fiac_inner where" +
            "        fiac_inner.financial_account_parent_id=fiac.id" +
            "      and fiac_inner.deleted_date is null" +
            "    and fiac_inner.organization_id = :organizationId" +
            ")) then 1 else 0 end haschild," +
            " fiac.financial_account_structure_id " +
            "  from fnac.financial_account fiac" +
            " inner join fnac.account_nature_type acnt" +
            "    on fiac.account_nature_type_id = acnt.id" +
            "   and acnt.deleted_date is null" +
            " inner join fnac.account_relation_type acrt" +
            "    on fiac.account_relation_type_id = acrt.id" +
            "   and acrt.deleted_date is null" +
            " inner join fnac.financial_account_structure fnas" +
            "    on fnas.id = fiac.financial_account_structure_id" +
            "   and fnas.deleted_date is null" +
            "  left outer join fnac.financial_account fiac_parent" +
            "    on fiac.financial_account_parent_id = fiac_parent.id" +
            "   and fiac_parent.deleted_date is null" +
            "  left outer join fnac.financial_account fiac_adjustment" +
            "    on fiac_adjustment.id = fiac.account_adjustment_id" +
            "   and fiac_adjustment.deleted_date is null" +
            " where fiac.organization_id = :organizationId" +
            "   and fnas.financial_coding_type_id = :financialCodingTypeId" +
            "   and (:description is null or fiac.description like %:description% )" +
            "   and fiac.deleted_date is null" +
            "   and ((:financialAccountParent is null and  fiac.financial_account_parent_id is null) " +
            "   or (:financialAccountParent is not null  and " +
            "       fiac.financial_account_parent_id =:financialAccountParentId))" +
            "   and (:accountNatureType is null or " +
            "       fiac.account_nature_type_id = :accountNatureTypeId)" +
            "   and (:financialAccountStructure is null or " +
            " fiac.financial_account_structure_id = :financialAccountStructureId)" +
            "  and (:accountRelationType is null or " +
            "fiac.account_relation_type_id = :accountRelationTypeId)"
            , nativeQuery = true)
    Page<Object[]> financialAccountList(Long organizationId, Long financialCodingTypeId, String description, Object financialAccountParent, Long financialAccountParentId, Object accountNatureType,
                                        Long accountNatureTypeId, Object financialAccountStructure, Long financialAccountStructureId,
                                        Object accountRelationType, Long accountRelationTypeId
            , Pageable pageable);

    @Query(value = " select fiac from  FinancialAccount fiac " +
            " where fiac.organization.id=:organizationId and fiac.deletedDate is null")
    List<FinancialAccount> findByFinancialAccountAdjustmentByOrganizationId(Long organizationId);


    @Query("select coalesce(COUNT(fa.id),0) from FinancialAccount fa  where fa.code=:code")
    Long getCountByFinancialAccountAndCode(String code);

    @Query("select coalesce(COUNT(fa.id),0) from FinancialAccount fa  where fa.code=:code and fa.id not in(:financialAccountId)")
    Long getCountByFinancialAccountAndCode(String code, Long financialAccountId);


    @Query(value = " select 1 from  FinancialAccount fa join fa.financialAccountStructure fs where fs.sequence = (select max(fs_inner.sequence) from  FinancialAccountStructure  fs_inner " +
            " where fs_inner.financialCodingType.id=fs.financialCodingType.id) " +
            " and fa.id=:financialAccountId and fa.deletedDate is null")
    Long findByFinancialAccountId(Long financialAccountId);


    @Query(value = " select 1 from  FinancialAccount fa join fa.accountRelationType art " +
            " where  fa.accountRelationType.id=:accountRelationTypeId" +
            " and fa.id=:financialAccountId ")
    Long findByFinancialAccountByAccountRelationTypeId(Long accountRelationTypeId, Long financialAccountId);


//    @Query("select count(adv.id) from FinancialAccount fa join adv.financialAccount fa " +
//            " where fa.id=:financialAccountId  and adv.accountRelationTypeDetail.id=:accountRelationTypeDetailId " +
//            " and (:centricAccount is null or " +
//            " adv.centricAccount.id = :centricAccountId)" +
//            " and adv.deletedDate is null")
//    Long findByAccountDefaultAndfinancialAccountAndAccountRelationTypeDetailId(Long accountRelationTypeDetailId, Long financialAccountId, Long centricAccountId, Object centricAccount);
}
