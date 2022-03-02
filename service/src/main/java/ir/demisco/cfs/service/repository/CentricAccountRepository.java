package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.CentricAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CentricAccountRepository extends JpaRepository<CentricAccount, Long> {

    @Query(value = " select cnac.id, cnac.code, cnac.name" +
            "  from CentricAccount cnac " +
            "  join cnac.centricAccountType cnat " +
            " where  cnat.id = :centricAccountTypeId " +
            "   and cnac.organization.id = :organizationId " +
            "   and cnac.deletedDate is null " +
            " and cnat.deletedDate is null " +
            "   and (:parentCentricAccount is null or " +
            " cnac.parentCentricAccount.id = :parentCentricAccountId) ")
    Page<Object[]> findByCentricAccountAndOrganizationIdAndParentCentricAccount(Long centricAccountTypeId, Long organizationId, Object parentCentricAccount, Long parentCentricAccountId, Pageable pageable);

    @Query("select ca from CentricAccount ca join ca.centricPersonRoleList cpr " +
            "where ca.organization.id=:organizationId and cpr.personRoleType.id=:personRoleTypeId and ca.person.id=:personId ")
    List<CentricAccount> findByCentricPersonRoleAndOrganizationAndPersonRoleTypeAndPerson(Long organizationId, Long personRoleTypeId, Long personId);

    @Query("select coalesce(1,0)  from  CentricAccount cnac where cnac.person.id=:personId and cnac.organization.id=:organizationId and cnac.deletedDate is null ")
    Long findByCentricAccountAndOrganizationAndPerson(Long personId, Long organizationId);


    @Query("select count(ca.id) from CentricAccount ca join ca.centricAccountType cat " +
            "where ca.organization.id=:organizationId  and (:personId is null or " +
            " ca.person.id=:personId) and cat.code='10' and ca.deletedDate is null")
    Long findByCountCentricAccountAndOrganizationAndPerson(Long organizationId, Long personId);


    @Query(value = " select acdv.id as accountDefaultValueId ,acdv.account_relation_typ_detail_id ," +
            " acdv.centric_account_id," +
            " cnac.name, " +
            " cnac.code, " +
            " acrt.description, " +
            " acrt.id , " +
            " artd.sequence, " +
            " cnat.id as centricAccountTypeId, " +
            " cnat.description as centricAccountTypeDescription " +
            " from fnac.centric_account cnac " +
            " right outer join fnac.account_default_value acdv " +
            " on cnac.id = acdv.centric_account_id " +
            " and acdv.deleted_date is null " +
            " left outer join fnac.account_relation_type_detail artd " +
            " on acdv.account_relation_typ_detail_id = artd.id " +
            " and artd.deleted_date is null " +
            " left outer join fnac.account_relation_type acrt " +
            " on acrt.id = artd.account_relation_type_id " +
            " and acrt.deleted_date is null " +
            " inner join fnac.centric_account_type cnat " +
            " on cnat.id = artd.centric_account_type_id " +
            " and cnat.deleted_date is null " +
            " where acdv.financial_account_id = :financialAccountId " +
            " and cnac.deleted_date is null "
            , nativeQuery = true)
    List<Object[]> findByCentricAccountListObject(Long financialAccountId);


    @Query("select count(ca.id) from CentricAccount ca join ca.centricAccountType cat " +
            "where ca.organization.id=:organizationId  and cat.id=:centricAccountTypeId" +
            " and ca.code=:code and ca.deletedDate is null")
    Long getCountByCentricAccountAndOrganizationAndCentricAccountTypeAndCode(Long organizationId, Long centricAccountTypeId, String code);

    //        @Query(value = " select cnac.id, cnac.code, cnac.name, cnac.parent_centric_account_id " +
//            "  from fnac.centric_account cnac " +
//            " inner join fnac.centric_account_type cnat " +
//            "    on cnac.centric_account_type_id = cnat.id " +
//            "   and cnat.deleted_date is null " +
//            " where centric_account_type_id in (:centricAccountTypeIdList) " +
//            "   and cnac.organization_id =:organizationId  " +
//            "   and cnac.deleted_date is null " +
//            " and cnac.deleted_date is null "
//            , nativeQuery = true)
    @Query(value = " SELECT CNAC.ID, CNAC.CODE, CNAC.NAME, CNAC.PARENT_CENTRIC_ACCOUNT_ID" +
            "  FROM fnac.CENTRIC_ACCOUNT CNAC" +
            " INNER JOIN FNAC.CENTRIC_ACCOUNT_TYPE CNAT" +
            "    ON CNAC.CENTRIC_ACCOUNT_TYPE_ID = CNAT.ID" +
            "   AND CNAT.DELETED_DATE IS NULL" +
            " WHERE CENTRIC_ACCOUNT_TYPE_ID IN" +
            "       (SELECT OUTER_R.CENTRIC_ACCOUNT_TYPE_ID" +
            "          FROM fnac.ACCOUNT_RELATION_TYPE_DETAIL OUTER_R" +
            "         INNER JOIN (SELECT INER_R.SEQUENCE, INER_R.ACCOUNT_RELATION_TYPE_ID" +
            "                      FROM fnac.ACCOUNT_RELATION_TYPE_DETAIL INER_R" +
            "                     WHERE INER_R.CENTRIC_ACCOUNT_TYPE_ID =" +
            "                           :centricAccountTypeId " +
            "                       AND INER_R.DELETED_DATE IS NULL) T" +
            "            ON OUTER_R.SEQUENCE <= T.SEQUENCE" +
            "           AND OUTER_R.ACCOUNT_RELATION_TYPE_ID = T.ACCOUNT_RELATION_TYPE_ID" +
            "           AND OUTER_R.DELETED_DATE IS NULL)" +
            "   AND EXISTS (SELECT 1" +
            "          FROM fnac.CENTRIC_ORG_REL INER_ORG_REL" +
            "         WHERE INER_ORG_REL.ORGANIZATION_ID = :organizationId " +
            "           AND INER_ORG_REL.CENTRIC_ACCOUNT_ID = CNAC.ID" +
            "           AND INER_ORG_REL.ACTIVE_FLAG = 1) "
            , nativeQuery = true)
    List<Object[]> findByCentricAccountAndCentricAccountTypeId(Long centricAccountTypeId, Long organizationId);

    @Query(value = "select count(t.id)" +
            "  from fnac.centric_account t" +
            " left join fnac.account_default_value adv" +
            "    on t.id = adv.centric_account_id" +
            " left join bkac.bank_account ba" +
            "   on t.id = ba.centric_account_id" +
            " left join fnac.centric_account ca" +
            "    on t.id = ca.parent_centric_account_id" +
            " left join fnac.centric_person_role cpr" +
            "    on t.id = cpr.centric_account_id" +
            " left join fndc.financial_document_item fdi1" +
            "    on t.id = fdi1.centric_account_id_1" +
            " left join fndc.financial_document_item fdi2" +
            "   on t.id = fdi2.centric_account_id_2" +
            " left join fndc.financial_document_item fdi3" +
            "   on t.id = fdi3.centric_account_id_3" +
            " left join fndc.financial_document_item fdi4" +
            "    on t.id = fdi4.centric_account_id_4" +
            " left join fndc.financial_document_item fdi5" +
            "    on t.id = fdi5.centric_account_id_5" +
            "  left join fndc.financial_document_item fdi6" +
            "    on t.id = fdi6.centric_account_id_6" +
            "  left join rprq.request_item ri1" +
            "  on t.id = ri1.centric_account_id_1" +
            "  left join rprq.request_item ri2" +
            "   on t.id = ri2.centric_account_id_2" +
            "  left join rprq.request_item ri3" +
            "    on t.id = ri3.centric_account_id_3" +
            " left  join rprq.request_item ri4" +
            "    on t.id = ri4.centric_account_id_4" +
            "  left join rprq.request_item ri5" +
            "   on t.id = ri5.centric_account_id_5" +
            " where t.id = :centricAccountId" +
            " and( adv.centric_account_id = :centricAccountId" +
            "   or ba.centric_account_id = :centricAccountId" +
            "   or ca.parent_centric_account_id = :centricAccountId" +
            "   or cpr.centric_account_id = :centricAccountId" +
            "   or fdi1.centric_account_id_1 = :centricAccountId" +
            "   or fdi2.centric_account_id_2 = :centricAccountId" +
            "   or fdi3.centric_account_id_3 = :centricAccountId" +
            "   or fdi4.centric_account_id_4 = :centricAccountId" +
            "   or fdi5.centric_account_id_5 = :centricAccountId" +
            "   or fdi6.centric_account_id_6 = :centricAccountId" +
            "   or ri1.centric_account_id_1 = :centricAccountId" +
            "   or ri2.centric_account_id_2 = :centricAccountId" +
            "   or ri3.centric_account_id_3 = :centricAccountId" +
            "   or ri4.centric_account_id_4 = :centricAccountId" +
            "   or ri5.centric_account_id_5 = :centricAccountId)"
            , nativeQuery = true)
    Long findByCentricAccountIdForDelete(Long centricAccountId);

    @Query(value = " SELECT CNAC.ID," +
            "       CNAC.CODE," +
            "       CNAC.NAME," +
            "       CNAC.ACTIVE_FLAG," +
            "       CNAC.ABBREVIATION_NAME," +
            "       CNAC.LATIN_NAME," +
            "       CNAC.CENTRIC_ACCOUNT_TYPE_ID," +
            "       CNAC.ORGANIZATION_ID," +
            "       CNAC.PERSON_ID," +
            "       CNAT.DESCRIPTION               AS CENTRIC_ACCOUNT_TYPE_DES," +
            "       CNAT.CODE                      AS CENTRIC_ACCOUNT_TYPE_CODE," +
            "       CNAC.PARENT_CENTRIC_ACCOUNT_ID," +
            "       PARENT_C.CODE                  PARENT_CODE," +
            "       PARENT_C.NAME                  PARENT_DESCRIPTION" +
            "  FROM fnac.CENTRIC_ACCOUNT CNAC" +
            " INNER JOIN FNAC.CENTRIC_ACCOUNT_TYPE CNAT" +
            "    ON CNAC.CENTRIC_ACCOUNT_TYPE_ID = CNAT.ID" +
            "  LEFT OUTER JOIN FNAC.CENTRIC_ACCOUNT PARENT_C" +
            "    ON PARENT_C.ID = CNAC.PARENT_CENTRIC_ACCOUNT_ID" +
            " WHERE CNAC.CENTRIC_ACCOUNT_TYPE_ID = :centricAccountTypeId" +
            "   and (:name is null or CNAC.NAME  like %:name%) " +
            "   AND  EXISTS (SELECT 1" +
            "          FROM fnac.CENTRIC_ORG_REL INER_ORG_REL" +
            "         WHERE INER_ORG_REL.ORGANIZATION_ID = :organizationId" +
            "           AND INER_ORG_REL.CENTRIC_ACCOUNT_ID = CNAC.ID" +
            "           AND INER_ORG_REL.ACTIVE_FLAG = 1)"
            , nativeQuery = true)
    Page<Object[]> centricAccountList(Long centricAccountTypeId, String name, Long organizationId
            , Pageable pageable);

    @Query(value = " SELECT CNAC.ID, CNAC.CODE, CNAC.NAME, CNAC.PARENT_CENTRIC_ACCOUNT_ID" +
            "  FROM fnac.CENTRIC_ACCOUNT CNAC" +
            " INNER JOIN FNAC.CENTRIC_ACCOUNT_TYPE CNAT" +
            "    ON CNAC.CENTRIC_ACCOUNT_TYPE_ID = CNAT.ID" +
            "   AND CNAT.DELETED_DATE IS NULL" +
            " WHERE CENTRIC_ACCOUNT_TYPE_ID = :centricAccountTypeId" +
            "   AND CNAC.DELETED_DATE IS NULL" +
            "   and (:parentCentricAccount is null or " +
            " CNAC.PARENT_CENTRIC_ACCOUNT_ID = :parentCentricAccountId)" +
            "   AND EXISTS (SELECT 1" +
            "          FROM fnac.CENTRIC_ORG_REL INER_ORG_REL" +
            "         WHERE INER_ORG_REL.ORGANIZATION_ID = :organizationId " +
            "           AND INER_ORG_REL.CENTRIC_ACCOUNT_ID = CNAC.ID" +
            "           AND INER_ORG_REL.ACTIVE_FLAG = 1)"
            , nativeQuery = true)
    Page<Object[]> findByCentricAccountAndCentricAccountTypeAndParentCentricAccountAndOrganization(Long centricAccountTypeId, Object parentCentricAccount, Long parentCentricAccountId, Long organizationId
            , Pageable pageable);
}

