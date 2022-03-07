package ir.demisco.cfs.model.dto.request;

import java.util.List;
import java.util.Map;

public class FinancialAccountLovRequest {
    private Long financialCodingTypeId;
    private Object financialAccountList;
    private List<Long> financialAccountIdList;
    private Long organizationId;
    private String description;
    private String code;
    Map<String, Object> paramMap;

    public Long getFinancialCodingTypeId() {
        return financialCodingTypeId;
    }

    public void setFinancialCodingTypeId(Long financialCodingTypeId) {
        this.financialCodingTypeId = financialCodingTypeId;
    }

    public Object getFinancialAccountList() {
        return financialAccountList;
    }

    public void setFinancialAccountList(Object financialAccountList) {
        this.financialAccountList = financialAccountList;
    }

    public List<Long> getFinancialAccountIdList() {
        return financialAccountIdList;
    }

    public void setFinancialAccountIdList(List<Long> financialAccountIdList) {
        this.financialAccountIdList = financialAccountIdList;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }
}
