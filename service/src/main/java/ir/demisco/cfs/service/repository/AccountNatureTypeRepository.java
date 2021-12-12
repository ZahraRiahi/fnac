package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.AccountNatureType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountNatureTypeRepository extends JpaRepository<AccountNatureType, Long> {
    @Query(value = "select acnt from   AccountNatureType acnt  where  acnt.deletedDate is null ")
    List<AccountNatureType> findByAccountNatureType();
}
