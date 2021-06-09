package ir.demisco.cfs.app.web.controller;

import ir.demisco.cfs.model.dto.response.CentricAccountTypeResponse;
import ir.demisco.cfs.service.api.CentricAccountTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-centricAccountType")
public class CentricAccountTypeController {
    private final CentricAccountTypeService centricAccountTypeService;

    public CentricAccountTypeController(CentricAccountTypeService centricAccountTypeService) {
        this.centricAccountTypeService = centricAccountTypeService;
    }

    @PostMapping("/list")
    public ResponseEntity<List<CentricAccountTypeResponse>> responseEntity() {
        return ResponseEntity.ok(centricAccountTypeService.getCentricAccountType());
    }
}
