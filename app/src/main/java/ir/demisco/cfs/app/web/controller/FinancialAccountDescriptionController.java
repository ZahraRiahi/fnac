package ir.demisco.cfs.app.web.controller;

import ir.demisco.cfs.model.dto.response.FinancialAccountDescriptionDto;
import ir.demisco.cfs.service.api.FinancialAccountDescriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api-financialAccountDescription")
public class FinancialAccountDescriptionController {
    private final FinancialAccountDescriptionService financialAccountDescriptionService;

    public FinancialAccountDescriptionController(FinancialAccountDescriptionService financialAccountDescriptionService) {
        this.financialAccountDescriptionService = financialAccountDescriptionService;
    }

    @PostMapping("/list")
    public ResponseEntity<List<FinancialAccountDescriptionDto>> responseEntity() {
        return ResponseEntity.ok(financialAccountDescriptionService.getFinancialAccountDescription());
    }

}
