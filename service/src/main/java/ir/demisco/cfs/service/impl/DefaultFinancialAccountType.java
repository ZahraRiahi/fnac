package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.request.FinancialAccountTypeRequest;
import ir.demisco.cfs.model.dto.response.FinancialAccountTypeDto;
import ir.demisco.cfs.service.api.FinancialAccountTypeService;
import ir.demisco.cfs.service.repository.FinancialAccountTypeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultFinancialAccountType implements FinancialAccountTypeService {
    private final FinancialAccountTypeRepository financialAccountTypeRepository;

    public DefaultFinancialAccountType(FinancialAccountTypeRepository financialAccountTypeRepository) {
        this.financialAccountTypeRepository = financialAccountTypeRepository;
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public List<FinancialAccountTypeDto> getFinancialAccountByFinancialAccountId(FinancialAccountTypeRequest financialAccountTypeRequest) {
        Object financialAccount;
        if (financialAccountTypeRequest.getFinancialAccountId() != null) {
            financialAccount = "financialAccount";
        } else {
            financialAccountTypeRequest.setFinancialAccountId(0L);
            financialAccount = null;
        }
        List<Object[]> financialAccountTypeListObject = financialAccountTypeRepository.findByFinancialAccountAndFinancialAccountId(financialAccount, financialAccountTypeRequest.getFinancialAccountId());
        return financialAccountTypeListObject.stream().map(objects -> FinancialAccountTypeDto.builder().id(Long.parseLong(objects[0].toString()))
                .description(objects[1].toString())
                .flgExists(Long.parseLong(objects[2].toString()))
                .build()).collect(Collectors.toList());

    }

}

