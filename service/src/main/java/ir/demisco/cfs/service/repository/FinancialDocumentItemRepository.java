package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.FinancialDocumentItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FinancialDocumentItemRepository extends JpaRepository<FinancialDocumentItem, Long> {

    @Query(" select count(fdi.id) from FinancialDocumentItem fdi where fdi.financialAccount.id=:financialAccountId ")
    Long getFinancialDocumentItemByFinancialAccountId(Long financialAccountId);

}
