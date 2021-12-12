package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.AccountStructureLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountStructureLevelRepository extends JpaRepository<AccountStructureLevel, Long> {

    //    @Query(value = " with Structure_Query as " +
//            "   (select t.id, t.sequence, t.description, t.digit_count " +
//            "      from fnac.financial_account_structure  t" +
//            "     where t.deleted_date is null" +
//            "     START WITH t.financial_coding_type_id = :financialCodingTypeId" +
//            "    connect by prior" +
//            "                t.id = (select id" +
//            "                          from fnac.financial_account_structure t1" +
//            "                         where t1.financial_coding_type_id =" +
//            "                               t.financial_coding_type_id" +
//            "                           and t1.sequence =" +
//            "                               (select max(t2.sequence)" +
//            "                                  from fnac.financial_account_structure t2" +
//            "                                 where t2.financial_coding_type_id =" +
//            "                                       t1.financial_coding_type_id" +
//            "                                   and t2.sequence > t1.sequence" +
//            "                                   and t2.deleted_date is null))" +
//            "     order by t.sequence)" +
//            "  select rownum," +
//            "         substr(:financialAccountCode," +
//            "                0," +
//            "                nvl((select sum(s1.digit_count)" +
//            "                      from Structure_Query s1" +
//            "                     where s1.sequence <= s.sequence)," +
//            "                    0))," +
//            "         s.ID" +
//            "    from Structure_Query s" +
//            "   where s.sequence <=" +
//            "         (select fnas.sequence" +
//            "          " +
//            "            from fnac.financial_account_structure fnas" +
//            "           where fnas.financial_coding_type_id = :financialCodingTypeId" +
//            "             and fnas.deleted_date is null" +
//            "             and fnas.sequence =" +
//            "                 (select min(fnas_inner1.sequence)" +
//            "                    from fnac.financial_account_structure fnas_inner1" +
//            "                   where fnas_inner1.financial_coding_type_id =" +
//            "                         :financialCodingTypeId" +
//            "                     and fnas_inner1.deleted_date is null" +
//            "                     and (:financialAccountStructure is null or" +
//            "                         (fnas_inner1.sequence >= " +
//            "                         (select fnas_inner2.sequence" +
//            "                              from fnac.financial_account_structure fnas_inner2" +
//            "                             where fnas_inner2.id = :financialAccountStructureId" +
//            "                               and fnas_inner2.deleted_date is null)))))"
//            , nativeQuery = true)
//    @Query(value = " WITH STRUCTURE_QUERY AS " +
//            "   (SELECT T.ID, " +
//            "           T.FINANCIAL_ACCOUNT_STRUCTURE_ID, " +
//            "           T.CODE, " +
//            "           T.FINANCIAL_ACCOUNT_PARENT_ID " +
//            "      FROM fnac.FINANCIAL_ACCOUNT T " +
//            "     WHERE T.DELETED_DATE IS NULL " +
//            "     START WITH T.ID = :financialAccountId " +
//            "    CONNECT BY PRIOR T.FINANCIAL_ACCOUNT_PARENT_ID = T.ID " +
//            "     ORDER BY T.ID ASC) " +
//            "  SELECT  " +
//            "         ROWNUM, " +
//            "         s.CODE, " +
//            "         s.FINANCIAL_ACCOUNT_STRUCTURE_ID,         " +
//            "         :financialAccountId,        " +
//            "         s.FINANCIAL_ACCOUNT_PARENT_ID  " +
//            "    FROM STRUCTURE_QUERY s"
//            , nativeQuery = true)
    @Query(value = " SELECT SEQUENCE, " +
            "       nvl(CODE, " +
            "           (SELECT CODE " +
            "              FROM fnac.FINANCIAL_ACCOUNT AC_INER " +
            "             WHERE AC_INER.ID = :financialAccountId)), " +
            "       FINANCIAL_ACCOUNT_STRUCTURE_ID, " +
            "       FINANCIAL_ACCOUNT_PARENT_ID " +
            "  FROM (SELECT AC.ID," +
            "               nvl(AST_P.ID, ast.id) FINANCIAL_ACCOUNT_STRUCTURE_ID," +
            "               AC_P.CODE CODE," +
            "               NVL(AC.FINANCIAL_ACCOUNT_PARENT_ID, :financialAccountId) FINANCIAL_ACCOUNT_PARENT_ID," +
            "               NVL(AST_P.SEQUENCE, AST.SEQUENCE) SEQUENCE" +
            "          FROM fnac.FINANCIAL_ACCOUNT AC" +
            "          LEFT OUTER JOIN fnac.FINANCIAL_ACCOUNT AC_P" +
            "            ON AC_P.ID = AC.FINANCIAL_ACCOUNT_PARENT_ID" +
            "          LEFT OUTER JOIN fnac.FINANCIAL_ACCOUNT_STRUCTURE AST_P" +
            "            ON AST_P.ID = AC_P.FINANCIAL_ACCOUNT_STRUCTURE_ID" +
            "         inner JOIN fnac.FINANCIAL_ACCOUNT_STRUCTURE AST" +
            "            ON AST.ID = (SELECT FA_INER.FINANCIAL_ACCOUNT_STRUCTURE_ID" +
            "                           FROM fnac.FINANCIAL_ACCOUNT FA_INER" +
            "                          WHERE FA_INER.ID = :financialAccountId)" +
            "         WHERE AC.DISABLE_DATE IS NULL" +
            "         START WITH AC.ID = :financialAccountId" +
            "        CONNECT BY PRIOR AC.FINANCIAL_ACCOUNT_PARENT_ID = AC.ID" +
            "         ORDER BY NVL(AST_P.SEQUENCE, AST.SEQUENCE)) "
            , nativeQuery = true)
    List<Object[]> findByFinancialAccountStructureListObject(Long financialAccountId);

    @Query(value = " select 1 " +
            "          from fnac.financial_account t1 " +
            "         where t1.id = :financialAccountId " +
            "           and (t1.code != :financialAccountCode or t1.financial_account_structure_id !=(select fnas.id " +
            "            from fnac.financial_account_structure fnas " +
            "           where fnas.financial_coding_type_id = :financialCodingTypeId " +
            "             and fnas.deleted_date is null " +
            "             and fnas.sequence =" +
            "                 (select min(fnas_inner1.sequence)" +
            "                    from fnac.financial_account_structure fnas_inner1 " +
            "                   where fnas_inner1.financial_coding_type_id = " +
            "                         :financialCodingTypeId " +
            "                     and fnas_inner1.deleted_date is null" +
            "                     and (:financialAccountStructure is null or" +
            "                         (fnas_inner1.sequence >= " +
            "                         (select fnas_inner2.sequence" +
            "                              from fnac.financial_account_structure fnas_inner2" +
            "                             where fnas_inner2.id = :financialAccountStructureId" +
            "                               and fnas_inner2.deleted_date is null)))))) "
            , nativeQuery = true)
    Long getAccountStructureLevelByFinancialAccountAndFinancialCodingAndFinancialAccountStructure(Long financialAccountId, Long financialCodingTypeId, String financialAccountCode, Long financialAccountStructureId, Object financialAccountStructure);


    List<AccountStructureLevel> findByFinancialAccountId(Long financialAccountId);
}
