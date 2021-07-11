package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.FinancialAccount;
import ir.demisco.cfs.model.entity.FinancialAccountStructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface FinancialAccountRepository extends JpaRepository<FinancialAccount, Long> {
    @Query(value = " select fa from  FinancialAccount fa where fa.organization.id=:organizationId")
    List<FinancialAccount> findByFinancialAccountByOrganizationId(Long organizationId);

    @Query("select fa from  FinancialAccount fa where fa.financialAccountStructure.id=:financialAccountStructureId and fa.deletedDate is null")
    List<FinancialAccount> findByFinancialAccountStructureId(Long financialAccountStructureId);


}
