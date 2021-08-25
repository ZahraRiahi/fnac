package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.AccountMoneyType;
import ir.demisco.cfs.model.entity.AccountRelatedType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountMoneyTypeRepository extends JpaRepository<AccountMoneyType, Long> {
    List<AccountMoneyType> findByFinancialAccountId(Long financialAccountId);


}
