package ir.demisco.cfs.service.repository;

import ir.demisco.cloud.basic.model.entity.prs.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
