package ir.demisco.cfs.app.web.controller;

import ir.demisco.cfs.model.dto.response.AccountRelationTypeDto;
import ir.demisco.cfs.service.api.AccountRelationTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api-accountRelationType")
public class AccountRelationTypeController {
    private final AccountRelationTypeService accountRelationTypeService;

    public AccountRelationTypeController(AccountRelationTypeService accountRelationTypeService) {
        this.accountRelationTypeService = accountRelationTypeService;
    }

    @PostMapping("/list")
    public ResponseEntity<List<AccountRelationTypeDto>> responseEntity() {
        return ResponseEntity.ok(accountRelationTypeService.getAccountRelationType());
    }

}
