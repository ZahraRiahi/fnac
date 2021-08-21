package ir.demisco.cfs.app.web.controller;

import ir.demisco.cfs.model.dto.request.AccountRelatedDescriptionRequest;
import ir.demisco.cfs.model.dto.response.AccountRelatedDescriptionDto;
import ir.demisco.cfs.service.api.AccountRelatedDescriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/save")
    public ResponseEntity<AccountRelatedDescriptionDto> saveCentricAccount(@RequestBody AccountRelatedDescriptionRequest accountRelatedDescriptionRequest) {
        AccountRelatedDescriptionDto accountRelatedDescriptionDto = accountRelatedDescriptionService.save(accountRelatedDescriptionRequest);
        return ResponseEntity.ok(accountRelatedDescriptionDto);

    }

}
