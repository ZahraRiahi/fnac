package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.response.FinancialCodingTypeDto;
import ir.demisco.cfs.model.entity.FinancialCodingType;
import ir.demisco.cfs.service.api.FinancialCodingTypeService;
import ir.demisco.cfs.service.repository.FinancialCodingTypeRepository;
import ir.demisco.cfs.service.repository.OrganizationRepository;
import ir.demisco.cloud.core.middle.exception.RuleException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultFinancialCodingType implements FinancialCodingTypeService {
    private final FinancialCodingTypeRepository financialCodingTypeRepository;
    private final OrganizationRepository organizationRepository;


    public DefaultFinancialCodingType(FinancialCodingTypeRepository financialCodingTypeRepository, OrganizationRepository organizationRepository) {
        this.financialCodingTypeRepository = financialCodingTypeRepository;
        this.organizationRepository = organizationRepository;
    }

    @Override
    @Transactional
    public List<FinancialCodingTypeDto> getFinancialCodingTypeByOrganizationId(Long OrganizationId) {
        List<FinancialCodingType> financialPeriodTypeList = financialCodingTypeRepository.findByOrganizationId(OrganizationId);
        return financialPeriodTypeList.stream().map(e -> FinancialCodingTypeDto.builder().id(e.getId())
                .description(e.getDescription())
                .build()).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public Long save(FinancialCodingTypeDto financialCodingTypeDto) {
        FinancialCodingType financialCodingType = financialCodingTypeRepository.findById(financialCodingTypeDto.getId() == null ? 0L : financialCodingTypeDto.getId()).orElse(new FinancialCodingType());
        financialCodingType.setOrganization(organizationRepository.getOne(4L));
        financialCodingType.setDescription(financialCodingTypeDto.getDescription());
        return financialCodingTypeRepository.save(financialCodingType).getId();
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public FinancialCodingTypeDto update(FinancialCodingTypeDto financialCodingTypeDto) {
        FinancialCodingType financialCodingType = financialCodingTypeRepository.findById(financialCodingTypeDto.getId()).orElseThrow(() -> new RuleException("برای انجام عملیات ویرایش شناسه ی کدیتگ حساب الزامی میباشد."));
        financialCodingType.setOrganization(organizationRepository.getOne(1L));
        financialCodingType.setDescription(financialCodingTypeDto.getDescription());
        financialCodingType = financialCodingTypeRepository.save(financialCodingType);
        return convertFinancialPeriodToDto(financialCodingType);
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public Boolean deleteFinancialCodingTypeById(Long financialCodingType) {
          financialCodingTypeRepository.delete(financialCodingTypeRepository.findById(financialCodingType).orElseThrow(() -> new RuleException("کدینگ حساب با این شناسه وجود ندارد.")));
        return true;
    }

    private FinancialCodingTypeDto convertFinancialPeriodToDto(FinancialCodingType financialCodingType) {
        return FinancialCodingTypeDto.builder().id(financialCodingType.getId())
                .description(financialCodingType.getDescription())
                .organization(financialCodingType.getOrganization().getId())
                .build();

    }
}
