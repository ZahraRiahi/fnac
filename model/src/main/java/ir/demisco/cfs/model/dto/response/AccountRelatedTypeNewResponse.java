package ir.demisco.cfs.model.dto.response;

public class AccountRelatedTypeNewResponse {
    private Long financialAccountTypeId;
    private String description;
    private String code;
    private Long flgExists;

    public Long getFinancialAccountTypeId() {
        return financialAccountTypeId;
    }

    public void setFinancialAccountTypeId(Long financialAccountTypeId) {
        this.financialAccountTypeId = financialAccountTypeId;
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

    public Long getFlgExists() {
        return flgExists;
    }

    public void setFlgExists(Long flgExists) {
        this.flgExists = flgExists;
    }
    public static AccountRelatedTypeNewResponse.Builder builder() {
        return new AccountRelatedTypeNewResponse.Builder();
    }

    public static final class Builder {
        private AccountRelatedTypeNewResponse accountRelatedTypeNewResponse;

        private Builder() {
            accountRelatedTypeNewResponse = new AccountRelatedTypeNewResponse();
        }

        public static Builder anAccountRelatedTypeNewResponse() {
            return new Builder();
        }

        public Builder financialAccountTypeId(Long financialAccountTypeId) {
            accountRelatedTypeNewResponse.setFinancialAccountTypeId(financialAccountTypeId);
            return this;
        }

        public Builder description(String description) {
            accountRelatedTypeNewResponse.setDescription(description);
            return this;
        }

        public Builder code(String code) {
            accountRelatedTypeNewResponse.setCode(code);
            return this;
        }

        public Builder flgExists(Long flgExists) {
            accountRelatedTypeNewResponse.setFlgExists(flgExists);
            return this;
        }

        public AccountRelatedTypeNewResponse build() {
            return accountRelatedTypeNewResponse;
        }
    }
}
