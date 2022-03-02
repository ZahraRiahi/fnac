package ir.demisco.cfs.model.dto.response;

public class CentricAccountListResponse {
    private Long id;
    private String code;
    private String name;
    private Long activeFlag;
    private String abbreviationName;
    private String latinName;
    private Long centricAccountTypeId;
    private Long organizationId;
    private Long personId;
    private String centricAccountTypeDescription;
    private String centricAccountTypeCode;
    private Long parentCentricAccountId;
    private String parentCentricAccountCode;
    private String parentCentricAccountName;

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

    public Long getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(Long activeFlag) {
        this.activeFlag = activeFlag;
    }

    public String getAbbreviationName() {
        return abbreviationName;
    }

    public void setAbbreviationName(String abbreviationName) {
        this.abbreviationName = abbreviationName;
    }

    public String getLatinName() {
        return latinName;
    }

    public void setLatinName(String latinName) {
        this.latinName = latinName;
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

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getCentricAccountTypeDescription() {
        return centricAccountTypeDescription;
    }

    public void setCentricAccountTypeDescription(String centricAccountTypeDescription) {
        this.centricAccountTypeDescription = centricAccountTypeDescription;
    }

    public String getCentricAccountTypeCode() {
        return centricAccountTypeCode;
    }

    public void setCentricAccountTypeCode(String centricAccountTypeCode) {
        this.centricAccountTypeCode = centricAccountTypeCode;
    }

    public Long getParentCentricAccountId() {
        return parentCentricAccountId;
    }

    public void setParentCentricAccountId(Long parentCentricAccountId) {
        this.parentCentricAccountId = parentCentricAccountId;
    }

    public String getParentCentricAccountCode() {
        return parentCentricAccountCode;
    }

    public void setParentCentricAccountCode(String parentCentricAccountCode) {
        this.parentCentricAccountCode = parentCentricAccountCode;
    }

    public String getParentCentricAccountName() {
        return parentCentricAccountName;
    }

    public void setParentCentricAccountName(String parentCentricAccountName) {
        this.parentCentricAccountName = parentCentricAccountName;
    }

    public static CentricAccountListResponse.Builder builder() {
        return new CentricAccountListResponse.Builder();
    }

    public static final class Builder {
        private CentricAccountListResponse centricAccountListResponse;

        private Builder() {
            centricAccountListResponse = new CentricAccountListResponse();
        }

        public static Builder centricAccountListResponse() {
            return new Builder();
        }

        public Builder id(Long id) {
            centricAccountListResponse.setId(id);
            return this;
        }

        public Builder code(String code) {
            centricAccountListResponse.setCode(code);
            return this;
        }

        public Builder name(String name) {
            centricAccountListResponse.setName(name);
            return this;
        }

        public Builder activeFlag(Long activeFlag) {
            centricAccountListResponse.setActiveFlag(activeFlag);
            return this;
        }

        public Builder abbreviationName(String abbreviationName) {
            centricAccountListResponse.setAbbreviationName(abbreviationName);
            return this;
        }

        public Builder latinName(String latinName) {
            centricAccountListResponse.setLatinName(latinName);
            return this;
        }

        public Builder centricAccountTypeId(Long centricAccountTypeId) {
            centricAccountListResponse.setCentricAccountTypeId(centricAccountTypeId);
            return this;
        }

        public Builder organizationId(Long organizationId) {
            centricAccountListResponse.setOrganizationId(organizationId);
            return this;
        }

        public Builder personId(Long personId) {
            centricAccountListResponse.setPersonId(personId);
            return this;
        }

        public Builder centricAccountTypeDescription(String centricAccountTypeDescription) {
            centricAccountListResponse.setCentricAccountTypeDescription(centricAccountTypeDescription);
            return this;
        }

        public Builder centricAccountTypeCode(String centricAccountTypeCode) {
            centricAccountListResponse.setCentricAccountTypeCode(centricAccountTypeCode);
            return this;
        }

        public Builder parentCentricAccountId(Long parentCentricAccountId) {
            centricAccountListResponse.setParentCentricAccountId(parentCentricAccountId);
            return this;
        }

        public Builder parentCentricAccountCode(String parentCentricAccountCode) {
            centricAccountListResponse.setParentCentricAccountCode(parentCentricAccountCode);
            return this;
        }

        public Builder parentCentricAccountName(String parentCentricAccountName) {
            centricAccountListResponse.setParentCentricAccountName(parentCentricAccountName);
            return this;
        }

        public CentricAccountListResponse build() {
            return centricAccountListResponse;
        }
    }
}
