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
            "where ca.organization.id=:organizationId  and ca.person.id=:personId and ca.deletedDate is null")
    List<CentricAccount> findByCentricAccountAndOrganizationAndPerson(Long organizationId, Long personId);

    @Query("select count(ca.id) from CentricAccount ca join ca.centricAccountType cat " +
            "where ca.organization.id=:organizationId  and ca.person.id=:personId and cat.code='10' and ca.deletedDate is null")
    Long findByCountCentricAccountAndOrganizationAndPerson(Long organizationId, Long personId);


    @Query(value = "select artd.id,cnac.id,cnac.name,cnac.code,acrt.description,acrt.id,coalesce(artd.sequence,0) " +
            " from  CentricAccount cnac left outer join cnac.accountDefaultValues acdv  " +
            " left outer join acdv.accountRelationTypeDetail artd " +
            " left outer join artd.accountRelationType acrt " +
            " where acdv.financialAccount.id=:financialAccountId and acdv.deletedDate is null and artd.deletedDate is null and acrt.deletedDate is null" +
            " and cnac.deletedDate is null ")
    List<Object[]> findByCentricAccountListObject(Long financialAccountId);

}
