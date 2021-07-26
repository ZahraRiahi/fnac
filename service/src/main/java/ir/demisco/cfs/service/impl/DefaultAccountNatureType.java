package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.response.AccountNatureTypeDto;
import ir.demisco.cfs.service.api.AccountNatureTypeService;
import ir.demisco.cfs.service.repository.AccountNatureTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultAccountNatureType implements AccountNatureTypeService {
    private final AccountNatureTypeRepository accountNatureTypeRepository;

    public DefaultAccountNatureType(AccountNatureTypeRepository accountNatureTypeRepository) {
        this.accountNatureTypeRepository = accountNatureTypeRepository;
    }

    @Override
    public List<AccountNatureTypeDto> getAccountNatureType() {
        return accountNatureTypeRepository.findByAccountNatureType().stream().map(e -> AccountNatureTypeDto.builder()
                .id(e.getId())
                .description(e.getDescription())
                .build()
        ).collect(Collectors.toList());
    }

}
