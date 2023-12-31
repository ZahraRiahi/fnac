package ir.demisco.cfs.model.dto.response;

import java.time.LocalDateTime;

public class CentricAccountDto {
    private Long id;
    private String code;
    private String name;
    private String abbreviationName;
    private String latinName;
    private Long centricAccountTypeId;
    private String centricAccountTypeDescription;
    private String centricAccountTypeCode;
    private Long organizationId;
    private Long personId;
    private String personName;
    private Boolean activeFlag;
    private Long parentCentricAccountId;
    private String parentCentricAccountCode;
    private String parentCentricAccountName;
    private LocalDateTime deletedDate;

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

    public String getCentricAccountTypeCode() {
        return centricAccountTypeCode;
    }

    public void setCentricAccountTypeCode(String centricAccountTypeCode) {
        this.centricAccountTypeCode = centricAccountTypeCode;
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

    public LocalDateTime getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(LocalDateTime deletedDate) {
        this.deletedDate = deletedDate;
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

    public static CentricAccountDto.Builder builder() {
        return new CentricAccountDto.Builder();
    }

    public static final class Builder {
        private CentricAccountDto centricAccountDto;

        private Builder() {
            centricAccountDto = new CentricAccountDto();
        }

        public static Builder aCentricAccountDto() {
            return new Builder();
        }

        public Builder id(Long id) {
            centricAccountDto.setId(id);
            return this;
        }

        public Builder code(String code) {
            centricAccountDto.setCode(code);
            return this;
        }

        public Builder name(String name) {
            centricAccountDto.setName(name);
            return this;
        }

        public Builder abbreviationName(String abbreviationName) {
            centricAccountDto.setAbbreviationName(abbreviationName);
            return this;
        }

        public Builder latinName(String latinName) {
            centricAccountDto.setLatinName(latinName);
            return this;
        }

        public Builder centricAccountTypeId(Long centricAccountTypeId) {
            centricAccountDto.setCentricAccountTypeId(centricAccountTypeId);
            return this;
        }

        public Builder centricAccountTypeDescription(String centricAccountTypeDescription) {
            centricAccountDto.setCentricAccountTypeDescription(centricAccountTypeDescription);
            return this;
        }
        public Builder centricAccountTypeCode(String centricAccountTypeCode) {
            centricAccountDto.setCentricAccountTypeCode(centricAccountTypeCode);
            return this;
        }

        public Builder organizationId(Long organizationId) {
            centricAccountDto.setOrganizationId(organizationId);
            return this;
        }

        public Builder personId(Long personId) {
            centricAccountDto.setPersonId(personId);
            return this;
        }

        public Builder personName(String personName) {
            centricAccountDto.setPersonName(personName);
            return this;
        }

        public Builder activeFlag(Boolean activeFlag) {
            centricAccountDto.setActiveFlag(activeFlag);
            return this;
        }

        public Builder parentCentricAccountId(Long parentCentricAccountId) {
            centricAccountDto.setParentCentricAccountId(parentCentricAccountId);
            return this;
        }

        public Builder parentCentricAccountCode(String parentCentricAccountCode) {
            centricAccountDto.setParentCentricAccountCode(parentCentricAccountCode);
            return this;
        }

        public Builder parentCentricAccountName(String parentCentricAccountName) {
            centricAccountDto.setParentCentricAccountName(parentCentricAccountName);
            return this;
        }

        public Builder deletedDate(LocalDateTime deletedDate) {
            centricAccountDto.setDeletedDate(deletedDate);
            return this;
        }

        public CentricAccountDto build() {
            return centricAccountDto;
        }
    }

}
