package ir.demisco.cfs.app.web.controller;

import ir.demisco.cfs.model.dto.response.FinancialAccountResponse;
import ir.demisco.cfs.service.api.FinancialAccountService;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api-financialAccount")
public class FinancialAccountController {
    private final FinancialAccountService financialAccountService;

    public FinancialAccountController(FinancialAccountService financialAccountService) {
        this.financialAccountService = financialAccountService;
    }

    @PostMapping("/list")
    public ResponseEntity<DataSourceResult> financialAccountResponseEntity(@RequestBody DataSourceRequest dataSourceRequest) {
        return ResponseEntity.ok(financialAccountService.getFinancialAccount(dataSourceRequest));
    }

    @PostMapping("/Get")
    public ResponseEntity<List<FinancialAccountResponse>> responseEntity() {
        return ResponseEntity.ok(financialAccountService.getFinancialAccountLov(1L));
    }

}