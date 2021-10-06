package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.AccountPermanentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountPermanentStatusRepository extends JpaRepository<AccountPermanentStatus, Long> {
}
