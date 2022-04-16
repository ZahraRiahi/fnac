package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.CentricPersonRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CentricPersonRoleRepository extends JpaRepository<CentricPersonRole, Long> {
    @Query("select cpr from  CentricPersonRole cpr where cpr.centricAccount.id=:centricAccountId ")
    List<CentricPersonRole> findByCentricAccountId(Long centricAccountId);
}

