package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.request.CentricAccountRequest;
import ir.demisco.cfs.model.dto.response.CentricAccountDto;
import ir.demisco.cfs.model.dto.response.CentricAccountNewResponse;
import ir.demisco.cfs.model.entity.CentricAccount;
import ir.demisco.cfs.model.entity.CentricPersonRole;
import ir.demisco.cfs.model.entity.PersonRoleType;
import ir.demisco.cfs.service.api.CentricAccountService;
import ir.demisco.cfs.service.repository.*;
import ir.demisco.cloud.core.middle.exception.RuleException;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import ir.demisco.cloud.core.middle.service.business.api.core.GridFilterService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class DefaultCentricAccount implements CentricAccountService {
    private final GridFilterService gridFilterService;
    private final CentricAccountListGridProvider financialPeriodListGridProvider;
    private final CentricAccountRepository centricAccountRepository;
    private final OrganizationRepository organizationRepository;
    private final PersonRepository personRepository;
    private final CentricAccountTypeRepository centricAccountTypeRepository;
    private final PersonRoleTypeRepository personRoleTypeRepository;
    private final CentricPersonRoleRepository centricPersonRoleRepository;

    public DefaultCentricAccount(GridFilterService gridFilterService, CentricAccountListGridProvider financialPeriodListGridProvider, CentricAccountRepository centricAccountRepository, CentricAccountTypeRepository centricAccountTypeRepository, OrganizationRepository organizationRepository, PersonRepository personRepository, CentricAccountTypeRepository centricAccountTypeRepository1, CentricPersonRoleRepository centricPersonRoleRepository, PersonRoleTypeRepository personRoleTypeRepository, CentricPersonRoleRepository centricPersonRoleRepository1) {
        this.gridFilterService = gridFilterService;
        this.financialPeriodListGridProvider = financialPeriodListGridProvider;
        this.centricAccountRepository = centricAccountRepository;
        this.organizationRepository = organizationRepository;
        this.personRepository = personRepository;
        this.centricAccountTypeRepository = centricAccountTypeRepository1;
        this.personRoleTypeRepository = personRoleTypeRepository;
        this.centricPersonRoleRepository = centricPersonRoleRepository1;
    }

    @Override
    @Transactional
    public DataSourceResult getCentricAccountByOrganizationIdAndPersonAndName(DataSourceRequest dataSourceRequest) {
        return gridFilterService.filter(dataSourceRequest, financialPeriodListGridProvider);
    }

    @Override
    @Transactional
    public List<CentricAccountNewResponse> getCentricAccountByOrganizationIdAndCentricAccountTypeId(Long centricAccountTypeId, Long organizationId) {
        List<CentricAccount> centricAccountList = centricAccountRepository.findByCentricAccountAndOrganizationId(centricAccountTypeId, 1L);
        return centricAccountList.stream().map(e -> CentricAccountNewResponse.builder().id(e.getId())
                .name(e.getName())
                .code(e.getCode()).build()).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public CentricAccountDto save(CentricAccountRequest centricAccountRequest) {
        CentricAccount centricAccount = centricAccountRepository.findById(centricAccountRequest.getId() == null ? 0L : centricAccountRequest.getId()).orElse(new CentricAccount());
        if (centricAccountRequest.getCentricAccountTypeId().equals(1L)) {
            centricAccount = saveCentricAccount(centricAccount, centricAccountRequest);
            CentricPersonRole centricPersonRole = new CentricPersonRole();
            centricPersonRole.setCentricAccount(centricAccount);
            centricPersonRole.setPersonRoleType(personRoleTypeRepository.findById(centricAccountRequest.getPeraonRoleTypeId() == null ? 0L : centricAccountRequest.getPeraonRoleTypeId()).orElse(new PersonRoleType()));
            centricPersonRoleRepository.save(centricPersonRole);
        } else {
            centricAccount = saveCentricAccount(centricAccount, centricAccountRequest);
        }
        return convertCentricAccountToDto(centricAccount);
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public CentricAccountDto update(CentricAccountRequest centricAccountRequest) {
        CentricAccount centricAccount = centricAccountRepository.findById(centricAccountRequest.getId()).orElseThrow(() -> new RuleException("برای انجام عملیات ویرایش شناسه ی کد تمرکز الزامی میباشد."));
        if (centricAccountRequest.getCentricAccountTypeId().equals(1L)) {
            centricAccount = saveCentricAccount(centricAccount, centricAccountRequest);
            CentricPersonRole centricPersonRole = new CentricPersonRole();
            centricPersonRole.setCentricAccount(centricAccount);
            centricPersonRole.setPersonRoleType(personRoleTypeRepository.findById(centricAccountRequest.getPeraonRoleTypeId() == null ? 0L : centricAccountRequest.getPeraonRoleTypeId()).orElse(new PersonRoleType()));
            centricPersonRoleRepository.save(centricPersonRole);
        } else {
            centricAccount = saveCentricAccount(centricAccount, centricAccountRequest);
        }
        return convertCentricAccountToDto(centricAccount);
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
                .organizationId(centricAccount.getOrganization().getId())
                .personId(centricAccount.getPerson().getId())
                .personName(centricAccount.getPerson().getPersonName())
                .activeFlag(centricAccount.getActiveFlag())
                .build();
    }

    private CentricAccount saveCentricAccount(CentricAccount centricAccount, CentricAccountRequest centricAccountRequest) {
        centricAccount.setCode(centricAccountRequest.getCode());
        centricAccount.setName(centricAccountRequest.getName());
        centricAccount.setCentricAccountType(centricAccountTypeRepository.findById(centricAccountRequest.getCentricAccountTypeId()).orElseThrow(() -> new RuleException("test")));
        centricAccount.setOrganization(organizationRepository.getOne(1L));
        centricAccount.setPerson(personRepository.getOne(centricAccountRequest.getPersonId()));
        centricAccount.setActiveFlag(centricAccountRequest.getActiveFlag());
        return centricAccountRepository.save(centricAccount);
    }

}