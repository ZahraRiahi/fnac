package ir.demisco.cfs.app.web.controller;

import ir.demisco.cfs.model.dto.request.CentricAccountRequest;
import ir.demisco.cfs.model.dto.response.CentricAccountDto;
import ir.demisco.cfs.model.dto.response.CentricAccountNewResponse;
import ir.demisco.cfs.model.dto.request.CentricAccountPersonRequest;
import ir.demisco.cfs.service.api.CentricAccountService;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
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

    @PostMapping("/list")
    public ResponseEntity<DataSourceResult> responseEntity(@RequestBody DataSourceRequest dataSourceRequest) {
        return ResponseEntity.ok(centricAccountService.getCentricAccountByOrganizationIdAndPersonAndName(dataSourceRequest));
    }

    @PostMapping("/Get/{id}")
    public ResponseEntity<List<CentricAccountNewResponse>> responseEntity(@PathVariable("id") Long centricAccountTypeId) {
        return ResponseEntity.ok(centricAccountService.getCentricAccountByOrganizationIdAndCentricAccountTypeId(centricAccountTypeId, 1L));

    }

    @PostMapping("/save")
    public ResponseEntity<CentricAccountDto> saveCentricAccount(@RequestBody CentricAccountRequest centricAccountRequest) {
        if (centricAccountRequest.getId() == null) {
            CentricAccountDto centricAccountDto = centricAccountService.save(centricAccountRequest);
            return ResponseEntity.ok(centricAccountDto);
        } else {
            return null;
//            return ResponseEntity.ok(centricAccountService.update(centricAccountRequest));
        }
    }


    @PostMapping("/GetByPerson")
    public ResponseEntity<List<CentricAccountNewResponse>> responseEntityCentricAccount(@RequestBody CentricAccountPersonRequest centricAccountPersonRequest) {
        return ResponseEntity.ok(centricAccountService.getCentricAccountByOrganIdAndPersonId(centricAccountPersonRequest));
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long centricAccountId) {
        boolean result;
        result = centricAccountService.deleteCentricAccountById(centricAccountId);
        return ResponseEntity.ok(result);
    }
}

