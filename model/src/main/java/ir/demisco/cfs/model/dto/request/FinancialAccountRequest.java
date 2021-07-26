package ir.demisco.cfs.model.dto.request;

import java.util.List;

public class FinancialAccountRequest {
    private Long id;
    private Long organizationId;
    private long financialAccountStructureId;
    private String fullDescription;
    private String Description;
    private String code;
    private Boolean activeFlag;
    private String latinDescription;
    private Long accountNatureTypeId;
    private Boolean relatedToOthersFlag;
    private Boolean permanentFlag;
    private Long accountRelationTypeId;
    private Long financialAccountParentId;
    private String relatedToFundType;
    private Boolean referenceFlag;
    private Boolean convertFlag;
    private Boolean exchangeFlag;
    private Long accountAdjustmentId;
    private List<Long> financialAccountTypeId;
    private List<AccountDefaultValueRequest> accountDefaultValueOutPutModel;
    private List<Long> financialAccountDescription;
    private List<Long> moneyTypeId;

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
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
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

    public Boolean getPermanentFlag() {
        return permanentFlag;
    }

    public void setPermanentFlag(Boolean permanentFlag) {
        this.permanentFlag = permanentFlag;
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

    public List<Long> getFinancialAccountTypeId() {
        return financialAccountTypeId;
    }

    public void setFinancialAccountTypeId(List<Long> financialAccountTypeId) {
        this.financialAccountTypeId = financialAccountTypeId;
    }

    public List<AccountDefaultValueRequest> getAccountDefaultValueOutPutModel() {
        return accountDefaultValueOutPutModel;
    }

    public void setAccountDefaultValueOutPutModel(List<AccountDefaultValueRequest> accountDefaultValueOutPutModel) {
        this.accountDefaultValueOutPutModel = accountDefaultValueOutPutModel;
    }

    public List<Long> getFinancialAccountDescription() {
        return financialAccountDescription;
    }

    public void setFinancialAccountDescription(List<Long> financialAccountDescription) {
        this.financialAccountDescription = financialAccountDescription;
    }

    public List<Long> getMoneyTypeId() {
        return moneyTypeId;
    }

    public void setMoneyTypeId(List<Long> moneyTypeId) {
        this.moneyTypeId = moneyTypeId;
    }
}
