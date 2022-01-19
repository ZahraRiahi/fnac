package ir.demisco.cfs.model.dto.response;

public class AccountRelatedDescriptionDto {
    private Long financialAccountId;
    private Long financialAccountDescriptionId;
    private String financialAccountDescription;
    private Long accountRelatedDescriptionId;

    public Long getFinancialAccountId() {
        return financialAccountId;
    }

    public void setFinancialAccountId(Long financialAccountId) {
        this.financialAccountId = financialAccountId;
    }

    public Long getFinancialAccountDescriptionId() {
        return financialAccountDescriptionId;
    }

    public void setFinancialAccountDescriptionId(Long financialAccountDescriptionId) {
        this.financialAccountDescriptionId = financialAccountDescriptionId;
    }

    public String getFinancialAccountDescription() {
        return financialAccountDescription;
    }

    public void setFinancialAccountDescription(String financialAccountDescription) {
        this.financialAccountDescription = financialAccountDescription;
    }

    public static AccountRelatedDescriptionDto.Builder builder() {
        return new AccountRelatedDescriptionDto.Builder();
    }

    public Long getAccountRelatedDescriptionId() {
        return accountRelatedDescriptionId;
    }

    public void setAccountRelatedDescriptionId(Long accountRelatedDescriptionId) {
        this.accountRelatedDescriptionId = accountRelatedDescriptionId;
    }

    public static final class Builder {
        private AccountRelatedDescriptionDto accountRelatedDescriptionDto;

        private Builder() {
            accountRelatedDescriptionDto = new AccountRelatedDescriptionDto();
        }

        public static Builder anAccountRelatedDescriptionDto() {
            return new Builder();
        }

        public Builder financialAccountId(Long financialAccountId) {
            accountRelatedDescriptionDto.setFinancialAccountId(financialAccountId);
            return this;
        }

        public Builder financialAccountDescriptionId(Long financialAccountDescriptionId) {
            accountRelatedDescriptionDto.setFinancialAccountDescriptionId(financialAccountDescriptionId);
            return this;
        }

        public Builder financialAccountDescription(String financialAccountDescription) {
            accountRelatedDescriptionDto.setFinancialAccountDescription(financialAccountDescription);
            return this;
        }
        public Builder accountRelatedDescriptionId(Long accountRelatedDescriptionId) {
            accountRelatedDescriptionDto.setAccountRelatedDescriptionId(accountRelatedDescriptionId);
            return this;
        }
        public AccountRelatedDescriptionDto build() {
            return accountRelatedDescriptionDto;
        }
    }
}
