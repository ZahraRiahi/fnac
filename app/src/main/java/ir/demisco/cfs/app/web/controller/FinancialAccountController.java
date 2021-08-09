package ir.demisco.cfs.app.web.controller;

import ir.demisco.cfs.model.dto.request.FinancialAccountRequest;
import ir.demisco.cfs.model.dto.response.FinancialAccountAdjustmentResponse;
import ir.demisco.cfs.model.dto.response.FinancialAccountOutPutDto;
import ir.demisco.cfs.model.dto.response.FinancialAccountOutPutResponse;
import ir.demisco.cfs.model.dto.response.FinancialAccountResponse;
import ir.demisco.cfs.service.api.FinancialAccountService;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok(financialAccountService.getFinancialAccountLov(100L));
    }

    @GetMapping("/Get/{financialAccountId}")
    public ResponseEntity<FinancialAccountOutPutResponse> responseEntity(@PathVariable Long financialAccountId) {
        return ResponseEntity.ok(financialAccountService.getFinancialAccountGetById(financialAccountId));

    }


    @PostMapping("/save")
    public ResponseEntity<FinancialAccountOutPutDto> saveCentricAccount(@RequestBody FinancialAccountRequest financialAccountRequest) {
        if (financialAccountRequest.getId() == null) {
            FinancialAccountOutPutDto financialAccountOutPutDto = financialAccountService.save(financialAccountRequest);
            return ResponseEntity.ok(financialAccountOutPutDto);
        } else {
            return null;
//            return ResponseEntity.ok(centricAccountService.update(centricAccountRequest));
        }
    }

    @PostMapping("/GetAdjustment")
    public ResponseEntity<List<FinancialAccountAdjustmentResponse>> responseEntityFinancialAccountAdjustmen() {
        return ResponseEntity.ok(financialAccountService.getFinancialAccountAdjustmentLov(100L));
    }


}
