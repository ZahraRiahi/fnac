package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.AccountPermanentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountPermanentStatusRepository extends JpaRepository<AccountPermanentStatus, Long> {
    @Query(value = "select aps from   AccountPermanentStatus aps  where  aps.deletedDate is null ")
    List<AccountPermanentStatus> findByAccountPermanentStatus();
}
