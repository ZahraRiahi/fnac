package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.FinancialAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface FinancialAccountRepository extends JpaRepository<FinancialAccount, Long> {
    @Query(value = " select fa from  FinancialAccount fa join fa.financialAccountStructure fs where fs.sequence = (select max(fs_inner.sequence) from  FinancialAccountStructure  fs_inner " +
            " where fs_inner.financialCodingType.id=fs.financialCodingType.id) " +
            " and fa.organization.id=:organizationId and fa.deletedDate is null")
    List<FinancialAccount> findByFinancialAccountByOrganizationId(Long organizationId);

    @Query("select fa from  FinancialAccount fa where fa.financialAccountStructure.id=:financialAccountStructureId and fa.deletedDate is null")
    List<FinancialAccount> findByFinancialAccountStructureId(Long financialAccountStructureId);


}
