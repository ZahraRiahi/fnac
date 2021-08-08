package ir.demisco.cfs.app.web.controller;

import ir.demisco.cfs.model.dto.response.MoneyTypeDto;
import ir.demisco.cfs.service.api.MoneyTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api-moneyType")
public class MoneyTypeController {
    private final MoneyTypeService moneyTypeService;

    public MoneyTypeController(MoneyTypeService moneyTypeService) {
        this.moneyTypeService = moneyTypeService;
    }

    @PostMapping("/list/{financialAccountId}")
    public ResponseEntity<List<MoneyTypeDto>> responseEntity(@PathVariable Long financialAccountId) {
        return ResponseEntity.ok(moneyTypeService.getMoneyType(financialAccountId));
    }
}
