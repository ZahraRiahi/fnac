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
import ir.demisco.cloud.core.middle.service.business.api.core.GridFilterService;
import ir.demisco.cloud.core.security.util.SecurityHelper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class DefaultCentricAccount implements CentricAccountService {
    private final GridFilterService gridFilterService;
    private final CentricAccountRepository centricAccountRepository;
    private final OrganizationRepository organizationRepository;
    private final PersonRepository personRepository;
    private final PersonRoleTypeRepository personRoleTypeRepository;
    private final CentricPersonRoleRepository centricPersonRoleRepository;
    private final CentricAccountTypeRepository centricAccountTypeRepository;
    private final CentricOrgRelRepository centricOrgRelRepository;
    private final CentricAccountListGridProvider centricAccountListGridProvider;

    public DefaultCentricAccount(GridFilterService gridFilterService, CentricAccountRepository centricAccountRepository, OrganizationRepository organizationRepository, PersonRepository personRepository, PersonRoleTypeRepository personRoleTypeRepository, CentricPersonRoleRepository centricPersonRoleRepository1, CentricAccountTypeRepository centricAccountTypeRepository2, CentricOrgRelRepository centricOrgRelRepository, CentricAccountListGridProvider centricAccountListGridProvider) {
        this.gridFilterService = gridFilterService;
        this.centricAccountRepository = centricAccountRepository;
        this.organizationRepository = organizationRepository;
        this.personRepository = personRepository;
        this.personRoleTypeRepository = personRoleTypeRepository;
        this.centricPersonRoleRepository = centricPersonRoleRepository1;
        this.centricAccountTypeRepository = centricAccountTypeRepository2;
        this.centricOrgRelRepository = centricOrgRelRepository;
        this.centricAccountListGridProvider = centricAccountListGridProvider;
    }

    private List<Object[]> getCentricAccountList(CentricAccountParamRequest centricAccountParamRequest, Map<String, Object> centricAccountListParamMap) {
        return centricAccountRepository.centricAccountList(centricAccountParamRequest.getCentricAccountTypeId(), centricAccountParamRequest.getName(), SecurityHelper.getCurrentUser().getOrganizationId());
    }

    private List<CentricAccountListResponse> getCentricAccountResponseList(List<Object[]> list) {
        return list.stream().map(item ->
                CentricAccountListResponse.builder()
                        .id(Long.parseLong(item[0].toString()))
                        .code(gatItemForString(item, 1))
                        .name(item[2].toString())
                        .activeFlag(getItemForLong(item, 3))
                        .abbreviationName(gatItemForString(item, 4))
                        .latinName(gatItemForString(item, 5))
                        .centricAccountTypeId(getItemForLong(item, 6))
                        .organizationId(getItemForLong(item, 7))
                        .personId(getItemForLong(item, 8))
                        .centricAccountTypeDescription(gatItemForString(item, 9))
                        .centricAccountTypeCode(item[10] == null ? null : item[10].toString())
                        .parentCentricAccountId(item[11] == null ? null : Long.parseLong(item[11].toString()))
                        .parentCentricAccountCode(item[12] == null ? null : (item[12].toString()))
                        .parentCentricAccountName(item[13] == null ? null : (item[13].toString()))
                        .build()).collect(Collectors.toList());
    }

    private Long getItemForLong(Object[] item, int i) {
        return item[i] == null ? null : Long.parseLong(item[i].toString());
    }

    private String gatItemForString(Object[] item, int i) {
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
                    if (item.getValue() != null) {
                        centricAccountParamRequest.setName(item.getValue().toString());
                    } else {
                        centricAccountParamRequest.setName("");
                    }
                    break;
                default:
                    break;
            }
        }
        return centricAccountParamRequest;
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
        if (centricAccountRequest.getCentricAccountTypeCode().equals("10")) {
            if (centricAccount.getId() != null) {
                List<CentricPersonRole> centricPersonRoles = centricPersonRoleRepository.findByCentricAccountId(centricAccount.getId());
                centricPersonRoles.forEach(e -> e.setDeletedDate(LocalDateTime.now()));
                CentricAccount finalCentricAccount = centricAccount;
                centricAccountRequest.getCentricPersonRoleListId().forEach(aLong -> {
                    CentricPersonRole centricPersonRole = new CentricPersonRole();
                    centricPersonRole.setCentricAccount(finalCentricAccount);
                    centricPersonRole.setPersonRoleType(personRoleTypeRepository.getOne(aLong));
                    centricPersonRoleRepository.save(centricPersonRole);
                });
                centricAccount.setActiveFlag(centricAccountRequest.getActiveFlag());
            } else {
                centricAccount = saveCentricAccount(centricAccount, centricAccountRequest);
                CentricAccount finalCentricAccount = centricAccount;
                centricAccountRequest.getCentricPersonRoleListId().forEach(aLong -> {
                    CentricPersonRole centricPersonRole = new CentricPersonRole();
                    centricPersonRole.setCentricAccount(finalCentricAccount);
                    centricPersonRole.setPersonRoleType(personRoleTypeRepository.getOne(aLong));
                    centricPersonRoleRepository.save(centricPersonRole);
                });
            }
        } else {
            centricAccount = saveCentricAccount(centricAccount, centricAccountRequest);
        }
        return convertCentricAccountToDto(centricAccount);
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public Boolean deleteCentricAccountById(Long centricAccountId) {
        Long centricOrgRelForDelete = centricOrgRelRepository.findByCentricAccountIdForDelete(centricAccountId, SecurityHelper.getCurrentUser().getOrganizationId());
        if (centricOrgRelForDelete != null) {
            centricOrgRelRepository.deleteById(centricOrgRelForDelete);
        }
        List<CentricPersonRole> centricPersonRoles = centricPersonRoleRepository.findByCentricAccountId(centricAccountId);
        CentricAccount centricAccount;
        if (!centricPersonRoles.isEmpty()) {
            centricPersonRoles.forEach(centricPersonRole -> centricPersonRoleRepository.deleteById(centricPersonRole.getId()));
        }
        centricAccount = centricAccountRepository.findById(centricAccountId).orElseThrow(() -> new RuleException("fin.ruleException.notFoundId"));
        Long accountIdForDelete = centricAccountRepository.findByCentricAccountIdForDelete(centricAccount.getId());
        if (accountIdForDelete > 0) {
            throw new RuleException("fin.centricAccount.check.for.delete");
        } else {
            centricAccountRepository.deleteById(centricAccount.getId());
            return true;
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
                .centricAccountTypeId(centricAccount.getCentricAccountType().getId())
                .centricAccountTypeDescription(centricAccount.getCentricAccountType().getDescription())
                .centricAccountTypeCode(centricAccount.getCentricAccountType().getCode())
                .organizationId(centricAccount.getOrganization().getId())
                .personId(centricAccount.getPerson() == null ? null : centricAccount.getPerson().getId())
                .personName(centricAccount.getPerson() == null ? "" : centricAccount.getPerson().getPersonName())
                .activeFlag(centricAccount.getActiveFlag())
                .parentCentricAccountId(centricAccount.getParentCentricAccount() == null ? null : centricAccount.getParentCentricAccount().getId())
                .parentCentricAccountCode(centricAccount.getParentCentricAccount() == null ? "" : centricAccount.getParentCentricAccount().getCode())
                .parentCentricAccountName(centricAccount.getParentCentricAccount() == null ? "" : centricAccount.getParentCentricAccount().getName())
                .build();
    }

    private CentricAccount saveCentricAccount(CentricAccount centricAccount, CentricAccountRequest centricAccountRequest) {

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
    public DataSourceResult getCentricAccountByOrganizationIdAndCentricAccountTypeId(DataSourceRequest dataSourceRequest) {
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

    private List<Object[]> getCentricAccountGet(CentricAccountGetRequest centricAccountGetRequest, Map<String, Object> centricAccountGetParamMap) {
        return centricAccountRepository.findByCentricAccountAndCentricAccountTypeAndParentCentricAccountAndOrganization(
                centricAccountGetRequest.getCentricAccountTypeId(), centricAccountGetParamMap.get("parentCentricAccount"), centricAccountGetRequest.getParentCentricAccountId(), centricAccountGetRequest.getName(), centricAccountGetRequest.getCode(), SecurityHelper.getCurrentUser().getOrganizationId());
    }

    private CentricAccountGetRequest setCentricAccountGet(List<DataSourceRequest.FilterDescriptor> filters) {
        CentricAccountGetRequest centricAccountGetRequest = new CentricAccountGetRequest();
        Map<String, Object> map = new HashMap<>();
        for (DataSourceRequest.FilterDescriptor item : filters) {
            switch (item.getField()) {

                case "centricAccountType.id":
                    centricAccountGetRequest.setCentricAccountTypeId(Long.parseLong(item.getValue().toString()));
                    break;

                case "parentCentricAccount.id":
                    if (item.getValue() != null) {
                        map.put("parentCentricAccount", "parentCentricAccount");
                        centricAccountGetRequest.setParamMap(map);
                        centricAccountGetRequest.setParentCentricAccountId(Long.parseLong(item.getValue().toString()));
                    } else {
                        map.put("parentCentricAccount", null);
                        centricAccountGetRequest.setParamMap(map);
                        centricAccountGetRequest.setParentCentricAccountId(0L);
                    }
                    break;

                case "name":
                    if (item.getValue() != null) {
                        centricAccountGetRequest.setName(item.getValue().toString());
                    } else {
                        centricAccountGetRequest.setName("");
                    }
                    break;

                case "code":
                    if (item.getValue() != null) {
                        centricAccountGetRequest.setCode(item.getValue().toString());
                    } else {
                        centricAccountGetRequest.setCode("");
                    }
                    break;
                default:
                    break;
            }
        }
        return centricAccountGetRequest;
    }

    @Override
    @Transactional
    public DataSourceResult getCentricAccountByOrganizationIdAndPersonAndName(DataSourceRequest dataSourceRequest) {
        dataSourceRequest.getFilter().getFilters().add(DataSourceRequest.FilterDescriptor.create("deletedDate", null, DataSourceRequest.Operators.IS_NULL));
        dataSourceRequest.getFilter().getFilters().add(DataSourceRequest.FilterDescriptor.create("accountDefaultValues.deletedDate", null, DataSourceRequest.Operators.IS_NULL));
        dataSourceRequest.getFilter().getFilters().add(DataSourceRequest.FilterDescriptor.create("accountDefaultValues.accountRelationTypeDetail.deletedDate", null, DataSourceRequest.Operators.IS_NULL));
        dataSourceRequest.getFilter().getFilters().add(DataSourceRequest.FilterDescriptor.create("accountDefaultValues.accountRelationTypeDetail.accountRelationType.deletedDate", null, DataSourceRequest.Operators.IS_NULL));
        dataSourceRequest.getFilter().getFilters().add(DataSourceRequest.FilterDescriptor.create("centricAccountType.deletedDate", null, DataSourceRequest.Operators.IS_NULL));
        return gridFilterService.filter(dataSourceRequest, centricAccountListGridProvider);
    }

    @Override
    @Transactional
    public DataSourceResult getCentricAccountByOrganIdAndCentricAccountTypeId(DataSourceRequest dataSourceRequest) {
        List<DataSourceRequest.FilterDescriptor> filters = dataSourceRequest.getFilter().getFilters();
        CentricAccountNewTypeRequest param = setCentricAccountGetByTypeId(filters);
        Map<String, Object> paramMap = param.getParamMap();
        List<Object[]> list = getCentricAccountGetByTypeId(param, paramMap);
        List<CentricAccountNewResponse> centricAccountNewResponseList = getCentricAccountNewResponse(list);
        DataSourceResult dataSourceResult = new DataSourceResult();
        dataSourceResult.setData(centricAccountNewResponseList.stream().limit(dataSourceRequest.getTake() + dataSourceRequest.getSkip()).skip(dataSourceRequest.getSkip()).collect(Collectors.toList()));
        dataSourceResult.setTotal(list.size());
        return dataSourceResult;
    }

    private CentricAccountNewTypeRequest setCentricAccountGetByTypeId(List<DataSourceRequest.FilterDescriptor> filters) {
        CentricAccountNewTypeRequest centricAccountNewTypeRequest = new CentricAccountNewTypeRequest();
        Map<String, Object> map = new HashMap<>();
        for (DataSourceRequest.FilterDescriptor item : filters) {
            switch (item.getField()) {

                case "centricAccountType.id":
                    centricAccountNewTypeRequest.setCentricAccountTypeId(Long.parseLong(item.getValue().toString()));
                    centricAccountNewTypeRequest.setParamMap(map);
                    break;

                case "name":
                    if (item.getValue() != null) {
                        centricAccountNewTypeRequest.setName(item.getValue().toString());
                    } else {
                        centricAccountNewTypeRequest.setName("");
                    }
                    break;

                case "code":
                    if (item.getValue() != null) {
                        centricAccountNewTypeRequest.setCode(item.getValue().toString());
                    } else {
                        centricAccountNewTypeRequest.setCode("");
                    }
                    break;
                default:
                    break;
            }
        }
        return centricAccountNewTypeRequest;
    }

    private List<Object[]> getCentricAccountGetByTypeId(CentricAccountNewTypeRequest centricAccountNewTypeRequest, Map<String, Object> centricAccountGetByTypeIdParamMap) {
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