package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.FinancialAccountStructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FinancialAccountStructureRepository extends JpaRepository<FinancialAccountStructure, Long> {
    @Query("select fas from  FinancialAccountStructure fas where fas.financialCodingType.id=:financialCodingTypeId and fas.deletedDate is null")
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

    @Query(value = "SELECT FNAS.FLG_PERMANENT_STATUS     " +
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
            " and (:financialAccountStructure is null or AST.ID != :financialAccountStructureId) "
            , nativeQuery = true)
    List<Long> getFinancialAccountStructureByCodingAndStructureId(Long financialCodingTypeId, Object financialAccountStructure, Long financialAccountStructureId);


    @Query(value = "SELECT 1  FROM fnac.FINANCIAL_ACCOUNT_STRUCTURE AST WHERE AST.ID =:childAccountStructure " +
            "    AND AST.DIGIT_COUNT = LENGTH(:code) ", nativeQuery = true)
    Long getFinancialAccountStructureByCodeAndChild(Long childAccountStructure, String code);

    @Query(value = " SELECT 1 FROM FNAC.FINANCIAL_ACCOUNT_STRUCTURE ST  " +
            "                            WHERE ST.ID = :financialAccountStructureId " +
            "                           AND ST.FLG_PERMANENT_STATUS = 1  ", nativeQuery = true)
    Long getFinancialAccountStructureByld(Long financialAccountStructureId);

}
