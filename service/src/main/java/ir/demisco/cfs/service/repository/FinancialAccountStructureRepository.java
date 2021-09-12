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
    Long getCountByFinancialAccountStructureSequenceAndId(Long sequence, Long financialCodingTypeId,Long financialAccountStructureId);


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


}
