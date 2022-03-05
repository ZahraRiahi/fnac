package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.CodingTypeOrgRel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CodingTypeOrgRelRepository extends JpaRepository<CodingTypeOrgRel, Long> {
    @Query(value = " SELECT 1" +
            "            FROM fnac.CODING_TYPE_ORG_REL INER_ORG_REL" +
            "           WHERE INER_ORG_REL.ORGANIZATION_ID = :organizationId" +
            "             AND INER_ORG_REL.FINANCIAL_CODING_TYPE_ID = :financialCodingTypeId" +
            "             AND INER_ORG_REL.ACTIVE_FLAG = 1 "
            , nativeQuery = true)
    Long getCodingTypeOrgRelByOrganizationAndFinancialCodingType(Long organizationId, Long financialCodingTypeId);


    @Query(value = "select INER_ORG_REL.id from fnac.CODING_TYPE_ORG_REL INER_ORG_REL WHERE INER_ORG_REL.FINANCIAL_CODING_TYPE_ID = :financialCodingTypeId"
            , nativeQuery = true)
    Long findByFinancialCodingTypeIdForDelete(Long financialCodingTypeId);
}
