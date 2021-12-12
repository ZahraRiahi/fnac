package ir.demisco.cfs.model.dto.response;


public class AccountRelationTypeDto {
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

    public static AccountRelationTypeDto.Builder builder() {
        return new AccountRelationTypeDto.Builder();
    }

    public static final class Builder {
        private AccountRelationTypeDto accountRelationTypeDto;

        private Builder() {
            accountRelationTypeDto = new AccountRelationTypeDto();
        }

        public static Builder anAccountRelationTypeDto() {
            return new Builder();
        }

        public Builder id(Long id) {
            accountRelationTypeDto.setId(id);
            return this;
        }

        public Builder description(String description) {
            accountRelationTypeDto.setDescription(description);
            return this;
        }

        public AccountRelationTypeDto build() {
            return accountRelationTypeDto;
        }
    }
}
