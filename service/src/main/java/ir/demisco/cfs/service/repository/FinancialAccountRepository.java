package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.FinancialAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinancialAccountRepository extends JpaRepository<FinancialAccount, Long> {
    @Query(value = " SELECT fac.id," +
            "       fac.code," +
            "       fac.description," +
            "       fac.referenceFlag," +
            "       fac.exchangeFlag," +
            "       art.id as accountRelationTypeId, " +
            "       fac.disableDate " +
            "  FROM FinancialAccount fac " +
            " JOIN fac.financialAccountStructure fs " +
            " left outer join fac.accountRelationType art " +
            " WHERE fac.organization.id = :organizationId " +
            "   AND fac.deletedDate IS NULL " +
            "   AND fac.disableDate IS NULL " +
            "   AND CASE " +
            "         WHEN (EXISTS " +
            "               (SELECT 1 " +
            "                  FROM FinancialAccount fai " +
            "                 WHERE fai.financialAccountParent.id = fac.id " +
            "                   AND fai.deletedDate IS NULL " +
            "                   AND fai.organization.id = :organizationId)) THEN " +
            "          1 " +
            "         ELSE " +
            "          0 " +
            "       END = 0 " +
            "   AND fs.flgShowInAcc = 1 ")
    Page<Object[]> findByFinancialAccountByOrganizationId(Long organizationId, Pageable pageable);


    @Query("select fa from  FinancialAccount fa where fa.financialAccountStructure.id=:financialAccountStructureId and fa.deletedDate is null")
    List<FinancialAccount> findByFinancialAccountStructureId(Long financialAccountStructureId);

    @Query(value = " select fiac.id," +
            "       fiac.organization_id," +
            "       fiac.code," +
            "       fiac.description," +
            "       fiac.account_nature_type_id," +
            "       fiac.financial_account_structure_id," +
            "       fiac.account_relation_type_id," +
            "       fiac.financial_account_parent_id," +
            "       case" +
            "         when fiac.disable_date is not null then" +
            "          0" +
            "         else" +
            "          1" +
            "       end active_flag," +
            "       acnt.description as account_nature_type_Description," +
            "       acrt.description as account_relation_type_Description," +
            "       case" +
            "         when (exists" +
            "               (select 1" +
            "                  from fnac.financial_account fiac_inner" +
            "                 where fiac_inner.financial_account_parent_id = fiac.id" +
            "                   and fiac_inner.deleted_date is null" +
            "                   and fiac_inner.organization_id = :organizationId)) then" +
            "          1" +
            "         else" +
            "          0" +
            "       end haschild," +
            "       fiac.account_permanent_status_id," +
            "       fsts.code as account_status_code," +
            "       fsts.description as account_status_description," +
            "       fnas.flg_show_in_acc," +
            "       fnas.flg_permanent_status, " +
            " fnas.color " +
            "  from fnac.financial_account fiac" +
            "  inner join fnac.account_nature_type acnt " +
            "    on fiac.account_nature_type_id = acnt.id " +
            "   and acnt.deleted_date is null " +
            "   and (:accountNatureType is null or " +
            "      fiac.account_nature_type_id = :accountNatureTypeId)" +
            " LEFT OUTER join fnac.account_relation_type acrt " +
            "    on fiac.account_relation_type_id = acrt.id " +
            "   and acrt.deleted_date is null " +
            " inner join fnac.financial_account_structure fnas " +
            "    on fnas.id = fiac.financial_account_structure_id " +
            "   and fnas.deleted_date is null " +
            "  left outer join fnac.financial_account fiac_parent " +
            "    on fiac.financial_account_parent_id = fiac_parent.id " +
            "   and fiac_parent.deleted_date is null " +
            "  left outer join fnac.financial_account fiac_adjustment " +
            "    on fiac_adjustment.id = fiac.account_adjustment_id " +
            "   and fiac_adjustment.deleted_date is null " +
            "  left outer join fnac.account_permanent_status fsts " +
            "    on fsts.id = fiac.account_permanent_status_id " +
            "   and fsts.deleted_date is null " +
            " where fiac.organization_id = :organizationId" +
            "   and fnas.financial_coding_type_id = :financialCodingTypeId" +
            "   and (:description is null or fiac.description like %:description% )" +
            "   and fiac.deleted_date is null " +
            "  and (:accountRelationType is null or " +
            "  fiac.account_relation_type_id = :accountRelationTypeId) " +
            "   and ((:financialAccountParent is null and  fiac.financial_account_parent_id is null) " +
            "   or (:financialAccountParent is not null  and " +
            "       fiac.financial_account_parent_id =:financialAccountParentId))" +
            "   and (:financialAccountStructure is null or " +
            " fiac.financial_account_structure_id = :financialAccountStructureId)"
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
            "    from fnac.financial_account fiac " +
            "   where fiac.financial_account_parent_id = :financialAccountId " +
            "     and fiac.organization_id = :organizationId " +
            "     and fiac.deleted_date is null " +
            "     and fiac.disable_date is null  "
            , nativeQuery = true)
    List<Long> findByFinancialAccountId(Long financialAccountId, Long organizationId);

    @Query(value = "   select 1 " +
            "    from fnac.financial_account fiac " +
            "    where fiac.id = :financialAccountId " +
            "    and fiac.organization_id = :organizationId " +
            "    and fiac.deleted_date is null " +
            "    and fiac.disable_date is not null  "
            , nativeQuery = true)
    Long findByFinancialAccountIdAndOrganization(Long financialAccountId, Long organizationId);


    @Query(value = "   select 1 " +
            "  from fnac.financial_account fiac " +
            "  inner join  fnac.financial_account fiac_parent " +
            "   on fiac.id = :financialAccountId and fiac.financial_account_parent_id = fiac_parent.id " +
            "    and fiac.organization_id = :organizationId " +
            "       and fiac_parent.organization_id = :organizationId " +
            "   and (fiac_parent.deleted_date is not null " +
            "   or fiac_parent.disable_date is not null)    "
            , nativeQuery = true)
    Long findByFinancialAccountOrganization(Long financialAccountId, Long organizationId);

    @Query("select 1 from  FinancialAccount fa where fa.disableDate is not null and fa.id=:financialAccountId")
    Long findByFinancialAccountAndIdAndDisableDateIsNotNull(Long financialAccountId);

    @Query(value = " SELECT FNAS.ID, " +
            "       FNAS.DIGIT_COUNT," +
            "  FNAS.FLG_SHOW_IN_ACC, " +
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

    @Query(value = " SELECT FS.ID, T.ACCOUNT_PERMANENT_STATUS_ID, FS.FLG_PERMANENT_STATUS,acs.description " +
            "      FROM fnac.FINANCIAL_ACCOUNT T " +
            "     INNER JOIN fnac.FINANCIAL_ACCOUNT_STRUCTURE FS " +
            "        ON FS.ID = T.FINANCIAL_ACCOUNT_STRUCTURE_ID " +
            "    INNER JOIN fnac.account_permanent_status acs " +
            "      on acs.id = t.account_permanent_status_id " +
            "     WHERE T.DELETED_DATE IS NULL " +
            "     START WITH T.ID = :financialAccountParentId " +
            "    CONNECT BY PRIOR T.FINANCIAL_ACCOUNT_PARENT_ID = T.ID " +
            "     ORDER BY T.ID ASC  "
            , nativeQuery = true)
    List<Object[]> findByFinancialAccountByParentId(Long financialAccountParentId);

    @Query(value = " SELECT T.ID," +
            "         T.FINANCIAL_ACCOUNT_STRUCTURE_ID," +
            "         T.CODE," +
            "         T.FINANCIAL_ACCOUNT_PARENT_ID," +
            "         AST.DIGIT_COUNT," +
            "         AST.SEQUENCE" +
            "    FROM fnac.FINANCIAL_ACCOUNT T" +
            "   INNER JOIN fnac.FINANCIAL_ACCOUNT_STRUCTURE AST" +
            "      ON T.FINANCIAL_ACCOUNT_STRUCTURE_ID = AST.ID" +
            "   WHERE T.DELETED_DATE IS NULL" +
            "   AND T.ID = :financialAccountParentId"
            , nativeQuery = true)
    List<Object[]> findByFinancialAccountAndFinancialAccountParent(Long financialAccountParentId);


    @Query(value = "   SELECT 1" +
            "  FROM FNAC.FINANCIAL_ACCOUNT FIAC" +
            " INNER JOIN FNAC.FINANCIAL_ACCOUNT_STRUCTURE FNAS" +
            "    ON FIAC.FINANCIAL_ACCOUNT_STRUCTURE_ID = FNAS.ID" +
            " WHERE FIAC.ID = :financialAccountStructureId " +
            "   AND FNAS.FLG_SHOW_IN_ACC = 1" +
            "   AND EXISTS (SELECT 1" +
            "          FROM FNDC.FINANCIAL_DOCUMENT_ITEM FNDI" +
            "         WHERE FNDI.FINANCIAL_ACCOUNT_ID = FNAS.id) "
            , nativeQuery = true)
    Long findByFinancialAccountIdAndStuctureAndAccountId(Long financialAccountStructureId);


    @Query(value = " SELECT FIAC.ID," +
            "       FIAC.CODE," +
            "       FIAC.DESCRIPTION," +
            "       FIAC.REFERENCE_FLAG," +
            "       FIAC.EXCHANGE_FLAG," +
            "       FIAC.ACCOUNT_RELATION_TYPE_ID," +
            "       FIAC.DISABLE_DATE " +
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
            "   AND FS.FLG_SHOW_IN_ACC = 1 "
            , nativeQuery = true)
    Page<Object[]> financialAccountLovList(Long organizationId, Pageable pageable);


    @Query(value = " select 1 from  FinancialAccount fa join fa.financialAccountStructure fs where fa.financialAccountStructure.id=:financialAccountStructureId and  fs.flgShowInAcc = 1 and fa.disableDate is null and fs.deletedDate is null " +
            " and exists (select 1 from FinancialDocumentItem fndi where fndi.financialAccount.id=fa.id and fndi.deletedDate is null )")
    List<Long> findByFinancialAccountIdAndStructureAndCodingType(Long financialAccountStructureId);

    @Query(value = " SELECT fiac.id," +
            "       fiac.code," +
            "       fiac.description, " +
            "       fiac.referenceFlag, " +
            "       fiac.exchangeFlag, " +
            "       art.id as accountRelationTypeId, " +
            "       fiac.disableDate " +
            "  FROM FinancialAccount fiac " +
            " JOIN fiac.financialAccountStructure fs " +
            " left outer join fiac.accountRelationType art " +
            " WHERE fiac.deletedDate IS NULL " +
            "   AND fiac.disableDate IS NULL " +
            " and fs.deletedDate is null " +
            "  AND fiac.organization.id=:organizationId " +
            "   AND (:financialAccountStructure is null or  fiac.financialAccountStructure.id=:financialAccountStructureId )")
    List<Object[]> findByFinancialAccountByOrganAndFinancialAccountStructureId(Long organizationId, Object financialAccountStructure, Long financialAccountStructureId);
}


