package ir.demisco.cfs.app.web.controller;

import ir.demisco.cfs.model.dto.response.FinancialCodingTypeDto;
import ir.demisco.cfs.model.dto.response.FinancialCodingTypeResponse;
import ir.demisco.cfs.service.api.FinancialCodingTypeService;
import ir.demisco.cloud.core.security.util.SecurityHelper;
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
    public ResponseEntity<List<FinancialCodingTypeResponse>> responseEntity() {
        return ResponseEntity.ok(financialCodingTypeService.getFinancialCodingTypeByOrganizationId(100L));
    }

    @PostMapping("/save")
    public ResponseEntity<FinancialCodingTypeDto> saveFinancialCodingType(@RequestBody FinancialCodingTypeDto financialCodingTypeDto) {
        if (financialCodingTypeDto.getId() == null) {
            Long aLong = financialCodingTypeService.save(financialCodingTypeDto);
            financialCodingTypeDto.setId(aLong);
            return ResponseEntity.ok(financialCodingTypeDto);
        } else {
            return ResponseEntity.ok(financialCodingTypeService.update(financialCodingTypeDto));
        }
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long FinancialCodingTypeId) {
        boolean result;
        result = financialCodingTypeService.deleteFinancialCodingTypeById(FinancialCodingTypeId);
        return ResponseEntity.ok(result);

    }
}
