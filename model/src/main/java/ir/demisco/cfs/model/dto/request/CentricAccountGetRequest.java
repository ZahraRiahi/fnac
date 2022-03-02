package ir.demisco.cfs.model.dto.request;

import java.util.Map;

public class CentricAccountGetRequest {
    private Long centricAccountTypeId;
    private String parentCentricAccount;
    private Long parentCentricAccountId;
    private Long organizationId;
    Map<String, Object> paramMap;

    public Long getCentricAccountTypeId() {
        return centricAccountTypeId;
    }

    public void setCentricAccountTypeId(Long centricAccountTypeId) {
        this.centricAccountTypeId = centricAccountTypeId;
    }

    public String getParentCentricAccount() {
        return parentCentricAccount;
    }

    public void setParentCentricAccount(String parentCentricAccount) {
        this.parentCentricAccount = parentCentricAccount;
    }

    public Long getParentCentricAccountId() {
        return parentCentricAccountId;
    }

    public void setParentCentricAccountId(Long parentCentricAccountId) {
        this.parentCentricAccountId = parentCentricAccountId;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

}
