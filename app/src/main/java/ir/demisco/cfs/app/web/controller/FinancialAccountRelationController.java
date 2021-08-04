package ir.demisco.cfs.app.web.controller;

import ir.demisco.cfs.service.api.FinancialAccountRelationService;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api-financialAccountRelation")
public class FinancialAccountRelationController {
    private final FinancialAccountRelationService financialAccountRelationService;

    public FinancialAccountRelationController(FinancialAccountRelationService financialAccountRelationService) {
        this.financialAccountRelationService = financialAccountRelationService;
    }

    @PostMapping("/list/{id}")
    public ResponseEntity<DataSourceResult> responseEntity(@RequestBody DataSourceRequest dataSourceRequest, @PathVariable("id") Long accountRelationTypeId) {
        return ResponseEntity.ok(financialAccountRelationService.getFinancialAccountRelationTypeDetailByFinancialCodingTypeId(accountRelationTypeId, dataSourceRequest));
    }
}
