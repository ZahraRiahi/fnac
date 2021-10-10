package ir.demisco.cfs.model.dto.response;

public class FinancialAccountStructureNewResponse {
    private Long accountPermanentStatusId;
    private Long flgPermanentStatus;

    public Long getAccountPermanentStatusId() {
        return accountPermanentStatusId;
    }

    public void setAccountPermanentStatusId(Long accountPermanentStatusId) {
        this.accountPermanentStatusId = accountPermanentStatusId;
    }

    public Long getFlgPermanentStatus() {
        return flgPermanentStatus;
    }

    public void setFlgPermanentStatus(Long flgPermanentStatus) {
        this.flgPermanentStatus = flgPermanentStatus;
    }
    public static FinancialAccountStructureNewResponse.Builder builder() {
        return new FinancialAccountStructureNewResponse.Builder();
    }

    public static final class Builder {
        private FinancialAccountStructureNewResponse financialAccountStructureNewResponse;

        private Builder() {
            financialAccountStructureNewResponse = new FinancialAccountStructureNewResponse();
        }

        public static Builder aFinancialAccountStructureNewResponse() {
            return new Builder();
        }

        public Builder accountPermanentStatusId(Long accountPermanentStatusId) {
            financialAccountStructureNewResponse.setAccountPermanentStatusId(accountPermanentStatusId);
            return this;
        }

        public Builder flgPermanentStatus(Long flgPermanentStatus) {
            financialAccountStructureNewResponse.setFlgPermanentStatus(flgPermanentStatus);
            return this;
        }

        public FinancialAccountStructureNewResponse build() {
            return financialAccountStructureNewResponse;
        }
    }
}
