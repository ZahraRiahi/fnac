package ir.demisco.cfs.model.dto.request;

public class AccountDefaultValueRequest {
    private Long id;
    private Long financialAccountId;
    private Long centricAccountId;
    private Long accountRelationTypeDetailId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFinancialAccountId() {
        return financialAccountId;
    }

    public void setFinancialAccountId(Long financialAccountId) {
        this.financialAccountId = financialAccountId;
    }

    public Long getCentricAccountId() {
        return centricAccountId;
    }

    public void setCentricAccountId(Long centricAccountId) {
        this.centricAccountId = centricAccountId;
    }

    public Long getAccountRelationTypeDetailId() {
        return accountRelationTypeDetailId;
    }

    public void setAccountRelationTypeDetailId(Long accountRelationTypeDetailId) {
        this.accountRelationTypeDetailId = accountRelationTypeDetailId;
    }
}
