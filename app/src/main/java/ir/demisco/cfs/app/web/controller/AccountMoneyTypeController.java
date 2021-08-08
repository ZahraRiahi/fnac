package ir.demisco.cfs.app.web.controller;

import ir.demisco.cfs.model.dto.response.MoneyTypeDto;
import ir.demisco.cfs.service.api.AccountMoneyTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api-accountMoneyType")
public class AccountMoneyTypeController {
    private final AccountMoneyTypeService accountMoneyTypeService;

    public AccountMoneyTypeController(AccountMoneyTypeService accountMoneyTypeService) {
        this.accountMoneyTypeService = accountMoneyTypeService;
    }


    @PostMapping("/Get/{financialAccountId}")
    public ResponseEntity<List<MoneyTypeDto>> responseEntity(@PathVariable Long financialAccountId) {
        return ResponseEntity.ok(accountMoneyTypeService.getAccountMoneyType(financialAccountId));
    }
}
