package ir.demisco.cfs.model.dto.response;


public class AccountMoneyTypeDto {
    private Long id;
    private String description;
    private Long flgExists;
    private Long nationalCurrencyFlag;

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

    public Long getNationalCurrencyFlag() {
        return nationalCurrencyFlag;
    }

    public void setNationalCurrencyFlag(Long nationalCurrencyFlag) {
        this.nationalCurrencyFlag = nationalCurrencyFlag;
    }

    public static AccountMoneyTypeDto.Builder builder() {
        return new AccountMoneyTypeDto.Builder();
    }

    public static final class Builder {
        private AccountMoneyTypeDto accountMoneyTypeDto;

        private Builder() {
            accountMoneyTypeDto = new AccountMoneyTypeDto();
        }

        public static Builder accountMoneyTypeDto() {
            return new Builder();
        }

        public Builder id(Long id) {
            accountMoneyTypeDto.setId(id);
            return this;
        }


        public Builder description(String description) {
            accountMoneyTypeDto.setDescription(description);
            return this;
        }

        public Builder flgExists(Long flgExists) {
            accountMoneyTypeDto.setFlgExists(flgExists);
            return this;
        }

        public Builder nationalCurrencyFlag(Long nationalCurrencyFlag) {
            accountMoneyTypeDto.setNationalCurrencyFlag(nationalCurrencyFlag);
            return this;
        }

        public AccountMoneyTypeDto build() {
            return accountMoneyTypeDto;
        }
    }
}
