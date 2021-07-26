package ir.demisco.cfs.app.web.controller;

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

    @GetMapping("/Get")
    public ResponseEntity<List<FinancialAccountResponse>> responseEntity() {
        return ResponseEntity.ok(financialAccountService.getFinancialAccountLov(100L));
    }

    @PostMapping("/Get/{financialAccountId}")
    public ResponseEntity<FinancialAccountOutPutResponse> responseEntity(@PathVariable Long financialAccountId) {
        return ResponseEntity.ok(financialAccountService.getFinancialAccountGetById(financialAccountId));

    }

//    @PostMapping("/save")
//    public ResponseEntity<FinancialCodingTypeDto> saveFinancialCodingType(@RequestBody FinancialCodingTypeDto financialCodingTypeDto) {
//        if (financialCodingTypeDto.getId() == null) {
//            Long aLong = financialCodingTypeService.save(financialCodingTypeDto);
//            financialCodingTypeDto.setId(aLong);
//            return ResponseEntity.ok(financialCodingTypeDto);
//        } else {
//            return ResponseEntity.ok(financialCodingTypeService.update(financialCodingTypeDto));
//        }
//    }

}
