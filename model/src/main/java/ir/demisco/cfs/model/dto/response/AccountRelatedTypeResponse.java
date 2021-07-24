package ir.demisco.cfs.model.dto.response;

public class AccountRelatedTypeResponse {
    private Long financialAccountTypeId;
    private String financialAccountTypeDescription;
    private Long flgExists;

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

    public Long getFlgExists() {
        return flgExists;
    }

    public void setFlgExists(Long flgExists) {
        this.flgExists = flgExists;
    }

    public static AccountRelatedTypeResponse.Builder builder() {
        return new AccountRelatedTypeResponse.Builder();
    }

    public static final class Builder {
        private AccountRelatedTypeResponse accountRelatedTypeResponse;

        private Builder() {
            accountRelatedTypeResponse = new AccountRelatedTypeResponse();
        }

        public static Builder accountRelatedTypeResponse() {
            return new Builder();
        }

        public Builder financialAccountTypeId(Long financialAccountTypeId) {
            accountRelatedTypeResponse.setFinancialAccountTypeId(financialAccountTypeId);
            return this;
        }

        public Builder financialAccountTypeDescription(String financialAccountTypeDescription) {
            accountRelatedTypeResponse.setFinancialAccountTypeDescription(financialAccountTypeDescription);
            return this;
        }

        public Builder flgExists(Long flgExists) {
            accountRelatedTypeResponse.setFlgExists(flgExists);
            return this;
        }

        public AccountRelatedTypeResponse build() {
            return accountRelatedTypeResponse;
        }
    }
}
