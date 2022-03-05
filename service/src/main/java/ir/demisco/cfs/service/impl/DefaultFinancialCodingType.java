package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.response.FinancialCodingTypeDto;
import ir.demisco.cfs.model.dto.response.FinancialCodingTypeResponse;
import ir.demisco.cfs.model.entity.CodingTypeOrgRel;
import ir.demisco.cfs.model.entity.FinancialAccountStructure;
import ir.demisco.cfs.model.entity.FinancialCodingType;
import ir.demisco.cfs.service.api.FinancialCodingTypeService;
import ir.demisco.cfs.service.repository.CodingTypeOrgRelRepository;
import ir.demisco.cfs.service.repository.FinancialAccountStructureRepository;
import ir.demisco.cfs.service.repository.FinancialCodingTypeRepository;
import ir.demisco.cfs.service.repository.OrganizationRepository;
import ir.demisco.cloud.core.middle.exception.RuleException;
import ir.demisco.cloud.core.security.util.SecurityHelper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultFinancialCodingType implements FinancialCodingTypeService {
    private final FinancialCodingTypeRepository financialCodingTypeRepository;
    private final OrganizationRepository organizationRepository;
    private final FinancialAccountStructureRepository financialAccountStructureRepository;
    private final CodingTypeOrgRelRepository codingTypeOrgRelRepository;

    public DefaultFinancialCodingType(FinancialCodingTypeRepository financialCodingTypeRepository, OrganizationRepository organizationRepository, FinancialAccountStructureRepository financialAccountStructureRepository, CodingTypeOrgRelRepository codingTypeOrgRelRepository) {
        this.financialCodingTypeRepository = financialCodingTypeRepository;
        this.organizationRepository = organizationRepository;
        this.financialAccountStructureRepository = financialAccountStructureRepository;
        this.codingTypeOrgRelRepository = codingTypeOrgRelRepository;
    }

    @Override
    @Transactional
    public List<FinancialCodingTypeResponse> getFinancialCodingTypeByOrganizationId(Long OrganizationId) {
        List<Object[]> financialPeriodTypeList = financialCodingTypeRepository.findByOrganizationId(SecurityHelper.getCurrentUser().getOrganizationId());
        return financialPeriodTypeList.stream().map(e -> FinancialCodingTypeResponse.builder().id(Long.parseLong(e[0].toString()))
                .description(e[1] == null ? null : e[1].toString())
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
        if (!financialAccountStructures.isEmpty()) {
            throw new RuleException("fin.financialCodingType.delete");
        } else {
            financialCodingTypeRepository.findById(financialCodingTypeId).orElseThrow(() -> new RuleException("fin.ruleException.notFoundId"));
            Long codingTypeOrgRelForDelete = codingTypeOrgRelRepository.findByFinancialCodingTypeIdForDelete(financialCodingTypeId);
            if (codingTypeOrgRelForDelete == null) {
                throw new RuleException("fin.ruleException.notFoundId");
            } else {
                codingTypeOrgRelRepository.deleteById(codingTypeOrgRelForDelete);
                financialCodingTypeRepository.deleteById(financialCodingTypeId);
                return true;
            }

        }
    }

    private FinancialCodingTypeDto convertFinancialPeriodToDto(FinancialCodingType financialCodingType) {
        return FinancialCodingTypeDto.builder().id(financialCodingType.getId())
                .description(financialCodingType.getDescription())
                .organization(financialCodingType.getOrganization().getId())
                .build();

    }
}
