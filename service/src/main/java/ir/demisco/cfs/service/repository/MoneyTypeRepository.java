package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.MoneyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MoneyTypeRepository extends JpaRepository<MoneyType, Long> {

    @Query(value = "select mnty.id," +
            " mnty.description," +
            " case" +
            "  when :financialAccountId is null or  not exists " +
            " (select 1" +
            " from fnac.account_money_type acmt" +
            " where mnty.id = acmt.money_type_id" +
            " and acmt.financial_account_id = :financialAccountId " +
            " and acmt.deleted_date is null) then " +
            " 0" +
            " else" +
            " 1 " +
            " end flg_exists" +
            " from fncr.money_type mnty" +
            " where mnty.deleted_date is null" +
            " and exists (select 1 " +
            "          from fncr.money_used t " +
            "         where t.deleted_date is null " +
            "           and t.organization_id = :organizationId and t.money_type_id=mnty.id) "
            , nativeQuery = true)
    List<Object[]> findByMonetTypeListObject(Long financialAccountId, Long organizationId);


    @Query(value = "select mnty.id," +
            "       mnty.description," +
            "       t.national_currency_flag," +
            "       case" +
            "         when :financialAccount is null or not exists" +
            "          (select 1" +
            "                 from fnac.account_money_type acmt" +
            "                where mnty.id = acmt.money_type_id" +
            " and (:financialAccount is null or " +
            "  acmt.financial_account_id = :financialAccountId) " +
            "                  and acmt.deleted_date is null) then" +
            "          0" +
            "         else" +
            "          1" +
            "       end flg_exists" +
            "  from fncr.money_type mnty" +
            "  inner join fncr.money_used t" +
            "    on t.deleted_date is null" +
            "   and t.organization_id = :organizationId" +
            "   and t.money_type_id = mnty.id" +
            " where mnty.deleted_date is null "
            , nativeQuery = true)
    List<Object[]> findByMoneyTypeAndFinancialAccountId(Object financialAccount, Long financialAccountId, Long organizationId);

}
