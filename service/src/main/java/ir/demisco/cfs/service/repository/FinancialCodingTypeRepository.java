package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.FinancialCodingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FinancialCodingTypeRepository extends JpaRepository<FinancialCodingType, Long> {

    @Query("select fct from FinancialCodingType fct where fct.organization.id=:organizationId")
    List<FinancialCodingType> findByOrganizationId(Long organizationId);
}
