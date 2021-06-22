package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.CentricAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CentricAccountRepository extends JpaRepository<CentricAccount, Long> {

    @Query("select ca from CentricAccount ca where ca.organization.id=:organizationId and ca.centricAccountType.id=:centricAccountTypeId")
    List<CentricAccount> findByCentricAccountAndOrganizationId(Long centricAccountTypeId, Long organizationId);

    @Query("select ca from CentricAccount ca join ca.centricPersonRoleList cpr " +
            "where ca.organization.id=:organizationId and cpr.personRoleType.id=:personRoleTypeId and ca.person.id=:personId ")
    List<CentricAccount> findByCentricPersonRoleAndOrganizationAndPersonRoleTypeAndPerson(Long organizationId, Long personRoleTypeId, Long personId);


    @Query("select ca from CentricAccount ca join ca.centricPersonRoleList cpr " +
            "where ca.organization.id=:organizationId  and ca.person.id=:personId ")
    List<CentricAccount> findByCentricAccountAndOrganizationAndPerson(Long organizationId, Long personId);
}
