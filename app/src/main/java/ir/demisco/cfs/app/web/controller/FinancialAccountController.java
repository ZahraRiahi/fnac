package ir.demisco.cfs.app.web.controller;

import ir.demisco.cfs.model.dto.request.FinancialAccountAllowChildRequest;
import ir.demisco.cfs.model.dto.request.FinancialAccountNewRequest;
import ir.demisco.cfs.model.dto.request.FinancialAccountRequest;
import ir.demisco.cfs.model.dto.request.FinancialAccountStatusRequest;
import ir.demisco.cfs.model.dto.response.AccountPermanentStatusDto;
import ir.demisco.cfs.model.dto.response.FinancialAccountNewResponse;
import ir.demisco.cfs.model.dto.response.FinancialAccountOutPutDto;
import ir.demisco.cfs.model.dto.response.FinancialAccountOutPutResponse;
import ir.demisco.cfs.service.api.FinancialAccountService;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import ir.demisco.cloud.core.security.util.SecurityHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ResponseEntity<DataSourceResult> responseEntity(@RequestBody DataSourceRequest dataSourceRequest) {
        return ResponseEntity.ok(financialAccountService.getFinancialAccountLov(dataSourceRequest));
    }

    @GetMapping("/Get/{financialAccountId}")
    public ResponseEntity<FinancialAccountOutPutResponse> responseEntity(@PathVariable Long financialAccountId) {
        return ResponseEntity.ok(financialAccountService.getFinancialAccountGetById(financialAccountId, SecurityHelper.getCurrentUser().getOrganizationId()));
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

    @PostMapping("/GetAdjustment")
    public ResponseEntity<DataSourceResult> responseEntityFinancialAccountAdjustment(@RequestBody DataSourceRequest dataSourceRequest) {
        return ResponseEntity.ok(financialAccountService.getFinancialAccountAdjustmentLov(SecurityHelper.getCurrentUser().getOrganizationId(), dataSourceRequest));
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long financialAccountId) {
        boolean result;
        result = financialAccountService.deleteFinancialAccountById(financialAccountId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/setStatus")
    public ResponseEntity<Boolean> getByPerson(@RequestBody FinancialAccountStatusRequest financialAccountStatusRequest) {
        boolean result;
        result = financialAccountService.getFinancialAccountByIdAndStatusFlag(financialAccountStatusRequest, SecurityHelper.getCurrentUser().getOrganizationId());
        return ResponseEntity.ok(result);
    }

    @PostMapping("/GetSuggestedCode")
    public ResponseEntity<List<FinancialAccountNewResponse>> responseEntity(@RequestBody FinancialAccountNewRequest financialAccountNewRequest) {
        return ResponseEntity.ok(financialAccountService.getFinancialAccountByFinancialAccountParentAndCodingAndStructure(financialAccountNewRequest));
    }

    @PostMapping("/GetAccountPermanentStatusList")
    public ResponseEntity<List<AccountPermanentStatusDto>> responseEntityAccountPermanentStatus() {
        return ResponseEntity.ok(financialAccountService.getAccountPermanentStatusLov());
    }

    @PostMapping("/insertAllowControl")
    public ResponseEntity<Boolean> getInsertAllowControl(@RequestBody FinancialAccountAllowChildRequest financialAccountAllowChildRequest) {
        boolean result;
        result = financialAccountService.getFinancialAccountGetInsertAllowControl(financialAccountAllowChildRequest);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/GetByStructure")
    public ResponseEntity<DataSourceResult> responseEntityGetByStructure(@RequestBody DataSourceRequest dataSourceRequest) {
        return ResponseEntity.ok(financialAccountService.getFinancialAccountByGetByStructure(dataSourceRequest));
    }
}
