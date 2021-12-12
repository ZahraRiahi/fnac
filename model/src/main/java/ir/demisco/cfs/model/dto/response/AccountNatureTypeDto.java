package ir.demisco.cfs.model.dto.response;

public class AccountNatureTypeDto {
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
    public static AccountNatureTypeDto.Builder builder() {
        return new AccountNatureTypeDto.Builder();
    }

    public static final class Builder {
        private AccountNatureTypeDto accountNatureTypeDto;

        private Builder() {
            accountNatureTypeDto = new AccountNatureTypeDto();
        }

        public static Builder accountNatureTypeDto() {
            return new Builder();
        }

        public Builder id(Long id) {
            accountNatureTypeDto.setId(id);
            return this;
        }

        public Builder description(String description) {
            accountNatureTypeDto.setDescription(description);
            return this;
        }

        public AccountNatureTypeDto build() {
            return accountNatureTypeDto;
        }
    }
}
