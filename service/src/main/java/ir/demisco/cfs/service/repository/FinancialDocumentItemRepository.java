package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.FinancialDocumentItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FinancialDocumentItemRepository extends JpaRepository<FinancialDocumentItem, Long> {

    @Query(" select count(fdi.id) from FinancialDocumentItem fdi where fdi.financialAccount.id=:financialAccountId ")
    Long getFinancialDocumentItemByFinancialAccountId(Long financialAccountId);
    @Query(value = "   SELECT 1 FROM FNDC.FINANCIAL_DOCUMENT_ITEM DI " +
            "                        INNER JOIN fnac.FINANCIAL_ACCOUNT FA " +
            "                              ON FA.ID = DI.FINANCIAL_ACCOUNT_ID " +
            "                              AND FA.FINANCIAL_ACCOUNT_STRUCTURE_ID = :financialAccountStructureId "
            , nativeQuery = true)
    Long findByFinancialDocumentAndFinancialAccountStructure(Long financialAccountStructureId);
}
