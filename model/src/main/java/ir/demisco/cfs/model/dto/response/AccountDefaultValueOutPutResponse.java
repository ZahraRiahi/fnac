package ir.demisco.cfs.model.dto.response;

public class AccountDefaultValueOutPutResponse {
    private Long accountRelationTypeDetailId;
    private Long centricAccountId;
    private String centricAccountName;
    private String centricAccountCode;
    private String accountRelationTypeDescription;
    private Long accountRelationTypeId;

    public Long getAccountRelationTypeDetailId() {
        return accountRelationTypeDetailId;
    }

    public void setAccountRelationTypeDetailId(Long accountRelationTypeDetailId) {
        this.accountRelationTypeDetailId = accountRelationTypeDetailId;
    }

    public Long getCentricAccountId() {
        return centricAccountId;
    }

    public void setCentricAccountId(Long centricAccountId) {
        this.centricAccountId = centricAccountId;
    }

    public String getCentricAccountName() {
        return centricAccountName;
    }

    public void setCentricAccountName(String centricAccountName) {
        this.centricAccountName = centricAccountName;
    }

    public String getCentricAccountCode() {
        return centricAccountCode;
    }

    public void setCentricAccountCode(String centricAccountCode) {
        this.centricAccountCode = centricAccountCode;
    }

    public String getAccountRelationTypeDescription() {
        return accountRelationTypeDescription;
    }

    public void setAccountRelationTypeDescription(String accountRelationTypeDescription) {
        this.accountRelationTypeDescription = accountRelationTypeDescription;
    }

    public Long getAccountRelationTypeId() {
        return accountRelationTypeId;
    }

    public void setAccountRelationTypeId(Long accountRelationTypeId) {
        this.accountRelationTypeId = accountRelationTypeId;
    }

    public static AccountDefaultValueOutPutResponse.Builder builder() {
        return new AccountDefaultValueOutPutResponse.Builder();
    }

    public static final class Builder {
        private AccountDefaultValueOutPutResponse accountDefaultValueOutPutResponse;

        private Builder() {
            accountDefaultValueOutPutResponse = new AccountDefaultValueOutPutResponse();
        }

        public static Builder accountDefaultValueOutPutResponse() {
            return new Builder();
        }

        public Builder accountRelationTypeDetailId(Long accountRelationTypeDetailId) {
            accountDefaultValueOutPutResponse.setAccountRelationTypeDetailId(accountRelationTypeDetailId);
            return this;
        }

        public Builder centricAccountId(Long centricAccountId) {
            accountDefaultValueOutPutResponse.setCentricAccountId(centricAccountId);
            return this;
        }

        public Builder centricAccountName(String centricAccountName) {
            accountDefaultValueOutPutResponse.setCentricAccountName(centricAccountName);
            return this;
        }

        public Builder centricAccountCode(String centricAccountCode) {
            accountDefaultValueOutPutResponse.setCentricAccountCode(centricAccountCode);
            return this;
        }

        public Builder accountRelationTypeDescription(String accountRelationTypeDescription) {
            accountDefaultValueOutPutResponse.setAccountRelationTypeDescription(accountRelationTypeDescription);
            return this;
        }

        public Builder accountRelationTypeId(Long accountRelationTypeId) {
            accountDefaultValueOutPutResponse.setAccountRelationTypeId(accountRelationTypeId);
            return this;
        }

        public AccountDefaultValueOutPutResponse build() {
            return accountDefaultValueOutPutResponse;
        }
    }
}
