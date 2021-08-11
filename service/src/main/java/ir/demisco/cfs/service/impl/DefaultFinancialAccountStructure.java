package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.request.FinancialAccountStructureRequest;
import ir.demisco.cfs.model.dto.response.FinancialAccountStructureDto;
import ir.demisco.cfs.model.dto.response.FinancialAccountStructureResponse;
import ir.demisco.cfs.model.entity.FinancialAccount;
import ir.demisco.cfs.model.entity.FinancialAccountStructure;
import ir.demisco.cfs.service.api.FinancialAccountStructureService;
import ir.demisco.cfs.service.repository.FinancialAccountRepository;
import ir.demisco.cfs.service.repository.FinancialAccountStructureRepository;
import ir.demisco.cfs.service.repository.FinancialCodingTypeRepository;
import ir.demisco.cloud.core.middle.exception.RuleException;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import ir.demisco.cloud.core.middle.service.business.api.core.GridFilterService;
import ir.demisco.cloud.core.security.util.SecurityHelper;
import org.apache.http.util.Asserts;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class DefaultFinancialAccountStructure implements FinancialAccountStructureService {
    private final GridFilterService gridFilterService;
    private final FinancialAccountStructureListGridProvider financialAccountStructureListGridProvider;
    private final FinancialAccountStructureRepository financialAccountStructureRepository;
    private final FinancialCodingTypeRepository financialCodingTypeRepository;
    private final FinancialAccountRepository financialAccountRepository;

    public DefaultFinancialAccountStructure(GridFilterService gridFilterService, FinancialAccountStructureListGridProvider financialAccountStructureListGridProvider, FinancialCodingTypeRepository financialCodingTypeRepository, FinancialAccountStructureRepository financialAccountStructureRepository, FinancialCodingTypeRepository financialCodingTypeRepository1, FinancialAccountRepository financialAccountRepository) {
        this.gridFilterService = gridFilterService;
        this.financialAccountStructureListGridProvider = financialAccountStructureListGridProvider;
        this.financialAccountStructureRepository = financialAccountStructureRepository;
        this.financialCodingTypeRepository = financialCodingTypeRepository1;
        this.financialAccountRepository = financialAccountRepository;
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
//        Long organizationId = SecurityHelper.getCurrentUser().getOrganizationId();
        Long financialAccountStructureCount = financialAccountStructureRepository.getCountByFinancialAccountStructureSequenceAndId(financialAccountStructureDto.getSequence(), financialAccountStructureDto.getFinancialCodingTypeId(), SecurityHelper.getCurrentUser().getOrganizationId());
        if (financialAccountStructureCount > 0) {
            throw new RuleException("ساختار حساب با این sequence، کدینگ و سازمان وجود دارد.");
        }
        FinancialAccountStructure financialAccountStructure = financialAccountStructureRepository.findById(financialAccountStructureDto.getId() == null ? 0L : financialAccountStructureDto.getId()).orElse(new FinancialAccountStructure());
        financialAccountStructure.setDescription(financialAccountStructureDto.getDescription());
        financialAccountStructure.setSequence(financialAccountStructureDto.getSequence());
        financialAccountStructure.setDigitCount(financialAccountStructureDto.getDigitCount());
        financialAccountStructure.setSumDigit(financialAccountStructureDto.getSumDigit());
        financialAccountStructure.setColor(financialAccountStructureDto.getColor());
        financialAccountStructure.setFinancialCodingType(financialCodingTypeRepository.findById(financialAccountStructureDto.getFinancialCodingTypeId()).orElseThrow(() -> new RuleException("کدینگ حسابی با این شناسه وجود ندارد.")));
        return financialAccountStructureRepository.save(financialAccountStructure).getId();
    }

    @Override
    public FinancialAccountStructureDto update(FinancialAccountStructureDto financialAccountStructureDto) {
        FinancialAccountStructure financialAccountStructure = financialAccountStructureRepository.findById(financialAccountStructureDto.getId()).orElseThrow(() -> new RuleException("برای انجام عملیات ویرایش شناسه ی دوره ی مالی الزامی میباشد."));
        if (financialAccountStructureDto.getSequence() <= 0) {
            throw new RuleException("مقدار sequence  باید بزرگتر از صفر باشد");
        }
        Long financialAccountStructureCount = financialAccountStructureRepository.getCountByFinancialAccountStructureSequenceAndId(financialAccountStructureDto.getSequence(), financialAccountStructureDto.getFinancialCodingTypeId(), SecurityHelper.getCurrentUser().getOrganizationId());
        if (financialAccountStructureCount > 0) {
            throw new RuleException("ساختار حساب با این sequence، کدینگ و سازمان وجود دارد.");
        }
        financialAccountStructure.setDescription(financialAccountStructureDto.getDescription());
        financialAccountStructure.setSequence(financialAccountStructureDto.getSequence());
        financialAccountStructure.setDigitCount(financialAccountStructureDto.getDigitCount());
        financialAccountStructure.setSumDigit(financialAccountStructureDto.getSumDigit());
        financialAccountStructure.setColor(financialAccountStructureDto.getColor());
        financialAccountStructure.setFinancialCodingType(financialCodingTypeRepository.findById(financialAccountStructureDto.getFinancialCodingTypeId()).orElseThrow(() -> new RuleException("کدینگ حسابی با این شناسه وجود ندارد.")));
        financialAccountStructure = financialAccountStructureRepository.save(financialAccountStructure);
        return convertFinancialAccountStructureToDto(financialAccountStructure);
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
        }else {
            financialAccountStructureRequest.setFinancialAccountStructureId(0L);
        }
        return financialAccountStructureRepository.findByFinancialCodingTypeAndFinancialAccountStructure(financialAccountStructureRequest.getFinancialCodingTypeId(), financialAccountStructureRequest.getFinancialAccountStructureId(), financialAccountStructure);
    }

    private FinancialAccountStructureDto convertFinancialAccountStructureToDto(FinancialAccountStructure financialAccountStructure) {
        return FinancialAccountStructureDto.builder().description(financialAccountStructure.getDescription())
                .sequence(financialAccountStructure.getSequence())
                .digitCount(financialAccountStructure.getDigitCount())
                .sumDigit(financialAccountStructure.getSumDigit())
                .color(financialAccountStructure.getColor())
                .financialCodingTypeId(financialAccountStructure.getFinancialCodingType().getId()).build();

    }
}
