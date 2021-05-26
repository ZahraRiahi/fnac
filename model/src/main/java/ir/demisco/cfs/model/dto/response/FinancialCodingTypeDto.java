package ir.demisco.cfs.model.dto.response;

import ir.demisco.cloud.basic.model.entity.org.Organization;

public class FinancialCodingTypeDto {
    private Long id;
    private String description;
    private Organization organization;

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

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
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

        public Builder organization(Organization organization) {
            financialCodingTypeDto.setOrganization(organization);
            return this;
        }

        public FinancialCodingTypeDto build() {
            return financialCodingTypeDto;
        }
    }
}
