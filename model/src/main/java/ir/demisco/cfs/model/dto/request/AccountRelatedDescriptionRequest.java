package ir.demisco.cfs.model.dto.request;

public class AccountRelatedDescriptionRequest {
    private Long id;
    private Long financialAccountId;
    private Long financialAccountDesId;
    private String description;

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

    public Long getFinancialAccountDesId() {
        return financialAccountDesId;
    }

    public void setFinancialAccountDesId(Long financialAccountDesId) {
        this.financialAccountDesId = financialAccountDesId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
