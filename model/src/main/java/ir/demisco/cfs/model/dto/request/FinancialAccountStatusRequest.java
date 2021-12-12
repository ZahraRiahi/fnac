package ir.demisco.cfs.model.dto.request;

public class FinancialAccountStatusRequest {
    private Long financialAccountId;
    private Boolean statusFlag;

    public Long getFinancialAccountId() {
        return financialAccountId;
    }

    public void setFinancialAccountId(Long financialAccountId) {
        this.financialAccountId = financialAccountId;
    }

    public Boolean getStatusFlag() {
        return statusFlag;
    }

    public void setStatusFlag(Boolean statusFlag) {
        this.statusFlag = statusFlag;
    }

}
