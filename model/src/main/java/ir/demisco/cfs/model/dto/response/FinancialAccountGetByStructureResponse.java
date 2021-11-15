package ir.demisco.cfs.model.dto.response;

public class FinancialAccountGetByStructureResponse {
    private Long id;
    private String description;
    private String code;
    private Boolean referenceFlag;
    private Boolean exchangeFlag;
    private Long accountRelationTypeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public static FinancialAccountGetByStructureResponse.Builder builder() {
        return new FinancialAccountGetByStructureResponse.Builder();
    }

    public static final class Builder {
        private FinancialAccountGetByStructureResponse financialAccountGetByStructureResponse;

        private Builder() {
            financialAccountGetByStructureResponse = new FinancialAccountGetByStructureResponse();
        }

        public static Builder financialAccountGetByStructureResponse() {
            return new Builder();
        }

        public Builder id(Long id) {
            financialAccountGetByStructureResponse.setId(id);
            return this;
        }

        public Builder description(String description) {
            financialAccountGetByStructureResponse.setDescription(description);
            return this;
        }

        public Builder code(String code) {
            financialAccountGetByStructureResponse.setCode(code);
            return this;
        }

        public Builder referenceFlag(Boolean referenceFlag) {
            financialAccountGetByStructureResponse.setReferenceFlag(referenceFlag);
            return this;
        }

        public Builder exchangeFlag(Boolean exchangeFlag) {
            financialAccountGetByStructureResponse.setExchangeFlag(exchangeFlag);
            return this;
        }

        public Builder accountRelationTypeId(Long accountRelationTypeId) {
            financialAccountGetByStructureResponse.setAccountRelationTypeId(accountRelationTypeId);
            return this;
        }

        public FinancialAccountGetByStructureResponse build() {
            return financialAccountGetByStructureResponse;
        }
    }
}
