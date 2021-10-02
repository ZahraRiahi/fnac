package ir.demisco.cfs.model.dto.response;

import java.time.LocalDateTime;

public class FinancialAccountDto {
    private Long id;
    private Long organizationId;
    private String code;
    private String description;
    private Boolean activeFlag;
//    private Boolean permanentFlag;
    private Long accountNatureTypeId;
    private String accountNatureTypeDescription;
    private Long accountRelationTypeId;
    private String accountRelationTypeDescription;
    private Long financialAccountParentId;
    private LocalDateTime deletedDate;
    private Long financialAccountStructureId;
    private Boolean hasChild;
    private Long accountStatusId;
    private String accountStatusCode;
    private String accountStatusDescription;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(Boolean activeFlag) {
        this.activeFlag = activeFlag;
    }

//    public Boolean getPermanentFlag() {
//        return permanentFlag;
//    }
//
//    public void setPermanentFlag(Boolean permanentFlag) {
//        this.permanentFlag = permanentFlag;
//    }

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

    public LocalDateTime getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(LocalDateTime deletedDate) {
        this.deletedDate = deletedDate;
    }

    public static FinancialAccountDto.Builder builder() {
        return new FinancialAccountDto.Builder();
    }

    public Long getFinancialAccountStructureId() {
        return financialAccountStructureId;
    }

    public void setFinancialAccountStructureId(Long financialAccountStructureId) {
        this.financialAccountStructureId = financialAccountStructureId;
    }

    public Boolean getHasChild() {
        return hasChild;
    }

    public void setHasChild(Boolean hasChild) {
        this.hasChild = hasChild;
    }

    public Long getAccountStatusId() {
        return accountStatusId;
    }

    public void setAccountStatusId(Long accountStatusId) {
        this.accountStatusId = accountStatusId;
    }

    public String getAccountStatusCode() {
        return accountStatusCode;
    }

    public void setAccountStatusCode(String accountStatusCode) {
        this.accountStatusCode = accountStatusCode;
    }

    public String getAccountStatusDescription() {
        return accountStatusDescription;
    }

    public void setAccountStatusDescription(String accountStatusDescription) {
        this.accountStatusDescription = accountStatusDescription;
    }

    public static final class Builder {
        private FinancialAccountDto financialAccountDto;

        private Builder() {
            financialAccountDto = new FinancialAccountDto();
        }

        public static Builder financialAccountDto() {
            return new Builder();
        }

        public Builder id(Long id) {
            financialAccountDto.setId(id);
            return this;
        }

        public Builder organizationId(Long organizationId) {
            financialAccountDto.setOrganizationId(organizationId);
            return this;
        }

        public Builder code(String code) {
            financialAccountDto.setCode(code);
            return this;
        }

        public Builder description(String description) {
            financialAccountDto.setDescription(description);
            return this;
        }

        public Builder activeFlag(Boolean activeFlag) {
            financialAccountDto.setActiveFlag(activeFlag);
            return this;
        }

//        public Builder permanentFlag(Boolean permanentFlag) {
//            financialAccountDto.setPermanentFlag(permanentFlag);
//            return this;
//        }

        public Builder accountNatureTypeId(Long accountNatureTypeId) {
            financialAccountDto.setAccountNatureTypeId(accountNatureTypeId);
            return this;
        }

        public Builder accountNatureTypeDescription(String accountNatureTypeDescription) {
            financialAccountDto.setAccountNatureTypeDescription(accountNatureTypeDescription);
            return this;
        }

        public Builder accountRelationTypeId(Long accountRelationTypeId) {
            financialAccountDto.setAccountRelationTypeId(accountRelationTypeId);
            return this;
        }

        public Builder accountRelationTypeDescription(String accountRelationTypeDescription) {
            financialAccountDto.setAccountRelationTypeDescription(accountRelationTypeDescription);
            return this;
        }

        public Builder financialAccountParentId(Long financialAccountParentId) {
            financialAccountDto.setFinancialAccountParentId(financialAccountParentId);
            return this;
        }

        public FinancialAccountDto.Builder deletedDate(LocalDateTime deletedDate) {
            financialAccountDto.setDeletedDate(deletedDate);
            return this;
        }

        public Builder financialAccountStructureId(Long financialAccountStructureId) {
            financialAccountDto.setFinancialAccountStructureId(financialAccountStructureId);
            return this;
        }

        public Builder hasChild(Boolean hasChild) {
            financialAccountDto.setHasChild(hasChild);
            return this;
        }

        public Builder accountStatusId(Long accountStatusId) {
            financialAccountDto.setAccountStatusId(accountStatusId);
            return this;
        }

        public Builder accountStatusCode(String accountStatusCode) {
            financialAccountDto.setAccountStatusCode(accountStatusCode);
            return this;
        }

        public Builder accountStatusDescription(String accountStatusDescription) {
            financialAccountDto.setAccountStatusDescription(accountStatusDescription);
            return this;
        }

        public FinancialAccountDto build() {
            return financialAccountDto;
        }
    }
}
