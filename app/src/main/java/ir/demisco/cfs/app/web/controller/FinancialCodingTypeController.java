package ir.demisco.cfs.app.web.controller;

import ir.demisco.cfs.model.dto.response.FinancialCodingTypeDto;
import ir.demisco.cfs.service.api.FinancialCodingTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api-financialCodingType")
public class FinancialCodingTypeController {
    private final FinancialCodingTypeService financialCodingTypeService;

    public FinancialCodingTypeController(FinancialCodingTypeService financialCodingTypeService) {
        this.financialCodingTypeService = financialCodingTypeService;
    }

    @PostMapping("/list")
    public ResponseEntity<List<FinancialCodingTypeDto>> responseEntity() {
        return ResponseEntity.ok(financialCodingTypeService.getFinancialCodingTypeByOrganizationId(2L));
    }
}
