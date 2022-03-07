package ir.demisco.cfs.app.web.controller;

import ir.demisco.cfs.model.dto.response.FinancialCodingTypeDto;
import ir.demisco.cfs.model.dto.response.FinancialCodingTypeResponse;
import ir.demisco.cfs.service.api.CodingTypeOrgRelService;
import ir.demisco.cfs.service.api.FinancialCodingTypeService;
import ir.demisco.cloud.core.security.util.SecurityHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-financialCodingType")
public class FinancialCodingTypeController {
    private final FinancialCodingTypeService financialCodingTypeService;
    private final CodingTypeOrgRelService codingTypeOrgRelService;

    public FinancialCodingTypeController(FinancialCodingTypeService financialCodingTypeService, CodingTypeOrgRelService codingTypeOrgRelService) {
        this.financialCodingTypeService = financialCodingTypeService;
        this.codingTypeOrgRelService = codingTypeOrgRelService;
    }

    @PostMapping("/list")
    public ResponseEntity<List<FinancialCodingTypeResponse>> responseEntity() {
        return ResponseEntity.ok(financialCodingTypeService.getFinancialCodingTypeByOrganizationId(SecurityHelper.getCurrentUser().getOrganizationId()));
    }

    @PostMapping("/save")
    public ResponseEntity<FinancialCodingTypeDto> saveFinancialCodingType(@RequestBody FinancialCodingTypeDto financialCodingTypeDto) {
        if (financialCodingTypeDto.getId() == null) {
            Long aLong = financialCodingTypeService.save(financialCodingTypeDto);
            codingTypeOrgRelService.save(aLong, SecurityHelper.getCurrentUser().getOrganizationId());
            financialCodingTypeDto.setId(aLong);
            financialCodingTypeDto.setOrganizationId(SecurityHelper.getCurrentUser().getOrganizationId());
            return ResponseEntity.ok(financialCodingTypeDto);
        } else {
//            Long aLongUpdate = financialCodingTypeService.save(financialCodingTypeDto);
            FinancialCodingTypeDto financialCodingTypeDtoUpdate = financialCodingTypeService.update(financialCodingTypeDto);
            codingTypeOrgRelService.save(financialCodingTypeDtoUpdate.getId(), SecurityHelper.getCurrentUser().getOrganizationId());
            financialCodingTypeDto.setId(financialCodingTypeDtoUpdate.getId());
            financialCodingTypeDto.setOrganizationId(SecurityHelper.getCurrentUser().getOrganizationId());
            return ResponseEntity.ok(financialCodingTypeDtoUpdate);
        }
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long FinancialCodingTypeId) {
        boolean result;
        result = financialCodingTypeService.deleteFinancialCodingTypeById(FinancialCodingTypeId);
        return ResponseEntity.ok(result);

    }
}
