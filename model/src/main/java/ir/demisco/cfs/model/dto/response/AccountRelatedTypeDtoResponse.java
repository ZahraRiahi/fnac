package ir.demisco.cfs.model.dto.response;

public class AccountRelatedTypeDtoResponse {
    private Long financialAccountTypeId;
    private String financialAccountTypeDescription;

    public Long getFinancialAccountTypeId() {
        return financialAccountTypeId;
    }

    public void setFinancialAccountTypeId(Long financialAccountTypeId) {
        this.financialAccountTypeId = financialAccountTypeId;
    }

    public String getFinancialAccountTypeDescription() {
        return financialAccountTypeDescription;
    }

    public void setFinancialAccountTypeDescription(String financialAccountTypeDescription) {
        this.financialAccountTypeDescription = financialAccountTypeDescription;
    }
}
