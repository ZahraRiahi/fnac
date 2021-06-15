package ir.demisco.cfs.app.web.controller;

import ir.demisco.cfs.model.dto.request.CentricPersonRoleRequest;
import ir.demisco.cfs.model.dto.response.CentricPersonRoleResponce;
import ir.demisco.cfs.service.api.CentricPersonRoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-person")
public class CentricPersonRoleController {
    private final CentricPersonRoleService centricPersonRoleService;

    public CentricPersonRoleController(CentricPersonRoleService centricPersonRoleService) {
        this.centricPersonRoleService = centricPersonRoleService;
    }
    @PostMapping("/list")
    public ResponseEntity<List<CentricPersonRoleResponce>> responseEntityCentricPersonRole(@RequestBody CentricPersonRoleRequest centricPersonRoleRequest) {
        return ResponseEntity.ok(centricPersonRoleService.getCentricPersonRoleByOrganAndPersonRoleTypeAndPersonId(centricPersonRoleRequest));
    }

}
