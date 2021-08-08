package ir.demisco.cfs.app.web.controller;

import ir.demisco.cfs.model.dto.response.FinancialAccountTypeDto;
import ir.demisco.cfs.service.api.FinancialAccountTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api-financialAccountType")
public class FinancialAccountTypeController {
    private final FinancialAccountTypeService financialAccountTypeService;

    public FinancialAccountTypeController(FinancialAccountTypeService financialAccountTypeService) {
        this.financialAccountTypeService = financialAccountTypeService;
    }

    @PostMapping("/list/{financialAccountId}")
    public ResponseEntity<List<FinancialAccountTypeDto>> responseEntity(@PathVariable Long financialAccountId) {
        return ResponseEntity.ok(financialAccountTypeService.getFinancialAccountType(financialAccountId));
    }

}
