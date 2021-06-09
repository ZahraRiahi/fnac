package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.CentricAccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CentricAccountTypeRepository extends JpaRepository<CentricAccountType, Long> {
    @Query(value = "select cat from  CentricAccountType cat  where  cat.activeFlag=1")
    List<CentricAccountType> findByCentricAccountType();
}
