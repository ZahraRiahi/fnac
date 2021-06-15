package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.request.CentricPersonRoleRequest;
import ir.demisco.cfs.model.dto.response.CentricPersonRoleResponce;
import ir.demisco.cfs.model.entity.CentricAccount;
import ir.demisco.cfs.service.api.CentricPersonRoleService;
import ir.demisco.cfs.service.repository.CentricAccountRepository;
import ir.demisco.cfs.service.repository.CentricPersonRoleRepository;
import ir.demisco.cloud.core.middle.service.business.api.core.GridFilterService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultCentricPersonRole implements CentricPersonRoleService {
    private final CentricAccountRepository centricAccountRepository;

    public DefaultCentricPersonRole(GridFilterService gridFilterService, CentricPersonRoleGridProvider centricPersonRoleGridProvider, CentricPersonRoleRepository centricPersonRoleRepository, CentricAccountRepository centricAccountRepository) {
        this.centricAccountRepository = centricAccountRepository;
    }

    @Override
    public List<CentricPersonRoleResponce> getCentricPersonRoleByOrganAndPersonRoleTypeAndPersonId(CentricPersonRoleRequest centricPersonRoleRequest) {
        List<CentricAccount> centricAccounts = centricAccountRepository.findByCentricPersonRoleAndOrganizationAndPersonRoleTypeAndPerson(1L, centricPersonRoleRequest.getPersonRoleTypeId(), centricPersonRoleRequest.getPersonId());
        return centricAccounts.stream().map(e -> CentricPersonRoleResponce.builder().id(e.getId())
                .name(e.getName())
                .code(e.getCode()).build()).collect(Collectors.toList());

    }
}

