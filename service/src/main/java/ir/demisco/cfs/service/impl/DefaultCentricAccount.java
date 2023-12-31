package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.request.CentricAccountGetRequest;
import ir.demisco.cfs.model.dto.request.CentricAccountNewTypeRequest;
import ir.demisco.cfs.model.dto.request.CentricAccountParamRequest;
import ir.demisco.cfs.model.dto.request.CentricAccountRequest;
import ir.demisco.cfs.model.dto.response.CentricAccountDto;
import ir.demisco.cfs.model.dto.response.CentricAccountListResponse;
import ir.demisco.cfs.model.dto.response.CentricAccountNewResponse;
import ir.demisco.cfs.model.dto.response.CentricAccountOutPutResponse;
import ir.demisco.cfs.model.dto.response.CentricAccountResponse;
import ir.demisco.cfs.model.dto.response.PersonRoleTypeDto;
import ir.demisco.cfs.model.entity.CentricAccount;
import ir.demisco.cfs.model.entity.CentricOrgRel;
import ir.demisco.cfs.model.entity.CentricPersonRole;
import ir.demisco.cfs.service.api.CentricAccountService;
import ir.demisco.cfs.service.repository.CentricAccountRepository;
import ir.demisco.cfs.service.repository.CentricAccountTypeRepository;
import ir.demisco.cfs.service.repository.CentricOrgRelRepository;
import ir.demisco.cfs.service.repository.CentricPersonRoleRepository;
import ir.demisco.cfs.service.repository.OrganizationRepository;
import ir.demisco.cfs.service.repository.PersonRepository;
import ir.demisco.cfs.service.repository.PersonRoleTypeRepository;
import ir.demisco.cloud.core.middle.exception.RuleException;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import ir.demisco.cloud.core.security.util.SecurityHelper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;


@Service
public class DefaultCentricAccount implements CentricAccountService {
    private final CentricAccountRepository centricAccountRepository;
    private final OrganizationRepository organizationRepository;
    private final PersonRepository personRepository;
    private final PersonRoleTypeRepository personRoleTypeRepository;
    private final CentricPersonRoleRepository centricPersonRoleRepository;
    private final CentricAccountTypeRepository centricAccountTypeRepository;
    private final CentricOrgRelRepository centricOrgRelRepository;
    private final EntityManager entityManager;

    public DefaultCentricAccount(CentricAccountRepository centricAccountRepository, OrganizationRepository organizationRepository, PersonRepository personRepository, PersonRoleTypeRepository personRoleTypeRepository, CentricPersonRoleRepository centricPersonRoleRepository1, CentricAccountTypeRepository centricAccountTypeRepository2, CentricOrgRelRepository centricOrgRelRepository, EntityManager entityManager) {
        this.centricAccountRepository = centricAccountRepository;
        this.organizationRepository = organizationRepository;
        this.personRepository = personRepository;
        this.personRoleTypeRepository = personRoleTypeRepository;
        this.centricPersonRoleRepository = centricPersonRoleRepository1;
        this.centricAccountTypeRepository = centricAccountTypeRepository2;
        this.centricOrgRelRepository = centricOrgRelRepository;
        this.entityManager = entityManager;
    }

    private Long getItemForLong(Object[] item, int i) {
        return item[i] == null ? null : Long.parseLong(item[i].toString());
    }

    private String getItemForString(Object[] item, int i) {
        return item[i] == null ? null : item[i].toString();
    }

    private CentricAccountParamRequest setParameterCentricAccount(List<DataSourceRequest.FilterDescriptor> filters) {
        CentricAccountParamRequest centricAccountParamRequest = new CentricAccountParamRequest();
        for (DataSourceRequest.FilterDescriptor item : filters) {
            switch (item.getField()) {
                case "centricAccountType.id":
                    centricAccountParamRequest.setCentricAccountTypeId(Long.parseLong(item.getValue().toString()));
                    break;

                case "name":
                    checkNameList(centricAccountParamRequest, item);
                    break;

                case "code":
                    checkCodeList(centricAccountParamRequest, item);
                    break;

                default:
                    break;
            }
        }
        return centricAccountParamRequest;
    }

    private void checkNameList(CentricAccountParamRequest
                                       centricAccountParamRequest, DataSourceRequest.FilterDescriptor item) {
        if (item.getValue() != null) {
            centricAccountParamRequest.setName(item.getValue().toString());
        } else {
            centricAccountParamRequest.setName("");
        }
    }

    private void checkCodeList(CentricAccountParamRequest
                                       centricAccountParamRequest, DataSourceRequest.FilterDescriptor item) {
        if (item.getValue() != null) {
            centricAccountParamRequest.setCode(item.getValue().toString());
        } else {
            centricAccountParamRequest.setCode("");
        }
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public CentricAccountDto save(CentricAccountRequest centricAccountRequest) {
        CentricAccount centricAccount = centricAccountRepository.findById(centricAccountRequest.getId() == null ? 0L : centricAccountRequest.getId()).orElse(new CentricAccount());
        if (centricAccount.getId() == null) {
            Long centricAccountUniqueCount = centricAccountRepository.getCountByCentricAccountAndOrganizationAndCentricAccountTypeAndCode(SecurityHelper.getCurrentUser().getOrganizationId(), centricAccountRequest.getCentricAccountTypeId(), centricAccountRequest.getCode());
            if (centricAccountUniqueCount > 0) {
                throw new RuleException("fin.centricAccountUnique.save");
            }
        }
        if (centricAccount.getId() != null) {
            Long countCentric = centricAccountRepository.getCentricAccountByCodeAndName(centricAccountRequest.getId(), centricAccountRequest.getCode(), centricAccountRequest.getName());
            if (countCentric != null) {
                List<Long> countCentricAccountId = centricAccountRepository.findByCentricById(centricAccountRequest.getId());
                if (countCentricAccountId.isEmpty()) {
                    entityManager.createNativeQuery(" update fnac.centric_account ca" +
                            "   set   ca.code = :code , ca.name = :name " +
                            "   WHERE ca.ID = :centricAccountId ")
                            .setParameter("code", centricAccountRequest.getCode())
                            .setParameter("name", centricAccountRequest.getName())
                            .setParameter("centricAccountId", centricAccountRequest.getId()).executeUpdate();
                } else {
                    throw new RuleException("امکان تغییر کد/ نام تمرکز به دلیل استفاده در اسناد وجود ندارد");
                }
            }
        }

        if (centricAccount.getId() != null && centricAccountRequest.getCentricAccountTypeCode().equals("10")) {
            entityManager.createNativeQuery(" delete fnac.centric_person_role  pr where pr.centric_account_id=:centricAccountId and not exists(" +
                    " select 1 " +
                    "  from fnac.centric_person_role t" +
                    " inner join fnac.account_relation_type_detail dt" +
                    "    on dt.person_role_type_id = t.person_role_type_id" +
                    "   and t.centric_account_id = :centricAccountId " +
                    "   and t.person_role_type_id=pr.person_role_type_id" +
                    " inner join fnac.account_relation_type rel" +
                    "    on rel.id = dt.account_relation_type_id" +
                    " inner join fnac.financial_account fn" +
                    "    on fn.account_relation_type_id = rel.id" +
                    " where exists (select 1" +
                    "          from fndc.financial_document_item di" +
                    "         where di.centric_account_id_1 = t.centric_account_id" +
                    "            or di.centric_account_id_2 =  t.centric_account_id" +
                    "            or di.centric_account_id_3 =  t.centric_account_id" +
                    "            or di.centric_account_id_4 =  t.centric_account_id" +
                    "            or di.centric_account_id_5 =  t.centric_account_id" +
                    "            or di.centric_account_id_6 =  t.centric_account_id)) ").setParameter("centricAccountId", centricAccount.getId()).executeUpdate();
            CentricAccount finalCentricAccount = centricAccount;
            centricAccountRequest.getCentricPersonRoleListId().forEach((Long aLong) -> {
                CentricPersonRole centricPersonRole = new CentricPersonRole();
                List<Long> count = centricPersonRoleRepository.findByCentricPersonRoleByIdAndCentricId(finalCentricAccount.getId(), aLong.longValue());
                if (count.isEmpty()) {
                    centricPersonRole.setCentricAccount(finalCentricAccount);
                    centricPersonRole.setPersonRoleType(personRoleTypeRepository.getOne(aLong));
                    centricPersonRoleRepository.save(centricPersonRole);
                }
            });
            centricAccount.setActiveFlag(centricAccountRequest.getActiveFlag());
        } else {
            centricAccount = saveCentricAccount(centricAccount, centricAccountRequest);
            CentricAccount finalCentricAccount = centricAccount;
            centricAccountRequest.getCentricPersonRoleListId().forEach((Long aLong) -> {
                CentricPersonRole centricPersonRole = new CentricPersonRole();
                centricPersonRole.setCentricAccount(finalCentricAccount);
                centricPersonRole.setPersonRoleType(personRoleTypeRepository.getOne(aLong));
                centricPersonRoleRepository.save(centricPersonRole);
            });
        }
        saveCentricAccount(centricAccount, centricAccountRequest);
        return convertCentricAccountToDto(centricAccount);

    }


    @Override
    @Transactional(rollbackOn = Throwable.class)
    public Boolean deleteCentricAccountById(Long centricAccountId) {
        List<Long> centricAccountCount = centricAccountRepository.findByCentricById(centricAccountId);
        if (centricAccountCount.isEmpty()) {
            List<CentricOrgRel> centricOrgRelForDelete = centricOrgRelRepository.findByCentricAccountId(centricAccountId);
            if (!centricOrgRelForDelete.isEmpty()) {
                centricOrgRelForDelete.forEach(centricOrgRel -> centricOrgRelRepository.deleteById(centricOrgRel.getId()));
            }
            List<CentricPersonRole> centricPersonRoles = centricPersonRoleRepository.findByCentricAccountId(centricAccountId);

            if (!centricPersonRoles.isEmpty()) {
                centricPersonRoles.forEach(centricPersonRole -> centricPersonRoleRepository.deleteById(centricPersonRole.getId()));
            }
            CentricAccount centricAccount;
            centricAccount = centricAccountRepository.findById(centricAccountId).orElseThrow(() -> new RuleException("fin.ruleException.notFoundId"));
            Long accountIdForDelete = centricAccountRepository.findByCentricAccountIdForDelete(centricAccount.getId());
            if (accountIdForDelete > 0) {
                throw new RuleException("fin.centricAccount.check.for.delete");
            } else {
                centricAccountRepository.deleteById(centricAccount.getId());
                return true;
            }
        } else {
            throw new RuleException("امکان حذف تمرکز به دلیل استفاده در اسناد وجود ندارد");
        }

    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public Boolean getCentricAccountByOrganIdAndPersonId(Long personId, Long organizationId) {
        Long centricAccounts = centricAccountRepository.findByCentricAccountAndOrganizationAndPerson(personId, SecurityHelper.getCurrentUser().getOrganizationId());
        return centricAccounts == null;
    }

    @Override
    @Transactional
    public CentricAccountOutPutResponse getCentricAccountGetById(Long centricAccountId) {
        CentricAccount centricAccount = centricAccountRepository.findById(centricAccountId).orElseThrow(() -> new RuleException("fin.ruleException.notFoundId"));
        CentricAccountOutPutResponse centricAccountOutPutResponse = CentricAccountOutPutResponse.builder().id(centricAccountId)
                .code(centricAccount.getCode())
                .name(centricAccount.getName())
                .abbreviationName(centricAccount.getAbbreviationName())
                .latinName(centricAccount.getLatinName())
                .centricAccountTypeId(centricAccount.getCentricAccountType().getId())
                .centricAccountTypeDescription(centricAccount.getCentricAccountType().getDescription())
                .organizationId(SecurityHelper.getCurrentUser().getOrganizationId())
                .personId(centricAccount.getPerson() == null ? null : centricAccount.getPerson().getId())
                .personName(centricAccount.getPerson() == null ? "" : centricAccount.getPerson().getPersonName())
                .activeFlag(centricAccount.getActiveFlag())
                .parentCentricAccountId(centricAccount.getParentCentricAccount() == null ? null : centricAccount.getParentCentricAccount().getId())
                .parentCentricAccountCode(centricAccount.getParentCentricAccount() == null ? "" : centricAccount.getParentCentricAccount().getCode())
                .parentCentricAccountName(centricAccount.getParentCentricAccount() == null ? "" : centricAccount.getParentCentricAccount().getName())
                .centricAccountTypeCode(centricAccount.getCentricAccountType().getCode())
                .build();
        centricAccountOutPutResponse.setPersonRoleTypeOutPutModel(personRoleTypeResponses(centricAccountId));
        return centricAccountOutPutResponse;
    }

    private List<PersonRoleTypeDto> personRoleTypeResponses(Long centricAccountId) {
        List<Object[]> personRoleTypeListObject = personRoleTypeRepository.findByPersonRoleTypeListObject(centricAccountId);
        return personRoleTypeListObject.stream().map(objects -> PersonRoleTypeDto.builder().id(Long.parseLong(objects[0].toString()))
                .description(objects[1].toString())
                .flagExist(Long.parseLong(objects[2].toString())).build()).collect(Collectors.toList());
    }

    private CentricAccountDto convertCentricAccountToDto(CentricAccount centricAccount) {
        return CentricAccountDto.builder()
                .id(centricAccount.getId())
                .code(centricAccount.getCode())
                .name(centricAccount.getName())
                .abbreviationName(centricAccount.getAbbreviationName())
                .latinName(centricAccount.getLatinName())
                .centricAccountTypeId(centricAccount.getCentricAccountType() == null ? null : centricAccount.getCentricAccountType().getId())
                .centricAccountTypeDescription(centricAccount.getCentricAccountType() == null ? "" : centricAccount.getCentricAccountType().getDescription())
                .centricAccountTypeCode(centricAccount.getCentricAccountType() == null ? "" : centricAccount.getCentricAccountType().getCode())
                .organizationId(centricAccount.getOrganization() == null ? null : centricAccount.getOrganization().getId())
                .personId(centricAccount.getPerson() == null ? null : centricAccount.getPerson().getId())
                .personName(centricAccount.getPerson() == null ? "" : centricAccount.getPerson().getPersonName())
                .activeFlag(centricAccount.getActiveFlag())
                .parentCentricAccountId(centricAccount.getParentCentricAccount() == null ? null : centricAccount.getParentCentricAccount().getId())
                .parentCentricAccountCode(centricAccount.getParentCentricAccount() == null ? "" : centricAccount.getParentCentricAccount().getCode())
                .parentCentricAccountName(centricAccount.getParentCentricAccount() == null ? "" : centricAccount.getParentCentricAccount().getName())
                .build();
    }

    private CentricAccount saveCentricAccount(CentricAccount centricAccount, CentricAccountRequest
            centricAccountRequest) {
        centricAccount.setCode(centricAccountRequest.getCode());
        centricAccount.setName(centricAccountRequest.getName());
        centricAccount.setCentricAccountType(centricAccountTypeRepository.getOne(centricAccountRequest.getCentricAccountTypeId()));
        centricAccount.setCentricAccountType(centricAccountTypeRepository.findByCentricAccountTypeCode(centricAccountRequest.getCentricAccountTypeCode()));
        centricAccount.setOrganization(organizationRepository.getOne(SecurityHelper.getCurrentUser().getOrganizationId()));
        if (centricAccountRequest.getPersonId() != null) {
            centricAccount.setPerson(personRepository.getOne(centricAccountRequest.getPersonId()));
        }
        centricAccount.setActiveFlag(centricAccountRequest.getActiveFlag());
        if (centricAccountRequest.getParentCentricAccountId() != null) {
            centricAccount.setParentCentricAccount(centricAccountRepository.getOne(centricAccountRequest.getParentCentricAccountId()));
        }
        return centricAccountRepository.save(centricAccount);
    }


    @Override
    @Transactional
    public DataSourceResult getCentricAccountByOrganizationIdAndCentricAccountTypeId(DataSourceRequest
                                                                                             dataSourceRequest) {
        List<DataSourceRequest.FilterDescriptor> filters = dataSourceRequest.getFilter().getFilters();
        CentricAccountGetRequest param = setCentricAccountGet(filters);
        Map<String, Object> paramMap = param.getParamMap();
        List<Object[]> list = getCentricAccountGet(param, paramMap);
        List<CentricAccountResponse> centricAccountResponseList = getCentricAccountResponse(list);
        DataSourceResult dataSourceResult = new DataSourceResult();
        dataSourceResult.setData(centricAccountResponseList.stream().limit(dataSourceRequest.getTake() + dataSourceRequest.getSkip()).skip(dataSourceRequest.getSkip()).collect(Collectors.toList()));
        dataSourceResult.setTotal(list.size());
        return dataSourceResult;
    }

    private List<CentricAccountResponse> getCentricAccountResponse(List<Object[]> list) {
        return list.stream().map(item ->
                CentricAccountResponse.builder()
                        .id(Long.parseLong(item[0].toString()))
                        .code(item[1] == null ? null : item[1].toString())
                        .name(item[2].toString())
                        .parentCentricAccountId(item[3] == null ? null : Long.parseLong(item[3].toString()))
                        .build()).collect(Collectors.toList());
    }

    private List<Object[]> getCentricAccountGet(CentricAccountGetRequest
                                                        centricAccountGetRequest, Map<String, Object> centricAccountGetParamMap) {
        return centricAccountRepository.findByCentricAccountAndCentricAccountTypeAndParentCentricAccountAndOrganization(
                centricAccountGetRequest.getCentricAccountTypeId(), centricAccountGetParamMap.get("parentCentricAccount"), centricAccountGetRequest.getParentCentricAccountId(), centricAccountGetRequest.getName(), centricAccountGetRequest.getCode(), SecurityHelper.getCurrentUser().getOrganizationId());
    }

    private CentricAccountGetRequest setCentricAccountGet(List<DataSourceRequest.FilterDescriptor> filters) {
        CentricAccountGetRequest centricAccountGetRequest = new CentricAccountGetRequest();
        for (DataSourceRequest.FilterDescriptor item : filters) {
            switch (item.getField()) {

                case "centricAccountType.id":
                    centricAccountGetRequest.setCentricAccountTypeId(Long.parseLong(item.getValue().toString()));
                    break;

                case "parentCentricAccount.id":
                    checkParentCentricAccount(centricAccountGetRequest, item);
                    break;

                case "name":
                    checkName(centricAccountGetRequest, item);
                    break;

                case "code":
                    checkCode(centricAccountGetRequest, item);
                    break;
                default:
                    break;
            }
        }
        return centricAccountGetRequest;
    }

    private void checkName(CentricAccountGetRequest
                                   centricAccountGetRequest, DataSourceRequest.FilterDescriptor item) {
        if (item.getValue() != null) {
            centricAccountGetRequest.setName(item.getValue().toString());
        } else {
            centricAccountGetRequest.setName("");
        }
    }

    private void checkCode(CentricAccountGetRequest
                                   centricAccountGetRequest, DataSourceRequest.FilterDescriptor item) {
        if (item.getValue() != null) {
            centricAccountGetRequest.setCode(item.getValue().toString());
        } else {
            centricAccountGetRequest.setCode("");
        }
    }

    private void checkParentCentricAccount(CentricAccountGetRequest
                                                   centricAccountGetRequest, DataSourceRequest.FilterDescriptor item) {
        Map<String, Object> map = new HashMap<>();
        if (item.getValue() != null) {
            map.put("parentCentricAccount", "parentCentricAccount");
            centricAccountGetRequest.setParamMap(map);
            centricAccountGetRequest.setParentCentricAccountId(Long.parseLong(item.getValue().toString()));
        } else {
            map.put("parentCentricAccount", null);
            centricAccountGetRequest.setParamMap(map);
            centricAccountGetRequest.setParentCentricAccountId(0L);
        }
    }


    @Override
    @Transactional
    public DataSourceResult getCentricAccountByOrganizationIdAndPersonAndName(DataSourceRequest dataSourceRequest) {
        List<DataSourceRequest.FilterDescriptor> filters = dataSourceRequest.getFilter().getFilters();
        CentricAccountParamRequest paramSearch = setParameterCentricAccount(filters);
        List<String> sorts = new ArrayList<>();
        AtomicReference<Sort.Direction> direction = new AtomicReference<>();
        dataSourceRequest.getSort()
                .forEach((DataSourceRequest.SortDescriptor sortDescriptor) ->
                        {
                            String field = sortDescriptor.getField();

                            if (sortDescriptor.getField().equals("code")) {
                                sorts.add("to_number(" + sortDescriptor.getField() + ") ");
                            }
                            checkFields(field, sorts, sortDescriptor);
                            checkSort(sortDescriptor, direction);
                        }
                );
        if (dataSourceRequest.getSort().size() == 0) {
            List<Sort.Order> sorts1 = new ArrayList<>();
            Pageable pageable1 = PageRequest.of((dataSourceRequest.getSkip() / dataSourceRequest.getTake()), dataSourceRequest.getTake(), Sort.by(sorts1));
            Page<Object[]> list1 = centricAccountRepository.centricAccountList(paramSearch.getCentricAccountTypeId(), paramSearch.getName(), paramSearch.getCode(), SecurityHelper.getCurrentUser().getOrganizationId(), pageable1);
            List<CentricAccountListResponse> centricAccountResponseList = list1.stream().map(item ->
                    CentricAccountListResponse.builder()
                            .id(Long.parseLong(item[0].toString()))
                            .code(getItemForString(item, 1))
                            .name(item[2].toString())
                            .activeFlag(getItemForLong(item, 3))
                            .abbreviationName(getItemForString(item, 4))
                            .latinName(getItemForString(item, 5))
                            .centricAccountTypeId(getItemForLong(item, 6))
                            .organizationId(getItemForLong(item, 7))
                            .personId(getItemForLong(item, 8))
                            .centricAccountTypeDescription(getItemForString(item, 9))
                            .centricAccountTypeCode(item[10] == null ? null : item[10].toString())
                            .parentCentricAccountId(item[11] == null ? null : Long.parseLong(item[11].toString()))
                            .parentCentricAccountCode(item[12] == null ? null : (item[12].toString()))
                            .parentCentricAccountName(item[13] == null ? null : (item[13].toString()))
                            .build()).collect(Collectors.toList());
            DataSourceResult dataSourceResult = new DataSourceResult();
            dataSourceResult.setData(centricAccountResponseList);
            dataSourceResult.setTotal(list1.getTotalElements());
            return dataSourceResult;
        } else {
            Pageable pageable = PageRequest.of((dataSourceRequest.getSkip() / dataSourceRequest.getTake()), dataSourceRequest.getTake(), JpaSort.unsafe(direction.get(), String.valueOf(sorts).replace("[", "").replace("]", "")));
            Page<Object[]> list = centricAccountRepository.centricAccountList(paramSearch.getCentricAccountTypeId(), paramSearch.getName(), paramSearch.getCode(), SecurityHelper.getCurrentUser().getOrganizationId(), pageable);
            List<CentricAccountListResponse> centricAccountResponseList = list.stream().map(item ->
                    CentricAccountListResponse.builder()
                            .id(Long.parseLong(item[0].toString()))
                            .code(getItemForString(item, 1))
                            .name(item[2].toString())
                            .activeFlag(getItemForLong(item, 3))
                            .abbreviationName(getItemForString(item, 4))
                            .latinName(getItemForString(item, 5))
                            .centricAccountTypeId(getItemForLong(item, 6))
                            .organizationId(getItemForLong(item, 7))
                            .personId(getItemForLong(item, 8))
                            .centricAccountTypeDescription(getItemForString(item, 9))
                            .centricAccountTypeCode(getItemForString(item, 10))
                            .parentCentricAccountId(item[11] == null ? null : Long.parseLong(item[11].toString()))
                            .parentCentricAccountCode(getItemForString(item, 12))
                            .parentCentricAccountName(getItemForString(item, 13))
                            .build()).collect(Collectors.toList());
            DataSourceResult dataSourceResult = new DataSourceResult();
            dataSourceResult.setData(centricAccountResponseList);
            dataSourceResult.setTotal(list.getTotalElements());
            return dataSourceResult;
        }
    }

    private void checkSort(DataSourceRequest.SortDescriptor
                                   sortDescriptor, AtomicReference<Sort.Direction> direction) {
        if (sortDescriptor.getDir().equals("asc")) {
            direction.set(Sort.Direction.ASC);
        } else {
            direction.set(Sort.Direction.DESC);
        }
    }

    private void checkFields(String field, List<String> sorts, DataSourceRequest.SortDescriptor sortDescriptor) {
        if (field.equals("name") || field.equals("parentCentricAccountName")) {
            sorts.add(sortDescriptor.getField());
        }
    }

    @Override
    @Transactional
    public DataSourceResult getCentricAccountByOrganIdAndCentricAccountTypeId(DataSourceRequest dataSourceRequest) {
        List<DataSourceRequest.FilterDescriptor> filters = dataSourceRequest.getFilter().getFilters();
        CentricAccountNewTypeRequest param = setCentricAccountGetByTypeId(filters);
        List<Object[]> list = getCentricAccountGetByTypeId(param);
        List<CentricAccountNewResponse> centricAccountNewResponseList = getCentricAccountNewResponse(list);
        DataSourceResult dataSourceResult = new DataSourceResult();
        dataSourceResult.setData(centricAccountNewResponseList.stream().limit(dataSourceRequest.getTake() + dataSourceRequest.getSkip()).skip(dataSourceRequest.getSkip()).collect(Collectors.toList()));
        dataSourceResult.setTotal(list.size());
        return dataSourceResult;
    }

    private CentricAccountNewTypeRequest setCentricAccountGetByTypeId
            (List<DataSourceRequest.FilterDescriptor> filters) {
        CentricAccountNewTypeRequest centricAccountNewTypeRequest = new CentricAccountNewTypeRequest();
        Map<String, Object> map = new HashMap<>();
        for (DataSourceRequest.FilterDescriptor item : filters) {
            switch (item.getField()) {

                case "centricAccountType.id":
                    centricAccountNewTypeRequest.setCentricAccountTypeId(Long.parseLong(item.getValue().toString()));
                    centricAccountNewTypeRequest.setParamMap(map);
                    break;
                case "name":
                    checkName(centricAccountNewTypeRequest, item);
                    break;
                case "code":
                    checkCode(centricAccountNewTypeRequest, item);
                    break;
                default:
                    break;
            }
        }
        return centricAccountNewTypeRequest;
    }

    private void checkName(CentricAccountNewTypeRequest
                                   centricAccountNewTypeRequest, DataSourceRequest.FilterDescriptor item) {
        if (item.getValue() != null) {
            centricAccountNewTypeRequest.setName(item.getValue().toString());
        } else {
            centricAccountNewTypeRequest.setName("");
        }
    }

    private void checkCode(CentricAccountNewTypeRequest
                                   centricAccountNewTypeRequest, DataSourceRequest.FilterDescriptor item) {
        if (item.getValue() != null) {
            centricAccountNewTypeRequest.setCode(item.getValue().toString());
        } else {
            centricAccountNewTypeRequest.setCode("");
        }
    }

    private List<Object[]> getCentricAccountGetByTypeId(CentricAccountNewTypeRequest centricAccountNewTypeRequest) {
        return centricAccountRepository.findByCentricAccountAndCentricAccountTypeId(
                centricAccountNewTypeRequest.getCentricAccountTypeId(), SecurityHelper.getCurrentUser().getOrganizationId(), centricAccountNewTypeRequest.getCode(), centricAccountNewTypeRequest.getName());
    }

    private List<CentricAccountNewResponse> getCentricAccountNewResponse(List<Object[]> list) {
        return list.stream().map(item ->
                CentricAccountNewResponse.builder()
                        .id(Long.parseLong(item[0].toString()))
                        .code(item[1] == null ? null : item[1].toString())
                        .name(item[2].toString())
                        .build()).collect(Collectors.toList());
    }
}