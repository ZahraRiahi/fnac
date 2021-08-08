package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.FinancialAccountDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FinancialAccountDescriptionRepozitory extends JpaRepository<FinancialAccountDescription, Long>  {

    @Query(value = "select fad from  FinancialAccountDescription fad  where  fad.deletedDate is null ")
    List<FinancialAccountDescription> findByFinancialAccountDescription();
}
