package ir.demisco.cfs.model.dto.response;

import java.time.LocalDateTime;
import java.util.Date;

public class FinancialAccountLovResponse {
    private Long id;
    private String code;
    private String description;
    private Long referenceFlag;
    private Long exchangeFlag;
    private Long accountRelationTypeId;
    private Date disableDate;

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

    public Long getReferenceFlag() {
        return referenceFlag;
    }

    public void setReferenceFlag(Long referenceFlag) {
        this.referenceFlag = referenceFlag;
    }

    public Long getExchangeFlag() {
        return exchangeFlag;
    }

    public void setExchangeFlag(Long exchangeFlag) {
        this.exchangeFlag = exchangeFlag;
    }

    public Long getAccountRelationTypeId() {
        return accountRelationTypeId;
    }

    public void setAccountRelationTypeId(Long accountRelationTypeId) {
        this.accountRelationTypeId = accountRelationTypeId;
    }

    public Date getDisableDate() {
        return disableDate;
    }

    public void setDisableDate(Date disableDate) {
        this.disableDate = disableDate;
    }

    public static FinancialAccountLovResponse.Builder builder() {
        return new FinancialAccountLovResponse.Builder();
    }

    public static final class Builder {
        private FinancialAccountLovResponse financialAccountLovResponse;

        private Builder() {
            financialAccountLovResponse = new FinancialAccountLovResponse();
        }

        public static Builder financialAccountLovResponse() {
            return new Builder();
        }

        public Builder id(Long id) {
            financialAccountLovResponse.setId(id);
            return this;
        }

        public Builder code(String code) {
            financialAccountLovResponse.setCode(code);
            return this;
        }

        public Builder description(String description) {
            financialAccountLovResponse.setDescription(description);
            return this;
        }

        public Builder referenceFlag(Long referenceFlag) {
            financialAccountLovResponse.setReferenceFlag(referenceFlag);
            return this;
        }

        public Builder exchangeFlag(Long exchangeFlag) {
            financialAccountLovResponse.setExchangeFlag(exchangeFlag);
            return this;
        }

        public Builder accountRelationTypeId(Long accountRelationTypeId) {
            financialAccountLovResponse.setAccountRelationTypeId(accountRelationTypeId);
            return this;
        }

        public Builder disableDate(Date disableDate) {
            financialAccountLovResponse.setDisableDate(disableDate);
            return this;
        }

        public FinancialAccountLovResponse build() {
            return financialAccountLovResponse;
        }
    }
}
