package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.response.CentricAccountDto;
import ir.demisco.cfs.model.entity.CentricOrgRel;
import ir.demisco.cfs.service.api.CentricOrgRelService;
import ir.demisco.cfs.service.repository.CentricAccountRepository;
import ir.demisco.cfs.service.repository.CentricOrgRelRepository;
import ir.demisco.cfs.service.repository.OrganizationRepository;
import ir.demisco.cloud.core.security.util.SecurityHelper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class DefaultCentricOrgRel implements CentricOrgRelService {
    private final CentricOrgRelRepository centricOrgRelRepository;
    private final OrganizationRepository organizationRepository;
    private final CentricAccountRepository centricAccountRepository;

    public DefaultCentricOrgRel(CentricOrgRelRepository centricOrgRelRepository, OrganizationRepository organizationRepository, CentricAccountRepository centricAccountRepository) {
        this.centricOrgRelRepository = centricOrgRelRepository;
        this.organizationRepository = organizationRepository;
        this.centricAccountRepository = centricAccountRepository;
    }

    @Override
    @Transactional
    public void save(Long organizationId, CentricAccountDto centricAccountDto) {
        Long count = centricOrgRelRepository.getCentricOrgRelByOrganizationAndCentricAccount(SecurityHelper.getCurrentUser().getOrganizationId(), centricAccountDto.getId());
        if (count == null) {
            CentricOrgRel centricOrgRel = new CentricOrgRel();
            centricOrgRel.setOrganization(organizationRepository.getOne(SecurityHelper.getCurrentUser().getOrganizationId()));
            centricOrgRel.setCentricAccount(centricAccountRepository.getById(centricAccountDto.getId()));
            centricOrgRel.setActiveFlag(1L);
            centricOrgRelRepository.save(centricOrgRel).getId();
        }
    }
}
