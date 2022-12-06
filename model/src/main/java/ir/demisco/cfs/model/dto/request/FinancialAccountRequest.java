package ir.demisco.cfs.model.dto.request;

import javax.validation.constraints.Size;
import java.util.List;

public class FinancialAccountRequest {
    private Long id;
    private Long organizationId;
    private Long financialAccountStructureId;
    private String fullDescription;
    private String description;
    private String code;
    private Boolean activeFlag;
    private String latinDescription;
    private Long accountNatureTypeId;
    private Boolean relatedToOthersFlag;
    private Long accountRelationTypeId;
    private Long financialAccountParentId;
    private String relatedToFundType;
    private Boolean referenceFlag;
    private Boolean convertFlag;
    private Boolean exchangeFlag;
    private Long accountAdjustmentId;
    private Long financialCodingTypeId;
    private List<Long> financialAccountTypeId;
    private List<AccountDefaultValueRequest> accountDefaultValueInPutModel;
    private List<AccountRelatedDescriptionRequest> accountRelatedDescriptionInPutModel;
    private List<Long> moneyTypeId;
    private Long accountStatusId;
    private Boolean profitLossAccountFlag;

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

    public Boolean getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(Boolean activeFlag) {
        this.activeFlag = activeFlag;
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

    public Long getFinancialAccountParentId() {
        return financialAccountParentId;
    }

    public void setFinancialAccountParentId(Long financialAccountParentId) {
        this.financialAccountParentId = financialAccountParentId;
    }

    @Size(max = 3 ,message = "تعداد کارکترهای این فیلد بیشتر از 3 نمیتواند باشد.")
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

    public Long getFinancialCodingTypeId() {
        return financialCodingTypeId;
    }

    public void setFinancialCodingTypeId(Long financialCodingTypeId) {
        this.financialCodingTypeId = financialCodingTypeId;
    }

    public List<Long> getFinancialAccountTypeId() {
        return financialAccountTypeId;
    }

    public void setFinancialAccountTypeId(List<Long> financialAccountTypeId) {
        this.financialAccountTypeId = financialAccountTypeId;
    }

    public List<AccountDefaultValueRequest> getAccountDefaultValueInPutModel() {
        return accountDefaultValueInPutModel;
    }

    public void setAccountDefaultValueInPutModel(List<AccountDefaultValueRequest> accountDefaultValueInPutModel) {
        this.accountDefaultValueInPutModel = accountDefaultValueInPutModel;
    }

    public List<AccountRelatedDescriptionRequest> getAccountRelatedDescriptionInPutModel() {
        return accountRelatedDescriptionInPutModel;
    }

    public void setAccountRelatedDescriptionInPutModel(List<AccountRelatedDescriptionRequest> accountRelatedDescriptionInPutModel) {
        this.accountRelatedDescriptionInPutModel = accountRelatedDescriptionInPutModel;
    }

    public List<Long> getMoneyTypeId() {
        return moneyTypeId;
    }

    public void setMoneyTypeId(List<Long> moneyTypeId) {
        this.moneyTypeId = moneyTypeId;
    }

    public Long getAccountStatusId() {
        return accountStatusId;
    }

    public void setAccountStatusId(Long accountStatusId) {
        this.accountStatusId = accountStatusId;
    }

    public Boolean getProfitLossAccountFlag() {
        return profitLossAccountFlag;
    }

    public void setProfitLossAccountFlag(Boolean profitLossAccountFlag) {
        this.profitLossAccountFlag = profitLossAccountFlag;
    }
}
