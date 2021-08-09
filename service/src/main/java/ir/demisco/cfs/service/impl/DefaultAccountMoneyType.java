package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.response.MoneyTypeDto;
import ir.demisco.cfs.service.api.AccountMoneyTypeService;
import ir.demisco.cfs.service.repository.MoneyTypeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class DefaultAccountMoneyType implements AccountMoneyTypeService {
    private final MoneyTypeRepository moneyTypeRepository;

    public DefaultAccountMoneyType(MoneyTypeRepository moneyTypeRepository) {
        this.moneyTypeRepository = moneyTypeRepository;
    }

    @Override
    @Transactional
    public List<MoneyTypeDto> getAccountMoneyType(Long financialAccountId) {
        List<Object[]> moneyTypeListObject = moneyTypeRepository.findByMoneyTypeAndFinancialAccountId(financialAccountId);
        return moneyTypeListObject.stream().map(objects -> MoneyTypeDto.builder().id(Long.parseLong(objects[0].toString()))
                .description(objects[1].toString())
                .flgExists(Long.parseLong(objects[2].toString())).build()).collect(Collectors.toList());
    }
}