package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.dto.response.CentricAccountNewResponse;
import ir.demisco.cfs.model.entity.CentricAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CentricAccountRepository extends JpaRepository<CentricAccount, Long> {

    @Query("select ca from CentricAccount ca where ca.organization.id=:organizationId and ca.centricAccountType.id=:centricAccountTypeId")
    List<CentricAccount> findByCentricAccountAndOrganizationId(Long centricAccountTypeId, Long organizationId);

}
