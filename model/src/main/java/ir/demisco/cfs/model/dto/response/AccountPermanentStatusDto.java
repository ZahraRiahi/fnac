package ir.demisco.cfs.model.dto.response;


public class AccountPermanentStatusDto {
    private Long id;
    private String code;
    private String description;

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
    public static AccountPermanentStatusDto.Builder builder() {
        return new AccountPermanentStatusDto.Builder();
    }

    public static final class Builder {
        private AccountPermanentStatusDto accountPermanentStatusDto;

        private Builder() {
            accountPermanentStatusDto = new AccountPermanentStatusDto();
        }

        public static Builder anAccountPermanentStatusDto() {
            return new Builder();
        }

        public Builder id(Long id) {
            accountPermanentStatusDto.setId(id);
            return this;
        }

        public Builder code(String code) {
            accountPermanentStatusDto.setCode(code);
            return this;
        }

        public Builder description(String description) {
            accountPermanentStatusDto.setDescription(description);
            return this;
        }

        public AccountPermanentStatusDto build() {
            return accountPermanentStatusDto;
        }
    }
}
