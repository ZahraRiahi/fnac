package ir.demisco.cfs.app.web.controller;

import ir.demisco.cfs.model.dto.request.AccountDefaultValueDtoRequest;
import ir.demisco.cfs.model.dto.request.AccountDefaultValueUpdateRequest;
import ir.demisco.cfs.model.dto.response.AccountDefaultValueDto;
import ir.demisco.cfs.model.dto.response.AccountDefaultValueOutPutResponse;
import ir.demisco.cfs.service.api.AccountDefaultValueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-accountDefaultValue")
public class AccountDefaultValueController {
    private final AccountDefaultValueService accountDefaultValueService;

    public AccountDefaultValueController(AccountDefaultValueService accountDefaultValueService) {
        this.accountDefaultValueService = accountDefaultValueService;
    }

    @PostMapping("/save")
    public ResponseEntity<List<AccountDefaultValueDto>> saveCentricAccount(@RequestBody AccountDefaultValueDtoRequest accountDefaultValueDtoRequest) {
        return ResponseEntity.ok(accountDefaultValueService.save(accountDefaultValueDtoRequest));

    }

    @PostMapping("/update")
    public ResponseEntity<List<AccountDefaultValueOutPutResponse>> updateCentricAccount(@RequestBody AccountDefaultValueUpdateRequest accountDefaultValueUpdateRequest) {
        return ResponseEntity.ok(accountDefaultValueService.updateAccountDefaultValueById(accountDefaultValueUpdateRequest));
    }
}
