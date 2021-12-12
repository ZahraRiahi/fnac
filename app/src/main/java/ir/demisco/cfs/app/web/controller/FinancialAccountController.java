package ir.demisco.cfs.app.web.controller;

import ir.demisco.cfs.model.dto.request.*;
import ir.demisco.cfs.model.dto.response.*;
import ir.demisco.cfs.service.api.FinancialAccountService;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import ir.demisco.cloud.core.security.util.SecurityHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@RestController
@RequestMapping("/api-financialAccount")
public class FinancialAccountController {
    private final FinancialAccountService financialAccountService;
    private final EntityManagerFactory entityManager;

    public FinancialAccountController(FinancialAccountService financialAccountService, EntityManagerFactory entityManager) {
        this.financialAccountService = financialAccountService;
        this.entityManager = entityManager;
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

    //   @GetMapping("/GetAdjustment")
//    public ResponseEntity<List<FinancialAccountAdjustmentResponse>> responseEntityFinancialAccountAdjustmen() {
//        return ResponseEntity.ok(financialAccountService.getFinancialAccountAdjustmentLov(SecurityHelper.getCurrentUser().getOrganizationId()));
//    }
    @PostMapping("/GetAdjustment")
    public ResponseEntity<DataSourceResult> responseEntityFinancialAccountAdjustment(@RequestBody DataSourceRequest dataSourceRequest) {
        return ResponseEntity.ok(financialAccountService.getFinancialAccountAdjustmentLov(SecurityHelper.getCurrentUser().getOrganizationId(), dataSourceRequest));
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

//    @PostMapping("/GetByStructure")
//    public ResponseEntity<List<FinancialAccountGetByStructureResponse>> responseEntityGetByStructure(@RequestBody FinancialAccountGetByStructureRequest financialAccountGetByStructureRequest) {
//        return ResponseEntity.ok(financialAccountService.getFinancialAccountByGetByStructure(SecurityHelper.getCurrentUser().getOrganizationId(),financialAccountGetByStructureRequest));
//    }

    @PostMapping("/GetByStructure")
    public ResponseEntity<DataSourceResult> responseEntityGetByStructure(@RequestBody DataSourceRequest dataSourceRequest) {
        return ResponseEntity.ok(financialAccountService.getFinancialAccountByGetByStructure(SecurityHelper.getCurrentUser().getOrganizationId(), dataSourceRequest));
    }
}
