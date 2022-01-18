package ir.demisco.cfs.app.web.controller;

import ir.demisco.cfs.model.dto.request.FinancialAccountStructureDtoRequest;
import ir.demisco.cfs.model.dto.request.FinancialAccountStructureNewRequest;
import ir.demisco.cfs.model.dto.request.FinancialAccountStructureRequest;
import ir.demisco.cfs.model.dto.response.*;
import ir.demisco.cfs.service.api.FinancialAccountStructureService;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
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

    @GetMapping("/Get/{financialCodingTypeId}")
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
    public ResponseEntity<Boolean> DeleteFinancialAccountStructure(@PathVariable("id") Long FinancialAccountStructureId) {
        boolean result;
        result = financialAccountStructureService.deleteFinancialAccountStructureById(FinancialAccountStructureId);
        return ResponseEntity.ok(result);

    }

    @PostMapping("/get")
    public ResponseEntity<Long> responseEntity(@RequestBody FinancialAccountStructureRequest financialAccountStructureRequest) {
        return ResponseEntity.ok(financialAccountStructureService.getFinancialAccountStructureByFinancialCodingTypeAndFinancialAccountStructure(financialAccountStructureRequest));
    }

    @PostMapping("/GetPermanentStatus")
    public ResponseEntity<FinancialAccountStructureNewResponse> responseEntity(@RequestBody FinancialAccountStructureNewRequest financialAccountStructureNewRequest) {
        return ResponseEntity.ok(financialAccountStructureService.getFinancialAccountStructureByCodingAndParentAndId(financialAccountStructureNewRequest));
    }

    @PostMapping("/GetSumDigitAndSequence")
    public ResponseEntity<List<FinancialAccountStructureDtoResponse>> responseEntityGetSumDigit(@RequestBody FinancialAccountStructureDtoRequest financialAccountStructureDtoRequest) {
        return ResponseEntity.ok(financialAccountStructureService.getSumDigitAndSequence(financialAccountStructureDtoRequest));
    }


}
