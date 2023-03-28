package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.FinancialAccountParameter;
import ir.demisco.cfs.model.dto.request.AccountDefaultValueRequest;
import ir.demisco.cfs.model.dto.request.AccountRelatedDescriptionRequest;
import ir.demisco.cfs.model.dto.request.FinancialAccountAllowChildRequest;
import ir.demisco.cfs.model.dto.request.FinancialAccountLovRequest;
import ir.demisco.cfs.model.dto.request.FinancialAccountNewRequest;
import ir.demisco.cfs.model.dto.request.FinancialAccountRequest;
import ir.demisco.cfs.model.dto.request.FinancialAccountStatusRequest;
import ir.demisco.cfs.model.dto.request.FinancialAccountStructureNewRequest;
import ir.demisco.cfs.model.dto.request.FinancialAccountStructureRequest;
import ir.demisco.cfs.model.dto.response.AccountDefaultValueResponse;
import ir.demisco.cfs.model.dto.response.AccountMoneyTypeDtoResponse;
import ir.demisco.cfs.model.dto.response.AccountMoneyTypeResponse;
import ir.demisco.cfs.model.dto.response.AccountPermanentStatusDto;
import ir.demisco.cfs.model.dto.response.AccountRelatedDescriptionDto;
import ir.demisco.cfs.model.dto.response.AccountRelatedDescriptionResponse;
import ir.demisco.cfs.model.dto.response.AccountRelatedTypeDtoResponse;
import ir.demisco.cfs.model.dto.response.AccountRelatedTypeNewResponse;
import ir.demisco.cfs.model.dto.response.FinancialAccountAdjustmentResponse;
import ir.demisco.cfs.model.dto.response.FinancialAccountDto;
import ir.demisco.cfs.model.dto.response.FinancialAccountGetByStructureResponse;
import ir.demisco.cfs.model.dto.response.FinancialAccountLovResponse;
import ir.demisco.cfs.model.dto.response.FinancialAccountNewResponse;
import ir.demisco.cfs.model.dto.response.FinancialAccountOutPutDto;
import ir.demisco.cfs.model.dto.response.FinancialAccountOutPutResponse;
import ir.demisco.cfs.model.dto.response.FinancialAccountStructureNewResponse;
import ir.demisco.cfs.model.entity.AccountDefaultValue;
import ir.demisco.cfs.model.entity.AccountMoneyType;
import ir.demisco.cfs.model.entity.AccountRelatedDescription;
import ir.demisco.cfs.model.entity.AccountRelatedType;
import ir.demisco.cfs.model.entity.AccountStructureLevel;
import ir.demisco.cfs.model.entity.FinancialAccount;
import ir.demisco.cfs.model.entity.FinancialAccountDescription;
import ir.demisco.cfs.service.api.AccountRelatedDescriptionService;
import ir.demisco.cfs.service.api.FinancialAccountService;
import ir.demisco.cfs.service.api.FinancialAccountStructureService;
import ir.demisco.cfs.service.repository.AccountDefaultValueRepository;
import ir.demisco.cfs.service.repository.AccountMoneyTypeRepository;
import ir.demisco.cfs.service.repository.AccountNatureTypeRepository;
import ir.demisco.cfs.service.repository.AccountPermanentStatusRepository;
import ir.demisco.cfs.service.repository.AccountRelatedDescriptionRepository;
import ir.demisco.cfs.service.repository.AccountRelatedTypeRepository;
import ir.demisco.cfs.service.repository.AccountRelationTypeDetailRepository;
import ir.demisco.cfs.service.repository.AccountRelationTypeRepository;
import ir.demisco.cfs.service.repository.AccountStructureLevelRepository;
import ir.demisco.cfs.service.repository.CentricAccountRepository;
import ir.demisco.cfs.service.repository.FinancialAccountDescriptionRepository;
import ir.demisco.cfs.service.repository.FinancialAccountRepository;
import ir.demisco.cfs.service.repository.FinancialAccountStructureRepository;
import ir.demisco.cfs.service.repository.FinancialAccountTypeRepository;
import ir.demisco.cfs.service.repository.FinancialDocumentItemRepository;
import ir.demisco.cfs.service.repository.MoneyTypeRepository;
import ir.demisco.cfs.service.repository.OrganizationRepository;
import ir.demisco.cloud.core.middle.exception.RuleException;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import ir.demisco.cloud.core.security.util.SecurityHelper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

    public DefaultFinancialAccount(FinancialAccountRepository financialAccountRepository, CentricAccountRepository centricAccountRepository, FinancialAccountTypeRepository financialAccountTypeRepository, AccountRelatedDescriptionRepository accountRelatedDescriptionRepository, MoneyTypeRepository moneyTypeRepository, OrganizationRepository organizationRepository, AccountNatureTypeRepository accountNatureTypeRepository, AccountRelationTypeRepository accountRelationTypeRepository, FinancialAccountStructureService financialAccountStructureService, AccountRelatedTypeRepository accountRelatedTypeRepository, AccountMoneyTypeRepository accountMoneyTypeRepository, AccountDefaultValueRepository accountDefaultValueRepository, AccountRelationTypeDetailRepository accountRelationTypeDetailRepository, AccountStructureLevelRepository accountStructureLevelRepository, AccountRelatedDescriptionService accountRelatedDescriptionService, FinancialAccountDescriptionRepository financialAccountDescriptionRepository, FinancialDocumentItemRepository financialDocumentItemRepository, FinancialAccountStructureRepository financialAccountStructureRepository1, AccountPermanentStatusRepository accountPermanentStatusRepository) {

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
    }

    @Override
    @Transactional
    public DataSourceResult getFinancialAccount(DataSourceRequest dataSourceRequest) {
        List<DataSourceRequest.FilterDescriptor> filters = dataSourceRequest.getFilter().getFilters();
        FinancialAccountParameter param = setParameter(filters);
        Map<String, Object> paramMap = param.getParamMap();
        param.setOrganizationId(SecurityHelper.getCurrentUser().getOrganizationId());
        List<Sort.Order> sorts = new ArrayList<>();
        dataSourceRequest.getSort()
                .forEach((DataSourceRequest.SortDescriptor sortDescriptor) ->
                        {
                            if (sortDescriptor.getDir().equals("asc")) {
                                sorts.add(Sort.Order.asc(sortDescriptor.getField()));
                            } else {
                                sorts.add(Sort.Order.desc(sortDescriptor.getField()));
                            }
                        }
                );
        Pageable pageable = PageRequest.of(dataSourceRequest.getSkip(), dataSourceRequest.getTake(), Sort.by(sorts));
        Page<Object[]> list = financialAccountRepository.financialAccountList(param.getOrganizationId(), param.getFinancialCodingTypeId(), param.getDescription(), paramMap.get("financialAccountParent"), param.getFinancialAccountParentId()
                , paramMap.get("accountNatureType"), param.getAccountNatureTypeId(), paramMap.get("financialAccountStructure"), param.getFinancialAccountStructureId(), paramMap.get("accountRelationType"), param.getAccountRelationTypeId()
                , pageable);
        List<FinancialAccountDto> financialAccountDtos = list.stream().map(item ->
                FinancialAccountDto.builder()
                        .id(Long.parseLong(item[0].toString()))
                        .organizationId(Long.parseLong(item[1].toString()))
                        .description(item[3].toString())
                        .code(item[2].toString())
                        .activeFlag(getItemForLong(item, 12))
                        .accountNatureTypeId(getItemForLong(item, 8))
                        .accountRelationTypeDescription(getItemForString(item, 14))
                        .accountRelationTypeId(getItemForLong(item, 10))
                        .accountNatureTypeDescription(getItemForString(item, 13))
                        .financialAccountParentId(getItemForLong(item, 11))
                        .financialAccountStructureId(getItemForLong(item, 9))
                        .hasChild(getItemForLong(item, 15))
                        .accountStatusId(getItemForLong(item, 16))
                        .accountStatusCode(getItemForString(item, 17))
                        .accountStatusDescription(item[18] == null ? null : (item[18].toString()))
                        .flgShowInAcc(Integer.parseInt(item[19].toString()) == 1)
                        .flgPermanentStatus(Integer.parseInt(item[20].toString()) == 1)
                        .color(item[21] == null ? null : item[21].toString())
                        .relatedToOtherFlag(item[4] == null ? null : Long.parseLong(item[4].toString()))
                        .referenceFlag(item[5] == null ? null : Long.parseLong(item[5].toString()))
                        .convertFlag(item[6] == null ? 0 : Long.parseLong(item[6].toString()))
                        .exchangeFlag(item[7] == null ? null : Long.parseLong(item[7].toString()))
                        .build()).collect(Collectors.toList());
        DataSourceResult dataSourceResult = new DataSourceResult();
        dataSourceResult.setData(financialAccountDtos);
        dataSourceResult.setTotal(list.getTotalElements());
        return dataSourceResult;
    }

    private Long getItemForLong(Object[] item, int i) {
        return item[i] == null ? null : Long.parseLong(item[i].toString());
    }

    private String getItemForString(Object[] item, int i) {
        return item[i] == null ? null : item[i].toString();
    }

    private FinancialAccountParameter setParameter(List<DataSourceRequest.FilterDescriptor> filters) {
        FinancialAccountParameter financialAccountParameter = new FinancialAccountParameter();
        for (DataSourceRequest.FilterDescriptor item : filters) {
            switch (item.getField()) {
                case "financialAccountStructure.financialCodingType.id":
                    checkFinancialAccountStructureCodingTypeSet(financialAccountParameter, item);
                    break;
                case "accountNatureType.id":
                    checkAccountNatureTypeSet(financialAccountParameter, item);
                    break;
                case "financialAccountStructure.id":
                    checkFinancialAccountStructureIdSet(financialAccountParameter, item);
                    break;
                case "accountRelationType.id":
                    checkAccountRelationType(financialAccountParameter, item);
                    break;
                case "financialAccountParent.id":
                    checkFinancialAccountParent(financialAccountParameter, item);
                    break;
                case "description":
                    if (item.getValue() != null) {
                        financialAccountParameter.setDescription(item.getValue().toString());
                    }
                    break;
                default:
                    break;
            }
        }
        return financialAccountParameter;
    }

    private void checkFinancialAccountParent(FinancialAccountParameter financialAccountParameter, DataSourceRequest.FilterDescriptor item) {
        Map<String, Object> map = new HashMap<>();
        if (item.getValue() != null) {
            map.put("financialAccountParent", "financialAccountParent");
            financialAccountParameter.setParamMap(map);
            financialAccountParameter.setFinancialAccountParentId(Long.parseLong(item.getValue().toString()));
        } else {
            map.put("financialAccountParent", null);
            financialAccountParameter.setParamMap(map);
            financialAccountParameter.setFinancialAccountParentId(0L);
        }
    }

    private void checkAccountRelationType(FinancialAccountParameter financialAccountParameter, DataSourceRequest.FilterDescriptor item) {
        Map<String, Object> map = new HashMap<>();
        if (item.getValue() != null) {
            map.put("accountRelationType", "accountRelationType");
            financialAccountParameter.setParamMap(map);
            financialAccountParameter.setAccountRelationTypeId(Long.parseLong(item.getValue().toString()));
        } else {
            map.put("accountRelationType", null);
            financialAccountParameter.setParamMap(map);
            financialAccountParameter.setAccountRelationTypeId(0L);
        }
    }

    private void checkFinancialAccountStructureCodingTypeSet(FinancialAccountParameter financialAccountParameter, DataSourceRequest.FilterDescriptor item) {
        financialAccountParameter.setFinancialCodingTypeId(Long.parseLong(item.getValue().toString()));
    }

    private void checkAccountNatureTypeSet(FinancialAccountParameter financialAccountParameter, DataSourceRequest.FilterDescriptor item) {
        Map<String, Object> map = new HashMap<>();
        if (item.getValue() != null) {
            map.put("accountNatureType", "accountNatureType");
            financialAccountParameter.setParamMap(map);
            financialAccountParameter.setAccountNatureTypeId(Long.parseLong(item.getValue().toString()));
        } else {
            map.put("accountNatureType", null);
            financialAccountParameter.setParamMap(map);
            financialAccountParameter.setAccountNatureTypeId(0L);
        }
    }

    private void checkFinancialAccountStructureIdSet(FinancialAccountParameter financialAccountParameter, DataSourceRequest.FilterDescriptor item) {
        Map<String, Object> map = new HashMap<>();
        if (item.getValue() != null) {
            map.put("financialAccountStructure", "financialAccountStructure");
            financialAccountParameter.setParamMap(map);
            financialAccountParameter.setFinancialAccountStructureId(Long.parseLong(item.getValue().toString()));
        } else {
            map.put("financialAccountStructure", null);
            financialAccountParameter.setParamMap(map);
            financialAccountParameter.setFinancialAccountStructureId(0L);
        }
    }

    @Override
    @Transactional
    public FinancialAccountOutPutResponse getFinancialAccountGetById(Long financialAccountId, Long organizationId) {
        FinancialAccount financialAccount = financialAccountRepository.findById(financialAccountId).orElseThrow(() -> new RuleException("fin.ruleException.notFoundId"));
        List<FinancialAccount> financialAccountParent = financialAccountRepository.findByFinancialAccountParentId(financialAccountId);
        Boolean flagPermanent;
        if (financialAccountParent.isEmpty()) {
            flagPermanent = financialAccount.getFinancialAccountStructure().getFlgPermanentStatus();
        } else {
            flagPermanent = false;
        }
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
                .flgPermanentStatus(flagPermanent)
                .profitLossAccountFlag(financialAccount.getProfitLossAccountFlag())
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
                .accountRelatedDescriptionId(Long.parseLong(objects[2].toString()))
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

        saveAccountStructureLevel(financialAccount);
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
        partOne(financialAccountRequest);
        FinancialAccountStructureNewRequest financialAccountStructureNewRequest = new FinancialAccountStructureNewRequest();
        financialAccountStructureNewRequest.setFinancialAccountParentId(financialAccountRequest.getFinancialAccountParentId());
        financialAccountStructureNewRequest.setFinancialCodingTypeId(financialAccountRequest.getFinancialCodingTypeId());
        financialAccountStructureNewRequest.setFinancialAccountStructureId(financialAccountRequest.getFinancialAccountStructureId());
        financialAccountStructureNewRequest.setFlgEditMode(checkNull(financialAccountRequest));

        FinancialAccount financialAccount = financialAccountRepository.findById(financialAccountRequest.getId() == null ? 0L : financialAccountRequest.getId()).orElse(new FinancialAccount());
        if (financialAccountRequest.getId() == 0L) {
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
            Long financialAccountStructureByCodeAndChild = financialAccountStructureRepository.getFinancialAccountStructureByCodeAndChild(financialAccountStructureId, financialAccountRequest.getCode());

            if (financialAccountStructureByCodeAndChild == null) {
                throw new RuleException("fin.financialAccount.structureCode");
            }

            Long financialAccountStructureByChildAccountStructureAndCode = financialAccountStructureRepository.getFinancialAccountStructureByChildAccountStructureAndCode(financialAccountStructureId, financialAccountRequest.getCode());
            if (financialAccountStructureByChildAccountStructureAndCode != null) {
                throw new RuleException("fin.financialAccountStructure.saveFinancialAccount");
            }
            if (financialAccountRequest.getFinancialAccountParentId() != null) {
                List<Object[]> financialAccountParent = financialAccountRepository.findByFinancialAccountAndFinancialAccountParent(financialAccountRequest.getFinancialAccountParentId());
                Optional<String> financialAccountParentOptional = financialAccountParent.stream().map(objects -> objects[2].toString()).findFirst();
                if (financialAccountParentOptional.isPresent()) {
                    newGeneratedCode = financialAccountParentOptional.get();
                    financialAccountRequest.setCode(newGeneratedCode + financialAccountRequest.getCode());
                }
            }
            if (financialAccountRequest.getId() == null) {

                financialAccountCodeCount = financialAccountRepository.getCountByFinancialAccountAndCode(financialAccountRequest.getCode(), financialAccountStructureId, SecurityHelper.getCurrentUser().getOrganizationId());

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
        }

        financialAccount.setOrganization(organizationRepository.getOne(SecurityHelper.getCurrentUser().getOrganizationId()));
        financialAccount.setFullDescription(financialAccountRequest.getFullDescription());
        financialAccount.setCode(financialAccountRequest.getCode());
        financialAccount.setDescription(financialAccountRequest.getDescription());
        financialAccount.setLatinDescription(financialAccountRequest.getLatinDescription());
        financialAccount = financialAccountRepository.save(partTwo(financialAccount, financialAccountRequest));
        return financialAccount;
    }

    private FinancialAccount partTwo(FinancialAccount financialAccount, FinancialAccountRequest financialAccountRequest) {
        if (financialAccountRequest.getAccountNatureTypeId() != null) {
            financialAccount.setAccountNatureType(accountNatureTypeRepository.getOne(financialAccountRequest.getAccountNatureTypeId()));
        } else {
            financialAccount.setAccountNatureType(null);
        }
        financialAccount.setRelatedToOthersFlag(financialAccountRequest.getRelatedToOthersFlag());
        if (financialAccountRequest.getAccountRelationTypeId() != null) {
            financialAccount.setAccountRelationType(accountRelationTypeRepository.getOne(financialAccountRequest.getAccountRelationTypeId()));
        } else {
            financialAccount.setAccountRelationType(null);
        }
        if (financialAccountRequest.getFinancialAccountParentId() != null) {
            financialAccount.setFinancialAccountParent(financialAccountRepository.getOne(financialAccountRequest.getFinancialAccountParentId()));
        } else {
            financialAccount.setFinancialAccountParent(null);
        }
        if (financialAccountRequest.getAccountStatusId() != null) {
            financialAccount.setAccountPermanentStatus(accountPermanentStatusRepository.getOne(financialAccountRequest.getAccountStatusId()));
        } else {
            financialAccount.setAccountPermanentStatus(null);
        }
        financialAccount.setRelatedToFundType(financialAccountRequest.getRelatedToFundType());
        financialAccount.setReferenceFlag(financialAccountRequest.getReferenceFlag());
        financialAccount.setConvertFlag(financialAccountRequest.getConvertFlag());
        financialAccount.setExchangeFlag(financialAccountRequest.getExchangeFlag());
        if (financialAccountRequest.getAccountAdjustmentId() != null) {
            financialAccount.setAccountAdjustment(financialAccountRepository.getOne(financialAccountRequest.getAccountAdjustmentId()));
        } else {
            financialAccount.setAccountAdjustment(null);
        }
        if (financialAccountRequest.getProfitLossAccountFlag() == null) {
            financialAccount.setProfitLossAccountFlag(false);
        } else {
            financialAccount.setProfitLossAccountFlag(financialAccountRequest.getProfitLossAccountFlag());
        }
        return financialAccount;
    }

    private void partOne(FinancialAccountRequest financialAccountRequest) {
        if (financialAccountRequest.getId() == null && financialAccountRequest.getFinancialAccountStructureId() != null) {
            List<Long> financialAccountStructureAndCodingTypeCount = financialAccountRepository.findByFinancialAccountIdAndStructureAndCodingType(financialAccountRequest.getFinancialAccountStructureId());
            if (financialAccountStructureAndCodingTypeCount.size() != 0) {
                throw new RuleException("fin.financialAccount.save");
            }
        }
        if (financialAccountRequest.getId() != null) {
            List<Long> financialDocumentItem = financialDocumentItemRepository.findByFinancialDocumentItemByFinancialAccountId(financialAccountRequest.getId());
            if (financialDocumentItem.size() != 0) {
                throw new RuleException("fin.financialAccount.update.useFinancialDocumentItem");
            }
        }
        Object idObject = null;
        if (financialAccountRequest.getId() != null) {
            idObject = "idObject";
        } else {
            financialAccountRequest.setId(0L);
        }
        if (financialAccountRequest.getProfitLossAccountFlag() == null) {
            financialAccountRequest.setProfitLossAccountFlag(false);
        }
        if (financialAccountRequest.getProfitLossAccountFlag().equals(true)) {
            String code = financialAccountRepository.findByFinancialAccountIdForDelete(idObject, financialAccountRequest.getId(), financialAccountRequest.getFinancialAccountStructureId());
            if (code != null) {
                throw new RuleException(" در این کدینگ ، حساب ".concat(code.concat("  به عنوان حساب سود و زیانی مشخص شده است ")));

            }
        }
    }

    private Boolean checkNull(FinancialAccountRequest financialAccountRequest) {
        return financialAccountRequest.getId() == null ? Boolean.FALSE : Boolean.TRUE;

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

    private List<AccountDefaultValueResponse> saveAccountDefaultValue
            (List<AccountDefaultValueRequest> accountDefaultValueOutPutModel, FinancialAccount financialAccount) {
        List<AccountDefaultValueResponse> accountDefaultValueDtos = new ArrayList<>();
        accountDefaultValueOutPutModel.forEach((AccountDefaultValueRequest e) -> {
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

    private List<AccountRelatedDescriptionDto> saveAccountRelatedDescriptionValue
            (List<AccountRelatedDescriptionRequest> accountRelatedDescriptionOutPutModel, FinancialAccount
                    financialAccount) {
        List<AccountRelatedDescriptionDto> accountRelatedDescriptionDtos = new ArrayList<>();
        accountRelatedDescriptionOutPutModel.forEach((AccountRelatedDescriptionRequest e) -> {
            e.setFinancialAccountId(financialAccount.getId());
            AccountRelatedDescriptionDto accountRelatedDescriptionDto = accountRelatedDescriptionService.save(e);
            accountRelatedDescriptionDtos.add(accountRelatedDescriptionDto);
        });
        return accountRelatedDescriptionDtos;
    }

    private List<AccountMoneyTypeDtoResponse> saveAccountMoneyType
            (List<Long> accountMoneyTypeOutPut, FinancialAccount financialAccount) {
        List<AccountMoneyTypeDtoResponse> accountMoneyTypeDtoResponses = new ArrayList<>();
        accountMoneyTypeOutPut.forEach((Long e) -> {
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

    private List<AccountRelatedTypeDtoResponse> saveAccountRelatedType
            (List<Long> accountRelatedTypeOutPutModel, FinancialAccount financialAccount) {
        List<AccountRelatedTypeDtoResponse> accountRelatedTypeDtoResponses = new ArrayList<>();
        accountRelatedTypeOutPutModel.forEach((Long e) -> {
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

    private void saveAccountStructureLevel(FinancialAccount financialAccount) {
        List<Object[]> financialAccountStructureListObject =
                accountStructureLevelRepository.findByFinancialAccountStructureListObject(financialAccount.getId());
        if (!financialAccountStructureListObject.isEmpty()) {

            financialAccountStructureListObject.forEach((Object[] e) -> {
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
    }

    @Override
    @Transactional
    public DataSourceResult getFinancialAccountAdjustmentLov(Long organizationId, DataSourceRequest
            dataSourceRequest) {
        List<DataSourceRequest.FilterDescriptor> filters = dataSourceRequest.getFilter().getFilters();
        FinancialAccountAdjustmentResponse param = setParameterFinancialAccountAdjustmentLov(filters);
        Map<String, Object> paramMap = param.getParamMap();
        Pageable pageable = PageRequest.of(dataSourceRequest.getSkip(), dataSourceRequest.getTake());
        Page<Object[]> list = financialAccountRepository.financialAccountAdjustment(SecurityHelper.getCurrentUser().getOrganizationId(), paramMap.get("descriptionObject"),
                param.getDescription(), paramMap.get("codeObject"), param.getCode(), paramMap.get("fullDescriptionObject"), param.getFullDescription(), pageable);
        List<FinancialAccountAdjustmentResponse> centricAccountListDtos = list.stream().map(item ->
                FinancialAccountAdjustmentResponse.builder()
                        .id(Long.parseLong(item[0].toString()))
                        .fullDescription(item[1] == null ? null : item[1].toString())
                        .code(item[2] == null ? null : item[2].toString())
                        .description(item[3].toString())
                        .build()).collect(Collectors.toList());
        DataSourceResult dataSourceResult = new DataSourceResult();
        dataSourceResult.setData(centricAccountListDtos);
        dataSourceResult.setTotal(list.getTotalElements());
        return dataSourceResult;
    }

    private FinancialAccountAdjustmentResponse setParameterFinancialAccountAdjustmentLov
            (List<DataSourceRequest.FilterDescriptor> filters) {
        FinancialAccountAdjustmentResponse financialAccountAdjustmentResponse = new FinancialAccountAdjustmentResponse();
        Map<String, Object> map = new HashMap<>();
        for (DataSourceRequest.FilterDescriptor item : filters) {
            switch (item.getField()) {
                case "description":
                    checkDescriptionObject(financialAccountAdjustmentResponse, item);
                    break;
                case "code":
                    checkCodeObject(financialAccountAdjustmentResponse, item);
                    break;
                case "fullDescription":
                    checkFullDescriptionObject(financialAccountAdjustmentResponse, item);
                    break;
                default:
                    break;

            }
        }
        financialAccountAdjustmentResponse.setParamMap(map);
        return financialAccountAdjustmentResponse;
    }

    private void checkFullDescriptionObject(FinancialAccountAdjustmentResponse
                                                    financialAccountAdjustmentResponse, DataSourceRequest.FilterDescriptor item) {
        Map<String, Object> map = new HashMap<>();
        if (item.getValue() != null) {
            map.put("fullDescriptionObject", "fullDescriptionObject");
            financialAccountAdjustmentResponse.setFullDescription(item.getValue().toString());
        } else {
            map.put("fullDescriptionObject", null);
            financialAccountAdjustmentResponse.setFullDescription(null);
        }
    }

    private void checkDescriptionObject(FinancialAccountAdjustmentResponse
                                                financialAccountAdjustmentResponse, DataSourceRequest.FilterDescriptor item) {
        Map<String, Object> map = new HashMap<>();
        if (item.getValue() != null) {
            map.put("descriptionObject", "descriptionObject");
            financialAccountAdjustmentResponse.setDescription(item.getValue().toString());
        } else {
            map.put("descriptionObject", null);
            financialAccountAdjustmentResponse.setDescription(null);
        }
    }

    private void checkCodeObject(FinancialAccountAdjustmentResponse
                                         financialAccountAdjustmentResponse, DataSourceRequest.FilterDescriptor item) {
        Map<String, Object> map = new HashMap<>();
        if (item.getValue() != null) {
            map.put("codeObject", "codeObject");
            financialAccountAdjustmentResponse.setCode(item.getValue().toString());
        } else {
            map.put("codeObject", null);
            financialAccountAdjustmentResponse.setCode(null);
        }
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


    private void updateAccountStructureLevel(FinancialAccountRequest financialAccountRequest, FinancialAccount
            financialAccount) {
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
            accountStructureLevelRepository.findByFinancialAccountId(financialAccount.getId()).forEach(accountStructureLevel -> accountStructureLevelRepository.deleteById(accountStructureLevel.getId()));
            saveAccountStructureLevel(financialAccount);
        }
    }

    private List<AccountRelatedTypeDtoResponse> updateAccountRelatedType
            (List<Long> accountRelatedTypeOutPutModel, FinancialAccount financialAccount) {
        accountRelatedTypeRepository.findByFinancialAccountId(financialAccount.getId()).forEach(accountRelatedType -> accountRelatedTypeRepository.deleteById(accountRelatedType.getId()));
        return saveAccountRelatedType(accountRelatedTypeOutPutModel, financialAccount);
    }

    private List<AccountMoneyTypeDtoResponse> updateAccountMoneyType
            (List<Long> accountMoneyTypeOutPut, FinancialAccount financialAccount) {
        accountMoneyTypeRepository.findByFinancialAccountId(financialAccount.getId()).forEach(accountMoneyType -> accountMoneyTypeRepository.deleteById(accountMoneyType.getId()));
        return saveAccountMoneyType(accountMoneyTypeOutPut, financialAccount);
    }

    private List<AccountDefaultValueResponse> updateAccountDefaultValue(FinancialAccountRequest
                                                                                financialAccountRequest, FinancialAccount financialAccount) {
        List<AccountDefaultValueResponse> accountDefaultValueResponses = new ArrayList<>();
        Long accountDefaultValueCount = financialAccountRepository.findByFinancialAccountByAccountRelationTypeId(financialAccountRequest.getAccountRelationTypeId(), financialAccountRequest.getId());

        if (accountDefaultValueCount != null) {
            accountDefaultValueRepository.findByFinancialAccountIdAndDeletedDateIsNull
                    (financialAccountRequest.getId())
                    .forEach(e -> financialAccountRequest.getAccountDefaultValueInPutModel().stream()
                            .filter(accountDefaultValueRequest ->
                                    e.getId().equals(accountDefaultValueRequest.getId()))
                            .forEach((AccountDefaultValueRequest accountDefaultValueRequest) -> {
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
                    .forEach(e -> accountDefaultValueRepository.deleteById(e.getId()));
            accountDefaultValueResponses.addAll(saveAccountDefaultValue
                    (financialAccountRequest.getAccountDefaultValueInPutModel(), financialAccount));
        }
        return accountDefaultValueResponses;
    }

    private List<AccountRelatedDescriptionDto> updateAccountRelatedDescription
            (List<AccountRelatedDescriptionRequest> accountRelatedDescriptionOutPutModel, FinancialAccount
                    financialAccount) {
        List<AccountRelatedDescriptionDto> accountRelatedDescriptionDtos = new ArrayList<>();
        List<AccountRelatedDescriptionRequest> accountRelatedDescriptionOutPutModelRequest = new ArrayList<>();
        List<FinancialAccountDescription> financialAccountDescriptionList = financialAccountDescriptionRepository.findByFinancialAccountDescriptionListId
                (accountRelatedDescriptionOutPutModel
                        .stream()
                        .map(AccountRelatedDescriptionRequest::getFinancialAccountDesId).collect(Collectors.toList()));
        accountRelatedDescriptionOutPutModel.forEach((AccountRelatedDescriptionRequest e) -> {
            if (e.getId() != null) {
                financialAccountDescriptionList.stream().filter(f -> f.getId().equals(e.getFinancialAccountDesId()))
                        .findAny()
                        .ifPresent((FinancialAccountDescription financialAccountDescription) -> {
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
                Long byFinancialAccountIdForDelete = financialAccountRepository.findByFinancialAccountIdForDelete(financialAccountId);
                if (byFinancialAccountIdForDelete == 0L) {
                    accountDefaultValueRepository.findByFinancialAccountIdAndDeletedDateIsNull(financialAccountId).forEach(e -> accountDefaultValueRepository.deleteById(e.getId()));
                    accountRelatedDescriptionRepository.findByFinancialAccountId(financialAccountId).forEach(e -> accountRelatedDescriptionRepository.deleteById(e.getId()));
                    accountRelatedTypeRepository.findByFinancialAccountId(financialAccountId).forEach(e -> accountRelatedTypeRepository.deleteById(e.getId()));
                    accountMoneyTypeRepository.findByFinancialAccountId(financialAccountId).forEach(e -> accountMoneyTypeRepository.deleteById(e.getId()));
                    financialAccountRepository.deleteById(financialAccountId);
                    return true;
                } else {
                    throw new RuleException("fin.financialAccountStructure.check.for.delete");
                }
            }
        }
        throw new RuleException("fin.financialAccount.delete");
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public Boolean getFinancialAccountByIdAndStatusFlag(FinancialAccountStatusRequest
                                                                financialAccountStatusRequest, Long organizationId) {
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
        List<Long> financialStructure = financialAccountStructureRepository.findFinancialAccountStructureByCoding(financialAccountNewRequest.getFinancialCodingTypeId());
        if (financialStructure.size() == 0) {
            throw new RuleException("fin.financialAccountStructure.flg.getPermanentStatus");
        }

        List<Object[]> financialAccountList = financialAccountRepository.findByFinancialAccountByFinancialAccountParentAndCodingAndStructure
                (financialAccountParent, financialAccountNewRequest.getFinancialAccountParentId(), financialAccountNewRequest.getFinancialCodingTypeId(),
                        financialAccountStructure, financialAccountNewRequest.getFinancialAccountStructureId());

        return financialAccountList.stream().map(e -> FinancialAccountNewResponse.builder().id(Long.parseLong(e[0].toString()))
                .digitCount(Long.parseLong(e[1].toString()))
                .preCode(e[3] == null ? null : e[3].toString())
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
    @Transactional(rollbackOn = Throwable.class)
    public Boolean getFinancialAccountGetInsertAllowControl(FinancialAccountAllowChildRequest
                                                                    financialAccountAllowChildRequest) {
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

    private void checkCodeObject(FinancialAccountStructureRequest
                                         financialAccountStructureRequest, DataSourceRequest.FilterDescriptor item) {
        Map<String, Object> map = new HashMap<>();
        if (item.getValue() != null) {
            map.put("codeObject", "codeObject");
            financialAccountStructureRequest.setParamMap(map);
            financialAccountStructureRequest.setCode(item.getValue().toString());
        } else {
            map.put("codeObject", null);
            financialAccountStructureRequest.setParamMap(map);
            financialAccountStructureRequest.setCode(null);
        }
    }

    private FinancialAccountStructureRequest setParameterFinancialAccountByGetByStructure
            (List<DataSourceRequest.FilterDescriptor> filters) {
        FinancialAccountStructureRequest financialAccountStructureRequest = new FinancialAccountStructureRequest();
        Map<String, Object> map = new HashMap<>();
        for (DataSourceRequest.FilterDescriptor item : filters) {
            switch (item.getField()) {
                case "financialAccountStructure.id":
                    financialAccountStructureRequest.setFinancialAccountStructureId(Long.parseLong(item.getValue().toString()));
                    financialAccountStructureRequest.setParamMap(map);
                    break;

                case "description":
                    checkDescription(financialAccountStructureRequest, item);
                    break;
                case "code":
                    checkCodeObject(financialAccountStructureRequest, item);
                    break;
                default:
                    break;
            }
        }
        return financialAccountStructureRequest;
    }

    private void checkDescription(FinancialAccountStructureRequest
                                          financialAccountStructureRequest, DataSourceRequest.FilterDescriptor item) {
        Map<String, Object> map = new HashMap<>();
        if (item.getValue() != null) {
            map.put("descriptionObject", "descriptionObject");
            financialAccountStructureRequest.setParamMap(map);
            financialAccountStructureRequest.setDescription(item.getValue().toString());
        } else {
            map.put("descriptionObject", null);
            financialAccountStructureRequest.setParamMap(map);
            financialAccountStructureRequest.setDescription(null);
        }
    }

    @Override
    @Transactional
    public DataSourceResult getFinancialAccountByGetByStructure(DataSourceRequest
                                                                        dataSourceRequest) {
        List<DataSourceRequest.FilterDescriptor> filters = dataSourceRequest.getFilter().getFilters();
        FinancialAccountStructureRequest param = setParameterFinancialAccountByGetByStructure(filters);
        List<Object[]> list = financialAccountRepository.financialAccountGetByStructure(param.getFinancialAccountStructureId(), SecurityHelper.getCurrentUser().getOrganizationId()
        );

        List<FinancialAccountGetByStructureResponse> financialAccountDtos = list.stream().map(item ->
                FinancialAccountGetByStructureResponse.builder()
                        .id(Long.parseLong(item[0].toString()))
                        .code(item[1] == null ? null : item[1].toString())
                        .description(item[2].toString())
                        .referenceFlag(item[3] == null ? null : Long.parseLong(item[3].toString()))
                        .exchangeFlag(item[4] == null ? null : Long.parseLong(item[4].toString()))
                        .accountRelationTypeId(item[5] == null ? null : Long.parseLong(item[5].toString()))
                        .build()).collect(Collectors.toList());
        DataSourceResult dataSourceResult = new DataSourceResult();
        dataSourceResult.setData(financialAccountDtos);
        dataSourceResult.setTotal(list.size());
        return dataSourceResult;
    }

    @Override
    @Transactional
    public DataSourceResult getFinancialAccountLov(DataSourceRequest dataSourceRequest) {
        List<DataSourceRequest.FilterDescriptor> filters = dataSourceRequest.getFilter().getFilters();
        FinancialAccountLovRequest param = setFinancialAccountLov(filters);
        Map<String, Object> paramMap = param.getParamMap();
        param.setOrganizationId(SecurityHelper.getCurrentUser().getOrganizationId());
        List<Sort.Order> sorts = new ArrayList<>();
        dataSourceRequest.getSort()
                .forEach((DataSourceRequest.SortDescriptor sortDescriptor) ->
                        {
                            if (sortDescriptor.getDir().equals("asc")) {
                                sorts.add(Sort.Order.asc(sortDescriptor.getField()));
                            } else {
                                sorts.add(Sort.Order.desc(sortDescriptor.getField()));
                            }
                        }
                );
        Pageable pageable = PageRequest.of(dataSourceRequest.getSkip(), dataSourceRequest.getTake(), Sort.by(sorts));

        Page<Object[]> list = financialAccountRepository.financialAccountLov(param.getOrganizationId(), param.getFinancialCodingTypeId(), paramMap.get("descriptionObject"),
                param.getDescription(), paramMap.get("codeObject"), param.getCode(), paramMap.get("financialAccountList"), param.getFinancialAccountIdList()
                , pageable);
        List<FinancialAccountLovResponse> financialAccountDtos = list.stream().map(item ->
                FinancialAccountLovResponse.builder()
                        .id(Long.parseLong(item[0].toString()))
                        .description(item[2].toString())
                        .code(item[1].toString())
                        .accountRelationTypeId(item[5] == null ? null : Long.parseLong(item[5].toString()))
                        .referenceFlag(item[3] == null ? null : Long.parseLong(item[3].toString()))
                        .exchangeFlag(item[4] == null ? null : Long.parseLong(item[4].toString()))
                        .disableDate(item[6] == null ? null : (Date) item[6])
                        .build()).collect(Collectors.toList());
        DataSourceResult dataSourceResult = new DataSourceResult();
        dataSourceResult.setData(financialAccountDtos);
        dataSourceResult.setTotal(list.getTotalElements());
        return dataSourceResult;
    }

    private FinancialAccountLovRequest setFinancialAccountLov(List<DataSourceRequest.FilterDescriptor> filters) {
        FinancialAccountLovRequest financialAccountLovRequest = new FinancialAccountLovRequest();
        for (DataSourceRequest.FilterDescriptor item : filters) {
            switch (item.getField()) {
                case "financialAccountStructure.financialCodingType.id":
                    financialAccountLovRequest.setFinancialCodingTypeId(Long.parseLong(item.getValue().toString()));
                    break;

                case "id":
                    checkFinancialAccountId(financialAccountLovRequest, item);
                    break;
                case "description":
                    checkDescription(financialAccountLovRequest, item);
                    break;
                case "code":
                    checkCodeObject(financialAccountLovRequest, item);
                    break;
                default:
                    break;

            }
        }
        return financialAccountLovRequest;
    }

    private void checkFinancialAccountId(FinancialAccountLovRequest
                                                 financialAccountLovRequest, DataSourceRequest.FilterDescriptor item) {
        Map<String, Object> map = new HashMap<>();
        List<Long> arrayList = (ArrayList) item.getValue();
        if (!arrayList.isEmpty()) {
            map.put("financialAccountList", "financialAccountList");
            financialAccountLovRequest.setParamMap(map);
            financialAccountLovRequest.setFinancialAccountIdList(arrayList);
        } else {
            map.put("financialAccountList", null);
            financialAccountLovRequest.setParamMap(map);
            financialAccountLovRequest.setFinancialAccountIdList(new ArrayList<>((int) 0L));
        }
    }

    private void checkCodeObject(FinancialAccountLovRequest
                                         financialAccountLovRequest, DataSourceRequest.FilterDescriptor item) {
        Map<String, Object> map = new HashMap<>();
        if (item.getValue() != null) {
            map.put("codeObject", "codeObject");
            financialAccountLovRequest.setParamMap(map);
            financialAccountLovRequest.setCode(item.getValue().toString());
        } else {
            map.put("codeObject", null);
            financialAccountLovRequest.setParamMap(map);
            financialAccountLovRequest.setCode(null);
        }
    }

    private void checkDescription(FinancialAccountLovRequest
                                          financialAccountLovRequest, DataSourceRequest.FilterDescriptor item) {
        Map<String, Object> map = new HashMap<>();
        if (item.getValue() != null) {
            map.put("descriptionObject", "descriptionObject");
            financialAccountLovRequest.setParamMap(map);
            financialAccountLovRequest.setDescription(item.getValue().toString());
        } else {
            map.put("descriptionObject", null);
            financialAccountLovRequest.setParamMap(map);
            financialAccountLovRequest.setDescription(null);
        }
    }
}
