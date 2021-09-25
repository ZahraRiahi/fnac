package ir.demisco.cfs.model.dto.request;

public class FinancialAccountStatusRequest {
    private Long financialAccountId;
    private Long statusFlag;

    public Long getFinancialAccountId() {
        return financialAccountId;
    }

    public void setFinancialAccountId(Long financialAccountId) {
        this.financialAccountId = financialAccountId;
    }

    public Long getStatusFlag() {
        return statusFlag;
    }

    public void setStatusFlag(Long statusFlag) {
        this.statusFlag = statusFlag;
    }

}
