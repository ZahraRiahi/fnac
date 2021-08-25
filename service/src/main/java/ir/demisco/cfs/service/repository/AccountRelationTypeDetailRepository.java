package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.AccountRelationTypeDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRelationTypeDetailRepository extends JpaRepository<AccountRelationTypeDetail, Long> {
    @Query(" select artd.id from  AccountRelationTypeDetail artd where artd.accountRelationType.id=:accountRelationTypeId and artd.deletedDate is null")
    List<Long> findByAccountRelationTypeDetail(Long accountRelationTypeId);
}
