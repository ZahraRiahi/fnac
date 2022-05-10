package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.FinancialAccountStructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FinancialAccountStructureRepository extends JpaRepository<FinancialAccountStructure, Long> {
    @Query("select fas from  FinancialAccountStructure fas where fas.financialCodingType.id=:financialCodingTypeId ")
    List<FinancialAccountStructure> findByFinancialCodingTypeId(Long financialCodingTypeId);

    @Query("select coalesce(COUNT(fas.id),0) from FinancialAccountStructure fas " +
            " join fas.financialCodingType fct" +
            " where fas.sequence=:sequence and fct.id=:financialCodingTypeId   and fas.deletedDate is null and fas.id not in(:financialAccountStructureId)")
    Long getCountByFinancialAccountStructureSequenceAndId(Long sequence, Long financialCodingTypeId, Long financialAccountStructureId);


    @Query("select coalesce(COUNT(fas.id),0) from FinancialAccountStructure fas " +
            " join fas.financialCodingType fct" +
            " where fas.sequence=:sequence and fct.id=:financialCodingTypeId   and fas.deletedDate is null ")
    Long getCountByFinancialAccountStructureSequenceAndIdSave(Long sequence, Long financialCodingTypeId);

    @Query(value = "select fnas.id" +
            " from fnac.financial_account_structure fnas" +
            " where fnas.financial_coding_type_id = :financialCodingTypeId" +
            "   and fnas.deleted_date is null" +
            " and fnas.sequence = (select min(fnas_inner1.sequence) " +
            " from  fnac.financial_account_structure fnas_inner1 " +
            " where fnas_inner1.financial_coding_type_id = :financialCodingTypeId " +
            " and fnas_inner1.deleted_date is null " +
            " and (:financialAccountStructure is null or " +
            " (fnas_inner1.sequence > " +
            " (select fnas_inner2.sequence " +
            " from fnac.financial_account_structure  fnas_inner2 " +
            " where fnas_inner2.id = :financialAccountStructureId " +
            " and fnas_inner2.deleted_date is null))))"
            , nativeQuery = true)
    Long findByFinancialCodingTypeAndFinancialAccountStructure(Long financialCodingTypeId, Long financialAccountStructureId, String financialAccountStructure);

    @Query(value = " SELECT FNAS.FLG_PERMANENT_STATUS     " +
            "  FROM FNAC.FINANCIAL_ACCOUNT_STRUCTURE FNAS " +
            " WHERE FNAS.FINANCIAL_CODING_TYPE_ID = :financialCodingTypeId " +
            "   AND FNAS.DELETED_DATE IS NULL " +
            "   AND FNAS.SEQUENCE = " +
            "       (SELECT MIN(FNAS_INNER1.SEQUENCE) " +
            "          FROM FNAC.FINANCIAL_ACCOUNT_STRUCTURE FNAS_INNER1 " +
            "         WHERE FNAS_INNER1.FINANCIAL_CODING_TYPE_ID = :financialCodingTypeId " +
            "           AND FNAS_INNER1.DELETED_DATE IS NULL " +
            "           AND (:financialAccountStructure IS NULL OR " +
            "               (FNAS_INNER1.SEQUENCE > " +
            "               (SELECT FNAS_INNER2.SEQUENCE " +
            "                    FROM FNAC.FINANCIAL_ACCOUNT_STRUCTURE FNAS_INNER2 " +
            "                   WHERE FNAS_INNER2.ID = :financialAccountStructureId " +
            "                     AND FNAS_INNER2.DELETED_DATE IS NULL))))"
            , nativeQuery = true)
    Long findByFinancialCodingTypeAndFinancialAccountStructureId(Long financialCodingTypeId, Object financialAccountStructure, Long financialAccountStructureId);

    @Query(value = " SELECT 1 " +
            "  FROM fnac.FINANCIAL_ACCOUNT_STRUCTURE AST " +
            " WHERE AST.FINANCIAL_CODING_TYPE_ID = :financialCodingTypeId " +
            "   AND AST.FLG_PERMANENT_STATUS = 1 " +
            " AND AST.DELETED_DATE IS NULL " +
            " and (:financialAccountStructure is null or AST.ID != :financialAccountStructureId) "
            , nativeQuery = true)
    List<Long> getFinancialAccountStructureByCodingAndStructureId(Long financialCodingTypeId, Object financialAccountStructure, Long financialAccountStructureId);


    @Query(value = "SELECT 1  FROM fnac.FINANCIAL_ACCOUNT_STRUCTURE AST WHERE AST.ID =:childAccountStructure " +
            "    AND AST.DIGIT_COUNT = LENGTH(:code) ", nativeQuery = true)
    Long getFinancialAccountStructureByCodeAndChild(Long childAccountStructure, String code);

    @Query(value = " SELECT 1 FROM FNAC.FINANCIAL_ACCOUNT_STRUCTURE ST  " +
            "                            WHERE ST.ID = :financialAccountStructureId " +
            "                           AND ST.FLG_PERMANENT_STATUS = 1  ", nativeQuery = true)
    Long getFinancialAccountStructureById(Long financialAccountStructureId);


    @Query(value = " SELECT t.sequence,t.sumDigit " +
            "      FROM FinancialAccountStructure t " +
            "     WHERE t.financialCodingType.id=:financialCodingTypeId and t.deletedDate is null" +
            "  and t.sequence= (SELECT MAX(iner_st.sequence) " +
            "          FROM FinancialAccountStructure  iner_st " +
            "         WHERE iner_st.financialCodingType.id = t.financialCodingType.id  and iner_st.deletedDate IS NULL) ")
    List<Object[]> findByFinancialCodingType(Long financialCodingTypeId);


    @Query(value = " SELECT 1 " +
            "  FROM fnac.FINANCIAL_ACCOUNT_STRUCTURE AST " +
            " WHERE AST.FINANCIAL_CODING_TYPE_ID = :financialCodingTypeId "
            , nativeQuery = true)
    List<Long> getFinancialAccountStructureByCoding(Long financialCodingTypeId);

    @Query(value = "   SELECT 1 " +
            "  FROM FNAC.FINANCIAL_ACCOUNT_STRUCTURE T " +
            " WHERE T.DELETED_DATE IS NULL " +
            "   AND T.FINANCIAL_CODING_TYPE_ID = :financialCodingTypeId  "
            , nativeQuery = true)
    List<Long> findFinancialAccountStructureByCoding(Long financialCodingTypeId);

    @Query(value = "   SELECT 1 " +
            "    FROM FNAC.FINANCIAL_ACCOUNT_STRUCTURE AST " +
            "    WHERE AST.ID = :childAccountStructure " +
            "    AND AST.SEQUENCE = 1 " +
            "    AND SUBSTR(:code, 0, 1) = '0'  "
            , nativeQuery = true)
    Long getFinancialAccountStructureByChildAccountStructureAndCode(Long childAccountStructure, String code);

    @Query(value = "select 1" +
            "            from fnac.financial_account_structure" +
            "           where id = :id" +
            "             and (sequence != :sequence " +
            "             or digit_count != :digitCount" +
            "             or sum_digit != :sumDigit" +
            "             or financial_coding_type_id != :financialCodingTypeId" +
            "             or flg_show_in_acc != :flgShowInAcc" +
            "             or flg_permanent_status != :flgPermanentStatus) ", nativeQuery = true)
    Long getFinancialAccountStructureBySequenceAndFlg(Long id, Long sequence,Long digitCount,Long sumDigit,Long financialCodingTypeId,Boolean flgShowInAcc,Boolean flgPermanentStatus);
}
