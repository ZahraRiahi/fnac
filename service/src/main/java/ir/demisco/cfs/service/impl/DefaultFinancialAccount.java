package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.FinancialAccountParameter;
import ir.demisco.cfs.model.dto.request.*;
import ir.demisco.cfs.model.dto.response.*;
import ir.demisco.cfs.model.entity.*;
import ir.demisco.cfs.service.api.AccountRelatedDescriptionService;
import ir.demisco.cfs.service.api.FinancialAccountService;
import ir.demisco.cfs.service.api.FinancialAccountStructureService;
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

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DefaultFinancialAccount implements FinancialAccountService {
    private final FinancialAccountRepository financialAccountRepository;
    private final FinancialAccountTypeRepository financialAccountTypeRepository;
    private final CentricAccountRepository centricAccountRepository;
    private final AccountRelatedDescriptionRepository accountRelatedDescriptionRepository;
    private final MoneyTypeRepository moneyTypeRepository;
    private final OrganizationRepository organizationRepository;
    private final AccountNatureTypeRepository accountNatureTypeRepository;
    private final AccountRelationTypeRepository accountRelationTypeRepository;
    private final FinancialAccountStructureService financialAccountStructureService;
    private final AccountRelatedTypeRepository accountRelatedTypeRepository;
    private final AccountMoneyTypeRepository accountMoneyTypeRepository;
    private final AccountDefaultValueRepository accountDefaultValueRepository;
    private final AccountRelationTypeDetailRepository accountRelationTypeDetailRepository;
    private final AccountStructureLevelRepository accountStructureLevelRepository;
    private final AccountRelatedDescriptionService accountRelatedDescriptionService;
    private final FinancialAccountDescriptionRepository financialAccountDescriptionRepository;
    private final FinancialDocumentItemRepository financialDocumentItemRepository;
    private final FinancialAccountStructureRepository financialAccountStructureRepository;
    private final AccountPermanentStatusRepository accountPermanentStatusRepository;
    private final FinancialAccountLovProvider financialAccountLovProvider;
    private final GridFilterService gridFilterService;
    private final EntityManager entityManager;
    private final FinancialAccountGetByStructureProvider financialAccountGetByStructureProvider;

    public DefaultFinancialAccount(FinancialAccountRepository financialAccountRepository, CentricAccountRepository centricAccountRepository, FinancialAccountTypeRepository financialAccountTypeRepository, AccountRelatedDescriptionRepository accountRelatedDescriptionRepository, MoneyTypeRepository moneyTypeRepository, OrganizationRepository organizationRepository, AccountNatureTypeRepository accountNatureTypeRepository, AccountRelationTypeRepository accountRelationTypeRepository, FinancialAccountStructureService financialAccountStructureService, AccountRelatedTypeRepository accountRelatedTypeRepository, AccountMoneyTypeRepository accountMoneyTypeRepository, AccountDefaultValueRepository accountDefaultValueRepository, AccountRelationTypeDetailRepository accountRelationTypeDetailRepository, AccountStructureLevelRepository accountStructureLevelRepository, AccountRelatedDescriptionService accountRelatedDescriptionService, FinancialAccountDescriptionRepository financialAccountDescriptionRepository, FinancialDocumentItemRepository financialDocumentItemRepository, FinancialAccountStructureRepository financialAccountStructureRepository1, AccountPermanentStatusRepository accountPermanentStatusRepository, FinancialAccountLovProvider financialAccountLovProvider, GridFilterService gridFilterService, EntityManager entityManager, FinancialAccountGetByStructureProvider financialAccountGetByStructureProvider) {

        this.financialAccountRepository = financialAccountRepository;
        this.financialAccountTypeRepository = financialAccountTypeRepository;
        this.centricAccountRepository = centricAccountRepository;
        this.accountRelatedDescriptionRepository = accountRelatedDescriptionRepository;
        this.moneyTypeRepository = moneyTypeRepository;
        this.organizationRepository = organizationRepository;
        this.accountNatureTypeRepository = accountNatureTypeRepository;
        this.accountRelationTypeRepository = accountRelationTypeRepository;
        this.financialAccountStructureService = financialAccountStructureService;
        this.accountRelatedTypeRepository = accountRelatedTypeRepository;
        this.accountMoneyTypeRepository = accountMoneyTypeRepository;
        this.accountDefaultValueRepository = accountDefaultValueRepository;
        this.accountRelationTypeDetailRepository = accountRelationTypeDetailRepository;
        this.accountStructureLevelRepository = accountStructureLevelRepository;
        this.accountRelatedDescriptionService = accountRelatedDescriptionService;
        this.financialAccountDescriptionRepository = financialAccountDescriptionRepository;
        this.financialDocumentItemRepository = financialDocumentItemRepository;
        this.financialAccountStructureRepository = financialAccountStructureRepository1;
        this.accountPermanentStatusRepository = accountPermanentStatusRepository;
        this.financialAccountLovProvider = financialAccountLovProvider;
        this.gridFilterService = gridFilterService;
        this.entityManager = entityManager;
        this.financialAccountGetByStructureProvider = financialAccountGetByStructureProvider;
    }

    @Override
    @Transactional
    public DataSourceResult getFinancialAccount(DataSourceRequest dataSourceRequest) {
        List<DataSourceRequest.FilterDescriptor> filters = dataSourceRequest.getFilter().getFilters();
        FinancialAccountParameter param = setParameter(filters);
        Map<String, Object> paramMap = param.getParamMap();
        param.setOrganizationId(SecurityHelper.getCurrentUser().getOrganizationId());
        Pageable pageable = PageRequest.of(dataSourceRequest.getSkip(), dataSourceRequest.getTake());
        Page<Object[]> list = financialAccountRepository.financialAccountList(param.getOrganizationId(), param.getFinancialCodingTypeId(), param.getDescription(), paramMap.get("financialAccountParent"), param.getFinancialAccountParentId()
                , paramMap.get("accountNatureType"), param.getAccountNatureTypeId(), paramMap.get("financialAccountStructure"), param.getFinancialAccountStructureId(), paramMap.get("accountRelationType"), param.getAccountRelationTypeId()
                , pageable);
        List<FinancialAccountDto> financialAccountDtos = list.stream().map(item ->
                FinancialAccountDto.builder()
                        .id(Long.parseLong(item[0].toString()))
                        .organizationId(Long.parseLong(item[1].toString()))
                        .description(item[3].toString())
                        .code(item[2].toString())
                        .activeFlag(item[8] == null ? null : Long.parseLong(item[8].toString()))
                        .accountNatureTypeId(item[4] == null ? null : Long.parseLong(item[4].toString()))
                        .accountRelationTypeDescription(item[10] == null ? null : item[10].toString())
                        .accountRelationTypeId(item[6] == null ? null : Long.parseLong(item[6].toString()))
                        .accountNatureTypeDescription(item[9] == null ? null : item[9].toString())
                        .financialAccountParentId(item[7] == null ? null : Long.parseLong(item[7].toString()))
                        .financialAccountStructureId(item[5] == null ? null : Long.parseLong(item[5].toString()))
                        .hasChild(item[11] == null ? null : Long.parseLong(item[11].toString()))
                        .accountStatusId(item[12] == null ? null : Long.parseLong(item[12].toString()))
                        .accountStatusCode(item[13] == null ? null : (item[13].toString()))
                        .accountStatusDescription(item[14] == null ? null : (item[14].toString()))
                        .flgShowInAcc(Integer.parseInt(item[15].toString()) == 1)
                        .flgPermanentStatus(Integer.parseInt(item[16].toString()) == 1)
                        .color(item[17] == null ? null : item[17].toString())
                        .build()).collect(Collectors.toList());
        DataSourceResult dataSourceResult = new DataSourceResult();
        dataSourceResult.setData(financialAccountDtos);
        dataSourceResult.setTotal(list.getTotalElements());
        return dataSourceResult;
    }

    private FinancialAccountParameter setParameter(List<DataSourceRequest.FilterDescriptor> filters) {
        FinancialAccountParameter financialAccountParameter = new FinancialAccountParameter();
        Map<String, Object> map = new HashMap<>();
        for (DataSourceRequest.FilterDescriptor item : filters) {
            switch (item.getField()) {
                case "financialAccountStructure.financialCodingType.id":
                    financialAccountParameter.setFinancialCodingTypeId(Long.parseLong(item.getValue().toString()));
                    break;
                case "accountNatureType.id":
                    if (item.getValue() != null) {
                        map.put("accountNatureType", "accountNatureType");
                        financialAccountParameter.setParamMap(map);
                        financialAccountParameter.setAccountNatureTypeId(Long.parseLong(item.getValue().toString()));
                    } else {
                        map.put("accountNatureType", null);
                        financialAccountParameter.setParamMap(map);
                        financialAccountParameter.setAccountNatureTypeId(0L);
                    }
                    break;
                case "financialAccountStructure.id":
                    if (item.getValue() != null) {
                        map.put("financialAccountStructure", "financialAccountStructure");
                        financialAccountParameter.setParamMap(map);
                        financialAccountParameter.setFinancialAccountStructureId(Long.parseLong(item.getValue().toString()));
                    } else {
                        map.put("financialAccountStructure", null);
                        financialAccountParameter.setParamMap(map);
                        financialAccountParameter.setFinancialAccountStructureId(0L);
                    }
                    break;
                case "accountRelationType.id":
                    if (item.getValue() != null) {
                        map.put("accountRelationType", "accountRelationType");
                        financialAccountParameter.setParamMap(map);
                        financialAccountParameter.setAccountRelationTypeId(Long.parseLong(item.getValue().toString()));
                    } else {
                        map.put("accountRelationType", null);
                        financialAccountParameter.setParamMap(map);
                        financialAccountParameter.setAccountRelationTypeId(0L);
                    }
                    break;
                case "financialAccountParent.id":
                    if (item.getValue() != null) {
                        map.put("financialAccountParent", "financialAccountParent");
                        financialAccountParameter.setParamMap(map);
                        financialAccountParameter.setFinancialAccountParentId(Long.parseLong(item.getValue().toString()));
                    } else {
                        map.put("financialAccountParent", null);
                        financialAccountParameter.setParamMap(map);
                        financialAccountParameter.setFinancialAccountParentId(0L);
                    }
                    break;
                case "description":
                    if (item.getValue() != null) {
                        financialAccountParameter.setDescription(item.getValue().toString());
                    }
            }
        }
        return financialAccountParameter;
    }

//    @Override
//    @Transactional(rollbackOn = Throwable.class)
//    public DataSourceResult getFinancialAccountLov(Long OrganizationId, DataSourceRequest dataSourceRequest) {
//        Pageable pageable = PageRequest.of(dataSourceRequest.getSkip(), dataSourceRequest.getTake());
//        Page<Object[]> financialAccount = financialAccountRepository.findByFinancialAccountByOrganizationId(OrganizationId, pageable);
//        List<FinancialAccountResponse> list = financialAccount.stream().map(e -> FinancialAccountResponse.builder()
//                .id(((Long) e[0]).longValue())
//                .description(e[2].toString())
//                .code(e[1].toString())
//                .referenceFlag(e[3] == null || ((Boolean) e[3]).equals(0))
//                .exchangeFlag(e[4] == null || ((Boolean) e[4]).equals(0))
//                .accountRelationTypeId(e[5] == null ? null : Long.parseLong(e[5].toString()))
//                .disableDate((Date) e[6])
//                .build()).collect(Collectors.toList());
//        DataSourceResult dataSourceResult = new DataSourceResult();
//        dataSourceResult.setData(list);
//        dataSourceResult.setTotal(financialAccount.getTotalElements());
//        return dataSourceResult;
//    }


    @Override
    @Transactional
    public FinancialAccountOutPutResponse getFinancialAccountGetById(Long financialAccountId, Long organizationId) {
        FinancialAccount financialAccount = financialAccountRepository.findById(financialAccountId).orElseThrow(() -> new RuleException("fin.ruleException.notFoundId"));
        FinancialAccountOutPutResponse financialAccountOutPutResponse = FinancialAccountOutPutResponse.builder().id(financialAccountId)
                .organizationId(financialAccount.getOrganization().getId())
                .financialAccountStructureId(financialAccount.getFinancialAccountStructure().getId())
                .fullDescription(financialAccount.getFullDescription())
                .code(financialAccount.getCode())
                .description(financialAccount.getDescription())
                .latinDescription(financialAccount.getLatinDescription())
                .accountNatureTypeId(financialAccount.getAccountNatureType() == null ? 0 : financialAccount.getAccountNatureType().getId())
                .accountNatureTypeDescription(financialAccount.getAccountNatureType() == null ? "" : financialAccount.getAccountNatureType().getDescription())
                .relatedToOthersFlag(financialAccount.getRelatedToOthersFlag())
                .accountRelationTypeId(financialAccount.getAccountRelationType() == null ? 0 : financialAccount.getAccountRelationType().getId())
                .accountRelationTypeDescription(financialAccount.getAccountRelationType() == null ? "" : financialAccount.getAccountRelationType().getDescription())
                .financialAccountParentId(financialAccount.getFinancialAccountParent() == null ? 0 : financialAccount.getFinancialAccountParent().getId())
                .financialAccountParentDescription(financialAccount.getFinancialAccountParent() == null ? "" : financialAccount.getFinancialAccountParent().getDescription())
                .relatedToFundType(financialAccount.getRelatedToFundType())
                .referenceFlag(financialAccount.getReferenceFlag())
                .convertFlag(financialAccount.getConvertFlag())
                .exchangeFlag(financialAccount.getExchangeFlag())
                .accountAdjustmentId(financialAccount.getAccountAdjustment() == null ? 0 : financialAccount.getAccountAdjustment().getId())
                .accountAdjustmentDescription(financialAccount.getAccountAdjustment() == null ? "" : financialAccount.getAccountAdjustment().getDescription())
                .accountStatusId(financialAccount.getAccountPermanentStatus() == null ? 0 : financialAccount.getAccountPermanentStatus().getId())
                .accountStatusCode(financialAccount.getAccountPermanentStatus() == null ? "" : financialAccount.getAccountPermanentStatus().getCode())
                .accountStatusDescription(financialAccount.getAccountPermanentStatus() == null ? "" : financialAccount.getAccountPermanentStatus().getDescription())
                .flgShowInAcc(financialAccount.getFinancialAccountStructure().getFlgShowInAcc())
                .flgPermanentStatus(financialAccount.getFinancialAccountStructure().getFlgPermanentStatus())
                .build();
        financialAccountOutPutResponse.setAccountRelatedTypeOutPutModel(accountRelatedTypeResponses(financialAccountId));
        financialAccountOutPutResponse.setAccountDefaultValueOutPutModel(accountDefaultValueResponses(financialAccountId));
        financialAccountOutPutResponse.setAccountRelatedDescriptionOutPutModel(accountRelatedDescriptionResponses(financialAccountId));
        financialAccountOutPutResponse.setAccountMoneyTypeOutPutModel(accountMoneyTypeResponses(financialAccountId));
        return financialAccountOutPutResponse;
    }

    private List<AccountRelatedTypeNewResponse> accountRelatedTypeResponses(Long financialAccountId) {
        List<Object[]> typeListObject = financialAccountTypeRepository.findByFinancialAccount(financialAccountId);
        return typeListObject.stream().map(objects -> AccountRelatedTypeNewResponse.builder().financialAccountTypeId(Long.parseLong(objects[0].toString()))
                .code(objects[1].toString())
                .description(objects[2].toString())
                .flgExists(Long.parseLong(objects[3].toString())).build()).collect(Collectors.toList());
    }


    private List<AccountDefaultValueResponse> accountDefaultValueResponses(Long financialAccountId) {
        List<Object[]> centricAccountListObject = centricAccountRepository.findByCentricAccountListObject(financialAccountId);
        return centricAccountListObject.stream().map(objects ->
                AccountDefaultValueResponse.builder()
                        .id(Long.parseLong(objects[0].toString()))
                        .accountRelationTypeDetailId(Long.parseLong(objects[1].toString()))
                        .centricAccountId(objects[2] == null ? null : Long.parseLong(objects[2].toString()))
                        .centricAccountName(objects[3] == null ? null : objects[3].toString())
                        .centricAccountCode(objects[4] == null ? null : objects[4].toString())
                        .accountRelationTypeDescription(objects[5].toString())
                        .accountRelationTypeId(Long.parseLong(objects[6].toString()))
                        .sequence(Long.parseLong(objects[7].toString()))
                        .centricAccountTypeId(Long.parseLong(objects[8].toString()))
                        .centricAccountTypeDescription(objects[9].toString())
                        .build()).collect(Collectors.toList());
    }

    private List<AccountRelatedDescriptionResponse> accountRelatedDescriptionResponses(Long financialAccountId) {
        List<Object[]> accountRelatedDescriptionListObject = accountRelatedDescriptionRepository.findByAccountRelatedDescriptionListObject(financialAccountId);
        return accountRelatedDescriptionListObject.stream().map(objects -> AccountRelatedDescriptionResponse.builder().financialAccountDescriptionId(Long.parseLong(objects[0].toString()))
                .financialAccountDescriptionDescription(objects[1].toString())
                .build()).collect(Collectors.toList());
    }

    private List<AccountMoneyTypeResponse> accountMoneyTypeResponses(Long financialAccountId) {
        List<Object[]> moneyTypeListObject = moneyTypeRepository.findByMonetTypeListObject(financialAccountId, SecurityHelper.getCurrentUser().getOrganizationId());
        return moneyTypeListObject.stream().map(objects -> AccountMoneyTypeResponse.builder().id(Long.parseLong(objects[0].toString()))
                .moneyTypeDescription(objects[1].toString())
                .flgExists(Long.parseLong(objects[2].toString())).build()).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public FinancialAccountOutPutDto save(FinancialAccountRequest financialAccountRequest) {
        FinancialAccountOutPutDto financialAccountOutPutDto;
        FinancialAccount financialAccount = saveFinancialAccount(financialAccountRequest);
        financialAccountOutPutDto = convertFinancialAccountDto(financialAccount);
        saveAccountStructureLevel(financialAccountRequest, financialAccount);
        financialAccountOutPutDto.setAccountDefaultValueOutPutModel(saveAccountDefaultValue
                (financialAccountRequest.getAccountDefaultValueInPutModel(), financialAccount));
        financialAccountOutPutDto.setAccountRelatedDescriptionOutputModel(saveAccountRelatedDescriptionValue
                (financialAccountRequest.getAccountRelatedDescriptionInPutModel(), financialAccount));

        financialAccountOutPutDto.setAccountMoneyTypeOutPut(saveAccountMoneyType
                (financialAccountRequest.getMoneyTypeId(), financialAccount));

        financialAccountOutPutDto.setAccountRelatedTypeOutPutModel(saveAccountRelatedType
                (financialAccountRequest.getFinancialAccountTypeId(), financialAccount));
        return financialAccountOutPutDto;
    }

    private FinancialAccount saveFinancialAccount(FinancialAccountRequest financialAccountRequest) {
        if (financialAccountRequest.getId() == null && financialAccountRequest.getFinancialAccountStructureId() != null) {
//            Long financialAccountIdAndStuctureAndAccountId = financialAccountRepository.findByFinancialAccountIdAndStuctureAndAccountId(financialAccountRequest.getFinancialAccountStructureId());
//
//            if (financialAccountIdAndStuctureAndAccountId != null) {
//                throw new RuleException("امکان ایجاد این سطح حساب ، به دلیل انتخاب سطح قبل به عنوان ، آخرین سطح ،وجود ندارد");
//            }
            List<Long> financialAccountStructureAndCodingTypeCount = financialAccountRepository.findByFinancialAccountIdAndStructureAndCodingType(financialAccountRequest.getFinancialAccountStructureId());
            if (financialAccountStructureAndCodingTypeCount.size() != 0) {
                throw new RuleException("fin.financialAccount.save");
            }
        }
        FinancialAccountStructureNewRequest financialAccountStructureNewRequest = new FinancialAccountStructureNewRequest();
        financialAccountStructureNewRequest.setFinancialAccountParentId(financialAccountRequest.getFinancialAccountParentId());
        financialAccountStructureNewRequest.setFinancialCodingTypeId(financialAccountRequest.getFinancialCodingTypeId());
        financialAccountStructureNewRequest.setFinancialAccountStructureId(financialAccountRequest.getFinancialAccountStructureId());
        if (financialAccountRequest.getId() == null) {
            financialAccountStructureNewRequest.setFlgEditMode(false);
        } else {
            financialAccountStructureNewRequest.setFlgEditMode(true);
        }
        FinancialAccount financialAccount = financialAccountRepository.findById(financialAccountRequest.getId() == null ? 0L : financialAccountRequest.getId()).orElse(new FinancialAccount());

        if (financialAccountRequest.getId() == null) {
            FinancialAccountStructureNewResponse financialAccountStructureNewResponse = financialAccountStructureService.getFinancialAccountStructureByCodingAndParentAndId(financialAccountStructureNewRequest);

            if (financialAccountStructureNewResponse.getFlgPermanentStatus() == 0) {
                financialAccountRequest.setAccountStatusId(financialAccountStructureNewResponse.getAccountPermanentStatusId());
            }

            Long financialAccountCodeCount;
            String newGeneratedCode;
            FinancialAccountStructureRequest financialAccountStructureRequest = new FinancialAccountStructureRequest();
            financialAccountStructureRequest.setFinancialAccountStructureId(financialAccountRequest.getFinancialAccountStructureId());
            financialAccountStructureRequest.setFinancialCodingTypeId(financialAccountRequest.getFinancialCodingTypeId());
            Long financialAccountStructureId = financialAccountStructureService.getFinancialAccountStructureByFinancialCodingTypeAndFinancialAccountStructure
                    (financialAccountStructureRequest);
            if (financialAccountRequest.getId() == null) {

                financialAccountCodeCount = financialAccountRepository.getCountByFinancialAccountAndCode(financialAccountRequest.getCode());

                if (financialAccountStructureId == null) {
                    throw new RuleException("fin.financialAccount.save.childAccount");
                }
                financialAccount.setFinancialAccountStructure(financialAccountStructureRepository.getOne(financialAccountStructureId));
            } else {
                financialAccountCodeCount = financialAccountRepository.getCountByFinancialAccountAndCode(financialAccountRequest.getCode(), financialAccount.getId());
                financialAccount.setFinancialAccountStructure(financialAccountStructureRepository.getOne(financialAccountRequest.getFinancialAccountStructureId()));
            }
            if (financialAccountCodeCount > 0) {
                throw new RuleException("fin.financialAccount.duplicateCode");
            }
            Long financialAccountStructureByCodeAndChild = financialAccountStructureRepository.getFinancialAccountStructureByCodeAndChild(financialAccountStructureId, financialAccountRequest.getCode());

            if (financialAccountStructureByCodeAndChild == null) {
                throw new RuleException("fin.financialAccount.structureCode");
            }
            if (financialAccountRequest.getFinancialAccountParentId() != null) {
                List<Object[]> financialAccountParent = financialAccountRepository.findByFinancialAccountAndFinancialAccountParent(financialAccountRequest.getFinancialAccountParentId());
                newGeneratedCode = financialAccountParent.stream().map(objects -> objects[2].toString()).findFirst().get();
                financialAccountRequest.setCode(newGeneratedCode + financialAccountRequest.getCode());

            }
        }
        financialAccount.setOrganization(organizationRepository.getOne(100L));
        financialAccount.setFullDescription(financialAccountRequest.getFullDescription());
        financialAccount.setCode(financialAccountRequest.getCode());
        financialAccount.setDescription(financialAccountRequest.getDescription());
        financialAccount.setLatinDescription(financialAccountRequest.getLatinDescription());
        if (financialAccountRequest.getAccountNatureTypeId() != null) {
            financialAccount.setAccountNatureType(accountNatureTypeRepository.getOne(financialAccountRequest.getAccountNatureTypeId()));
        }
        financialAccount.setRelatedToOthersFlag(financialAccountRequest.getRelatedToOthersFlag());
        if (financialAccountRequest.getAccountRelationTypeId() != null) {
            financialAccount.setAccountRelationType(accountRelationTypeRepository.getOne(financialAccountRequest.getAccountRelationTypeId()));
        }
        if (financialAccountRequest.getFinancialAccountParentId() != null) {
            financialAccount.setFinancialAccountParent(financialAccountRepository.getOne(financialAccountRequest.getFinancialAccountParentId()));
        }
        if (financialAccountRequest.getAccountStatusId() != null) {
            financialAccount.setAccountPermanentStatus(accountPermanentStatusRepository.getOne(financialAccountRequest.getAccountStatusId()));

        }
        financialAccount.setRelatedToFundType(financialAccountRequest.getRelatedToFundType());
        financialAccount.setReferenceFlag(financialAccountRequest.getReferenceFlag());
        financialAccount.setConvertFlag(financialAccountRequest.getConvertFlag());
        financialAccount.setExchangeFlag(financialAccountRequest.getExchangeFlag());
        if (financialAccountRequest.getAccountAdjustmentId() != null) {
            financialAccount.setAccountAdjustment(financialAccountRepository.getOne(financialAccountRequest.getAccountAdjustmentId()));
        }

        return financialAccountRepository.save(financialAccount);
    }


    private FinancialAccountOutPutDto convertFinancialAccountDto(FinancialAccount financialAccount) {
        FinancialAccountOutPutDto financialAccountOutPutDto = new FinancialAccountOutPutDto();
        financialAccountOutPutDto.setId(financialAccount.getId());
        financialAccountOutPutDto.setOrganizationId(financialAccount.getOrganization().getId());
        financialAccountOutPutDto.setFinancialAccountStructureId(financialAccount.getFinancialAccountStructure() == null ? 0 : financialAccount.getFinancialAccountStructure().getId());
        financialAccountOutPutDto.setFullDescription(financialAccount.getFullDescription());
        financialAccountOutPutDto.setDescription(financialAccount.getDescription());
        financialAccountOutPutDto.setCode(financialAccount.getCode());
        financialAccountOutPutDto.setLatinDescription(financialAccount.getLatinDescription());
        financialAccountOutPutDto.setAccountNatureTypeId(financialAccount.getAccountNatureType() == null ? 0 : financialAccount.getAccountNatureType().getId());
        financialAccountOutPutDto.setAccountNatureTypeDescription(financialAccount.getAccountNatureType() == null ? " " : financialAccount.getAccountNatureType().getDescription());
        financialAccountOutPutDto.setRelatedToOthersFlag(financialAccount.getRelatedToOthersFlag());
        financialAccountOutPutDto.setAccountRelationTypeId(financialAccount.getAccountRelationType() == null ? 0 : financialAccount.getAccountRelationType().getId());
        financialAccountOutPutDto.setAccountRelationTypeDescription(financialAccount.getAccountRelationType() == null ? " " : financialAccount.getAccountRelationType().getDescription());
        financialAccountOutPutDto.setFinancialAccountParentId(financialAccount.getFinancialAccountParent() == null ? 0 : financialAccount.getFinancialAccountParent().getId());
        financialAccountOutPutDto.setFinancialAccountParentDescription(financialAccount.getFinancialAccountParent() == null ? " " : financialAccount.getFinancialAccountParent().getDescription());
        financialAccountOutPutDto.setRelatedToFundType(financialAccount.getRelatedToFundType());
        financialAccountOutPutDto.setReferenceFlag(financialAccount.getReferenceFlag());
        financialAccountOutPutDto.setConvertFlag(financialAccount.getConvertFlag());
        financialAccountOutPutDto.setExchangeFlag(financialAccount.getExchangeFlag());
        financialAccountOutPutDto.setAccountAdjustmentId(financialAccount.getAccountAdjustment() == null ? 0 : financialAccount.getAccountAdjustment().getId());
        financialAccountOutPutDto.setAccountAdjustmentDescription(financialAccount.getAccountAdjustment() == null ? " " : financialAccount.getAccountAdjustment().getDescription());
        financialAccountOutPutDto.setAccountStatusId(financialAccount.getAccountPermanentStatus() == null ? 0 : financialAccount.getAccountPermanentStatus().getId());

        return financialAccountOutPutDto;
    }


    private List<AccountDefaultValueResponse> saveAccountDefaultValue(List<AccountDefaultValueRequest> accountDefaultValueOutPutModel, FinancialAccount financialAccount) {
        List<AccountDefaultValueResponse> accountDefaultValueDtos = new ArrayList<>();
        accountDefaultValueOutPutModel.forEach(e -> {
            Object centricAccount = null;
            Long centricAccountId;
            if (e.getCentricAccountId() != null) {
                centricAccount = "centricAccount";
                centricAccountId = e.getCentricAccountId();
            } else {
                centricAccountId = 0L;
            }
            Long countAccountDefaultValue = accountDefaultValueRepository.findByAccountDefaultAndFinancialAccountAndAccountRelationTypeDetailId(e.getAccountRelationTypeDetailId(), financialAccount.getId(), centricAccountId, centricAccount);
            if (countAccountDefaultValue > 0) {
                throw new RuleException("fin.financialAccount.accountRelationTypeDetail.financialAccountType");
            }
            AccountDefaultValue accountDefaultValue = new AccountDefaultValue();
            accountDefaultValue.setFinancialAccount(financialAccount);
            accountDefaultValue.setAccountRelationTypeDetail(accountRelationTypeDetailRepository.getOne(e.getAccountRelationTypeDetailId()));
            if (e.getCentricAccountId() != null) {
                accountDefaultValue.setCentricAccount(centricAccountRepository.getOne(e.getCentricAccountId()));
            }
            accountDefaultValue = accountDefaultValueRepository.save(accountDefaultValue);
            accountDefaultValueDtos.add(convertAccountDefaultValueResponse(accountDefaultValue));
        });
        return accountDefaultValueDtos;
    }

    private AccountDefaultValueResponse convertAccountDefaultValueResponse(AccountDefaultValue accountDefaultValue) {
        AccountDefaultValueResponse accountDefaultValueResponse = new AccountDefaultValueResponse();
        accountDefaultValueResponse.setId(accountDefaultValue.getId());
        accountDefaultValueResponse.setAccountRelationTypeDetailId(accountDefaultValue.getAccountRelationTypeDetail().getId());
        accountDefaultValueResponse.setCentricAccountId(accountDefaultValue.getCentricAccount() == null ? 0L : accountDefaultValue.getCentricAccount().getId());
        accountDefaultValueResponse.setCentricAccountName(accountDefaultValue.getCentricAccount() == null ? "" : accountDefaultValue.getCentricAccount().getName());
        accountDefaultValueResponse.setCentricAccountCode(accountDefaultValue.getCentricAccount() == null ? "" : accountDefaultValue.getCentricAccount().getCode());
        accountDefaultValueResponse.setAccountRelationTypeDescription(accountDefaultValue.getAccountRelationTypeDetail().getAccountRelationType().getDescription());
        accountDefaultValueResponse.setAccountRelationTypeId(accountDefaultValue.getAccountRelationTypeDetail().getAccountRelationType().getId());
        return accountDefaultValueResponse;
    }

    private List<AccountRelatedDescriptionDto> saveAccountRelatedDescriptionValue(List<AccountRelatedDescriptionRequest> accountRelatedDescriptionOutPutModel, FinancialAccount financialAccount) {
        List<AccountRelatedDescriptionDto> accountRelatedDescriptionDtos = new ArrayList<>();
        accountRelatedDescriptionOutPutModel.forEach(e -> {
            e.setFinancialAccountId(financialAccount.getId());
            AccountRelatedDescriptionDto accountRelatedDescriptionDto = accountRelatedDescriptionService.save(e);
            accountRelatedDescriptionDtos.add(accountRelatedDescriptionDto);
        });
        return accountRelatedDescriptionDtos;
    }

    private List<AccountMoneyTypeDtoResponse> saveAccountMoneyType(List<Long> accountMoneyTypeOutPut, FinancialAccount financialAccount) {
        List<AccountMoneyTypeDtoResponse> accountMoneyTypeDtoResponses = new ArrayList<>();
        accountMoneyTypeOutPut.forEach(e -> {
            AccountMoneyType accountMoneyType = new AccountMoneyType();
            accountMoneyType.setFinancialAccount(financialAccount);
            accountMoneyType.setMoneyType(moneyTypeRepository.getOne(e));
            accountMoneyType = accountMoneyTypeRepository.save(accountMoneyType);
            AccountMoneyTypeDtoResponse accountMoneyTypeDtoResponse = new AccountMoneyTypeDtoResponse();
            accountMoneyTypeDtoResponse.setId(accountMoneyType.getId());
            accountMoneyTypeDtoResponse.setMoneyTypeId(accountMoneyType.getMoneyType().getId());
            accountMoneyTypeDtoResponse.setMoneyTypeDescription(accountMoneyType.getMoneyType().getDescription());
            accountMoneyTypeDtoResponses.add(accountMoneyTypeDtoResponse);
        });
        return accountMoneyTypeDtoResponses;
    }

    private List<AccountRelatedTypeDtoResponse> saveAccountRelatedType(List<Long> accountRelatedTypeOutPutModel, FinancialAccount financialAccount) {
        List<AccountRelatedTypeDtoResponse> accountRelatedTypeDtoResponses = new ArrayList<>();
        accountRelatedTypeOutPutModel.forEach(e -> {
            AccountRelatedType accountRelatedType = new AccountRelatedType();
            accountRelatedType.setFinancialAccount(financialAccount);
            accountRelatedType.setFinancialAccountType(financialAccountTypeRepository.getOne(e));
            accountRelatedType = accountRelatedTypeRepository.save(accountRelatedType);
            AccountRelatedTypeDtoResponse accountRelatedTypeDtoResponse = new AccountRelatedTypeDtoResponse();
            accountRelatedTypeDtoResponse.setFinancialAccountTypeId(accountRelatedType.getFinancialAccountType().getId());
            accountRelatedTypeDtoResponse.setFinancialAccountTypeDescription(accountRelatedType.getFinancialAccountType().getDescription());
            accountRelatedTypeDtoResponses.add(accountRelatedTypeDtoResponse);
        });
        return accountRelatedTypeDtoResponses;
    }

    private void saveAccountStructureLevel(FinancialAccountRequest financialAccountRequest, FinancialAccount financialAccount) {
        Object financialAccountStructure = null;
        if (financialAccountRequest.getFinancialAccountStructureId() != null) {
            financialAccountStructure = "financialAccountStructure";
        } else {
            financialAccountRequest.setFinancialAccountStructureId(0L);
        }
        List<Object[]> financialAccountStructureListObject =
                accountStructureLevelRepository.findByFinancialAccountStructureListObject(financialAccount.getId());

        financialAccountStructureListObject.forEach(e -> {
            AccountStructureLevel accountStructureLevel = new AccountStructureLevel();
            accountStructureLevel.setFinancialAccount(financialAccount);
            accountStructureLevel.setFinancialAccountStructure(financialAccountStructureRepository.getOne(Long.parseLong(e[2].toString())));
            accountStructureLevel.setStructureLevel(Long.parseLong(e[0].toString()));
            accountStructureLevel.setStructureLevelCode(e[1].toString());
            if (e[3] != null) {
                accountStructureLevel.setRelatedAccountId(Long.parseLong(e[3].toString()));
            }
            accountStructureLevelRepository.save(accountStructureLevel);
        });
    }

    @Override
    @Transactional
    public List<FinancialAccountAdjustmentResponse> getFinancialAccountAdjustmentLov(Long OrganizationId) {
        List<FinancialAccount> financialAccount = financialAccountRepository.findByFinancialAccountAdjustmentByOrganizationId(OrganizationId);
        return financialAccount.stream().map(e -> FinancialAccountAdjustmentResponse.builder().id(e.getId())
                .description(e.getDescription())
                .code(e.getCode())
                .fullDescription(e.getFullDescription())
                .build()).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public FinancialAccountOutPutDto update(FinancialAccountRequest financialAccountRequest) {
        FinancialAccountOutPutDto financialAccountOutPutDto;
        FinancialAccount financialAccount = financialAccountRepository.getOne(financialAccountRequest.getId());
        List<AccountDefaultValueResponse> accountDefaultValueResponses = updateAccountDefaultValue(financialAccountRequest, financialAccount);
        updateAccountStructureLevel(financialAccountRequest, financialAccount);
        financialAccount = saveFinancialAccount(financialAccountRequest);
        financialAccountOutPutDto = convertFinancialAccountDto(financialAccount);
        financialAccountOutPutDto.setAccountDefaultValueOutPutModel(accountDefaultValueResponses);
        financialAccountOutPutDto.setAccountRelatedTypeOutPutModel(updateAccountRelatedType
                (financialAccountRequest.getFinancialAccountTypeId(), financialAccount));
        financialAccountOutPutDto.setAccountMoneyTypeOutPut(updateAccountMoneyType
                (financialAccountRequest.getMoneyTypeId(), financialAccount));
        financialAccountOutPutDto.setAccountRelatedDescriptionOutputModel
                (updateAccountRelatedDescription(financialAccountRequest.getAccountRelatedDescriptionInPutModel(), financialAccount));
        return financialAccountOutPutDto;
    }


    private void updateAccountStructureLevel(FinancialAccountRequest financialAccountRequest, FinancialAccount financialAccount) {
        Object financialAccountStructure = null;
        if (financialAccountRequest.getFinancialAccountStructureId() != null) {
            financialAccountStructure = "financialAccountStructure";
        } else {
            financialAccountRequest.setFinancialAccountStructureId(0L);
        }
        Long countFinancialAccountStructure =
                accountStructureLevelRepository.getAccountStructureLevelByFinancialAccountAndFinancialCodingAndFinancialAccountStructure(financialAccount.getId(), financialAccountRequest.getFinancialCodingTypeId()
                        , financialAccountRequest.getCode(),
                        financialAccountRequest.getFinancialAccountStructureId(),
                        financialAccountStructure);
        if (countFinancialAccountStructure != null) {
            accountStructureLevelRepository.findByFinancialAccountId(financialAccount.getId()).forEach(accountStructureLevel ->
                    accountStructureLevel.setDeletedDate(LocalDateTime.now())
            );
            saveAccountStructureLevel(financialAccountRequest, financialAccount);
        }
    }

    private List<AccountRelatedTypeDtoResponse> updateAccountRelatedType(List<Long> accountRelatedTypeOutPutModel, FinancialAccount financialAccount) {
        accountRelatedTypeRepository.findByFinancialAccountId(financialAccount.getId()).forEach(accountRelatedType ->
                accountRelatedType.setDeletedDate(LocalDateTime.now())
        );
        return saveAccountRelatedType(accountRelatedTypeOutPutModel, financialAccount);
    }

    private List<AccountMoneyTypeDtoResponse> updateAccountMoneyType(List<Long> accountMoneyTypeOutPut, FinancialAccount financialAccount) {
        accountMoneyTypeRepository.findByFinancialAccountId(financialAccount.getId()).forEach(accountMoneyType ->
                accountMoneyType.setDeletedDate(LocalDateTime.now())
        );
        return saveAccountMoneyType(accountMoneyTypeOutPut, financialAccount);
    }

    private List<AccountDefaultValueResponse> updateAccountDefaultValue(FinancialAccountRequest financialAccountRequest, FinancialAccount financialAccount) {
        List<AccountDefaultValueResponse> accountDefaultValueResponses = new ArrayList<>();
        Long accountDefaultValueCount = financialAccountRepository.findByFinancialAccountByAccountRelationTypeId(financialAccountRequest.getAccountRelationTypeId(), financialAccountRequest.getId());

        if (accountDefaultValueCount != null) {
            accountDefaultValueRepository.findByFinancialAccountIdAndDeletedDateIsNull
                    (financialAccountRequest.getId())
                    .forEach(e -> financialAccountRequest.getAccountDefaultValueInPutModel().stream()
                            .filter(accountDefaultValueRequest ->
                                    e.getId().equals(accountDefaultValueRequest.getId()))
                            .forEach(accountDefaultValueRequest -> {
                                if (accountDefaultValueRequest.getCentricAccountId() != null) {
                                    e.setCentricAccount(centricAccountRepository.getOne(accountDefaultValueRequest.getCentricAccountId()));
                                } else {
                                    e.setCentricAccount(null);
                                }
                                accountDefaultValueResponses.add(convertAccountDefaultValueResponse(e));
                            })
                    );
        } else {
            accountDefaultValueRepository.findByFinancialAccountIdAndDeletedDateIsNull(financialAccountRequest.getId())
                    .forEach(e -> e.setDeletedDate(LocalDateTime.now()));
            accountDefaultValueResponses.addAll(saveAccountDefaultValue
                    (financialAccountRequest.getAccountDefaultValueInPutModel(), financialAccount));
        }
        return accountDefaultValueResponses;
    }

    private List<AccountRelatedDescriptionDto> updateAccountRelatedDescription(List<AccountRelatedDescriptionRequest> accountRelatedDescriptionOutPutModel, FinancialAccount financialAccount) {
        List<AccountRelatedDescriptionDto> accountRelatedDescriptionDtos = new ArrayList<>();
        List<AccountRelatedDescriptionRequest> accountRelatedDescriptionOutPutModelRequest = new ArrayList<>();
        List<FinancialAccountDescription> financialAccountDescriptionList = financialAccountDescriptionRepository.findByFinancialAccountDescriptionListId
                (accountRelatedDescriptionOutPutModel
                        .stream()
                        .map(AccountRelatedDescriptionRequest::getFinancialAccountDesId).collect(Collectors.toList()));
        accountRelatedDescriptionOutPutModel.forEach(e -> {
            if (e.getId() != null) {
                financialAccountDescriptionList.stream().filter(f -> f.getId().equals(e.getFinancialAccountDesId()))
                        .findAny()
                        .ifPresent(financialAccountDescription -> {
                            financialAccountDescription.setDescription(e.getDescription());
                            AccountRelatedDescription accountRelatedDescription = accountRelatedDescriptionRepository
                                    .findByFinancialAccountIdAndFinancialAccountDescriptionIdAndDeletedDateIsNull
                                            (financialAccount.getId(), financialAccountDescription.getId());
                            accountRelatedDescriptionDtos.add(accountRelatedDescriptionService.convertAccountRelatedDescriptionDto(accountRelatedDescription));
                        });
            } else {
                accountRelatedDescriptionOutPutModelRequest.add(e);
            }
        });
        if (!accountRelatedDescriptionOutPutModelRequest.isEmpty()) {
            accountRelatedDescriptionDtos.addAll(saveAccountRelatedDescriptionValue
                    (accountRelatedDescriptionOutPutModelRequest, financialAccount));
        }

        return accountRelatedDescriptionDtos;
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public Boolean deleteFinancialAccountById(Long financialAccountId) {
        Long financialAccountStructureCount = financialAccountRepository.findByFinancialAccountId(financialAccountId);
        if (financialAccountStructureCount != null) {
            Long financialDocumentItemCount = financialDocumentItemRepository.getFinancialDocumentItemByFinancialAccountId(financialAccountId);
            if (financialDocumentItemCount > 0) {
                throw new RuleException("حساب مورد نظر در اسناد مالی استفاده شده است");
            } else {
                accountDefaultValueRepository.findByFinancialAccountIdAndDeletedDateIsNull(financialAccountId).forEach(e ->
                        e.setDeletedDate(LocalDateTime.now()));
                accountRelatedDescriptionRepository.findByFinancialAccountId(financialAccountId).forEach(e -> e.setDeletedDate(LocalDateTime.now()));
                accountRelatedTypeRepository.findByFinancialAccountId(financialAccountId).forEach(e -> e.setDeletedDate(LocalDateTime.now()));
                accountMoneyTypeRepository.findByFinancialAccountId(financialAccountId).forEach(e -> e.setDeletedDate(LocalDateTime.now()));

                FinancialAccount financialAccount = financialAccountRepository.getOne(financialAccountId);
                financialAccount.setDeletedDate(LocalDateTime.now());
                return true;
            }
        }
        throw new RuleException("fin.financialAccount.delete");
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public Boolean getFinancialAccountByIdAndStatusFlag(FinancialAccountStatusRequest financialAccountStatusRequest, Long organizationId) {
        FinancialAccount financialAccount = financialAccountRepository.getOne(financialAccountStatusRequest.getFinancialAccountId());
        if (financialAccountStatusRequest.getStatusFlag().equals(false)) {
            List<Long> financialAccountCount = financialAccountRepository.findByFinancialAccountId(financialAccountStatusRequest.getFinancialAccountId(), SecurityHelper.getCurrentUser().getOrganizationId());
            Long financialAccountOrganCount = financialAccountRepository.findByFinancialAccountIdAndOrganization(financialAccountStatusRequest.getFinancialAccountId(), SecurityHelper.getCurrentUser().getOrganizationId());
            if (financialAccountCount.size() != 0 || financialAccountOrganCount != null) {
                throw new RuleException("fin.financialAccount.setStatus");
            }
            financialAccount.setDisableDate(new Date());
        } else {
            Long financialAccountActiveCount = financialAccountRepository.findByFinancialAccountOrganization(financialAccountStatusRequest.getFinancialAccountId(), SecurityHelper.getCurrentUser().getOrganizationId());
            if (financialAccountActiveCount != null) {
                throw new RuleException("fin.financialAccount.parentActive");
            } else {
                Long financialAccountCount = financialAccountRepository.findByFinancialAccountAndIdAndDisableDateIsNotNull(financialAccountStatusRequest.getFinancialAccountId());
                if (financialAccountCount != null) {
                    financialAccount.setDisableDate(null);
                } else {
                    throw new RuleException("fin.financialAccount.statusActive");

                }

            }
        }
        return true;
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public List<FinancialAccountNewResponse> getFinancialAccountByFinancialAccountParentAndCodingAndStructure
            (FinancialAccountNewRequest financialAccountNewRequest) {
        Object financialAccountParent;
        if (financialAccountNewRequest.getFinancialAccountParentId() != null) {
            financialAccountParent = "financialAccountParent";
        } else {
            financialAccountNewRequest.setFinancialAccountParentId(0L);
            financialAccountParent = null;
        }

        Object financialAccountStructure;
        if (financialAccountNewRequest.getFinancialAccountStructureId() != null) {
            financialAccountStructure = "financialAccountStructure";
        } else {
            financialAccountNewRequest.setFinancialAccountStructureId(0L);
            financialAccountStructure = null;
        }

        List<Object[]> financialAccountList = financialAccountRepository.findByFinancialAccountByFinancialAccountParentAndCodingAndStructure
                (financialAccountParent, financialAccountNewRequest.getFinancialAccountParentId(), financialAccountNewRequest.getFinancialCodingTypeId(),
                        financialAccountStructure, financialAccountNewRequest.getFinancialAccountStructureId());

        return financialAccountList.stream().map(e -> FinancialAccountNewResponse.builder().id(Long.parseLong(e[0].toString()))
                .digitCount(Long.parseLong(e[1].toString()))
                .preCode(e[3] == null ? null : e[2].toString())
                .suggestedCode(e[4].toString())
                .flgShowInAcc(Long.parseLong(e[2].toString()))
                .build()).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public List<AccountPermanentStatusDto> getAccountPermanentStatusLov() {
        return accountPermanentStatusRepository.findByAccountPermanentStatus().stream().map(e -> AccountPermanentStatusDto.builder()
                .id(e.getId())
                .code(e.getCode())
                .description(e.getDescription())
                .build()
        ).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public DataSourceResult getFinancialAccountLov(Long OrganizationId, DataSourceRequest dataSourceRequest) {
        return gridFilterService.filter(dataSourceRequest, financialAccountLovProvider);
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public Boolean getFinancialAccountGetInsertAllowControl(FinancialAccountAllowChildRequest financialAccountAllowChildRequest) {
        if (financialAccountAllowChildRequest.getId() == null && financialAccountAllowChildRequest.getFinancialAccountStructureId() != null) {
            List<Long> financialAccountStructureAndCodingTypeCount = financialAccountRepository.findByFinancialAccountIdAndStructureAndCodingType(financialAccountAllowChildRequest.getFinancialAccountStructureId());
            if (financialAccountStructureAndCodingTypeCount.size() != 0) {
                throw new RuleException("fin.financialAccount.save");
            }
        }
        FinancialAccountStructureRequest financialAccountStructureRequest = new FinancialAccountStructureRequest();
        financialAccountStructureRequest.setFinancialAccountStructureId(financialAccountAllowChildRequest.getFinancialAccountStructureId());
        financialAccountStructureRequest.setFinancialCodingTypeId(financialAccountAllowChildRequest.getFinancialCodingTypeId());
        Long financialAccountStructureId = financialAccountStructureService.getFinancialAccountStructureByFinancialCodingTypeAndFinancialAccountStructure
                (financialAccountStructureRequest);
        if (financialAccountAllowChildRequest.getId() == null && financialAccountStructureId == null) {
            throw new RuleException("fin.financialAccount.finalLevelAccount");
        }
        return true;
    }

//    @Override
//    @Transactional(rollbackOn = Throwable.class)
//    public List<FinancialAccountGetByStructureResponse> getFinancialAccountByGetByStructure
//            (Long organizationId, FinancialAccountGetByStructureRequest financialAccountGetByStructureRequest) {
//        if (financialAccountGetByStructureRequest.getFinancialAccountStructureId() == 0L || financialAccountGetByStructureRequest.getFinancialAccountStructureId() == null) {
//            throw new RuleException("fin.financialAccount.getByStructure");
//        }
//        Object financialAccountStructure;
//        if (financialAccountGetByStructureRequest.getFinancialAccountStructureId() != null) {
//            financialAccountStructure = "financialAccountStructure";
//        } else {
//            financialAccountGetByStructureRequest.setFinancialAccountStructureId(0L);
//            financialAccountStructure = null;
//        }
//
//
//        List<Object[]> financialAccountList = financialAccountRepository.findByFinancialAccountByOrganAndFinancialAccountStructureId
//                (SecurityHelper.getCurrentUser().getOrganizationId(), financialAccountStructure, financialAccountGetByStructureRequest.getFinancialAccountStructureId());
//
//        return financialAccountList.stream().map(e -> FinancialAccountGetByStructureResponse.builder().id(Long.parseLong(e[0].toString()))
//                .code(e[1].toString())
//                .description(e[2].toString())
//                .referenceFlag(e[3] == null || ((Boolean) e[3]).equals(0))
//                .exchangeFlag(e[4] == null || ((Boolean) e[4]).equals(0))
//                .accountRelationTypeId(e[5] == null ? null : Long.parseLong(e[5].toString()))
//                .build()).collect(Collectors.toList());
//    }

    @Override
    @Transactional
    public DataSourceResult getFinancialAccountByGetByStructure(Long OrganizationId, DataSourceRequest dataSourceRequest) {
        dataSourceRequest.getFilter().getFilters().add(DataSourceRequest.FilterDescriptor.create("deletedDate", null, DataSourceRequest.Operators.IS_NULL));
        dataSourceRequest.getFilter().getFilters().add(DataSourceRequest.FilterDescriptor.create("disableDate", null, DataSourceRequest.Operators.IS_NULL));
        dataSourceRequest.getFilter().getFilters().add(DataSourceRequest.FilterDescriptor.create("financialAccountStructure.deletedDate", null, DataSourceRequest.Operators.IS_NULL));
        dataSourceRequest.getFilter().getFilters().add(DataSourceRequest.FilterDescriptor
                .create("organization.id", SecurityHelper.getCurrentUser().getOrganizationId(), DataSourceRequest.Operators.EQUALS));
        return gridFilterService.filter(dataSourceRequest, financialAccountGetByStructureProvider);

    }
}
