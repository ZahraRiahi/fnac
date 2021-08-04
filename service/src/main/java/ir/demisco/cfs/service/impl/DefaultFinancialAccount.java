package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.FinancialAccountParameter;
import ir.demisco.cfs.model.dto.request.FinancialAccountRequest;
import ir.demisco.cfs.model.dto.request.FinancialAccountStructureRequest;
import ir.demisco.cfs.model.dto.response.*;
import ir.demisco.cfs.model.entity.FinancialAccount;
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

    public DefaultFinancialAccount(FinancialAccountRepository financialAccountRepository, CentricAccountRepository centricAccountRepository, FinancialAccountTypeRepository financialAccountTypeRepository, AccountRelatedDescriptionRepository accountRelatedDescriptionRepository, MoneyTypeRepository moneyTypeRepository, OrganizationRepository organizationRepository, FinancialAccountStructureRepository financialAccountStructureRepository, AccountNatureTypeRepository accountNatureTypeRepository, AccountRelationTypeRepository accountRelationTypeRepository, FinancialAccountStructureService financialAccountStructureService) {

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
                        .activeFlag(Integer.parseInt(item[4].toString()) == 1)
                        .permanentFlag(Integer.parseInt(item[5].toString()) == 1)
                        .accountNatureTypeId(Long.parseLong(item[6].toString()))
                        .accountRelationTypeDescription(item[10].toString())
                        .accountRelationTypeId(Long.parseLong(item[7].toString()))
                        .accountNatureTypeDescription(item[9].toString())
                        .financialAccountParentId(Long.parseLong(item[8] == null ? "0" : item[8].toString()))
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
                .build()).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public FinancialAccountOutPutResponse getFinancialAccountGetById(Long financialAccountId) {
        FinancialAccount financialAccount = financialAccountRepository.findById(financialAccountId).orElseThrow(() -> new RuleException("آیتمی با این شناسه وجود ندارد"));
        FinancialAccountOutPutResponse financialAccountOutPutResponse = FinancialAccountOutPutResponse.builder().id(financialAccountId)
                .organizationId(financialAccount.getOrganization().getId())
                .financialAccountStructureId(financialAccount.getFinancialAccountStructure().getId())
                .fullDescription(financialAccount.getFullDescription())
                .code(financialAccount.getCode())
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
                .accountAdjustmentId(financialAccount.getAccountAdjustment().getId())
                .accountAdjustmentDescription(financialAccount.getAccountAdjustment().getDescription()).build();
        financialAccountOutPutResponse.setAccountRelatedTypeOutPutModel(accountRelatedTypeResponses(financialAccountId));
        financialAccountOutPutResponse.setAccountDefaultValueOutPutModel(accountDefaultValueResponses(financialAccountId));
        financialAccountOutPutResponse.setAccountRelatedDescriptionOutPutModel(accountRelatedDescriptionResponses(financialAccountId));
        financialAccountOutPutResponse.setAccountMoneyTypeOutPutModel(accountMoneyTypeResponses(financialAccountId));
        return financialAccountOutPutResponse;
    }

    private List<AccountRelatedTypeResponse> accountRelatedTypeResponses(Long financialAccountId) {
        List<Object[]> typeListObject = financialAccountTypeRepository.findByFinancialAccountTypeListObject(financialAccountId);
        return typeListObject.stream().map(objects -> AccountRelatedTypeResponse.builder().financialAccountTypeId(Long.parseLong(objects[0].toString()))
                .financialAccountTypeDescription(objects[1].toString())
                .flgExists(Long.parseLong(objects[2].toString())).build()).collect(Collectors.toList());
    }


    private List<AccountDefaultValueResponse> accountDefaultValueResponses(Long financialAccountId) {
        List<Object[]> centricAccountListObject = centricAccountRepository.findByCentricAccountListObject(financialAccountId);
        return centricAccountListObject.stream().map(objects ->
                AccountDefaultValueResponse.builder()
                        .accountRelationTypeDetailId(Long.parseLong(objects[0].toString()))
                        .centricAccountId(Long.parseLong(objects[1].toString()))
                        .centricAccountName(objects[2].toString())
                        .centricAccountCode(objects[3].toString())
                        .accountRelationTypeDescription(objects[4].toString())
                        .accountRelationTypeId(Long.parseLong(objects[5].toString()))
                        .sequence(Long.parseLong(objects[6].toString()))
                        .build()).collect(Collectors.toList());
    }

    private List<AccountRelatedDescriptionResponse> accountRelatedDescriptionResponses(Long financialAccountId) {
        List<Object[]> accountRelatedDescriptionListObject = accountRelatedDescriptionRepository.findByAccountRelatedDescriptionListObject(financialAccountId);
        return accountRelatedDescriptionListObject.stream().map(objects -> AccountRelatedDescriptionResponse.builder().financialAccountDescriptionId(Long.parseLong(objects[0].toString()))
                .financialAccountDescriptionDescription(objects[1].toString())
                .build()).collect(Collectors.toList());
    }

    private List<AccountMoneyTypeResponse> accountMoneyTypeResponses(Long financialAccountId) {
        List<Object[]> moneyTypeListObject = moneyTypeRepository.findByMonetTypeListObject(financialAccountId);
        return moneyTypeListObject.stream().map(objects -> AccountMoneyTypeResponse.builder().id(Long.parseLong(objects[0].toString()))
                .moneyTypeId(Long.parseLong(objects[1].toString()))
                .moneyTypeDescription(objects[2].toString())
                .flgExists(Long.parseLong(objects[3].toString())).build()).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public FinancialAccountOutPutDto save(FinancialAccountRequest financialAccountRequest) {
        FinancialAccount financialAccount = financialAccountRepository.findById(financialAccountRequest.getId() == null ? 0L : financialAccountRequest.getId()).orElse(new FinancialAccount());

        financialAccount = saveFinancialAccount(financialAccount, financialAccountRequest);
        FinancialAccount finalFinancialAccount = financialAccount;
//        financialAccountRequest.getCentricPersonRoleListId().forEach(aLong -> {
//            CentricPersonRole centricPersonRole = new CentricPersonRole();
//            centricPersonRole.setCentricAccount(finalCentricAccount);
//            centricPersonRole.setPersonRoleType(personRoleTypeRepository.getOne(aLong));
//            centricPersonRoleRepository.save(centricPersonRole);
//        });
//
//        return convertCentricAccountToDto(centricAccount);
//    }
        return null;
    }

    private FinancialAccount saveFinancialAccount(FinancialAccount financialAccount, FinancialAccountRequest financialAccountRequest) {
        financialAccount.setOrganization(organizationRepository.getOne(100L));
        FinancialAccountStructureRequest financialAccountStructureRequest = new FinancialAccountStructureRequest();
        financialAccountStructureRequest.setFinancialAccountStructureId(financialAccountRequest.getFinancialAccountStructureId());
        financialAccountStructureRequest.setFinancialCodingTypeId(financialAccountRequest.getFinancialCodingTypeId());
        Long financialAccountStructureId = financialAccountStructureService.getFinancialAccountStructureByFinancialCodingTypeAndFinancialAccountStructure
                (financialAccountStructureRequest);
        financialAccount.setFinancialAccountStructure(financialAccountStructureRepository.getOne(financialAccountStructureId));
        financialAccount.setFullDescription(financialAccountRequest.getFullDescription());
        financialAccount.setCode(financialAccountRequest.getCode());
        financialAccount.setDescription(financialAccountRequest.getDescription());
        financialAccount.setActiveFlag(financialAccountRequest.getActiveFlag());
        financialAccount.setLatinDescription(financialAccountRequest.getLatinDescription());
        financialAccount.setAccountNatureType(accountNatureTypeRepository.findById(financialAccountRequest.getAccountNatureTypeId()).orElseThrow(() -> new RuleException("")));
        financialAccount.setRelatedToOthersFlag(financialAccountRequest.getRelatedToOthersFlag());
        financialAccount.setPermanentFlag(financialAccountRequest.getPermanentFlag());
        financialAccount.setAccountRelationType(accountRelationTypeRepository.findById(financialAccountRequest.getAccountRelationTypeId()).orElseThrow(() -> new RuleException("")));
        financialAccount.setFinancialAccountParent(financialAccountRepository.findById(financialAccountRequest.getFinancialAccountParentId()).orElseThrow(() -> new RuleException("")));
        financialAccount.setRelatedToFundType(financialAccountRequest.getRelatedToFundType());
        financialAccount.setReferenceFlag(financialAccountRequest.getReferenceFlag());
        financialAccount.setConvertFlag(financialAccountRequest.getConvertFlag());
        financialAccount.setExchangeFlag(financialAccountRequest.getExchangeFlag());
        financialAccount.setAccountAdjustment(financialAccountRepository.findById(financialAccountRequest.getAccountAdjustmentId()).orElseThrow(() -> new RuleException("")));
        return financialAccountRepository.save(financialAccount);
    }
}

