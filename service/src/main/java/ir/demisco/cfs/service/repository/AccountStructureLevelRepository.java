package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.AccountStructureLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountStructureLevelRepository extends JpaRepository<AccountStructureLevel, Long> {

    @Query(value = " with Structure_Query as " +
            "   (select t.id, t.sequence, t.description, t.digit_count " +
            "      from fnac.financial_account_structure  t" +
            "     where t.deleted_date is null" +
            "     START WITH t.financial_coding_type_id = :financialCodingTypeId" +
            "    connect by prior" +
            "                t.id = (select id" +
            "                          from fnac.financial_account_structure t1" +
            "                         where t1.financial_coding_type_id =" +
            "                               t.financial_coding_type_id" +
            "                           and t1.sequence =" +
            "                               (select max(t2.sequence)" +
            "                                  from fnac.financial_account_structure t2" +
            "                                 where t2.financial_coding_type_id =" +
            "                                       t1.financial_coding_type_id" +
            "                                   and t2.sequence > t1.sequence" +
            "                                   and t2.deleted_date is null))" +
            "     order by t.sequence)" +
            "  select rownum," +
            "         substr(:financialAccountCode," +
            "                0," +
            "                nvl((select sum(s1.digit_count)" +
            "                      from Structure_Query s1" +
            "                     where s1.sequence <= s.sequence)," +
            "                    0))," +
            "         s.ID" +
            "    from Structure_Query s" +
            "   where s.sequence <=" +
            "         (select fnas.sequence" +
            "          " +
            "            from fnac.financial_account_structure fnas" +
            "           where fnas.financial_coding_type_id = :financialCodingTypeId" +
            "             and fnas.deleted_date is null" +
            "             and fnas.sequence =" +
            "                 (select min(fnas_inner1.sequence)" +
            "                    from fnac.financial_account_structure fnas_inner1" +
            "                   where fnas_inner1.financial_coding_type_id =" +
            "                         :financialCodingTypeId" +
            "                     and fnas_inner1.deleted_date is null" +
            "                     and (:financialAccountStructure is null or" +
            "                         (fnas_inner1.sequence >=" +
            "                         (select fnas_inner2.sequence" +
            "                              from fnac.financial_account_structure fnas_inner2" +
            "                             where fnas_inner2.id = :financialAccountStructureId" +
            "                               and fnas_inner2.deleted_date is null)))))"
            , nativeQuery = true)
    List<Object[]> findByFinancialAccountStructureListObject(Long financialCodingTypeId, String financialAccountCode, Long financialAccountStructureId, Object financialAccountStructure);


    @Query(value = " select 1 " +
            "          from fnac.financial_account t1 " +
            "         where t1.id = :financialAccountId " +
            "           and (t1.code != :financialAccountCode or t1.financial_account_structure_id !=(select fnas.id " +
            "            from fnac.financial_account_structure fnas " +
            "           where fnas.financial_coding_type_id = :financialCodingTypeId " +
            "             and fnas.deleted_date is null " +
            "             and fnas.sequence =" +
            "                 (select min(fnas_inner1.sequence)" +
            "                    from fnac.financial_account_structure fnas_inner1 " +
            "                   where fnas_inner1.financial_coding_type_id = " +
            "                         :financialCodingTypeId " +
            "                     and fnas_inner1.deleted_date is null" +
            "                     and (:financialAccountStructure is null or" +
            "                         (fnas_inner1.sequence >=" +
            "                         (select fnas_inner2.sequence" +
            "                              from fnac.financial_account_structure fnas_inner2" +
            "                             where fnas_inner2.id = :financialAccountStructureId" +
            "                               and fnas_inner2.deleted_date is null)))))) "
            , nativeQuery = true)
    Long getAccountStructureLevelByFinancialAccountAndFinancialCodingAndFinancialAccountStructure(Long financialAccountId, Long financialCodingTypeId, String financialAccountCode, Long financialAccountStructureId, Object financialAccountStructure);


    List<AccountStructureLevel> findByFinancialAccountId(Long financialAccountId);
}
