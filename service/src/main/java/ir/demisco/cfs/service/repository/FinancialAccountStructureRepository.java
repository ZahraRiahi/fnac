package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.FinancialAccountStructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface FinancialAccountStructureRepository extends JpaRepository<FinancialAccountStructure, Long> {
    @Query("select fas from  FinancialAccountStructure fas where fas.financialCodingType.id=:financialCodingTypeId")
    List<FinancialAccountStructure> findByFinancialCodingTypeId(Long financialCodingTypeId);


    @Query("select coalesce(COUNT(fas.id),0) from FinancialAccountStructure fas where fas.sequence=:sequence ")
    Long getCountByFinancialAccountStructureSequenceAndId(Long sequence);
}
