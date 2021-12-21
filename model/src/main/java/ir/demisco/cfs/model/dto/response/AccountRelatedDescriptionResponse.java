package ir.demisco.cfs.model.dto.response;

public class AccountRelatedDescriptionResponse {
    private Long financialAccountDescriptionId;
    private String financialAccountDescriptionDescription;
    private Long accountRelatedDescriptionId;

    public Long getFinancialAccountDescriptionId() {
        return financialAccountDescriptionId;
    }

    public void setFinancialAccountDescriptionId(Long financialAccountDescriptionId) {
        this.financialAccountDescriptionId = financialAccountDescriptionId;
    }

    public String getFinancialAccountDescriptionDescription() {
        return financialAccountDescriptionDescription;
    }

    public void setFinancialAccountDescriptionDescription(String financialAccountDescriptionDescription) {
        this.financialAccountDescriptionDescription = financialAccountDescriptionDescription;
    }

    public Long getAccountRelatedDescriptionId() {
        return accountRelatedDescriptionId;
    }

    public void setAccountRelatedDescriptionId(Long accountRelatedDescriptionId) {
        this.accountRelatedDescriptionId = accountRelatedDescriptionId;
    }

    public static AccountRelatedDescriptionResponse.Builder builder() {
        return new AccountRelatedDescriptionResponse.Builder();
    }
    public static final class Builder {
        private AccountRelatedDescriptionResponse accountRelatedDescriptionResponse;

        private Builder() {
            accountRelatedDescriptionResponse = new AccountRelatedDescriptionResponse();
        }

        public static Builder anAccountRelatedDescriptionResponse() {
            return new Builder();
        }

        public Builder financialAccountDescriptionId(Long financialAccountDescriptionId) {
            accountRelatedDescriptionResponse.setFinancialAccountDescriptionId(financialAccountDescriptionId);
            return this;
        }

        public Builder financialAccountDescriptionDescription(String financialAccountDescriptionDescription) {
            accountRelatedDescriptionResponse.setFinancialAccountDescriptionDescription(financialAccountDescriptionDescription);
            return this;
        }
        public Builder accountRelatedDescriptionId(Long accountRelatedDescriptionId) {
            accountRelatedDescriptionResponse.setAccountRelatedDescriptionId(accountRelatedDescriptionId);
            return this;
        }

        public AccountRelatedDescriptionResponse build() {
            return accountRelatedDescriptionResponse;
        }
    }
}
