package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.FinancialAccountDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FinancialAccountDescriptionRepository extends JpaRepository<FinancialAccountDescription, Long>  {


    @Query("select fad  from FinancialAccountDescription fad where fad.id in (select fade.id from  AccountRelatedDescription ard join ard.financialAccountDescription fade where  fade.id in (:accountRelatedDescriptionIdList)) ")
    List<FinancialAccountDescription> findByFinancialAccountDescriptionListId(List<Long> accountRelatedDescriptionIdList);

}
