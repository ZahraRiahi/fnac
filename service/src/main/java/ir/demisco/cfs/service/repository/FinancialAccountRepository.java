package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.FinancialAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Pageable;

import java.util.List;


public interface FinancialAccountRepository extends JpaRepository<FinancialAccount, Long> {
    @Query(value = " SELECT FIAC.ID," +
            "       FIAC.CODE," +
            "       FIAC.DESCRIPTION," +
            "       FIAC.REFERENCE_FLAG," +
            "       FIAC.EXCHANGE_FLAG," +
            "       FIAC.ACCOUNT_RELATION_TYPE_ID," +
            "       FIAC.DISABLE_DATE," +
            "       CASE " +
            "         WHEN FIAC.DISABLE_DATE IS NOT NULL THEN" +
            "          0 " +
            "         ELSE " +
            "          1 " +
            "       END ACTIVE_FLAG " +
            "  FROM FNAC.FINANCIAL_ACCOUNT FIAC " +
            " INNER JOIN FNAC.FINANCIAL_ACCOUNT_STRUCTURE FS " +
            "    ON FIAC.FINANCIAL_ACCOUNT_STRUCTURE_ID = FS.ID " +
            " WHERE ORGANIZATION_ID = :organizationId " +
            "   AND FIAC.DELETED_DATE IS NULL " +
            "   AND FIAC.DISABLE_DATE IS NULL " +
            "   AND CASE " +
            "         WHEN (EXISTS " +
            "               (SELECT 1 " +
            "                  FROM FNAC.FINANCIAL_ACCOUNT FIAC_INNER " +
            "                 WHERE FIAC_INNER.FINANCIAL_ACCOUNT_PARENT_ID = FIAC.ID " +
            "                   AND FIAC_INNER.DELETED_DATE IS NULL " +
            "                   AND FIAC_INNER.ORGANIZATION_ID = :organizationId)) THEN " +
            "          1 " +
            "         ELSE " +
            "          0 " +
            "       END = 0 " +
            "   AND FS.SHOW_IN_ACC_FLAG = 1 "
            , nativeQuery = true)
    List<Object[]> findByFinancialAccountByOrganizationId(Long organizationId);


    @Query("select fa from  FinancialAccount fa where fa.financialAccountStructure.id=:financialAccountStructureId and fa.deletedDate is null")
    List<FinancialAccount> findByFinancialAccountStructureId(Long financialAccountStructureId);

    @Query(value = " select fiac.id," +
            "       fiac.organization_id," +
            "       fiac.code," +
            "       fiac.description," +
            "       fiac.account_nature_type_id," +
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
            " fiac.financial_account_structure_id, " +
            " fiac.account_status_id, " +
            " fsts.code as account_status_code, " +
            " fsts.description as account_status_description " +
            "  from fnac.financial_account fiac" +
            "  left outer join fnac.account_nature_type acnt" +
            "    on fiac.account_nature_type_id = acnt.id" +
            "   and acnt.deleted_date is null" +
            "  left outer join fnac.account_relation_type acrt" +
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
            "  left outer join fnac.account_status fsts " +
            "    on fsts.id = fiac.account_status_id " +
            "   and fsts.deleted_date is null " +
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

    @Query(value = "   select 1 " +
            "  from fnac.financial_account fiac " +
            " inner join fnac.financial_account_structure fs " +
            "    on fiac.financial_account_structure_id = fs.id " +
            "   and fs.sequence = (select max(fs_inner.sequence) " +
            "                        from fnac.financial_account_structure fs_inner " +
            "                       where fs_inner.financial_coding_type_id = " +
            "                             fs.financial_coding_type_id) " +
            " where " +
            "    fiac.deleted_date is null " +
            "   and fiac.disable_date is null   " +
            "   and fiac.id = :financialAccountId " +
            " and fiac.organization_id= :organizationId "
            , nativeQuery = true)
    Long findByFinancialAccountIdAndStatusFlag(Long financialAccountId, Long organizationId);

    @Query("select 1 from  FinancialAccount fa where fa.disableDate is not null and fa.id=:financialAccountId")
    Long findByFinancialAccountAndIdAndDisableDateIsNotNull(Long financialAccountId);

    @Query(value = " SELECT FNAS.ID, " +
            "       FNAS.DIGIT_COUNT, " +
            "       CASE " +
            "         WHEN :financialAccountParent IS NULL THEN " +
            "          NULL" +
            "         ELSE " +
            "          (SELECT FA_INNER.CODE " +
            "             FROM FNAC.FINANCIAL_ACCOUNT FA_INNER " +
            "            WHERE FA_INNER.ID =" +
            "                  :financialAccountParentId)" +
            "       END PRE_CODE," +
            "       CASE " +
            "         WHEN :financialAccountParent IS NULL THEN" +
            "          LPAD('1', FNAS.DIGIT_COUNT, '0')" +
            "         ELSE " +
            "          LPAD(NVL((SELECT MAX(TO_NUMBER(SUBSTR(FA_INNER.CODE," +
            "                                               LENGTH(FA_INNER.CODE) -FNAS.DIGIT_COUNT +1," +
            "                                               FNAS.DIGIT_COUNT))) + 1" +
            "                     FROM FNAC.FINANCIAL_ACCOUNT FA_INNER" +
            "                    WHERE FA_INNER.FINANCIAL_ACCOUNT_PARENT_ID = " +
            "                          :financialAccountParentId)," +
            "                   1)," +
            "               FNAS.DIGIT_COUNT," +
            "               '0')" +
            "       END SUGGESTED_CODE" +
            "  FROM FNAC.FINANCIAL_ACCOUNT_STRUCTURE FNAS" +
            " WHERE FNAS.FINANCIAL_CODING_TYPE_ID = :financialCodingTypeId " +
            "   AND FNAS.DELETED_DATE IS NULL" +
            "   AND FNAS.SEQUENCE =" +
            "       (SELECT MIN(FNAS_INNER1.SEQUENCE)" +
            "          FROM FNAC.FINANCIAL_ACCOUNT_STRUCTURE FNAS_INNER1" +
            "         WHERE FNAS_INNER1.FINANCIAL_CODING_TYPE_ID = :financialCodingTypeId " +
            "           AND FNAS_INNER1.DELETED_DATE IS NULL" +
            "           AND (:financialAccountStructure IS NULL OR" +
            "               (FNAS_INNER1.SEQUENCE >" +
            "               (SELECT FNAS_INNER2.SEQUENCE" +
            "                    FROM FNAC.FINANCIAL_ACCOUNT_STRUCTURE FNAS_INNER2" +
            "                   WHERE FNAS_INNER2.ID = :financialAccountStructureId" +
            "                     AND FNAS_INNER2.DELETED_DATE IS NULL)))) "
            , nativeQuery = true)
    List<Object[]> findByFinancialAccountByFinancialAccountParentAndCodingAndStructure(Object financialAccountParent, Long financialAccountParentId, Long financialCodingTypeId, Object financialAccountStructure, Long financialAccountStructureId);


}
