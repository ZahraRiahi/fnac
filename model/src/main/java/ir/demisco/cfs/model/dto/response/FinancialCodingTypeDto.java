package ir.demisco.cfs.model.dto.response;

public class FinancialCodingTypeDto {
    private Long id;
    private String description;
    private Long organizationId;

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

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private FinancialCodingTypeDto financialCodingTypeDto;

        private Builder() {
            financialCodingTypeDto = new FinancialCodingTypeDto();
        }

        public static Builder financialCodingTypeDto() {
            return new Builder();
        }

        public Builder id(Long id) {
            financialCodingTypeDto.setId(id);
            return this;
        }

        public Builder description(String description) {
            financialCodingTypeDto.setDescription(description);
            return this;
        }

        public Builder organization(Long organizationId) {
            financialCodingTypeDto.setOrganizationId(organizationId);
            return this;
        }

        public FinancialCodingTypeDto build() {
            return financialCodingTypeDto;
        }
    }
}
