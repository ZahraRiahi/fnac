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

    @Query("select coalesce(1,0)  from  CentricAccount cnac where cnac.person.id=:personId and cnac.organization.id=:organizationId and cnac.deletedDate is null ")
    Long findByCentricAccountAndOrganizationAndPerson(Long personId, Long organizationId);


    @Query("select count(ca.id) from CentricAccount ca join ca.centricAccountType cat " +
            "where ca.organization.id=:organizationId  and ca.person.id=:personId and cat.code='10' and ca.deletedDate is null")
    Long findByCountCentricAccountAndOrganizationAndPerson(Long organizationId, Long personId);

    @Query(value = " select acdv.account_relation_typ_detail_id ," +
            " acdv.centric_account_id," +
            " cnac.name, " +
            " cnac.code, " +
            " acrt.description, " +
            " acrt.id , " +
            " artd.sequence, " +
            " cnat.id as centricAccountTypeId, " +
            " cnat.description as centricAccountTypeDescription " +
            " from fnac.centric_account cnac " +
            " right outer join fnac.account_default_value acdv " +
            " on cnac.id = acdv.centric_account_id " +
            " and acdv.deleted_date is null " +
            " left outer join fnac.account_relation_type_detail artd " +
            " on acdv.account_relation_typ_detail_id = artd.id " +
            " and artd.deleted_date is null " +
            " left outer join fnac.account_relation_type acrt " +
            " on acrt.id = artd.account_relation_type_id " +
            " and acrt.deleted_date is null " +
            " inner join fnac.centric_account_type cnat " +
            " on cnat.id = artd.centric_account_type_id " +
            " and cnat.deleted_date is null " +
            " where acdv.financial_account_id = :financialAccountId " +
            " and cnac.deleted_date is null "
            , nativeQuery = true)
    List<Object[]> findByCentricAccountListObject(Long financialAccountId);
}

