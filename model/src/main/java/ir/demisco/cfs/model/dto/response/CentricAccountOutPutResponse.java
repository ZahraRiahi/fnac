package ir.demisco.cfs.model.dto.response;


import java.util.List;

public class CentricAccountOutPutResponse {
    private Long id;
    private String code;
    private String name;
    private String abbreviationName;
    private String latinName;
    private Long centricAccountTypeId;
    private String centricAccountTypeDescription;
    private Long organizationId;
    private Long personId;
    private String personName;
    private Boolean activeFlag;
    private Long parentCentricAccountId;
    private String parentCentricAccountCode;
    private String parentCentricAccountName;
    private String centricAccountTypeCode;
    private List<PersonRoleTypeDto> personRoleTypeOutPutModel;

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

    public String getCentricAccountTypeDescription() {
        return centricAccountTypeDescription;
    }

    public void setCentricAccountTypeDescription(String centricAccountTypeDescription) {
        this.centricAccountTypeDescription = centricAccountTypeDescription;
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

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public Boolean getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(Boolean activeFlag) {
        this.activeFlag = activeFlag;
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

    public String getCentricAccountTypeCode() {
        return centricAccountTypeCode;
    }

    public void setCentricAccountTypeCode(String centricAccountTypeCode) {
        this.centricAccountTypeCode = centricAccountTypeCode;
    }

    public List<PersonRoleTypeDto> getPersonRoleTypeOutPutModel() {
        return personRoleTypeOutPutModel;
    }

    public void setPersonRoleTypeOutPutModel(List<PersonRoleTypeDto> personRoleTypeOutPutModel) {
        this.personRoleTypeOutPutModel = personRoleTypeOutPutModel;
    }

    public static CentricAccountOutPutResponse.Builder builder() {
        return new CentricAccountOutPutResponse.Builder();
    }

    public static final class Builder {
        private CentricAccountOutPutResponse centricAccountOutPutResponse;

        private Builder() {
            centricAccountOutPutResponse = new CentricAccountOutPutResponse();
        }

        public static Builder aCentricAccountOutPutResponse() {
            return new Builder();
        }

        public Builder id(Long id) {
            centricAccountOutPutResponse.setId(id);
            return this;
        }

        public Builder code(String code) {
            centricAccountOutPutResponse.setCode(code);
            return this;
        }

        public Builder name(String name) {
            centricAccountOutPutResponse.setName(name);
            return this;
        }

        public Builder abbreviationName(String abbreviationName) {
            centricAccountOutPutResponse.setAbbreviationName(abbreviationName);
            return this;
        }

        public Builder latinName(String latinName) {
            centricAccountOutPutResponse.setLatinName(latinName);
            return this;
        }

        public Builder centricAccountTypeId(Long centricAccountTypeId) {
            centricAccountOutPutResponse.setCentricAccountTypeId(centricAccountTypeId);
            return this;
        }

        public Builder centricAccountTypeDescription(String centricAccountTypeDescription) {
            centricAccountOutPutResponse.setCentricAccountTypeDescription(centricAccountTypeDescription);
            return this;
        }

        public Builder organizationId(Long organizationId) {
            centricAccountOutPutResponse.setOrganizationId(organizationId);
            return this;
        }

        public Builder personId(Long personId) {
            centricAccountOutPutResponse.setPersonId(personId);
            return this;
        }

        public Builder personName(String personName) {
            centricAccountOutPutResponse.setPersonName(personName);
            return this;
        }

        public Builder activeFlag(Boolean activeFlag) {
            centricAccountOutPutResponse.setActiveFlag(activeFlag);
            return this;
        }

        public Builder parentCentricAccountId(Long parentCentricAccountId) {
            centricAccountOutPutResponse.setParentCentricAccountId(parentCentricAccountId);
            return this;
        }

        public Builder parentCentricAccountCode(String parentCentricAccountCode) {
            centricAccountOutPutResponse.setParentCentricAccountCode(parentCentricAccountCode);
            return this;
        }

        public Builder parentCentricAccountName(String parentCentricAccountName) {
            centricAccountOutPutResponse.setParentCentricAccountName(parentCentricAccountName);
            return this;
        }

        public Builder centricAccountTypeCode(String centricAccountTypeCode) {
            centricAccountOutPutResponse.setCentricAccountTypeCode(centricAccountTypeCode);
            return this;
        }

        public Builder personRoleTypeOutPutModel(List<PersonRoleTypeDto> personRoleTypeOutPutModel) {
            centricAccountOutPutResponse.setPersonRoleTypeOutPutModel(personRoleTypeOutPutModel);
            return this;
        }

        public CentricAccountOutPutResponse build() {
            return centricAccountOutPutResponse;
        }
    }
}
