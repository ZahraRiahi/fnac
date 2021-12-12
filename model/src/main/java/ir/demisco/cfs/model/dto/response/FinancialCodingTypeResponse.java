package ir.demisco.cfs.model.dto.response;

public class FinancialCodingTypeResponse {
    private Long id;
    private String description;

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
    public static FinancialCodingTypeResponse.Builder builder() {
        return new FinancialCodingTypeResponse.Builder();
    }
    public static final class Builder {
        private FinancialCodingTypeResponse financialCodingTypeResponse;

        private Builder() {
            financialCodingTypeResponse = new FinancialCodingTypeResponse();
        }

        public static Builder financialCodingTypeResponse() {
            return new Builder();
        }

        public Builder id(Long id) {
            financialCodingTypeResponse.setId(id);
            return this;
        }

        public Builder description(String description) {
            financialCodingTypeResponse.setDescription(description);
            return this;
        }

        public FinancialCodingTypeResponse build() {
            return financialCodingTypeResponse;
        }
    }
}
