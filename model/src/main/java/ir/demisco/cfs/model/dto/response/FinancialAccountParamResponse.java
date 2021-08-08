package ir.demisco.cfs.model.dto.response;

public class FinancialAccountParamResponse {
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

    public static FinancialAccountParamResponse.Builder builder() {
        return new FinancialAccountParamResponse.Builder();
    }

    public static final class Builder {
        private FinancialAccountParamResponse financialAccountParamResponse;

        private Builder() {
            financialAccountParamResponse = new FinancialAccountParamResponse();
        }

        public static Builder financialAccountParamResponse() {
            return new Builder();
        }

        public Builder id(Long id) {
            financialAccountParamResponse.setId(id);
            return this;
        }

        public Builder code(String code) {
            financialAccountParamResponse.setCode(code);
            return this;
        }

        public Builder description(String description) {
            financialAccountParamResponse.setDescription(description);
            return this;
        }

        public Builder fullDescription(String fullDescription) {
            financialAccountParamResponse.setFullDescription(fullDescription);
            return this;
        }

        public FinancialAccountParamResponse build() {
            return financialAccountParamResponse;
        }
    }
}
