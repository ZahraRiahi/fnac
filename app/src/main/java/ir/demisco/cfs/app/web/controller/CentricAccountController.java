package ir.demisco.cfs.app.web.controller;

import ir.demisco.cfs.service.api.CentricAccountService;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
