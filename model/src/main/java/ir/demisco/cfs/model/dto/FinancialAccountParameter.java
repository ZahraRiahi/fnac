package ir.demisco.cfs.model.dto;


import java.util.HashMap;
import java.util.Map;

public class FinancialAccountParameter {
    private Long id;
    private Long organizationId;
    private String description;
    private Long accountNatureTypeId;
    private String accountNatureTypeDescription;
    private Long accountRelationTypeId;
    private String accountRelationTypeDescription;
    private Long financialAccountParentId;
    private Long financialCodingTypeId;
    private Long financialAccountStructureId;
    Map<String, Object> paramMap;

    public Long getFinancialCodingTypeId() {
        return financialCodingTypeId;
    }

    public Long getFinancialAccountStructureId() {
        return financialAccountStructureId;
    }

    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    public FinancialAccountParameter() {
        paramMap = new HashMap<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getAccountNatureTypeId() {
        return accountNatureTypeId;
    }

    public void setAccountNatureTypeId(Long accountNatureTypeId) {
        this.accountNatureTypeId = accountNatureTypeId;
    }

    public String getAccountNatureTypeDescription() {
        return accountNatureTypeDescription;
    }

    public void setAccountNatureTypeDescription(String accountNatureTypeDescription) {
        this.accountNatureTypeDescription = accountNatureTypeDescription;
    }

    public Long getAccountRelationTypeId() {
        return accountRelationTypeId;
    }

    public void setAccountRelationTypeId(Long accountRelationTypeId) {
        this.accountRelationTypeId = accountRelationTypeId;
    }

    public String getAccountRelationTypeDescription() {
        return accountRelationTypeDescription;
    }

    public void setAccountRelationTypeDescription(String accountRelationTypeDescription) {
        this.accountRelationTypeDescription = accountRelationTypeDescription;
    }

    public Long getFinancialAccountParentId() {
        return financialAccountParentId;
    }

    public void setFinancialAccountParentId(Long financialAccountParentId) {
        this.financialAccountParentId = financialAccountParentId;
    }

    public void setFinancialCodingTypeId(Long financialCodingTypeId) {
        this.financialCodingTypeId = financialCodingTypeId;
    }

    public void setFinancialAccountStructureId(Long financialAccountStructureId) {
        this.financialAccountStructureId = financialAccountStructureId;
    }
}
