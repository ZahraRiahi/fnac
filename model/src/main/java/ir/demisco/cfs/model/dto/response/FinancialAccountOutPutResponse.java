package ir.demisco.cfs.model.dto.response;

import java.util.List;

public class FinancialAccountOutPutResponse {
    private Long id;
    private long organizationId;
    private long financialAccountStructureId;
    private String fullDescription;
    private String description;
    private String code;
    private String latinDescription;
    private Long accountNatureTypeId;
    private String accountNatureTypeDescription;
    private Boolean relatedToOthersFlag;
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
    private Long accountStatusId;
    private String accountStatusCode;
    private String accountStatusDescription;
    private Long flgShowInAcc;
    private Long flgPermanentStatus;
    private List<AccountRelatedTypeNewResponse> accountRelatedTypeOutPutModel;
    private List<AccountRelatedDescriptionResponse> accountRelatedDescriptionOutPutModel;
    private List<AccountDefaultValueResponse> accountDefaultValueOutPutModel;
    private List<AccountMoneyTypeResponse> accountMoneyTypeOutPutModel;

    public FinancialAccountOutPutResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(long organizationId) {
        this.organizationId = organizationId;
    }

    public long getFinancialAccountStructureId() {
        return financialAccountStructureId;
    }

    public void setFinancialAccountStructureId(long financialAccountStructureId) {
        this.financialAccountStructureId = financialAccountStructureId;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
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

    public List<AccountRelatedTypeNewResponse> getAccountRelatedTypeOutPutModel() {
        return accountRelatedTypeOutPutModel;
    }

    public void setAccountRelatedTypeOutPutModel(List<AccountRelatedTypeNewResponse> accountRelatedTypeOutPutModel) {
        this.accountRelatedTypeOutPutModel = accountRelatedTypeOutPutModel;
    }

    public List<AccountRelatedDescriptionResponse> getAccountRelatedDescriptionOutPutModel() {
        return accountRelatedDescriptionOutPutModel;
    }

    public void setAccountRelatedDescriptionOutPutModel(List<AccountRelatedDescriptionResponse> accountRelatedDescriptionOutPutModel) {
        this.accountRelatedDescriptionOutPutModel = accountRelatedDescriptionOutPutModel;
    }

    public List<AccountDefaultValueResponse> getAccountDefaultValueOutPutModel() {
        return accountDefaultValueOutPutModel;
    }

    public void setAccountDefaultValueOutPutModel(List<AccountDefaultValueResponse> accountDefaultValueOutPutModel) {
        this.accountDefaultValueOutPutModel = accountDefaultValueOutPutModel;
    }

    public List<AccountMoneyTypeResponse> getAccountMoneyTypeOutPutModel() {
        return accountMoneyTypeOutPutModel;
    }

    public void setAccountMoneyTypeOutPutModel(List<AccountMoneyTypeResponse> accountMoneyTypeOutPutModel) {
        this.accountMoneyTypeOutPutModel = accountMoneyTypeOutPutModel;
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

    public Long getFlgShowInAcc() {
        return flgShowInAcc;
    }

    public void setFlgShowInAcc(Long flgShowInAcc) {
        this.flgShowInAcc = flgShowInAcc;
    }

    public Long getFlgPermanentStatus() {
        return flgPermanentStatus;
    }

    public void setFlgPermanentStatus(Long flgPermanentStatus) {
        this.flgPermanentStatus = flgPermanentStatus;
    }

    public static FinancialAccountOutPutResponse.Builder builder() {
        return new FinancialAccountOutPutResponse.Builder();
    }

    public static final class Builder {
        private FinancialAccountOutPutResponse financialAccountOutPutResponse;

        private Builder() {
            financialAccountOutPutResponse = new FinancialAccountOutPutResponse();
        }

        public static Builder financialAccountOutPutResponse() {
            return new Builder();
        }

        public Builder id(Long id) {
            financialAccountOutPutResponse.setId(id);
            return this;
        }

        public Builder organizationId(long organizationId) {
            financialAccountOutPutResponse.setOrganizationId(organizationId);
            return this;
        }

        public Builder financialAccountStructureId(long financialAccountStructureId) {
            financialAccountOutPutResponse.setFinancialAccountStructureId(financialAccountStructureId);
            return this;
        }

        public Builder fullDescription(String fullDescription) {
            financialAccountOutPutResponse.setFullDescription(fullDescription);
            return this;
        }

        public Builder description(String description) {
            financialAccountOutPutResponse.setDescription(description);
            return this;
        }

        public Builder code(String code) {
            financialAccountOutPutResponse.setCode(code);
            return this;
        }

        public Builder latinDescription(String latinDescription) {
            financialAccountOutPutResponse.setLatinDescription(latinDescription);
            return this;
        }

        public Builder accountNatureTypeId(Long accountNatureTypeId) {
            financialAccountOutPutResponse.setAccountNatureTypeId(accountNatureTypeId);
            return this;
        }

        public Builder accountNatureTypeDescription(String accountNatureTypeDescription) {
            financialAccountOutPutResponse.setAccountNatureTypeDescription(accountNatureTypeDescription);
            return this;
        }

        public Builder relatedToOthersFlag(Boolean relatedToOthersFlag) {
            financialAccountOutPutResponse.setRelatedToOthersFlag(relatedToOthersFlag);
            return this;
        }

//        public Builder permanentFlag(Boolean permanentFlag) {
//            financialAccountOutPutResponse.setPermanentFlag(permanentFlag);
//            return this;
//        }

        public Builder accountRelationTypeId(Long accountRelationTypeId) {
            financialAccountOutPutResponse.setAccountRelationTypeId(accountRelationTypeId);
            return this;
        }

        public Builder accountRelationTypeDescription(String accountRelationTypeDescription) {
            financialAccountOutPutResponse.setAccountRelationTypeDescription(accountRelationTypeDescription);
            return this;
        }

        public Builder financialAccountParentId(Long financialAccountParentId) {
            financialAccountOutPutResponse.setFinancialAccountParentId(financialAccountParentId);
            return this;
        }

        public Builder financialAccountParentDescription(String financialAccountParentDescription) {
            financialAccountOutPutResponse.setFinancialAccountParentDescription(financialAccountParentDescription);
            return this;
        }

        public Builder relatedToFundType(String relatedToFundType) {
            financialAccountOutPutResponse.setRelatedToFundType(relatedToFundType);
            return this;
        }

        public Builder referenceFlag(Boolean referenceFlag) {
            financialAccountOutPutResponse.setReferenceFlag(referenceFlag);
            return this;
        }

        public Builder convertFlag(Boolean convertFlag) {
            financialAccountOutPutResponse.setConvertFlag(convertFlag);
            return this;
        }

        public Builder exchangeFlag(Boolean exchangeFlag) {
            financialAccountOutPutResponse.setExchangeFlag(exchangeFlag);
            return this;
        }

        public Builder accountAdjustmentId(Long accountAdjustmentId) {
            financialAccountOutPutResponse.setAccountAdjustmentId(accountAdjustmentId);
            return this;
        }

        public Builder accountAdjustmentDescription(String accountAdjustmentDescription) {
            financialAccountOutPutResponse.setAccountAdjustmentDescription(accountAdjustmentDescription);
            return this;
        }

        public Builder accountStatusId(Long accountStatusId) {
            financialAccountOutPutResponse.setAccountStatusId(accountStatusId);
            return this;
        }

        public Builder accountStatusCode(String accountStatusCode) {
            financialAccountOutPutResponse.setAccountStatusCode(accountStatusCode);
            return this;
        }

        public Builder accountStatusDescription(String accountStatusDescription) {
            financialAccountOutPutResponse.setAccountStatusDescription(accountStatusDescription);
            return this;
        }

        public Builder accountRelatedTypeOutPutModel(List<AccountRelatedTypeNewResponse> accountRelatedTypeOutPutModel) {
            financialAccountOutPutResponse.setAccountRelatedTypeOutPutModel(accountRelatedTypeOutPutModel);
            return this;
        }

        public Builder accountRelatedDescriptionOutPutModel(List<AccountRelatedDescriptionResponse> accountRelatedDescriptionOutPutModel) {
            financialAccountOutPutResponse.setAccountRelatedDescriptionOutPutModel(accountRelatedDescriptionOutPutModel);
            return this;
        }

        public Builder accountDefaultValueOutPutModel(List<AccountDefaultValueResponse> accountDefaultValueOutPutModel) {
            financialAccountOutPutResponse.setAccountDefaultValueOutPutModel(accountDefaultValueOutPutModel);
            return this;
        }

        public Builder accountMoneyTypeOutPutModel(List<AccountMoneyTypeResponse> accountMoneyTypeOutPutModel) {
            financialAccountOutPutResponse.setAccountMoneyTypeOutPutModel(accountMoneyTypeOutPutModel);
            return this;
        }

        public Builder flgShowInAcc(Long flgShowInAcc) {
            financialAccountOutPutResponse.setFlgShowInAcc(flgShowInAcc);
            return this;
        }

        public Builder flgPermanentStatus(Long flgPermanentStatus) {
            financialAccountOutPutResponse.setFlgPermanentStatus(flgPermanentStatus);
            return this;
        }

        public FinancialAccountOutPutResponse build() {
            return financialAccountOutPutResponse;
        }
    }
}
