package ir.demisco.cfs.app.web.controller;

import ir.demisco.cfs.model.dto.request.CentricAccountRequest;
import ir.demisco.cfs.model.dto.response.CentricAccountDto;
import ir.demisco.cfs.model.dto.response.CentricAccountOutPutResponse;
import ir.demisco.cfs.service.api.CentricAccountService;
import ir.demisco.cfs.service.api.CentricOrgRelService;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import ir.demisco.cloud.core.security.util.SecurityHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api-centricAccount")
public class CentricAccountController {
    private final CentricAccountService centricAccountService;
    private final CentricOrgRelService centricOrgRelService;

    public CentricAccountController(CentricAccountService centricAccountService, CentricOrgRelService centricOrgRelService) {
        this.centricAccountService = centricAccountService;
        this.centricOrgRelService = centricOrgRelService;
    }


    @PostMapping("/list")
    public ResponseEntity<DataSourceResult> responseEntity(@RequestBody DataSourceRequest dataSourceRequest) {
        return ResponseEntity.ok(centricAccountService.getCentricAccountByOrganizationIdAndPersonAndName(dataSourceRequest));
    }


    @PostMapping("/Get")
    public ResponseEntity<DataSourceResult> responseEntityLov(@RequestBody DataSourceRequest dataSourceRequest) {
        return ResponseEntity.ok(centricAccountService.getCentricAccountByOrganizationIdAndCentricAccountTypeId(dataSourceRequest));
    }

    @PostMapping("/save")
    public ResponseEntity<CentricAccountDto> saveCentricAccount(@RequestBody CentricAccountRequest centricAccountRequest) {
        CentricAccountDto centricAccountDto = centricAccountService.save(centricAccountRequest);
        centricOrgRelService.save(SecurityHelper.getCurrentUser().getOrganizationId(), centricAccountDto);
        return ResponseEntity.ok(centricAccountDto);

    }

    @GetMapping("/GetByPerson/{id}")
    public ResponseEntity<Boolean> getByPerson(@PathVariable("id") Long personId) {
        boolean result;
        result = centricAccountService.getCentricAccountByOrganIdAndPersonId(personId, SecurityHelper.getCurrentUser().getOrganizationId());
        return ResponseEntity.ok(result);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteCentricAccount(@PathVariable("id") Long centricAccountId) {
        boolean result;
        result = centricAccountService.deleteCentricAccountById(centricAccountId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/GetForUpdate/{centricAccountId}")
    public ResponseEntity<CentricAccountOutPutResponse> responseEntity(@PathVariable Long centricAccountId) {
        return ResponseEntity.ok(centricAccountService.getCentricAccountGetById(centricAccountId));

    }

    @PostMapping("/GetByTypeId")
    public ResponseEntity<DataSourceResult> responseEntityCentricAccountType(@RequestBody DataSourceRequest dataSourceRequest) {
        return ResponseEntity.ok(centricAccountService.getCentricAccountByOrganIdAndCentricAccountTypeId(dataSourceRequest));
    }

}

