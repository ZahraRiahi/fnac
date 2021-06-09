package ir.demisco.cfs.app.web.controller;

import ir.demisco.cfs.model.dto.response.FinancialAccountStructureDto;
import ir.demisco.cfs.model.dto.response.FinancialAccountStructureResponse;
import ir.demisco.cfs.service.api.FinancialAccountStructureService;
import ir.demisco.cloud.core.middle.exception.RuleException;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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

    @PostMapping("/Get/{financialCodingTypeId}")
    public ResponseEntity<List<FinancialAccountStructureResponse>> responseEntity(@PathVariable Long financialCodingTypeId) {
        return ResponseEntity.ok(financialAccountStructureService.getFinancialAccountStructureByFinancialCodingTypeIdLov(financialCodingTypeId));
    }
    @PostMapping("/save")
    public ResponseEntity<FinancialAccountStructureDto> saveFinancialPeriod(@RequestBody FinancialAccountStructureDto financialAccountStructureDto) {
        if (financialAccountStructureDto.getId() == null) {
            Long aLong = financialAccountStructureService.save(financialAccountStructureDto);
            financialAccountStructureDto.setId(aLong);
            return ResponseEntity.ok(financialAccountStructureDto);
        } else {
            return ResponseEntity.ok(financialAccountStructureService.update(financialAccountStructureDto));
        }
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Boolean> financialAccountStructure(@PathVariable("id") Long FinancialAccountStructureId) {
        boolean result;
        try {
            result = financialAccountStructureService.deleteFinancialAccountStructureById(FinancialAccountStructureId);
        } catch (DataIntegrityViolationException e) {
            throw new RuleException("به دلیل استفاده ی اطلاعات در جداول دیگر امکان حذف این ردیف وجود ندارد");
        }
        return ResponseEntity.ok(result);
    }

}
