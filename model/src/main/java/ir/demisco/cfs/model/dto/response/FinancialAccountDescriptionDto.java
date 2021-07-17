package ir.demisco.cfs.model.dto.response;

public class FinancialAccountDescriptionDto {
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

    public static FinancialAccountDescriptionDto.Builder builder() {
        return new FinancialAccountDescriptionDto.Builder();
    }

    public static final class Builder {
        private FinancialAccountDescriptionDto financialAccountDescriptionDto;

        private Builder() {
            financialAccountDescriptionDto = new FinancialAccountDescriptionDto();
        }

        public static FinancialAccountDescriptionDto.Builder financialAccountDescriptionDto() {
            return new FinancialAccountDescriptionDto.Builder();
        }

        public FinancialAccountDescriptionDto.Builder id(Long id) {
            financialAccountDescriptionDto.setId(id);
            return this;
        }

        public FinancialAccountDescriptionDto.Builder description(String description) {
            financialAccountDescriptionDto.setDescription(description);
            return this;
        }

        public FinancialAccountDescriptionDto build() {
            return financialAccountDescriptionDto;
        }
    }
}
