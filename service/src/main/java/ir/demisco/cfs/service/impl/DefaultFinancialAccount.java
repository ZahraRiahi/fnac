package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.FinancialAccountParameter;
import ir.demisco.cfs.model.dto.request.AccountDefaultValueRequest;
import ir.demisco.cfs.model.dto.request.AccountRelatedDescriptionRequest;
import ir.demisco.cfs.model.dto.request.FinancialAccountRequest;
import ir.demisco.cfs.model.dto.request.FinancialAccountStructureRequest;
import ir.demisco.cfs.model.dto.response.*;
import ir.demisco.cfs.model.entity.*;
import ir.demisco.cfs.service.api.AccountRelatedDescriptionService;
import ir.demisco.cfs.service.api.FinancialAccountService;
import ir.demisco.cfs.service.api.FinancialAccountStructureService;
import ir.demisco.cfs.service.repository.*;
import ir.demisco.cloud.core.middle.exception.RuleException;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import ir.demisco.cloud.core.security.util.SecurityHelper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DefaultFinancialAccount implements FinancialAccountService {
    private final FinancialAccountRepository financialAccountRepository;
    private final FinancialAccountTypeRepository financialAccountTypeRepository;
    private final CentricAccountRepository centricAccountRepository;
    private final AccountRelatedDescriptionRepository accountRelatedDescriptionRepository;
    private final MoneyTypeRepository moneyTypeRepository;
    private final OrganizationRepository organizationRepository;
    private final FinancialAccountStructureRepository financialAccountStructureRepository;
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

    public DefaultFinancialAccount(FinancialAccountRepository financialAccountRepository, CentricAccountRepository centricAccountRepository, FinancialAccountTypeRepository financialAccountTypeRepository, AccountRelatedDescriptionRepository accountRelatedDescriptionRepository, MoneyTypeRepository moneyTypeRepository, OrganizationRepository organizationRepository, FinancialAccountStructureRepository financialAccountStructureRepository, AccountNatureTypeRepository accountNatureTypeRepository, AccountRelationTypeRepository accountRelationTypeRepository, FinancialAccountStructureService financialAccountStructureService, AccountRelatedTypeRepository accountRelatedTypeRepository, AccountMoneyTypeRepository accountMoneyTypeRepository, AccountDefaultValueRepository accountDefaultValueRepository, AccountRelationTypeDetailRepository accountRelationTypeDetailRepository, AccountStructureLevelRepository accountStructureLevelRepository, AccountRelatedDescriptionService accountRelatedDescriptionService, FinancialAccountDescriptionRepository financialAccountDescriptionRepository, FinancialDocumentItemRepository financialDocumentItemRepository) {

        this.financialAccountRepository = financialAccountRepository;
        this.financialAccountTypeRepository = financialAccountTypeRepository;
        this.centricAccountRepository = centricAccountRepository;
        this.accountRelatedDescriptionRepository = accountRelatedDescriptionRepository;
        this.moneyTypeRepository = moneyTypeRepository;
        this.organizationRepository = organizationRepository;
        this.financialAccountStructureRepository = financialAccountStructureRepository;
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
    }

    @Override
    @Transactional
    public DataSourceResult getFinancialAccount(DataSourceRequest dataSourceRequest) {
        List<DataSourceRequest.FilterDescriptor> filters = dataSourceRequest.getFilter().getFilters();
        FinancialAccountParameter param = setParameter(filters);
        Map<String, Object> paramMap = param.getParamMap();
        param.setOrganizationId(100L);
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
                        .activeFlag(Integer.parseInt(item[4].toString()) == 1)
                        .permanentFlag(item[6] == null ? null : Integer.parseInt(item[6].toString()) == 1)
                        .accountNatureTypeId(item[5] == null ? null : Long.parseLong(item[5].toString()))
                        .accountRelationTypeDescription(item[10].toString())
                        .accountRelationTypeId(item[7] == null ? null : Long.parseLong(item[7].toString()))
                        .accountNatureTypeDescription(item[9].toString())
                        .financialAccountParentId(item[8] == null ? null : Long.parseLong(item[8].toString()))
                        .financialAccountStructureId(item[12] == null ? null : Long.parseLong(item[12].toString()))
                        .hasChild(Integer.parseInt(item[11].toString()) == 1)
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

    @Override
    @Transactional
    public List<FinancialAccountResponse> getFinancialAccountLov(Long OrganizationId) {
        List<FinancialAccount> financialAccount = financialAccountRepository.findByFinancialAccountByOrganizationId(OrganizationId);
        return financialAccount.stream().map(e -> FinancialAccountResponse.builder().id(e.getId())
                .description(e.getDescription())
                .code(e.getCode())
                .referenceFlag(e.getReferenceFlag())
                .exchangeFlag(e.getExchangeFlag())
                .accountRelationTypeId(e.getAccountRelationType().getId())
                .build()).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public FinancialAccountOutPutResponse getFinancialAccountGetById(Long financialAccountId, Long organizationId) {
        FinancialAccount financialAccount = financialAccountRepository.findById(financialAccountId).orElseThrow(() -> new RuleException("آیتمی با این شناسه وجود ندارد"));
        FinancialAccountOutPutResponse financialAccountOutPutResponse = FinancialAccountOutPutResponse.builder().id(financialAccountId)
                .organizationId(financialAccount.getOrganization().getId())
                .financialAccountStructureId(financialAccount.getFinancialAccountStructure().getId())
                .fullDescription(financialAccount.getFullDescription())
                .code(financialAccount.getCode())
                .description(financialAccount.getDescription())
                .activeFlag(financialAccount.getActiveFlag())
                .latinDescription(financialAccount.getLatinDescription())
                .accountNatureTypeId(financialAccount.getAccountNatureType().getId())
                .accountNatureTypeDescription(financialAccount.getAccountNatureType().getDescription())
                .relatedToOthersFlag(financialAccount.getRelatedToOthersFlag())
                .permanentFlag(financialAccount.getPermanentFlag())
                .accountRelationTypeId(financialAccount.getAccountRelationType().getId())
                .accountRelationTypeDescription(financialAccount.getAccountRelationType().getDescription())
                .financialAccountParentId(financialAccount.getFinancialAccountParent() == null ? 0 : financialAccount.getFinancialAccountParent().getId())
                .financialAccountParentDescription(financialAccount.getFinancialAccountParent() == null ? "" : financialAccount.getFinancialAccountParent().getDescription())
                .relatedToFundType(financialAccount.getRelatedToFundType())
                .referenceFlag(financialAccount.getReferenceFlag())
                .convertFlag(financialAccount.getConvertFlag())
                .exchangeFlag(financialAccount.getExchangeFlag())
                .convertFlag(financialAccount.getConvertFlag())
                .accountAdjustmentId(financialAccount.getAccountAdjustment() == null ? 0 : financialAccount.getAccountAdjustment().getId())
                .accountAdjustmentDescription(financialAccount.getAccountAdjustment() == null ? "" : financialAccount.getAccountAdjustment().getDescription()).build();
        financialAccountOutPutResponse.setAccountRelatedTypeOutPutModel(accountRelatedTypeResponses(financialAccountId));
        financialAccountOutPutResponse.setAccountDefaultValueOutPutModel(accountDefaultValueResponses(financialAccountId));
        financialAccountOutPutResponse.setAccountRelatedDescriptionOutPutModel(accountRelatedDescriptionResponses(financialAccountId));
        financialAccountOutPutResponse.setAccountMoneyTypeOutPutModel(accountMoneyTypeResponses(financialAccountId));
        return financialAccountOutPutResponse;
    }

    private List<AccountRelatedTypeResponse> accountRelatedTypeResponses(Long financialAccountId) {
        List<Object[]> typeListObject = financialAccountTypeRepository.findByFinancialAccount(financialAccountId);
        return typeListObject.stream().map(objects -> AccountRelatedTypeResponse.builder().financialAccountTypeId(Long.parseLong(objects[0].toString()))
                .financialAccountTypeCode(objects[1].toString())
                .financialAccountTypeDescription(objects[2].toString())
                .flgExists(Long.parseLong(objects[3].toString())).build()).collect(Collectors.toList());
    }


    private List<AccountDefaultValueResponse> accountDefaultValueResponses(Long financialAccountId) {
        List<Object[]> centricAccountListObject = centricAccountRepository.findByCentricAccountListObject(financialAccountId);
        return centricAccountListObject.stream().map(objects ->
                AccountDefaultValueResponse.builder()
                        .id(Long.parseLong(objects[0].toString()))
                        .accountRelationTypeDetailId(Long.parseLong(objects[1].toString()))
                        .centricAccountId(objects[1] == null ? null : Long.parseLong(objects[2].toString()))
                        .centricAccountName(objects[2] == null ? null : objects[3].toString())
                        .centricAccountCode(objects[3] == null ? null : objects[4].toString())
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
        FinancialAccount financialAccount = financialAccountRepository.findById(financialAccountRequest.getId() == null ? 0L : financialAccountRequest.getId()).orElse(new FinancialAccount());
        Long financialAccountCodeCount;
        if (financialAccountRequest.getId() == null) {
            financialAccountCodeCount = financialAccountRepository.getCountByFinancialAccountAndCode(financialAccountRequest.getCode());
            FinancialAccountStructureRequest financialAccountStructureRequest = new FinancialAccountStructureRequest();
            financialAccountStructureRequest.setFinancialAccountStructureId(financialAccountRequest.getFinancialAccountStructureId());
            financialAccountStructureRequest.setFinancialCodingTypeId(financialAccountRequest.getFinancialCodingTypeId());
            Long financialAccountStructureId = financialAccountStructureService.getFinancialAccountStructureByFinancialCodingTypeAndFinancialAccountStructure
                    (financialAccountStructureRequest);

            if (financialAccountStructureId == null) {
                throw new RuleException("حساب انتخاب شده آخرین سطح حساب می باشد و امکان ایجاد فرزند برای حساب انتخابی وجود ندارد");
            }
            financialAccount.setFinancialAccountStructure(financialAccountStructureRepository.getOne(financialAccountStructureId));

        } else {
            financialAccountCodeCount = financialAccountRepository.getCountByFinancialAccountAndCode(financialAccountRequest.getCode(), financialAccount.getId());
            financialAccount.setFinancialAccountStructure(financialAccountStructureRepository.getOne(financialAccountRequest.getFinancialAccountStructureId()));
        }
        if (financialAccountCodeCount > 0) {
            throw new RuleException("حساب مالی با این کد قبلا ثبت شده است");
        }
        financialAccount.setOrganization(organizationRepository.getOne(100L));
//        FinancialAccountStructureRequest financialAccountStructureRequest = new FinancialAccountStructureRequest();
//        financialAccountStructureRequest.setFinancialAccountStructureId(financialAccountRequest.getFinancialAccountStructureId());
//        financialAccountStructureRequest.setFinancialCodingTypeId(financialAccountRequest.getFinancialCodingTypeId());
//        Long financialAccountStructureId = financialAccountStructureService.getFinancialAccountStructureByFinancialCodingTypeAndFinancialAccountStructure
//                (financialAccountStructureRequest);
//
//        if (financialAccountStructureId == null) {
//            throw new RuleException("حساب انتخاب شده آخرین سطح حساب می باشد و امکان ایجاد فرزند برای حساب انتخابی وجود ندارد");
//        }
//
//
//        financialAccount.setFinancialAccountStructure(financialAccountStructureRepository.getOne(financialAccountStructureId));
        financialAccount.setFullDescription(financialAccountRequest.getFullDescription());
        financialAccount.setCode(financialAccountRequest.getCode());
        financialAccount.setDescription(financialAccountRequest.getDescription());
        financialAccount.setActiveFlag(financialAccountRequest.getActiveFlag());
        financialAccount.setLatinDescription(financialAccountRequest.getLatinDescription());
//        if (financialAccountRequest.getAccountNatureTypeId() != null) {
        financialAccount.setAccountNatureType(accountNatureTypeRepository.getOne(financialAccountRequest.getAccountNatureTypeId()));
//        }
        financialAccount.setRelatedToOthersFlag(financialAccountRequest.getRelatedToOthersFlag());
        financialAccount.setPermanentFlag(financialAccountRequest.getPermanentFlag());
//        if (financialAccountRequest.getAccountRelationTypeId() != null) {
        financialAccount.setAccountRelationType(accountRelationTypeRepository.getOne(financialAccountRequest.getAccountRelationTypeId()));
//        }
        if (financialAccountRequest.getFinancialAccountParentId() != null) {
            financialAccount.setFinancialAccountParent(financialAccountRepository.getOne(financialAccountRequest.getFinancialAccountParentId()));
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
        financialAccountOutPutDto.setActiveFlag(financialAccount.getActiveFlag());
        financialAccountOutPutDto.setLatinDescription(financialAccount.getLatinDescription());
        financialAccountOutPutDto.setAccountNatureTypeId(financialAccount.getAccountNatureType().getId());
        financialAccountOutPutDto.setAccountNatureTypeDescription(financialAccount.getAccountNatureType().getDescription());
        financialAccountOutPutDto.setRelatedToOthersFlag(financialAccount.getRelatedToOthersFlag());
        financialAccountOutPutDto.setPermanentFlag(financialAccount.getPermanentFlag());
        financialAccountOutPutDto.setAccountRelationTypeId(financialAccount.getAccountRelationType().getId());
        financialAccountOutPutDto.setAccountRelationTypeDescription(financialAccount.getAccountRelationType().getDescription());
        financialAccountOutPutDto.setFinancialAccountParentId(financialAccount.getFinancialAccountParent() == null ? 0 : financialAccount.getFinancialAccountParent().getId());
        financialAccountOutPutDto.setFinancialAccountParentDescription(financialAccount.getFinancialAccountParent() == null ? " " : financialAccount.getFinancialAccountParent().getDescription());
        financialAccountOutPutDto.setRelatedToFundType(financialAccount.getRelatedToFundType());
        financialAccountOutPutDto.setReferenceFlag(financialAccount.getReferenceFlag());
        financialAccountOutPutDto.setConvertFlag(financialAccount.getConvertFlag());
        financialAccountOutPutDto.setExchangeFlag(financialAccount.getExchangeFlag());
        financialAccountOutPutDto.setAccountAdjustmentId(financialAccount.getAccountAdjustment() == null ? 0 : financialAccount.getAccountAdjustment().getId());
        financialAccountOutPutDto.setAccountAdjustmentDescription(financialAccount.getAccountAdjustment() == null ? " " : financialAccount.getAccountAdjustment().getDescription());
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
            Long countAccountDefaultValue = accountDefaultValueRepository.findByAccountDefaultAndfinancialAccountAndAccountRelationTypeDetailId(e.getAccountRelationTypeDetailId(), financialAccount.getId(), centricAccountId, centricAccount);
            if (countAccountDefaultValue > 0) {
                throw new RuleException("برای این نوع حساب و نوع جزئیات وابستگی رکوردی قبلا درج شده است");
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
                accountStructureLevelRepository.findByFinancialAccountStructureListObject(financialAccountRequest.getFinancialCodingTypeId()
                        , financialAccountRequest.getCode(),
                        financialAccount.getFinancialAccountStructure().getId(),
                        financialAccountStructure);

        financialAccountStructureListObject.forEach(e -> {
            AccountStructureLevel accountStructureLevel = new AccountStructureLevel();
            accountStructureLevel.setFinancialAccount(financialAccount);
            accountStructureLevel.setFinancialAccountStructure(financialAccountStructureRepository.getOne(Long.parseLong(e[2].toString())));
            accountStructureLevel.setStructureLevel(Long.parseLong(e[0].toString()));
            accountStructureLevel.setStructureLevelCode(e[1].toString());
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
        throw new RuleException("حساب انتخاب شده آخرین سطح حساب نمی باشد و امکان حذف آن وجود ندارد");
    }
}
