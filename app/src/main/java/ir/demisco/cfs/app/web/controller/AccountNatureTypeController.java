package ir.demisco.cfs.app.web.controller;

import ir.demisco.cfs.model.dto.response.AccountNatureTypeDto;
import ir.demisco.cfs.service.api.AccountNatureTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api-accountNatureType")
public class AccountNatureTypeController {
    private final AccountNatureTypeService accountNatureTypeService;

    public AccountNatureTypeController(AccountNatureTypeService accountNatureTypeService) {
        this.accountNatureTypeService = accountNatureTypeService;
    }


    @PostMapping("/list")
    public ResponseEntity<List<AccountNatureTypeDto>> responseEntity() {
        return ResponseEntity.ok(accountNatureTypeService.getAccountNatureType());
    }
}
