package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.response.*;
import ir.demisco.cfs.model.entity.FinancialAccount;
import ir.demisco.cfs.service.api.FinancialAccountService;
import ir.demisco.cfs.service.repository.*;
import ir.demisco.cloud.core.middle.exception.RuleException;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import ir.demisco.cloud.core.middle.service.business.api.core.GridFilterService;
import ir.demisco.cloud.core.security.util.SecurityHelper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultFinancialAccount implements FinancialAccountService {
    private final GridFilterService gridFilterService;
    private final FinancialAccountListGridProvider financialAccountListGridProvider;
    private final FinancialAccountRepository financialAccountRepository;
    private final FinancialAccountTypeRepository financialAccountTypeRepository;
    private final CentricAccountRepository centricAccountRepository;
    private final AccountRelatedDescriptionRepository accountRelatedDescriptionRepository;
    private final MoneyTypeRepository moneyTypeRepository;

    public DefaultFinancialAccount(GridFilterService gridFilterService, FinancialAccountListGridProvider financialAccountListGridProvider, FinancialAccountRepository financialAccountRepository, CentricAccountRepository centricAccountRepository, FinancialAccountTypeRepository financialAccountTypeRepository, AccountRelatedDescriptionRepository accountRelatedDescriptionRepository, MoneyTypeRepository moneyTypeRepository) {

        this.gridFilterService = gridFilterService;
        this.financialAccountListGridProvider = financialAccountListGridProvider;
        this.financialAccountRepository = financialAccountRepository;
        this.financialAccountTypeRepository = financialAccountTypeRepository;
        this.centricAccountRepository = centricAccountRepository;
        this.accountRelatedDescriptionRepository = accountRelatedDescriptionRepository;
        this.moneyTypeRepository = moneyTypeRepository;
    }

//    @Override
//    @Transactional
    public DataSourceResult getFinancialAccount(DataSourceRequest dataSourceRequest) {
        List<DataSourceRequest.FilterDescriptor> filters = dataSourceRequest.getFilter().getFilters();

        Long id=null;
       for(DataSourceRequest.FilterDescriptor item:filters){
            if(item.getField().equals("id")){
                id= (Long) item.getValue();
            }
        }
        Pageable pageable = PageRequest.of(dataSourceRequest.getSkip(), dataSourceRequest.getTake(), Sort.by("id"));
//        Page<FinancialAccount> list = financialAccountRepository.test(id,pageable);
//        List<FinancialAccount> test = (List<FinancialAccount>) list.get();
//        List<FinancialAccountDto> dto = test.stream().map(item -> {
//            return FinancialAccountDto.builder()
//                    .id(item.getId())
//                    .description(item.getDescription())
//                    .build();
//        }).collect(Collectors.toList());

//        DataSourceResult dataSourceResult = new DataSourceResult();
//        dataSourceResult.setData(dto);
//        dataSourceResult.setTotal(list.getTotalElements());
//        return dataSourceResult;
        return null;
//        dataSourceRequest.getFilter().getFilters().add(DataSourceRequest.FilterDescriptor.create("deletedDate", null, DataSourceRequest.Operators.IS_NULL));
//        dataSourceRequest.getFilter().getFilters().add(DataSourceRequest.FilterDescriptor.create("accountNatureType.deletedDate", null, DataSourceRequest.Operators.IS_NULL));
//        dataSourceRequest.getFilter().getFilters().add(DataSourceRequest.FilterDescriptor.create("accountRelationType.deletedDate", null, DataSourceRequest.Operators.IS_NULL));
//        dataSourceRequest.getFilter().getFilters().add(DataSourceRequest.FilterDescriptor.create("financialAccountStructure.deletedDate", null, DataSourceRequest.Operators.IS_NULL));
//        dataSourceRequest.getFilter().getFilters().add(DataSourceRequest.FilterDescriptor.create("financialAccountParent.deletedDate", null, DataSourceRequest.Operators.IS_NULL));
//        dataSourceRequest.getFilter().getFilters().add(DataSourceRequest.FilterDescriptor.create("accountAdjustment.deletedDate", null, DataSourceRequest.Operators.IS_NULL));
//        DataSourceResult t = gridFilterService.filter(dataSourceRequest, financialAccountListGridProvider);
//        List<FinancialAccountDto> data = (List<FinancialAccountDto>) t.getData();
//        for (FinancialAccountDto dto : data) {
//            dto.setHasChild(methode(SecurityHelper.getCurrentUser().getOrganizationId()));
//        }
//        return t;
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

}

