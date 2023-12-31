package ir.demisco.cfs.model.dto.response;

import java.util.Date;

public class FinancialAccountGetByStructureResponse {
    private Long id;
    private String description;
    private String code;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public static FinancialAccountGetByStructureResponse.Builder builder() {
        return new FinancialAccountGetByStructureResponse.Builder();
    }

    public static final class Builder {
        private FinancialAccountGetByStructureResponse financialAccountGetByStructureResponse;

        private Builder() {
            financialAccountGetByStructureResponse = new FinancialAccountGetByStructureResponse();
        }

        public static Builder financialAccountGetByStructureResponse() {
            return new Builder();
        }

        public Builder id(Long id) {
            financialAccountGetByStructureResponse.setId(id);
            return this;
        }

        public Builder description(String description) {
            financialAccountGetByStructureResponse.setDescription(description);
            return this;
        }

        public Builder code(String code) {
            financialAccountGetByStructureResponse.setCode(code);
            return this;
        }

        public Builder referenceFlag(Long referenceFlag) {
            financialAccountGetByStructureResponse.setReferenceFlag(referenceFlag);
            return this;
        }

        public Builder exchangeFlag(Long exchangeFlag) {
            financialAccountGetByStructureResponse.setExchangeFlag(exchangeFlag);
            return this;
        }

        public Builder accountRelationTypeId(Long accountRelationTypeId) {
            financialAccountGetByStructureResponse.setAccountRelationTypeId(accountRelationTypeId);
            return this;
        }

        public Builder disableDate(Date disableDate) {
            financialAccountGetByStructureResponse.setDisableDate(disableDate);
            return this;
        }
        public FinancialAccountGetByStructureResponse build() {
            return financialAccountGetByStructureResponse;
        }
    }
}
