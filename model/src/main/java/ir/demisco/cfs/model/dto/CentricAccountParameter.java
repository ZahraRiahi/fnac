package ir.demisco.cfs.model.dto;

import java.util.Map;

public class CentricAccountParameter {
    private Long id;
    private String code;
    private String name;
    private Long parentCentricAccountId;
    private Long centricAccountTypeId;
    private Long organizationId;
    Map<String, Object> paramMap;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentCentricAccountId() {
        return parentCentricAccountId;
    }

    public void setParentCentricAccountId(Long parentCentricAccountId) {
        this.parentCentricAccountId = parentCentricAccountId;
    }

    public Long getCentricAccountTypeId() {
        return centricAccountTypeId;
    }

    public void setCentricAccountTypeId(Long centricAccountTypeId) {
        this.centricAccountTypeId = centricAccountTypeId;
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
