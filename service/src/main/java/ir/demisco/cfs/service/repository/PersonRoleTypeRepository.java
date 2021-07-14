package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.PersonRoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonRoleTypeRepository extends JpaRepository<PersonRoleType, Long> {
    @Query(value = "select distinct  prt.id,prt.description,case when cpr.id is null then 0 else 1 end as flg_exists from  PersonRoleType prt left outer join CentricPersonRole cpr on prt.id=cpr.personRoleType.id" +
            " where prt.deletedDate is null ")
    List<Object[]> findByPersonRoleTypeListObject();

}
