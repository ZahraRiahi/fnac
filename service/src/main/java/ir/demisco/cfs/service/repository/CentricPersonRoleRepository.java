package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.CentricPersonRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CentricPersonRoleRepository extends JpaRepository<CentricPersonRole, Long> {
    @Query("select cpr from  CentricPersonRole cpr where cpr.centricAccount.id=:centricAccountId ")
    List<CentricPersonRole> findByCentricAccountId(Long centricAccountId);

    @Query(value = " select 1" +
            "  from fnac.centric_person_role t" +
            " where t.centric_account_id = :centricAccountId" +
            "   and t.person_role_type_id = :personRoleTypeId "
            , nativeQuery = true)
    List<Long> findByCentricPersonRoleByIdAndCentricId(Long centricAccountId, Long personRoleTypeId);
}

