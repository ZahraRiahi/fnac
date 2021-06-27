package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.AccountRelationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRelationTypeRepository extends JpaRepository<AccountRelationType, Long> {
    @Query(value = "select acrt from  AccountRelationType acrt  where  acrt.deletedDate is null ")
    List<AccountRelationType> findByAccountRelationType();
}
