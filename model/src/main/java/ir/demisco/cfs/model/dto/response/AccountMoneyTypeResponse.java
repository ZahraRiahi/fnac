package ir.demisco.cfs.model.dto.response;

public class AccountMoneyTypeResponse {
    private Long id;
    private Long moneyTypeId;
    private String moneyTypeDescription;
    private Long flgExists;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMoneyTypeId() {
        return moneyTypeId;
    }

    public void setMoneyTypeId(Long moneyTypeId) {
        this.moneyTypeId = moneyTypeId;
    }

    public String getMoneyTypeDescription() {
        return moneyTypeDescription;
    }

    public void setMoneyTypeDescription(String moneyTypeDescription) {
        this.moneyTypeDescription = moneyTypeDescription;
    }

    public Long getFlgExists() {
        return flgExists;
    }

    public void setFlgExists(Long flgExists) {
        this.flgExists = flgExists;
    }
    public static AccountMoneyTypeResponse.Builder builder() {
        return new AccountMoneyTypeResponse.Builder();
    }
    public static final class Builder {
        private AccountMoneyTypeResponse accountMoneyTypeResponse;

        private Builder() {
            accountMoneyTypeResponse = new AccountMoneyTypeResponse();
        }

        public static Builder anAccountMoneyTypeResponse() {
            return new Builder();
        }

        public Builder id(Long id) {
            accountMoneyTypeResponse.setId(id);
            return this;
        }

        public Builder moneyTypeId(Long moneyTypeId) {
            accountMoneyTypeResponse.setMoneyTypeId(moneyTypeId);
            return this;
        }

        public Builder moneyTypeDescription(String moneyTypeDescription) {
            accountMoneyTypeResponse.setMoneyTypeDescription(moneyTypeDescription);
            return this;
        }

        public Builder flgExists(Long flgExists) {
            accountMoneyTypeResponse.setFlgExists(flgExists);
            return this;
        }

        public AccountMoneyTypeResponse build() {
            return accountMoneyTypeResponse;
        }
    }
}
