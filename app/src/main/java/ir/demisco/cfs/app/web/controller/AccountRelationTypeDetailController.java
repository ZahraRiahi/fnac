package ir.demisco.cfs.app.web.controller;

import ir.demisco.cfs.service.api.AccountRelationTypeDetailService;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api-accountRelationTypeDetail")
public class AccountRelationTypeDetailController {
    private final AccountRelationTypeDetailService accountRelationTypeDetailService;

    public AccountRelationTypeDetailController(AccountRelationTypeDetailService accountRelationTypeDetailService) {
        this.accountRelationTypeDetailService = accountRelationTypeDetailService;
    }

    @PostMapping("/Get")
    public ResponseEntity<DataSourceResult> responseEntity(@RequestBody DataSourceRequest dataSourceRequest) {
        return ResponseEntity.ok(accountRelationTypeDetailService.getFinancialAccountByFinancialAccountId(dataSourceRequest));
    }
}
