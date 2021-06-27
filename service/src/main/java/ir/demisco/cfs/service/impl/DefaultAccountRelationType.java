package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.response.AccountRelationTypeDto;
import ir.demisco.cfs.service.api.AccountRelationTypeService;
import ir.demisco.cfs.service.repository.AccountRelationTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultAccountRelationType implements AccountRelationTypeService {
    private final AccountRelationTypeRepository accountRelationTypeRepository;

    public DefaultAccountRelationType(AccountRelationTypeRepository accountRelationTypeRepository) {
        this.accountRelationTypeRepository = accountRelationTypeRepository;
    }

    @Override
    public List<AccountRelationTypeDto> getAccountRelationType() {
        return accountRelationTypeRepository.findByAccountRelationType().stream().map(e -> AccountRelationTypeDto.builder()
                .id(e.getId())
                .description(e.getDescription())
                .build()
        ).collect(Collectors.toList());
    }
}
