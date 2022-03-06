package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.CentricOrgRel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CentricOrgRelRepository extends JpaRepository<CentricOrgRel, Long> {
    @Query(value = " SELECT 1" +
            "            FROM fnac.CENTRIC_ORG_REL INER_ORG_REL" +
            "           WHERE INER_ORG_REL.ORGANIZATION_ID = :organizationId" +
            "             AND INER_ORG_REL.CENTRIC_ACCOUNT_ID = :centricAccountId " +
            "             AND INER_ORG_REL.ACTIVE_FLAG = 1 "
            , nativeQuery = true)
    Long getCentricOrgRelByOrganizationAndCentricAccount(Long organizationId, Long centricAccountId);

    @Query(value = "select t.id from FNAC.CENTRIC_ORG_REL  T WHERE T.centric_account_id = :centricAccountId and T.organization_id=:organizationId"
            , nativeQuery = true)
    Long findByCentricAccountIdForDelete(Long centricAccountId,Long organizationId);
}

