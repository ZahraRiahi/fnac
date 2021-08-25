package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.AccountRelatedType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRelatedTypeRepository extends JpaRepository<AccountRelatedType, Long> {

    List<AccountRelatedType> findByFinancialAccountId(Long financialAccountId);
}
