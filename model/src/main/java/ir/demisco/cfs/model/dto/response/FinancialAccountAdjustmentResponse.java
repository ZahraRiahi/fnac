package ir.demisco.cfs.model.dto.response;

public class FinancialAccountAdjustmentResponse {
    private Long id;
    private String code;
    private String description;
    private String fullDescription;

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

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public static FinancialAccountAdjustmentResponse.Builder builder() {
        return new FinancialAccountAdjustmentResponse.Builder();
    }

    public static final class Builder {
        private FinancialAccountAdjustmentResponse financialAccountAdjustmentResponse;

        private Builder() {
            financialAccountAdjustmentResponse = new FinancialAccountAdjustmentResponse();
        }

        public static Builder financialAccountAdjustmentResponse() {
            return new Builder();
        }

        public Builder id(Long id) {
            financialAccountAdjustmentResponse.setId(id);
            return this;
        }

        public Builder code(String code) {
            financialAccountAdjustmentResponse.setCode(code);
            return this;
        }

        public Builder description(String description) {
            financialAccountAdjustmentResponse.setDescription(description);
            return this;
        }

        public Builder fullDescription(String fullDescription) {
            financialAccountAdjustmentResponse.setFullDescription(fullDescription);
            return this;
        }

        public FinancialAccountAdjustmentResponse build() {
            return financialAccountAdjustmentResponse;
        }
    }
}
