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

    @Query(value = "  SELECT 1 FROM fnac.FINANCIAL_ACCOUNT_STRUCTURE OUTER_FANS " +
            "    WHERE EXISTS (SELECT 1 " +
            "            FROM fnac.FINANCIAL_ACCOUNT_STRUCTURE INER_FANS " +
            "            WHERE INER_FANS.FINANCIAL_CODING_TYPE_ID = " +
            "            OUTER_FANS.FINANCIAL_CODING_TYPE_ID " +
            "            AND INER_FANS.DELETED_DATE IS NULL " +
            "            AND INER_FANS.SEQUENCE >   OUTER_FANS.SEQUENCE) " +
            "    AND OUTER_FANS.ID = :financialAccountStructureId " +
            "    AND OUTER_FANS.DELETED_DATE IS NULL "
            , nativeQuery = true)
    Long findByFinancialAccountStructureId(Long financialAccountStructureId);

    @Query(value = " SELECT FIAC.ID," +
            "       FIAC.ORGANIZATION_ID," +
            "       FIAC.CODE," +
            "       FIAC.DESCRIPTION," +
            "       FIAC.RELATED_TO_OTHERS_FLAG," +
            "       FIAC.REFERENCE_FLAG," +
            "       FIAC.CONVERT_FLAG," +
            "       FIAC.EXCHANGE_FLAG," +
            "       FIAC.ACCOUNT_NATURE_TYPE_ID," +
            "       FIAC.FINANCIAL_ACCOUNT_STRUCTURE_ID," +
            "       FIAC.ACCOUNT_RELATION_TYPE_ID," +
            "       FIAC.FINANCIAL_ACCOUNT_PARENT_ID," +
            "       CASE" +
            "         WHEN FIAC.DISABLE_DATE IS NOT NULL THEN" +
            "          0" +
            "         ELSE" +
            "          1" +
            "       END ACTIVE_FLAG," +
            "       ACNT.DESCRIPTION AS ACCOUNT_NATURE_TYPE_DESCRIPTION," +
            "       ACRT.DESCRIPTION AS ACCOUNT_RELATION_TYPE_DESCRIPTION," +
            "       CASE" +
            "         WHEN (EXISTS" +
            "               (SELECT 1" +
            "                  FROM FNAC.FINANCIAL_ACCOUNT FIAC" +
            "                 INNER JOIN fnac.FINANCIAL_ACCOUNT_STRUCTURE FNAS_INNER" +
            "                    ON FNAS_INNER.ID =" +
            "                       FIAC.FINANCIAL_ACCOUNT_STRUCTURE_ID " +
            "                 WHERE FIAC.FINANCIAL_ACCOUNT_PARENT_ID = FIAC.ID" +
            "                   AND EXISTS" +
            "                 (SELECT 1" +
            "                          FROM fnac.CODING_TYPE_ORG_REL INER_ORG_REL" +
            "                         WHERE INER_ORG_REL.ORGANIZATION_ID = :organizationId" +
            "                           AND INER_ORG_REL.FINANCIAL_CODING_TYPE_ID =" +
            "                               FNAS_INNER.FINANCIAL_CODING_TYPE_ID" +
            "                           AND INER_ORG_REL.ACTIVE_FLAG = 1)" +
            "                )" +
            "              ) THEN" +
            "          1" +
            "         ELSE" +
            "          0" +
            "       END HASCHILD," +
            "       FIAC.ACCOUNT_PERMANENT_STATUS_ID," +
            "       FSTS.CODE AS ACCOUNT_STATUS_CODE," +
            "       FSTS.DESCRIPTION AS ACCOUNT_STATUS_DESCRIPTION," +
            "       FNAS.FLG_SHOW_IN_ACC," +
            "       FNAS.FLG_PERMANENT_STATUS," +
            "       FNAS.COLOR" +
            "  FROM FNAC.FINANCIAL_ACCOUNT FIAC" +
            " INNER JOIN FNAC.ACCOUNT_NATURE_TYPE ACNT" +
            "    ON FIAC.ACCOUNT_NATURE_TYPE_ID = ACNT.ID" +
            "   and (:accountNatureType is null or " +
            "      fiac.account_nature_type_id = :accountNatureTypeId)" +
            "   AND ACNT.DELETED_DATE IS NULL" +
            "  LEFT OUTER JOIN FNAC.ACCOUNT_RELATION_TYPE ACRT" +
            "    ON FIAC.ACCOUNT_RELATION_TYPE_ID = ACRT.ID" +
            "   AND ACRT.DELETED_DATE IS NULL" +
            " INNER JOIN FNAC.FINANCIAL_ACCOUNT_STRUCTURE FNAS" +
            "    ON FNAS.ID = FIAC.FINANCIAL_ACCOUNT_STRUCTURE_ID" +
            "   AND FNAS.DELETED_DATE IS NULL" +
            "  LEFT OUTER JOIN FNAC.FINANCIAL_ACCOUNT FIAC_PARENT" +
            "    ON FIAC.FINANCIAL_ACCOUNT_PARENT_ID = FIAC_PARENT.ID" +
            "   AND FIAC_PARENT.DELETED_DATE IS NULL" +
            "  LEFT OUTER JOIN FNAC.FINANCIAL_ACCOUNT FIAC_ADJUSTMENT" +
            "    ON FIAC_ADJUSTMENT.ID = FIAC.ACCOUNT_ADJUSTMENT_ID" +
            "   AND FIAC_ADJUSTMENT.DELETED_DATE IS NULL" +
            "  LEFT OUTER JOIN FNAC.ACCOUNT_PERMANENT_STATUS FSTS" +
            "    ON FSTS.ID = FIAC.ACCOUNT_PERMANENT_STATUS_ID" +
            "   AND FSTS.DELETED_DATE IS NULL" +
            " WHERE EXISTS" +
            " (SELECT 1" +
            "          FROM fnac.CODING_TYPE_ORG_REL INER_ORG_REL" +
            "         WHERE INER_ORG_REL.ORGANIZATION_ID = :organizationId" +
            "           AND INER_ORG_REL.FINANCIAL_CODING_TYPE_ID =" +
            "               FNAS.FINANCIAL_CODING_TYPE_ID" +
            "           AND INER_ORG_REL.ACTIVE_FLAG = 1)" +
            "  and (:accountRelationType is null or " +
            "  fiac.account_relation_type_id = :accountRelationTypeId) " +
            "   and (:financialAccountStructure is null or " +
            " fiac.financial_account_structure_id = :financialAccountStructureId)" +
            "   AND FNAS.FINANCIAL_CODING_TYPE_ID = :financialCodingTypeId " +
            "   and (:description is null or fiac.description like %:description% )" +
            "   AND FIAC.DELETED_DATE IS NULL" +
            "   and ((:financialAccountParent is null and  fiac.financial_account_parent_id is null) " +
            "   or (:financialAccountParent is not null  and " +
            "       fiac.financial_account_parent_id =:financialAccountParentId))"
            , nativeQuery = true)
    Page<Object[]> financialAccountList(Long organizationId, Long financialCodingTypeId, String description, Object financialAccountParent, Long financialAccountParentId, Object accountNatureType,
                                        Long accountNatureTypeId, Object financialAccountStructure, Long financialAccountStructureId,
                                        Object accountRelationType, Long accountRelationTypeId
            , Pageable pageable);

    @Query(value = " select fiac from  FinancialAccount fiac " +
            " where fiac.organization.id=:organizationId and fiac.deletedDate is null")
    List<FinancialAccount> findByFinancialAccountAdjustmentByOrganizationId(Long organizationId);


    @Query("select coalesce(COUNT(fa.id),0) from FinancialAccount fa  where fa.code=:code and fa.financialAccountStructure.id=:financialAccountStructureId and fa.organization.id=:organizationId ")
    Long getCountByFinancialAccountAndCode(String code, Long financialAccountStructureId, Long organizationId);

    @Query("select coalesce(COUNT(fa.id),0) from FinancialAccount fa  where fa.code=:code and fa.id not in(:financialAccountId)  ")
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
            "       FNAS.DIGIT_COUNT, " +
            "       FNAS.FLG_SHOW_IN_ACC, " +
            "       CASE " +
            "         WHEN :financialAccountParent IS NULL THEN " +
            "          NULL" +
            "         ELSE " +
            "          (SELECT FA_INNER.CODE " +
            "             FROM FNAC.FINANCIAL_ACCOUNT FA_INNER " +
            "            WHERE FA_INNER.ID = :financialAccountParentId) " +
            "       END PRE_CODE," +
            "       CASE " +
            "         WHEN :financialAccountParent IS NULL THEN " +
            "          RPAD(NVL((SELECT MAX(TO_NUMBER(SUBSTR(FA_INNER.CODE, " +
            "                                               LENGTH(FA_INNER.CODE)- " +
            "                                               FNAS.DIGIT_COUNT + 1, " +
            "                                               FNAS.DIGIT_COUNT))) +1 " +
            "                     FROM FNAC.FINANCIAL_ACCOUNT FA_INNER " +
            "                    INNER JOIN fnac.FINANCIAL_ACCOUNT_STRUCTURE FAS_INNER " +
            "                       ON FA_INNER.FINANCIAL_ACCOUNT_STRUCTURE_ID =" +
            "                          FAS_INNER.ID" +
            "                      AND FAS_INNER.DELETED_DATE IS NULL" +
            "                      AND FAS_INNER.FINANCIAL_CODING_TYPE_ID = :financialCodingTypeId" +
            "                    WHERE FA_INNER.FINANCIAL_ACCOUNT_PARENT_ID IS NULL)," +
            "                   1)," +
            "               FNAS.DIGIT_COUNT," +
            "               '0')" +
            "         ELSE        " +
            "          LPAD(NVL((SELECT MAX(TO_NUMBER(SUBSTR(FA_INNER.CODE, " +
            "                                               LENGTH(FA_INNER.CODE)- " +
            "                                               FNAS.DIGIT_COUNT + 1, " +
            "                                               FNAS.DIGIT_COUNT)))+ 1 " +
            "                     FROM FNAC.FINANCIAL_ACCOUNT FA_INNER " +
            "                    WHERE FA_INNER.FINANCIAL_ACCOUNT_PARENT_ID = " +
            "                          :financialAccountParentId), " +
            "                   1)," +
            "               FNAS.DIGIT_COUNT," +
            "               '0')" +
            "       END SUGGESTED_CODE" +
            "  FROM FNAC.FINANCIAL_ACCOUNT_STRUCTURE FNAS " +
            " WHERE FNAS.FINANCIAL_CODING_TYPE_ID = :financialCodingTypeId " +
            "   AND FNAS.DELETED_DATE IS NULL " +
            "   AND FNAS.SEQUENCE = " +
            "       (SELECT MIN(FNAS_INNER1.SEQUENCE) " +
            "          FROM FNAC.FINANCIAL_ACCOUNT_STRUCTURE FNAS_INNER1 " +
            "         WHERE FNAS_INNER1.FINANCIAL_CODING_TYPE_ID = :financialCodingTypeId " +
            "           AND FNAS_INNER1.DELETED_DATE IS NULL " +
            "           AND (:financialAccountStructure IS NULL OR " +
            "                          (FNAS_INNER1.SEQUENCE > " +
            "                        (SELECT FNAS_INNER2.SEQUENCE " +
            "                             FROM FNAC.FINANCIAL_ACCOUNT_STRUCTURE FNAS_INNER2 " +
            "                              WHERE FNAS_INNER2.ID = :financialAccountStructureId " +
            "                              AND FNAS_INNER2.DELETED_DATE IS NULL)))) "
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

    List<FinancialAccount> findByFinancialAccountParentId(Long financialAccountParentId);

    @Query(value = " SELECT 1 " +
            "     FROM fnac.FINANCIAL_ACCOUNT T " +
            "     WHERE T.FINANCIAL_ACCOUNT_STRUCTURE_ID = :financialAccountStructureId  "
            , nativeQuery = true)
    List<Long> getFinancialAccountByFinancialAccountStructureId(Long financialAccountStructureId);

    @Query(value = " SELECT fiac.id" +
            "  FROM FinancialAccount fiac " +
            " JOIN fiac.financialAccountStructure fs " +
            " WHERE  fiac.financialAccountStructure.id = :financialAccountStructureId ")
    List<Object[]> findByFinancialAccountStructureIdForDelete(Long financialAccountStructureId);


    @Query(value = "select count(t.id) from fnac.financial_account t" +
            " left join fnac.account_structure_level asl" +
            "  on asl.financial_account_id=t.id" +
            " left join bkac.bank_account ba" +
            "  on t.id=ba.financial_account_id" +
            " left join fndc.financial_document_item fdi" +
            "  on t.id=fdi.financial_account_id" +
            " left join fndc.financial_document_pattern fdp" +
            "  on fdp.financial_account_id= t.id" +
            " left join rprq.request_subject rs" +
            "  on t.id=rs.financial_account_id" +
            " left join fnac.financial_account fa1" +
            "  on fa1.id=t.financial_account_parent_id" +
            " where t.id=:financialAccountId" +
            " and( asl.financial_account_id=:financialAccountId" +
            " or ba.financial_account_id =:financialAccountId" +
            " or fdi.financial_account_id =:financialAccountId" +
            " or fdp.financial_account_id=:financialAccountId" +
            " or rs.financial_account_id=:financialAccountId" +
            " or t.financial_account_parent_id = :financialAccountId)", nativeQuery = true)
    Long findByFinancialAccountIdForDelete(Long financialAccountId);

    @Query(value = " SELECT FIAC.ID," +
            "       FIAC.CODE," +
            "       FIAC.DESCRIPTION," +
            "       FIAC.REFERENCE_FLAG," +
            "       FIAC.EXCHANGE_FLAG," +
            "       FIAC.ACCOUNT_RELATION_TYPE_ID," +
            "       FIAC.DISABLE_DATE" +
            "  FROM FNAC.FINANCIAL_ACCOUNT FIAC" +
            " INNER JOIN FNAC.FINANCIAL_ACCOUNT_STRUCTURE FS" +
            "    ON FIAC.FINANCIAL_ACCOUNT_STRUCTURE_ID = FS.ID" +
            " WHERE EXISTS (SELECT 1" +
            "          FROM fnac.CODING_TYPE_ORG_REL INER_ORG_REL" +
            "         WHERE INER_ORG_REL.ORGANIZATION_ID = :organizationId" +
            "           AND INER_ORG_REL.FINANCIAL_CODING_TYPE_ID =" +
            "               FS.FINANCIAL_CODING_TYPE_ID" +
            "           AND INER_ORG_REL.ACTIVE_FLAG = 1)" +
            "   AND FS.FINANCIAL_CODING_TYPE_ID = :financialCodingTypeId " +
            "   and (:descriptionObject is null or FIAC.DESCRIPTION like %:description% )" +
            "   and (:codeObject is null or FIAC.CODE like %:code% )" +
            "   AND FIAC.DISABLE_DATE IS NULL" +
            "   and (:financialAccountList is null or" +
            "       FIAC.ID in (:financialAccountIdList))" +
            "   AND CASE" +
            "         WHEN (EXISTS" +
            "               (SELECT 1" +
            "                  FROM FNAC.FINANCIAL_ACCOUNT FIAC_INNER1" +
            "                 INNER JOIN fnac.FINANCIAL_ACCOUNT_STRUCTURE FNAS_INNER" +
            "                    ON FNAS_INNER.ID =" +
            "                       FIAC_INNER1.FINANCIAL_ACCOUNT_STRUCTURE_ID" +
            "                 WHERE FIAC_INNER1.FINANCIAL_ACCOUNT_PARENT_ID = FIAC.ID" +
            "                   AND EXISTS" +
            "                 (SELECT 1" +
            "                          FROM fnac.CODING_TYPE_ORG_REL INER_ORG_REL" +
            "                         WHERE INER_ORG_REL.ORGANIZATION_ID = :organizationId" +
            "                           AND INER_ORG_REL.FINANCIAL_CODING_TYPE_ID =" +
            "                               FNAS_INNER.FINANCIAL_CODING_TYPE_ID" +
            "                           AND INER_ORG_REL.ACTIVE_FLAG = 1))) THEN" +
            "          1" +
            "         ELSE" +
            "          0" +
            "       END = 0" +
            "   AND FS.FLG_SHOW_IN_ACC = 1" +
            "   AND FIAC.DISABLE_DATE IS NULL "
            , countQuery = " select count(FIAC.id)   " +
            "  FROM FNAC.FINANCIAL_ACCOUNT FIAC" +
            " INNER JOIN FNAC.FINANCIAL_ACCOUNT_STRUCTURE FS" +
            "    ON FIAC.FINANCIAL_ACCOUNT_STRUCTURE_ID = FS.ID" +
            " WHERE EXISTS (SELECT 1" +
            "          FROM fnac.CODING_TYPE_ORG_REL INER_ORG_REL" +
            "         WHERE INER_ORG_REL.ORGANIZATION_ID = :organizationId" +
            "           AND INER_ORG_REL.FINANCIAL_CODING_TYPE_ID =" +
            "               FS.FINANCIAL_CODING_TYPE_ID" +
            "           AND INER_ORG_REL.ACTIVE_FLAG = 1)" +
            "   AND FS.FINANCIAL_CODING_TYPE_ID = :financialCodingTypeId " +
            "   and (:descriptionObject is null or FIAC.DESCRIPTION like %:description% )" +
            "   and (:codeObject is null or FIAC.CODE like %:code% )" +
            "   AND FIAC.DISABLE_DATE IS NULL" +
            "   and (:financialAccountList is null or" +
            "       FIAC.ID in (:financialAccountIdList))" +
            "   AND CASE" +
            "         WHEN (EXISTS" +
            "               (SELECT 1" +
            "                  FROM FNAC.FINANCIAL_ACCOUNT FIAC_INNER" +
            "                 INNER JOIN fnac.FINANCIAL_ACCOUNT_STRUCTURE FNAS_INNER" +
            "                    ON FNAS_INNER.ID =" +
            "                       FIAC_INNER.FINANCIAL_ACCOUNT_STRUCTURE_ID" +
            "                 WHERE FIAC_INNER.FINANCIAL_ACCOUNT_PARENT_ID = FIAC.ID" +
            "                   AND EXISTS" +
            "                 (SELECT 1" +
            "                          FROM fnac.CODING_TYPE_ORG_REL INER_ORG_REL" +
            "                         WHERE INER_ORG_REL.ORGANIZATION_ID = :organizationId" +
            "                           AND INER_ORG_REL.FINANCIAL_CODING_TYPE_ID =" +
            "                               FNAS_INNER.FINANCIAL_CODING_TYPE_ID" +
            "                           AND INER_ORG_REL.ACTIVE_FLAG = 1))) THEN" +
            "          1" +
            "         ELSE" +
            "          0" +
            "       END = 0" +
            "   AND FS.FLG_SHOW_IN_ACC = 1" +
            "   AND FIAC.DISABLE_DATE IS NULL "
            , nativeQuery = true)
    Page<Object[]> financialAccountLov(Long organizationId, Long financialCodingTypeId, Object descriptionObject, String description, Object codeObject, String code, Object financialAccountList, List<Long> financialAccountIdList
            , Pageable pageable);

    @Query(value = " SELECT FIAC.ID," +
            "       FIAC.CODE," +
            "       FIAC.DESCRIPTION," +
            "       FIAC.REFERENCE_FLAG," +
            "       FIAC.EXCHANGE_FLAG," +
            "       FIAC.ACCOUNT_RELATION_TYPE_ID," +
            "       FIAC.DISABLE_DATE" +
            "  FROM FNAC.FINANCIAL_ACCOUNT FIAC" +
            " INNER JOIN FNAC.FINANCIAL_ACCOUNT_STRUCTURE FS" +
            "    ON FIAC.FINANCIAL_ACCOUNT_STRUCTURE_ID = FS.ID" +
            "   AND FS.DELETED_DATE IS NULL" +
            " WHERE FIAC.DELETED_DATE IS NULL" +
            "   AND FIAC.DISABLE_DATE IS NULL" +
            "   AND FIAC.FINANCIAL_ACCOUNT_STRUCTURE_ID =" +
            "       :financialAccountStructureId" +
            "   and (:descriptionObject is null or FIAC.DESCRIPTION like %:description% )" +
            "   and (:codeObject is null or FIAC.CODE like %:code% )" +
            " AND EXISTS (SELECT 1" +
            "          FROM fnac.CODING_TYPE_ORG_REL INER_ORG_REL" +
            "         WHERE INER_ORG_REL.ORGANIZATION_ID = :organizationId" +
            "           AND INER_ORG_REL.FINANCIAL_CODING_TYPE_ID =" +
            "               FS.FINANCIAL_CODING_TYPE_ID" +
            "           AND INER_ORG_REL.ACTIVE_FLAG = 1)"
            , countQuery = " SELECT count(FIAC.id)" +
            "              FROM FNAC.FINANCIAL_ACCOUNT FIAC" +
            "             INNER JOIN FNAC.FINANCIAL_ACCOUNT_STRUCTURE FS" +
            "                ON FIAC.FINANCIAL_ACCOUNT_STRUCTURE_ID = FS.ID" +
            "               AND FS.DELETED_DATE IS NULL" +
            "             WHERE FIAC.DELETED_DATE IS NULL" +
            "               AND FIAC.DISABLE_DATE IS NULL" +
            "               AND FIAC.FINANCIAL_ACCOUNT_STRUCTURE_ID =" +
            "                   :financialAccountStructureId" +
            "               and (:descriptionObject is null or FIAC.DESCRIPTION like %:description% )" +
            "               and (:codeObject is null or FIAC.CODE like %:code% )" +
            "             AND EXISTS (SELECT 1" +
            "                      FROM fnac.CODING_TYPE_ORG_REL INER_ORG_REL" +
            "                     WHERE INER_ORG_REL.ORGANIZATION_ID = :organizationId" +
            "                       AND INER_ORG_REL.FINANCIAL_CODING_TYPE_ID =" +
            "                           FS.FINANCIAL_CODING_TYPE_ID" +
            "                       AND INER_ORG_REL.ACTIVE_FLAG = 1)"
            , nativeQuery = true)
    List<Object[]>  financialAccountGetByStructure(Long organizationId, Long financialAccountStructureId, Object descriptionObject, String description, Object codeObject, String code);


    @Query(value = " SELECT FIAC.ID, FIAC.FULL_DESCRIPTION, FIAC.CODE, FIAC.DESCRIPTION" +
            "  FROM fnac.FINANCIAL_ACCOUNT FIAC" +
            " INNER JOIN FNAC.FINANCIAL_ACCOUNT_STRUCTURE FS" +
            "    ON FIAC.FINANCIAL_ACCOUNT_STRUCTURE_ID = FS.ID" +
            " WHERE EXISTS (SELECT 1" +
            "          FROM fnac.CODING_TYPE_ORG_REL INER_ORG_REL" +
            "         WHERE INER_ORG_REL.ORGANIZATION_ID = :organizationId" +
            "           AND INER_ORG_REL.FINANCIAL_CODING_TYPE_ID =" +
            "               FS.FINANCIAL_CODING_TYPE_ID" +
            "           AND INER_ORG_REL.ACTIVE_FLAG = 1)" +
            "   and (:descriptionObject is null or FIAC.DESCRIPTION like %:description% )" +
            "   and (:codeObject is null or FIAC.CODE like %:code% )" +
            "   and (:fullDescriptionObject is null or FIAC.FULL_DESCRIPTION like %:fullDescription% )"
            , countQuery = " select count(FIAC.id)   " +
            "  FROM fnac.FINANCIAL_ACCOUNT FIAC" +
            " INNER JOIN FNAC.FINANCIAL_ACCOUNT_STRUCTURE FS" +
            "    ON FIAC.FINANCIAL_ACCOUNT_STRUCTURE_ID = FS.ID" +
            " WHERE EXISTS (SELECT 1" +
            "          FROM fnac.CODING_TYPE_ORG_REL INER_ORG_REL" +
            "         WHERE INER_ORG_REL.ORGANIZATION_ID = :organizationId" +
            "           AND INER_ORG_REL.FINANCIAL_CODING_TYPE_ID =" +
            "               FS.FINANCIAL_CODING_TYPE_ID" +
            "           AND INER_ORG_REL.ACTIVE_FLAG = 1)" +
            "   and (:descriptionObject is null or FIAC.DESCRIPTION like %:description% )" +
            "   and (:codeObject is null or FIAC.CODE like %:code% )" +
            "   and (:fullDescriptionObject is null or FIAC.FULL_DESCRIPTION like %:fullDescription% )"
            , nativeQuery = true)
    Page<Object[]> financialAccountAdjustment(Long organizationId, Object descriptionObject, String description, Object codeObject, String code, Object fullDescriptionObject, String fullDescription, Pageable pageable);
}


