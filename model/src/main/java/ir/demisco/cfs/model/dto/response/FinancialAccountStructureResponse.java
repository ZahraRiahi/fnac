package ir.demisco.cfs.model.dto.response;

public class FinancialAccountStructureResponse {
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

    public static FinancialAccountStructureResponse.Builder builder() {
        return new FinancialAccountStructureResponse.Builder();
    }

    public static final class Builder {
        private FinancialAccountStructureResponse financialAccountStructureResponse;

        private Builder() {
            financialAccountStructureResponse = new FinancialAccountStructureResponse();
        }

        public static Builder financialAccountStructureResponse() {
            return new Builder();
        }

        public Builder id(Long id) {
            financialAccountStructureResponse.setId(id);
            return this;
        }

        public Builder description(String description) {
            financialAccountStructureResponse.setDescription(description);
            return this;
        }

        public FinancialAccountStructureResponse build() {
            return financialAccountStructureResponse;
        }
    }
}
