package ir.demisco.cfs.app.web.controller;

import ir.demisco.cfs.model.dto.response.PersonRoleTypeDto;
import ir.demisco.cfs.service.api.PersonRoleTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api-person_role_type")
public class PersonRoleTypeController {
    private final PersonRoleTypeService personRoleTypeService;

    public PersonRoleTypeController(PersonRoleTypeService personRoleTypeService) {
        this.personRoleTypeService = personRoleTypeService;
    }

    @PostMapping("/list")
    public ResponseEntity<List<PersonRoleTypeDto>> responseEntity() {
        return ResponseEntity.ok(personRoleTypeService.getPersonRoleType());
    }
}
