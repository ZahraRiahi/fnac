package ir.demisco.cfs.app.web.controller;

import ir.demisco.cfs.model.dto.request.CentricPersonRoleRequest;
import ir.demisco.cfs.model.dto.response.CentricPersonRoleResponce;
import ir.demisco.cfs.service.api.CentricPersonRoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api-centric_person_role")
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
