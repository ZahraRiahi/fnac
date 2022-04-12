package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.FinancialCodingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FinancialCodingTypeRepository extends JpaRepository<FinancialCodingType, Long> {

    @Query(value = " SELECT FNCT.id, FNCT.DESCRIPTION" +
            "    FROM fnac.FINANCIAL_CODING_TYPE FNCT" +
            "    WHERE EXISTS (SELECT 1" +
            "            FROM fnac.CODING_TYPE_ORG_REL INER_ORG_REL" +
            "            WHERE INER_ORG_REL.ORGANIZATION_ID = :organizationId" +
            "            AND INER_ORG_REL.FINANCIAL_CODING_TYPE_ID = FNCT.ID" +
            "            AND INER_ORG_REL.ACTIVE_FLAG = 1) "
            , nativeQuery = true)
    List<Object[]> findByOrganizationId(Long organizationId);


}
