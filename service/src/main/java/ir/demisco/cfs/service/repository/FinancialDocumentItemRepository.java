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
            "                              AND FA.FINANCIAL_ACCOUNT_STRUCTURE_ID = :financialAccountStructureId AND DI.DELETED_DATE IS NULL  "
            , nativeQuery = true)
    List<Object> findByFinancialDocumentAndFinancialAccountStructure(Long financialAccountStructureId);

    @Query(value = " SELECT 1 " +
            "  FROM fndc.FINANCIAL_DOCUMENT_ITEM FDI " +
            " WHERE FDI.FINANCIAL_ACCOUNT_ID = :financialAccountId " +
            "   AND FDI.DELETED_DATE IS NULL  "
            , nativeQuery = true)
    List<Long> findByFinancialDocumentItemByFinancialAccountId(Long financialAccountId);
}
