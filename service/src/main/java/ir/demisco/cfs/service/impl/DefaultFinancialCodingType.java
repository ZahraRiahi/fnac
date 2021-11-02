package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.response.FinancialCodingTypeDto;
import ir.demisco.cfs.model.entity.FinancialAccountStructure;
import ir.demisco.cfs.model.entity.FinancialCodingType;
import ir.demisco.cfs.service.api.FinancialCodingTypeService;
import ir.demisco.cfs.service.repository.FinancialAccountStructureRepository;
import ir.demisco.cfs.service.repository.FinancialCodingTypeRepository;
import ir.demisco.cfs.service.repository.OrganizationRepository;
import ir.demisco.cloud.core.middle.exception.RuleException;
import ir.demisco.cloud.core.security.util.SecurityHelper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultFinancialCodingType implements FinancialCodingTypeService {
    private final FinancialCodingTypeRepository financialCodingTypeRepository;
    private final OrganizationRepository organizationRepository;
    private final FinancialAccountStructureRepository financialAccountStructureRepository;


    public DefaultFinancialCodingType(FinancialCodingTypeRepository financialCodingTypeRepository, OrganizationRepository organizationRepository, FinancialAccountStructureRepository financialAccountStructureRepository) {
        this.financialCodingTypeRepository = financialCodingTypeRepository;
        this.organizationRepository = organizationRepository;
        this.financialAccountStructureRepository = financialAccountStructureRepository;
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
        financialCodingType.setOrganization(organizationRepository.getOne(SecurityHelper.getCurrentUser().getOrganizationId()));
        financialCodingType.setDescription(financialCodingTypeDto.getDescription());
        return financialCodingTypeRepository.save(financialCodingType).getId();
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public FinancialCodingTypeDto update(FinancialCodingTypeDto financialCodingTypeDto) {
        FinancialCodingType financialCodingType = financialCodingTypeRepository.findById(financialCodingTypeDto.getId()).orElseThrow(() -> new RuleException("fin.financialCodingType.update"));
        financialCodingType.setOrganization(organizationRepository.getOne(SecurityHelper.getCurrentUser().getOrganizationId()));
        financialCodingType.setDescription(financialCodingTypeDto.getDescription());
        financialCodingType = financialCodingTypeRepository.save(financialCodingType);
        return convertFinancialPeriodToDto(financialCodingType);
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public boolean deleteFinancialCodingTypeById(Long financialCodingTypeId) {
        List<FinancialAccountStructure> financialAccountStructures = financialAccountStructureRepository.findByFinancialCodingTypeId(financialCodingTypeId);
        FinancialCodingType financialCodingType;
        if (!financialAccountStructures.isEmpty()) {
            throw new RuleException("fin.financialCodingType.delete");
        } else {
            financialCodingType = financialCodingTypeRepository.findById(financialCodingTypeId).orElseThrow(() -> new RuleException("fin.ruleException.notFoundId"));
            financialCodingType.setDeletedDate(LocalDateTime.now());
            financialCodingTypeRepository.save(financialCodingType);
            return true;
        }
    }

    private FinancialCodingTypeDto convertFinancialPeriodToDto(FinancialCodingType financialCodingType) {
        return FinancialCodingTypeDto.builder().id(financialCodingType.getId())
                .description(financialCodingType.getDescription())
                .organization(financialCodingType.getOrganization().getId())
                .build();

    }
}
