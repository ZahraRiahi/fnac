package ir.demisco.cfs.app.web.controller;

import ir.demisco.cfs.service.api.FinancialAccountStructureService;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api-financialAccountStructure")
public class FinancialAccountStructureController {
    private final FinancialAccountStructureService financialAccountStructureService;

    public FinancialAccountStructureController(FinancialAccountStructureService financialAccountStructureService) {
        this.financialAccountStructureService = financialAccountStructureService;
    }

    @PostMapping("/list/{id}")
    public ResponseEntity<DataSourceResult> responseEntity(@RequestBody DataSourceRequest dataSourceRequest, @PathVariable("id") Long financialCodingTypeId) {
        return ResponseEntity.ok(financialAccountStructureService.getFinancialAccountStructureByFinancialCodingTypeId(financialCodingTypeId, dataSourceRequest));
    }
}
