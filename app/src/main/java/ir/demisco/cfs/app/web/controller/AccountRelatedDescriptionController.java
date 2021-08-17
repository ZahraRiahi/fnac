package ir.demisco.cfs.app.web.controller;

import ir.demisco.cfs.service.api.AccountRelatedDescriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api-accountRelatedDescription")
public class AccountRelatedDescriptionController {
    private final AccountRelatedDescriptionService accountRelatedDescriptionService;

    public AccountRelatedDescriptionController(AccountRelatedDescriptionService accountRelatedDescriptionService) {
        this.accountRelatedDescriptionService = accountRelatedDescriptionService;
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long accountRelatedDescriptionId) {
        boolean result;
        result = accountRelatedDescriptionService.deleteAccountRelatedDescriptionById(accountRelatedDescriptionId);
        return ResponseEntity.ok(result);

    }



}
