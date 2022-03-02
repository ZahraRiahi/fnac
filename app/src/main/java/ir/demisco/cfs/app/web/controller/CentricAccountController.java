package ir.demisco.cfs.app.web.controller;

import ir.demisco.cfs.model.dto.request.*;
import ir.demisco.cfs.model.dto.response.*;
import ir.demisco.cfs.service.api.CentricAccountService;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import ir.demisco.cloud.core.security.util.SecurityHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-centricAccount")
public class CentricAccountController {
    private final CentricAccountService centricAccountService;

    public CentricAccountController(CentricAccountService centricAccountService) {
        this.centricAccountService = centricAccountService;
    }

//    @PostMapping("/list")
//    public ResponseEntity<DataSourceResult> responseEntity(@RequestBody DataSourceRequest dataSourceRequest) {
//        return ResponseEntity.ok(centricAccountService.getCentricAccountByOrganizationIdAndPersonAndName(dataSourceRequest));
//    }

    @PostMapping("/list")
    public ResponseEntity<DataSourceResult> responseEntity(@RequestBody DataSourceRequest dataSourceRequest) {
        return ResponseEntity.ok(centricAccountService.getCentricAccountByOrganizationIdAndPersonAndName(dataSourceRequest));
    }

//    @PostMapping("/Get")
//    public ResponseEntity<DataSourceResult> responseEntityLov(@RequestBody DataSourceRequest dataSourceRequest) {
//        return ResponseEntity.ok(centricAccountService.getCentricAccountByOrganizationIdAndCentricAccountTypeId(dataSourceRequest));
//    }


    @PostMapping("/Get")
    public ResponseEntity<DataSourceResult> responseEntityLov(@RequestBody DataSourceRequest dataSourceRequest) {
        return ResponseEntity.ok(centricAccountService.getCentricAccountLov(SecurityHelper.getCurrentUser().getOrganizationId(), dataSourceRequest));
    }


    @PostMapping("/save")
    public ResponseEntity<CentricAccountDto> saveCentricAccount(@RequestBody CentricAccountRequest centricAccountRequest) {
        CentricAccountDto centricAccountDto = centricAccountService.save(centricAccountRequest);
        return ResponseEntity.ok(centricAccountDto);

    }


    @GetMapping("/GetByPerson/{id}")
    public ResponseEntity<Boolean> GetByPerson(@PathVariable("id") Long personId) {
        boolean result;
        result = centricAccountService.getCentricAccountByOrganIdAndPersonId(personId, SecurityHelper.getCurrentUser().getOrganizationId());
        return ResponseEntity.ok(result);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Boolean> DeleteCentricAccount(@PathVariable("id") Long centricAccountId) {
        boolean result;
        result = centricAccountService.deleteCentricAccountById(centricAccountId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/GetForUpdate/{centricAccountId}")
    public ResponseEntity<CentricAccountOutPutResponse> responseEntity(@PathVariable Long centricAccountId) {
        return ResponseEntity.ok(centricAccountService.getCentricAccountGetById(centricAccountId));

    }


    @PostMapping("/GetByTypeId")
    public ResponseEntity<List<CentricAccountNewResponse>> responseEntityCentricAccountType(@RequestBody CentricAccountNewTypeRequest centricAccountNewTypeRequest) {
        return ResponseEntity.ok(centricAccountService.getCentricAccountByOrganIdAndcentricAccountTypeId(centricAccountNewTypeRequest));
    }


}

