package ir.demisco.cfs.app.web.controller;

import ir.demisco.cfs.model.dto.request.AccountMoneyTypeRequest;
import ir.demisco.cfs.model.dto.response.AccountMoneyTypeDto;
import ir.demisco.cfs.service.api.AccountMoneyTypeService;
import ir.demisco.cloud.core.security.util.SecurityHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/Get")
    public ResponseEntity<List<AccountMoneyTypeDto>> responseEntity(@RequestBody AccountMoneyTypeRequest accountMoneyTypeRequest) {
        return ResponseEntity.ok(accountMoneyTypeService.getAccountMoneyType(accountMoneyTypeRequest, SecurityHelper.getCurrentUser().getOrganizationId()));
    }
}
