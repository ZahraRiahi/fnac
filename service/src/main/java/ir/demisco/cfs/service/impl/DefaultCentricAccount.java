package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.CentricAccountParameter;
import ir.demisco.cfs.model.dto.request.CentricAccountNewTypeRequest;
import ir.demisco.cfs.model.dto.request.CentricAccountRequest;
import ir.demisco.cfs.model.dto.response.*;
import ir.demisco.cfs.model.entity.*;
import ir.demisco.cfs.service.api.CentricAccountService;
import ir.demisco.cfs.service.repository.*;
import ir.demisco.cloud.core.middle.exception.RuleException;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import ir.demisco.cloud.core.middle.service.business.api.core.GridFilterService;
import ir.demisco.cloud.core.security.util.SecurityHelper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private final CentricAccountListGridProvider financialPeriodListGridProvider;
    private final CentricAccountRepository centricAccountRepository;
    private final OrganizationRepository organizationRepository;
    private final PersonRepository personRepository;
    private final PersonRoleTypeRepository personRoleTypeRepository;
    private final CentricPersonRoleRepository centricPersonRoleRepository;
    private final CentricAccountTypeRepository centricAccountTypeRepository;

    public DefaultCentricAccount(GridFilterService gridFilterService, CentricAccountListGridProvider financialPeriodListGridProvider, CentricAccountRepository centricAccountRepository, OrganizationRepository organizationRepository, PersonRepository personRepository, PersonRoleTypeRepository personRoleTypeRepository, CentricPersonRoleRepository centricPersonRoleRepository1, CentricAccountTypeRepository centricAccountTypeRepository2) {
        this.gridFilterService = gridFilterService;
        this.financialPeriodListGridProvider = financialPeriodListGridProvider;
        this.centricAccountRepository = centricAccountRepository;
        this.organizationRepository = organizationRepository;
        this.personRepository = personRepository;
        this.personRoleTypeRepository = personRoleTypeRepository;
        this.centricPersonRoleRepository = centricPersonRoleRepository1;
        this.centricAccountTypeRepository = centricAccountTypeRepository2;
    }

    @Override
    @Transactional
    public DataSourceResult getCentricAccountByOrganizationIdAndPersonAndName(DataSourceRequest dataSourceRequest) {
        dataSourceRequest.getFilter().getFilters().add(DataSourceRequest.FilterDescriptor.create("deletedDate", null, DataSourceRequest.Operators.IS_NULL));
        dataSourceRequest.getFilter().getFilters().add(DataSourceRequest.FilterDescriptor.create("centricAccountType.deletedDate", null, DataSourceRequest.Operators.IS_NULL));
        return gridFilterService.filter(dataSourceRequest, financialPeriodListGridProvider);
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public CentricAccountDto save(CentricAccountRequest centricAccountRequest) {
        CentricAccount centricAccount = centricAccountRepository.findById(centricAccountRequest.getId() == null ? 0L : centricAccountRequest.getId()).orElse(new CentricAccount());
        if (centricAccount.getId() == null) {
            Long centricAccountUniqueCount = centricAccountRepository.getCountByCentricAccountAndOrganizationAndCentricAccountTypeAndCode(SecurityHelper.getCurrentUser().getOrganizationId(), centricAccountRequest.getCentricAccountTypeId(), centricAccountRequest.getCode());
            if (centricAccountUniqueCount > 0) {
                throw new RuleException("کد تمرکز برای این سازمان با این نوع تمرکز و این کد قبلا ثبت شده است.");
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
        List<CentricPersonRole> centricPersonRoles = centricPersonRoleRepository.findByCentricAccountId(centricAccountId);
        CentricAccount centricAccount;
        if (!centricPersonRoles.isEmpty()) {
            centricPersonRoles.forEach(e -> e.setDeletedDate(LocalDateTime.now()));
        }
        centricAccount = centricAccountRepository.findById(centricAccountId).orElseThrow(() -> new RuleException("ایتمی با این شناسه وجود ندارد"));
        centricAccount.setDeletedDate(LocalDateTime.now());
        return true;
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
        CentricAccount centricAccount = centricAccountRepository.findById(centricAccountId).orElseThrow(() -> new RuleException("آیتمی با این شناسه وجود ندارد"));
        CentricAccountOutPutResponse centricAccountOutPutResponse = CentricAccountOutPutResponse.builder().id(centricAccountId)
                .code(centricAccount.getCode())
                .name(centricAccount.getName())
                .abbreviationName(centricAccount.getAbbreviationName())
                .latinName(centricAccount.getLatinName())
                .centricAccountTypeId(centricAccount.getCentricAccountType().getId())
                .centricAccountTypeDescription(centricAccount.getCentricAccountType().getDescription())
                .organizationId(centricAccount.getOrganization().getId())
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

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public DataSourceResult getCentricAccountByOrganizationIdAndCentricAccountTypeId(DataSourceRequest dataSourceRequest) {
        List<DataSourceRequest.FilterDescriptor> filters = dataSourceRequest.getFilter().getFilters();
        CentricAccountParameter param = setParameter(filters);
        Map<String, Object> paramMap = param.getParamMap();
        param.setOrganizationId(100L);
        Pageable pageable = PageRequest.of(dataSourceRequest.getSkip(), dataSourceRequest.getTake());
        Page<Object[]> centricAccountList = centricAccountRepository.findByCentricAccountAndOrganizationIdAndParentCentricAccount(param.getCentricAccountTypeId(), param.getOrganizationId()
                , paramMap.get("parentCentricAccount"), param.getParentCentricAccountId(), pageable);
        List<CentricAccountNewResponse> list = centricAccountList.stream().map(e -> CentricAccountNewResponse.builder()
                .id(((Long) e[0]).longValue())
                .name(e[2].toString())
                .code(e[1].toString()).build()).collect(Collectors.toList());
        DataSourceResult dataSourceResult = new DataSourceResult();
        dataSourceResult.setData(list);
        dataSourceResult.setTotal(centricAccountList.getTotalElements());
        return dataSourceResult;
    }

    private CentricAccountParameter setParameter(List<DataSourceRequest.FilterDescriptor> filters) {
        CentricAccountParameter centricAccountParameter = new CentricAccountParameter();
        Map<String, Object> map = new HashMap<>();
        for (DataSourceRequest.FilterDescriptor item : filters) {
            switch (item.getField()) {
                case "centricAccountType.id":
                    centricAccountParameter.setCentricAccountTypeId(Long.parseLong(item.getValue().toString()));
                    break;
                case "parentCentricAccount.id":
                    if (item.getValue() != null) {
                        map.put("parentCentricAccount", "parentCentricAccount");
                        centricAccountParameter.setParamMap(map);
                        centricAccountParameter.setParentCentricAccountId(Long.parseLong(item.getValue().toString()));
                    } else {
                        map.put("parentCentricAccount", null);
                        centricAccountParameter.setParamMap(map);
                        centricAccountParameter.setParentCentricAccountId(0L);
                    }
                    break;
            }
        }
        return centricAccountParameter;
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
    public List<CentricAccountNewResponse> getCentricAccountByOrganIdAndcentricAccountTypeId(CentricAccountNewTypeRequest centricAccountNewTypeRequest) {
        List<Object[]> centricAccountAndCentricAccountTypeList = centricAccountRepository.findByCentricAccountAndCentricAccountTypeListId(centricAccountNewTypeRequest.getCentricAccountTypeListId(), SecurityHelper.getCurrentUser().getOrganizationId());
        return centricAccountAndCentricAccountTypeList.stream().map(e -> CentricAccountNewResponse.builder().id(Long.parseLong(e[0].toString()))
                .name(e[2].toString())
                .code(e[1].toString()).build()).collect(Collectors.toList());

    }
}