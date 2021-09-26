package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.PersonRoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonRoleTypeRepository extends JpaRepository<PersonRoleType, Long> {
    @Query(value = "select prty.id," +
            " prty.description," +
            " case" +
            " when :centricAccountId is null or not exists " +
            " (select 1 " +
            " from fnac.centric_person_role cnpr " +
            " where (:centricAccount is null or " +
            " cnpr.centric_account_id = :centricAccountId)" +
            " and cnpr.person_role_type_id = prty.id" +
            " and cnpr.deleted_date is null) then " +
            " 0 " +
            " else " +
            " 1 " +
            " end flg_exists " +
            " from fnac.person_role_type prty " +
            " where prty.deleted_date is null "
            , nativeQuery = true)
    List<Object[]> findByPersonRoleTypeListObject(Object centricAccount, Long centricAccountId);


    @Query(value = "    SELECT PR.ID PERSON_ROLE_TYPE_ID, " +
            "          PR.DESCRIPTION, " +
            "          CASE " +
            "            WHEN CP.ID IS NULL THEN\n" +
            "             0 " +
            "            ELSE " +
            "             1 " +
            "          END FLG_EXISTS " +
            "     FROM fnac.PERSON_ROLE_TYPE PR " +
            "     LEFT OUTER JOIN fnac.CENTRIC_PERSON_ROLE CP " +
            "       ON CP.PERSON_ROLE_TYPE_ID = PR.ID " +
            "      AND CP.CENTRIC_ACCOUNT_ID = :centricAccountId "
            , nativeQuery = true)
    List<Object[]> findByPersonRoleTypeListObject(Long centricAccountId);


}
