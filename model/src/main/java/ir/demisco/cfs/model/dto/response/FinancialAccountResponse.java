package ir.demisco.cfs.model.dto.response;

public class FinancialAccountResponse {
    private Long id;
    private String code;
    private String description;
    private Boolean referenceFlag;
    private Boolean exchangeFlag;
    private Long accountRelationTypeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getReferenceFlag() {
        return referenceFlag;
    }

    public void setReferenceFlag(Boolean referenceFlag) {
        this.referenceFlag = referenceFlag;
    }

    public Boolean getExchangeFlag() {
        return exchangeFlag;
    }

    public void setExchangeFlag(Boolean exchangeFlag) {
        this.exchangeFlag = exchangeFlag;
    }

    public Long getAccountRelationTypeId() {
        return accountRelationTypeId;
    }

    public void setAccountRelationTypeId(Long accountRelationTypeId) {
        this.accountRelationTypeId = accountRelationTypeId;
    }

    public static FinancialAccountResponse.Builder builder() {
        return new FinancialAccountResponse.Builder();
    }

    public static final class Builder {
        private FinancialAccountResponse financialAccountResponse;

        private Builder() {
            financialAccountResponse = new FinancialAccountResponse();
        }

        public static Builder aFinancialAccountResponse() {
            return new Builder();
        }

        public Builder id(Long id) {
            financialAccountResponse.setId(id);
            return this;
        }

        public Builder code(String code) {
            financialAccountResponse.setCode(code);
            return this;
        }

        public Builder description(String description) {
            financialAccountResponse.setDescription(description);
            return this;
        }

        public Builder referenceFlag(Boolean referenceFlag) {
            financialAccountResponse.setReferenceFlag(referenceFlag);
            return this;
        }

        public Builder exchangeFlag(Boolean exchangeFlag) {
            financialAccountResponse.setExchangeFlag(exchangeFlag);
            return this;
        }

        public Builder accountRelationTypeId(Long accountRelationTypeId) {
            financialAccountResponse.setAccountRelationTypeId(accountRelationTypeId);
            return this;
        }

        public FinancialAccountResponse build() {
            return financialAccountResponse;
        }
    }
}
