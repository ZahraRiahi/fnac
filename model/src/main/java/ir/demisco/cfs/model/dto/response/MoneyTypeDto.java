package ir.demisco.cfs.model.dto.response;


public class MoneyTypeDto {
    private Long id;
    private String description;
    private Long flgExists;

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

    public Long getFlgExists() {
        return flgExists;
    }

    public void setFlgExists(Long flgExists) {
        this.flgExists = flgExists;
    }

    public static MoneyTypeDto.Builder builder() {
        return new MoneyTypeDto.Builder();
    }

    public static final class Builder {
        private MoneyTypeDto moneyTypeDto;

        private Builder() {
            moneyTypeDto = new MoneyTypeDto();
        }

        public static Builder moneyTypeDto() {
            return new Builder();
        }

        public Builder id(Long id) {
            moneyTypeDto.setId(id);
            return this;
        }

        public Builder description(String description) {
            moneyTypeDto.setDescription(description);
            return this;
        }

        public Builder flgExists(Long flgExists) {
            moneyTypeDto.setFlgExists(flgExists);
            return this;
        }

        public MoneyTypeDto build() {
            return moneyTypeDto;
        }
    }
}
