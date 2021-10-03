package ir.demisco.cfs.app.web.controller;

import ir.demisco.cfs.model.dto.request.FinancialAccountRequest;
import ir.demisco.cfs.model.dto.request.FinancialAccountStatusRequest;
import ir.demisco.cfs.model.dto.response.*;
import ir.demisco.cfs.service.api.FinancialAccountService;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import ir.demisco.cloud.core.security.util.SecurityHelper;
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

    @GetMapping("/Get")
    public ResponseEntity<List<FinancialAccountResponse>> responseEntity() {
        return ResponseEntity.ok(financialAccountService.getFinancialAccountLov(100L));
    }

    @GetMapping("/Get/{financialAccountId}")
    public ResponseEntity<FinancialAccountOutPutResponse> responseEntity(@PathVariable Long financialAccountId) {
        return ResponseEntity.ok(financialAccountService.getFinancialAccountGetById(financialAccountId,SecurityHelper.getCurrentUser().getOrganizationId()));

    }

    @PostMapping("/save")
    public ResponseEntity<FinancialAccountOutPutDto> saveCentricAccount(@RequestBody FinancialAccountRequest financialAccountRequest) {
        if (financialAccountRequest.getId() == null) {
            FinancialAccountOutPutDto financialAccountOutPutDto = financialAccountService.save(financialAccountRequest);
            return ResponseEntity.ok(financialAccountOutPutDto);
        } else {
            return ResponseEntity.ok(financialAccountService.update(financialAccountRequest));
        }
    }

    @GetMapping("/GetAdjustment")
    public ResponseEntity<List<FinancialAccountAdjustmentResponse>> responseEntityFinancialAccountAdjustmen() {
        return ResponseEntity.ok(financialAccountService.getFinancialAccountAdjustmentLov(SecurityHelper.getCurrentUser().getOrganizationId()));
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long FinancialAccountId) {
        boolean result;
        result = financialAccountService.deleteFinancialAccountById(FinancialAccountId);
        return ResponseEntity.ok(result);

    }
    @PostMapping("/setStatus")
    public ResponseEntity<Boolean> GetByPerson(@RequestBody FinancialAccountStatusRequest financialAccountStatusRequest) {
        boolean result;
        result = financialAccountService.getFinancialAccountByIdAndStatusFlag(financialAccountStatusRequest,SecurityHelper.getCurrentUser().getOrganizationId());
        return ResponseEntity.ok(result);
    }
}
