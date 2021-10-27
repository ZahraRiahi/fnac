package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.request.FinancialAccountStructureDtoRequest;
import ir.demisco.cfs.model.dto.request.FinancialAccountStructureNewRequest;
import ir.demisco.cfs.model.dto.request.FinancialAccountStructureRequest;
import ir.demisco.cfs.model.dto.response.*;
import ir.demisco.cfs.model.entity.FinancialAccount;
import ir.demisco.cfs.model.entity.FinancialAccountStructure;
import ir.demisco.cfs.service.api.FinancialAccountStructureService;
import ir.demisco.cfs.service.repository.FinancialAccountRepository;
import ir.demisco.cfs.service.repository.FinancialAccountStructureRepository;
import ir.demisco.cfs.service.repository.FinancialCodingTypeRepository;
import ir.demisco.cfs.service.repository.FinancialDocumentItemRepository;
import ir.demisco.cloud.core.middle.exception.RuleException;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import ir.demisco.cloud.core.middle.service.business.api.core.GridFilterService;
import org.apache.http.util.Asserts;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;


@Service
public class DefaultFinancialAccountStructure implements FinancialAccountStructureService {
    private final GridFilterService gridFilterService;
    private final FinancialAccountStructureListGridProvider financialAccountStructureListGridProvider;
    private final FinancialAccountStructureRepository financialAccountStructureRepository;
    private final FinancialCodingTypeRepository financialCodingTypeRepository;
    private final FinancialAccountRepository financialAccountRepository;
    private final FinancialDocumentItemRepository financialDocumentItemRepository;

    public DefaultFinancialAccountStructure(GridFilterService gridFilterService, FinancialAccountStructureListGridProvider financialAccountStructureListGridProvider, FinancialAccountStructureRepository financialAccountStructureRepository, FinancialCodingTypeRepository financialCodingTypeRepository1, FinancialAccountRepository financialAccountRepository, FinancialDocumentItemRepository financialDocumentItemRepository) {
        this.gridFilterService = gridFilterService;
        this.financialAccountStructureListGridProvider = financialAccountStructureListGridProvider;
        this.financialAccountStructureRepository = financialAccountStructureRepository;
        this.financialCodingTypeRepository = financialCodingTypeRepository1;
        this.financialAccountRepository = financialAccountRepository;
        this.financialDocumentItemRepository = financialDocumentItemRepository;
    }

    @Override
    @Transactional
    public DataSourceResult getFinancialAccountStructureByFinancialCodingTypeId(Long financialCodingTypeId, DataSourceRequest dataSourceRequest) {
        Asserts.notNull(financialCodingTypeId, "financialCodingTypeId is null");
        dataSourceRequest.getFilter().setLogic("and");
        dataSourceRequest.getFilter().getFilters().add(DataSourceRequest
                .FilterDescriptor.create("financialCodingType.id", financialCodingTypeId, DataSourceRequest.Operators.EQUALS));
        dataSourceRequest.getFilter().getFilters().add(DataSourceRequest.FilterDescriptor.create("deletedDate", null, DataSourceRequest.Operators.IS_NULL));
        return gridFilterService.filter(dataSourceRequest, financialAccountStructureListGridProvider);
    }

    @Override
    @Transactional
    public List<FinancialAccountStructureResponse> getFinancialAccountStructureByFinancialCodingTypeIdLov(Long financialCodingTypeId) {
        List<FinancialAccountStructure> financialAccountStructureList = financialAccountStructureRepository.findByFinancialCodingTypeId(financialCodingTypeId);
        return financialAccountStructureList.stream().map(e -> FinancialAccountStructureResponse.builder().id(e.getId())
                .description(e.getDescription())
                .build()).collect(Collectors.toList());

    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public Long save(FinancialAccountStructureDto financialAccountStructureDto) throws RuleException {
        if (financialAccountStructureDto.getSequence() <= 0) {
            throw new RuleException("مقدار sequence  باید بزرگتر از صفر باشد");
        }
        Long financialAccountStructureCount = financialAccountStructureRepository.getCountByFinancialAccountStructureSequenceAndIdSave(financialAccountStructureDto.getSequence(), financialAccountStructureDto.getFinancialCodingTypeId());
        if (financialAccountStructureCount > 0) {
            throw new RuleException("ساختار حساب با این sequence، کدینگ و سازمان وجود دارد.");
        }
        String financialAccountStructure = null;
        if (financialAccountStructureDto.getId() != null) {
            financialAccountStructure = "financialAccountStructure";
        } else {
            financialAccountStructureDto.setId(0L);
        }
        if (financialAccountStructureDto.getFlgPermanentStatus().equals(true)) {
            List<Long> financialAccountStructureCoding = financialAccountStructureRepository.getFinancialAccountStructureByCodingAndStructureId(financialAccountStructureDto.getFinancialCodingTypeId(), financialAccountStructure, financialAccountStructureDto.getId());
            if (financialAccountStructureCoding.size() != 0) {
                throw new RuleException("برای این کدینگ ،وضعیت حساب دائمی پیش فرض ، در سطح دیگری انتخاب شده است");
            }
        }
        FinancialAccountStructure financialAccountStructureFlg = financialAccountStructureRepository.findById(financialAccountStructureDto.getId() == null ? 0L : financialAccountStructureDto.getId()).orElse(new FinancialAccountStructure());
        financialAccountStructureFlg.setDescription(financialAccountStructureDto.getDescription());
        financialAccountStructureFlg.setSequence(financialAccountStructureDto.getSequence());
        financialAccountStructureFlg.setDigitCount(financialAccountStructureDto.getDigitCount());
        financialAccountStructureFlg.setSumDigit(financialAccountStructureDto.getSumDigit());
        financialAccountStructureFlg.setColor(financialAccountStructureDto.getColor());
        financialAccountStructureFlg.setFinancialCodingType(financialCodingTypeRepository.findById(financialAccountStructureDto.getFinancialCodingTypeId()).orElseThrow(() -> new RuleException("کدینگ حسابی با این شناسه وجود ندارد.")));
        financialAccountStructureFlg.setFlgShowInAcc(financialAccountStructureDto.getFlgShowInAcc());
        financialAccountStructureFlg.setFlgPermanentStatus(financialAccountStructureDto.getFlgPermanentStatus());
        return financialAccountStructureRepository.save(financialAccountStructureFlg).getId();
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public FinancialAccountStructureDto update(FinancialAccountStructureDto financialAccountStructureDto) {
        FinancialAccountStructure financialAccountStructureFlg = financialAccountStructureRepository.findById(financialAccountStructureDto.getId()).orElseThrow(() -> new RuleException("برای انجام عملیات ویرایش شناسه ی دوره ی مالی الزامی میباشد."));
        Long financialDocument = financialDocumentItemRepository.findByFinancialDocumentAndFinancialAccountStructure(financialAccountStructureDto.getId());
        if (financialAccountStructureDto.getFlgShowInAcc().equals(false) && financialDocument != null) {
            throw new RuleException("امکان ویرایش این سطح ، به دلیل استفاده در اسناد وجود ندارد");
        }
        if (financialAccountStructureDto.getSequence() <= 0) {
            throw new RuleException("مقدار sequence  باید بزرگتر از صفر باشد");
        }
        Long financialAccountStructureCount = financialAccountStructureRepository.getCountByFinancialAccountStructureSequenceAndId(financialAccountStructureDto.getSequence(), financialAccountStructureDto.getFinancialCodingTypeId(), financialAccountStructureFlg.getId());
        if (financialAccountStructureCount > 0) {
            throw new RuleException("ساختار حساب با این sequence، کدینگ و سازمان وجود دارد.");
        }
        String financialAccountStructure = null;
        if (financialAccountStructureDto.getId() != null) {
            financialAccountStructure = "financialAccountStructure";
        } else {
            financialAccountStructureDto.setId(0L);
        }
        if (financialAccountStructureDto.getFlgPermanentStatus().equals(true)) {
            List<Long> financialAccountStructureCoding = financialAccountStructureRepository.getFinancialAccountStructureByCodingAndStructureId(financialAccountStructureDto.getFinancialCodingTypeId(), financialAccountStructure, financialAccountStructureDto.getId());
            if (financialAccountStructureCoding.size() != 0) {
                throw new RuleException("برای این کدینگ ،وضعیت حساب دائمی پیش فرض ، در سطح دیگری انتخاب شده است");
            }
        }
        financialAccountStructureFlg.setId(financialAccountStructureDto.getId());
        financialAccountStructureFlg.setDescription(financialAccountStructureDto.getDescription());
        financialAccountStructureFlg.setSequence(financialAccountStructureDto.getSequence());
        financialAccountStructureFlg.setDigitCount(financialAccountStructureDto.getDigitCount());
        financialAccountStructureFlg.setSumDigit(financialAccountStructureDto.getSumDigit());
        financialAccountStructureFlg.setColor(financialAccountStructureDto.getColor());
        financialAccountStructureFlg.setFinancialCodingType(financialCodingTypeRepository.findById(financialAccountStructureDto.getFinancialCodingTypeId()).orElseThrow(() -> new RuleException("کدینگ حسابی با این شناسه وجود ندارد.")));
        financialAccountStructureFlg.setFlgShowInAcc(financialAccountStructureDto.getFlgShowInAcc());
        financialAccountStructureFlg.setFlgPermanentStatus(financialAccountStructureDto.getFlgPermanentStatus());
        financialAccountStructureFlg = financialAccountStructureRepository.save(financialAccountStructureFlg);
        return convertFinancialAccountStructureToDto(financialAccountStructureFlg);
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public Boolean deleteFinancialAccountStructureById(Long financialAccountStructureId) {
        List<FinancialAccount> financialAccounts = financialAccountRepository.findByFinancialAccountStructureId(financialAccountStructureId);
        FinancialAccountStructure financialAccountStructure;
        if (!financialAccounts.isEmpty()) {
            throw new RuleException("به دلیل استفاده ی اطلاعات در جداول دیگر امکان حذف این ردیف وجود ندارد");
        } else {
            financialAccountStructure = financialAccountStructureRepository.findById(financialAccountStructureId).orElseThrow(() -> new RuleException("آیتمی با این شناسه وجود ندارد"));
            financialAccountStructure.setDeletedDate(LocalDateTime.now());
            financialAccountStructureRepository.save(financialAccountStructure);
            return true;
        }
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public Long getFinancialAccountStructureByFinancialCodingTypeAndFinancialAccountStructure(FinancialAccountStructureRequest financialAccountStructureRequest) {
        String financialAccountStructure = null;
        if (financialAccountStructureRequest.getFinancialAccountStructureId() != null) {
            financialAccountStructure = "financialAccountStructure";
        } else {
            financialAccountStructureRequest.setFinancialAccountStructureId(0L);
        }
        return financialAccountStructureRepository.findByFinancialCodingTypeAndFinancialAccountStructure(financialAccountStructureRequest.getFinancialCodingTypeId(), financialAccountStructureRequest.getFinancialAccountStructureId(), financialAccountStructure);
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public FinancialAccountStructureNewResponse getFinancialAccountStructureByCodingAndParentAndId(FinancialAccountStructureNewRequest financialAccountStructureNewRequest) {
        String financialAccountStructure = null;
        if (financialAccountStructureNewRequest.getFinancialAccountStructureId() != null) {
            financialAccountStructure = "financialAccountStructure";
        } else {
            financialAccountStructureNewRequest.setFinancialAccountStructureId(0L);
        }
        FinancialAccountStructureNewResponse financialAccountStructureNewResponse = new FinancialAccountStructureNewResponse();
        if (financialAccountStructureNewRequest.getFlgEditMode().equals(true)) {
            Long financialAccountStructureId = financialAccountStructureRepository.getFinancialAccountStructureByld(financialAccountStructureNewRequest.getFinancialAccountStructureId());

            if (financialAccountStructureId != null) {
                financialAccountStructureNewResponse.setFlgPermanentStatus(1L);
                financialAccountStructureNewResponse.setAccountPermanentStatusId(null);
            }
        }

        Long financialAccountStructureFlg = financialAccountStructureRepository.findByFinancialCodingTypeAndFinancialAccountStructureId(financialAccountStructureNewRequest.getFinancialCodingTypeId(), financialAccountStructure, financialAccountStructureNewRequest.getFinancialAccountStructureId());
        if (financialAccountStructureFlg == 1) {
            financialAccountStructureNewResponse.setFlgPermanentStatus(1L);
            financialAccountStructureNewResponse.setAccountPermanentStatusId(null);
            return financialAccountStructureNewResponse;
        } else if (financialAccountStructureFlg == 0 && financialAccountStructureNewRequest.getFinancialAccountParentId() == null) {
            throw new RuleException("در هیچ سطحی ، وضعیت دائمی حساب ، به صورت پیش فرض مشخص نشده است");
        } else {
            List<Object[]> financialAccount = financialAccountRepository.findByFinancialAccountByParentId(financialAccountStructureNewRequest.getFinancialAccountParentId());
            AtomicReference<Long> accountPermanentStatusId = new AtomicReference<>();
            AtomicReference<String> accountPermanentStatusDescription = new AtomicReference<>();
            financialAccount.stream().filter(objects -> Long.parseLong(objects[2].toString()) == 1L).findAny().ifPresent(objects -> {
                        accountPermanentStatusDescription.set(objects[3] == null ? null : objects[3].toString());
                        accountPermanentStatusId.set(objects[1] == null ? null : Long.parseLong(objects[1].toString()));
                    }
            );
            if (accountPermanentStatusId.get() != null) {
                financialAccountStructureNewResponse.setFlgPermanentStatus(0L);
                financialAccountStructureNewResponse.setAccountPermanentStatusId(accountPermanentStatusId.get());
                financialAccountStructureNewResponse.setAccountPermanentStatusDescription(accountPermanentStatusDescription.get());
                return financialAccountStructureNewResponse;
            } else {
                throw new RuleException("در هیچ سطحی ، وضعیت دائمی حساب ، به صورت پیش فرض مشخص نشده است");

            }
        }
    }

    @Override
    @Transactional
    public List<FinancialAccountStructureDtoResponse> getSumDigitAndSequence(FinancialAccountStructureDtoRequest financialAccountStructureDtoRequest) {
        List<Object[]> financialAccountStructureList = financialAccountStructureRepository.findByFinancialCodingType(financialAccountStructureDtoRequest.getFinancialCodingTypeId());
        return financialAccountStructureList.stream().map(e -> FinancialAccountStructureDtoResponse.builder()
                .sequence(e[0] == null ? null : Long.parseLong(e[0].toString()))
                .sumDigit(e[1] == null ? null : Long.parseLong(e[1].toString()))
                .build()).collect(Collectors.toList());
    }

    private FinancialAccountStructureDto convertFinancialAccountStructureToDto(FinancialAccountStructure financialAccountStructure) {
        return FinancialAccountStructureDto.builder().description(financialAccountStructure.getDescription())
                .sequence(financialAccountStructure.getSequence())
                .digitCount(financialAccountStructure.getDigitCount())
                .sumDigit(financialAccountStructure.getSumDigit())
                .color(financialAccountStructure.getColor())
                .financialCodingTypeId(financialAccountStructure.getFinancialCodingType().getId())
                .id(financialAccountStructure.getId())
                .build();

    }
}
