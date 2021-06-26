package ir.demisco.cfs.model.dto.response;

public class FinancialAccountResponse {
    private Long id;
    private String code;
    private String description;

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

        public FinancialAccountResponse build() {
            return financialAccountResponse;
        }
    }
}
