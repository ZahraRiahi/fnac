package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.response.FinancialAccountTypeDto;
import ir.demisco.cfs.service.api.FinancialAccountTypeService;
import ir.demisco.cfs.service.repository.AccountRelatedTypeRepository;
import ir.demisco.cfs.service.repository.FinancialAccountTypeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultFinancialAccountType implements FinancialAccountTypeService {
    private final FinancialAccountTypeRepository financialAccountTypeRepository;

    public DefaultFinancialAccountType(FinancialAccountTypeRepository financialAccountTypeRepository, AccountRelatedTypeRepository accountRelatedTypeRepository, FinancialAccountTypeRepository financialAccountTypeRepository1) {
        this.financialAccountTypeRepository = financialAccountTypeRepository;
    }


    @Override
    @Transactional
    public List<FinancialAccountTypeDto> getFinancialAccountType(Long financialAccountId) {
        List<Object[]> typeListObject = financialAccountTypeRepository.findByFinancialAccountAndFinancialAccountId(financialAccountId);
        return typeListObject.stream().map(objects -> FinancialAccountTypeDto.builder().id(Long.parseLong(objects[0].toString()))
                .code(objects[1].toString())
                .description(objects[2].toString())
                .flgExists(Long.parseLong(objects[3].toString())).build()).collect(Collectors.toList());
    }

}
