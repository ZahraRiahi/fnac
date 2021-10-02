package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.AccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountStatusRepository extends JpaRepository<AccountStatus, Long> {
}
