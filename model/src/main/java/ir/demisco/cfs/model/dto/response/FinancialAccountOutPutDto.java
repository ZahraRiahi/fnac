package ir.demisco.cfs.model.dto.response;

import java.util.List;

public class FinancialAccountOutPutDto {
    private Long id;
    private Long organizationId;
    private Long financialAccountStructureId;
    private String fullDescription;
    private String code;
    private String description;
    //    private Boolean activeFlag;
    private String latinDescription;
    private Long accountNatureTypeId;
    private String accountNatureTypeDescription;
    private Boolean relatedToOthersFlag;
    //    private Boolean permanentFlag;
    private Long accountRelationTypeId;
    private String accountRelationTypeDescription;
    private Long financialAccountParentId;
    private String financialAccountParentDescription;
    private String relatedToFundType;
    private Boolean referenceFlag;
    private Boolean convertFlag;
    private Boolean exchangeFlag;
    private Long accountAdjustmentId;
    private String accountAdjustmentDescription;
    private List<AccountRelatedTypeDtoResponse> accountRelatedTypeOutPutModel;
    private List<AccountDefaultValueResponse> accountDefaultValueOutPutModel;
    private List<AccountRelatedDescriptionDto> accountRelatedDescriptionOutputModel;
    private List<AccountMoneyTypeDtoResponse> accountMoneyTypeOutPut;
    private Long accountStatusId;

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

    public Long getFinancialAccountStructureId() {
        return financialAccountStructureId;
    }

    public void setFinancialAccountStructureId(Long financialAccountStructureId) {
        this.financialAccountStructureId = financialAccountStructureId;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
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

//    public Boolean getActiveFlag() {
//        return activeFlag;
//    }
//
//    public void setActiveFlag(Boolean activeFlag) {
//        this.activeFlag = activeFlag;
//    }

    public String getLatinDescription() {
        return latinDescription;
    }

    public void setLatinDescription(String latinDescription) {
        this.latinDescription = latinDescription;
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

    public Boolean getRelatedToOthersFlag() {
        return relatedToOthersFlag;
    }

    public void setRelatedToOthersFlag(Boolean relatedToOthersFlag) {
        this.relatedToOthersFlag = relatedToOthersFlag;
    }

//    public Boolean getPermanentFlag() {
//        return permanentFlag;
//    }
//
//    public void setPermanentFlag(Boolean permanentFlag) {
//        this.permanentFlag = permanentFlag;
//    }

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

    public String getFinancialAccountParentDescription() {
        return financialAccountParentDescription;
    }

    public void setFinancialAccountParentDescription(String financialAccountParentDescription) {
        this.financialAccountParentDescription = financialAccountParentDescription;
    }

    public String getRelatedToFundType() {
        return relatedToFundType;
    }

    public void setRelatedToFundType(String relatedToFundType) {
        this.relatedToFundType = relatedToFundType;
    }

    public Boolean getReferenceFlag() {
        return referenceFlag;
    }

    public void setReferenceFlag(Boolean referenceFlag) {
        this.referenceFlag = referenceFlag;
    }

    public Boolean getConvertFlag() {
        return convertFlag;
    }

    public void setConvertFlag(Boolean convertFlag) {
        this.convertFlag = convertFlag;
    }

    public Boolean getExchangeFlag() {
        return exchangeFlag;
    }

    public void setExchangeFlag(Boolean exchangeFlag) {
        this.exchangeFlag = exchangeFlag;
    }

    public Long getAccountAdjustmentId() {
        return accountAdjustmentId;
    }

    public void setAccountAdjustmentId(Long accountAdjustmentId) {
        this.accountAdjustmentId = accountAdjustmentId;
    }

    public String getAccountAdjustmentDescription() {
        return accountAdjustmentDescription;
    }

    public void setAccountAdjustmentDescription(String accountAdjustmentDescription) {
        this.accountAdjustmentDescription = accountAdjustmentDescription;
    }

    public List<AccountRelatedTypeDtoResponse> getAccountRelatedTypeOutPutModel() {
        return accountRelatedTypeOutPutModel;
    }

    public void setAccountRelatedTypeOutPutModel(List<AccountRelatedTypeDtoResponse> accountRelatedTypeOutPutModel) {
        this.accountRelatedTypeOutPutModel = accountRelatedTypeOutPutModel;
    }

    public List<AccountDefaultValueResponse> getAccountDefaultValueOutPutModel() {
        return accountDefaultValueOutPutModel;
    }

    public void setAccountDefaultValueOutPutModel(List<AccountDefaultValueResponse> accountDefaultValueOutPutModel) {
        this.accountDefaultValueOutPutModel = accountDefaultValueOutPutModel;
    }

    public List<AccountMoneyTypeDtoResponse> getAccountMoneyTypeOutPut() {
        return accountMoneyTypeOutPut;
    }

    public void setAccountMoneyTypeOutPut(List<AccountMoneyTypeDtoResponse> accountMoneyTypeOutPut) {
        this.accountMoneyTypeOutPut = accountMoneyTypeOutPut;
    }

    public Long getAccountStatusId() {
        return accountStatusId;
    }

    public void setAccountStatusId(Long accountStatusId) {
        this.accountStatusId = accountStatusId;
    }

    public static FinancialAccountOutPutDto.Builder builder() {
        return new FinancialAccountOutPutDto.Builder();
    }

    public List<AccountRelatedDescriptionDto> getAccountRelatedDescriptionOutputModel() {
        return accountRelatedDescriptionOutputModel;
    }

    public void setAccountRelatedDescriptionOutputModel(List<AccountRelatedDescriptionDto> accountRelatedDescriptionOutputModel) {
        this.accountRelatedDescriptionOutputModel = accountRelatedDescriptionOutputModel;
    }

    public static final class Builder {
        private FinancialAccountOutPutDto financialAccountOutPutDto;

        private Builder() {
            financialAccountOutPutDto = new FinancialAccountOutPutDto();
        }

        public static Builder financialAccountOutPutDto() {
            return new Builder();
        }

        public Builder id(Long id) {
            financialAccountOutPutDto.setId(id);
            return this;
        }

        public Builder organizationId(Long organizationId) {
            financialAccountOutPutDto.setOrganizationId(organizationId);
            return this;
        }

        public Builder financialAccountStructureId(Long financialAccountStructureId) {
            financialAccountOutPutDto.setFinancialAccountStructureId(financialAccountStructureId);
            return this;
        }

        public Builder fullDescription(String fullDescription) {
            financialAccountOutPutDto.setFullDescription(fullDescription);
            return this;
        }

        public Builder code(String code) {
            financialAccountOutPutDto.setCode(code);
            return this;
        }

        public Builder description(String description) {
            financialAccountOutPutDto.setDescription(description);
            return this;
        }

//        public Builder activeFlag(Boolean activeFlag) {
//            financialAccountOutPutDto.setActiveFlag(activeFlag);
//            return this;
//        }

        public Builder latinDescription(String latinDescription) {
            financialAccountOutPutDto.setLatinDescription(latinDescription);
            return this;
        }

        public Builder accountNatureTypeId(Long accountNatureTypeId) {
            financialAccountOutPutDto.setAccountNatureTypeId(accountNatureTypeId);
            return this;
        }

        public Builder accountNatureTypeDescription(String accountNatureTypeDescription) {
            financialAccountOutPutDto.setAccountNatureTypeDescription(accountNatureTypeDescription);
            return this;
        }

        public Builder relatedToOthersFlag(Boolean relatedToOthersFlag) {
            financialAccountOutPutDto.setRelatedToOthersFlag(relatedToOthersFlag);
            return this;
        }

//        public Builder permanentFlag(Boolean permanentFlag) {
//            financialAccountOutPutDto.setPermanentFlag(permanentFlag);
//            return this;
//        }

        public Builder accountRelationTypeId(Long accountRelationTypeId) {
            financialAccountOutPutDto.setAccountRelationTypeId(accountRelationTypeId);
            return this;
        }

        public Builder accountRelationTypeDescription(String accountRelationTypeDescription) {
            financialAccountOutPutDto.setAccountRelationTypeDescription(accountRelationTypeDescription);
            return this;
        }

        public Builder financialAccountParentId(Long financialAccountParentId) {
            financialAccountOutPutDto.setFinancialAccountParentId(financialAccountParentId);
            return this;
        }

        public Builder financialAccountParentDescription(String financialAccountParentDescription) {
            financialAccountOutPutDto.setFinancialAccountParentDescription(financialAccountParentDescription);
            return this;
        }

        public Builder relatedToFundType(String relatedToFundType) {
            financialAccountOutPutDto.setRelatedToFundType(relatedToFundType);
            return this;
        }

        public Builder referenceFlag(Boolean referenceFlag) {
            financialAccountOutPutDto.setReferenceFlag(referenceFlag);
            return this;
        }

        public Builder convertFlag(Boolean convertFlag) {
            financialAccountOutPutDto.setConvertFlag(convertFlag);
            return this;
        }

        public Builder exchangeFlag(Boolean exchangeFlag) {
            financialAccountOutPutDto.setExchangeFlag(exchangeFlag);
            return this;
        }

        public Builder accountAdjustmentId(Long accountAdjustmentId) {
            financialAccountOutPutDto.setAccountAdjustmentId(accountAdjustmentId);
            return this;
        }

        public Builder accountAdjustmentDescription(String accountAdjustmentDescription) {
            financialAccountOutPutDto.setAccountAdjustmentDescription(accountAdjustmentDescription);
            return this;
        }

        public Builder accountRelatedTypeOutPutModel(List<AccountRelatedTypeDtoResponse> accountRelatedTypeOutPutModel) {
            financialAccountOutPutDto.setAccountRelatedTypeOutPutModel(accountRelatedTypeOutPutModel);
            return this;
        }

        public Builder accountDefaultValueOutPutModel(List<AccountDefaultValueResponse> accountDefaultValueOutPutModel) {
            financialAccountOutPutDto.setAccountDefaultValueOutPutModel(accountDefaultValueOutPutModel);
            return this;
        }

        public Builder accountStatusId(Long accountStatusId) {
            financialAccountOutPutDto.setAccountStatusId(accountStatusId);
            return this;
        }

        public FinancialAccountOutPutDto build() {
            return financialAccountOutPutDto;
        }
    }
}
