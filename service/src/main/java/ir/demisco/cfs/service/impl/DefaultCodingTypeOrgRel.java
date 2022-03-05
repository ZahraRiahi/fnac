package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.entity.CodingTypeOrgRel;
import ir.demisco.cfs.service.api.CodingTypeOrgRelService;
import ir.demisco.cfs.service.repository.CodingTypeOrgRelRepository;
import ir.demisco.cfs.service.repository.FinancialCodingTypeRepository;
import ir.demisco.cfs.service.repository.OrganizationRepository;
import ir.demisco.cloud.core.security.util.SecurityHelper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class DefaultCodingTypeOrgRel implements CodingTypeOrgRelService {
    private final CodingTypeOrgRelRepository codingTypeOrgRelRepository;
    private final OrganizationRepository organizationRepository;
    private final FinancialCodingTypeRepository financialCodingTypeRepository;

    public DefaultCodingTypeOrgRel(CodingTypeOrgRelRepository codingTypeOrgRelRepository, OrganizationRepository organizationRepository, FinancialCodingTypeRepository financialCodingTypeRepository) {
        this.codingTypeOrgRelRepository = codingTypeOrgRelRepository;
        this.organizationRepository = organizationRepository;
        this.financialCodingTypeRepository = financialCodingTypeRepository;
    }

    @Override
    @Transactional
    public void save(Long along, Long organizationId) {
        Long count = codingTypeOrgRelRepository.getCodingTypeOrgRelByOrganizationAndFinancialCodingType(SecurityHelper.getCurrentUser().getOrganizationId(), along);
        if (count == null) {
            CodingTypeOrgRel codingTypeOrgRel = new CodingTypeOrgRel();
            codingTypeOrgRel.setOrganization(organizationRepository.getOne(SecurityHelper.getCurrentUser().getOrganizationId()));
            codingTypeOrgRel.setFinancialCodingType(financialCodingTypeRepository.getById(along));
            codingTypeOrgRel.setActiveFlag(1L);
            codingTypeOrgRelRepository.save(codingTypeOrgRel).getId();
        }
    }
}
