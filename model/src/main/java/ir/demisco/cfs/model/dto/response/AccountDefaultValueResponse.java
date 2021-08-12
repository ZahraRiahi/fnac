package ir.demisco.cfs.model.dto.response;

public class AccountDefaultValueResponse {
    private Long accountRelationTypeDetailId;
    private Long centricAccountId;
    private String centricAccountName;
    private String centricAccountCode;
    private String accountRelationTypeDescription;
    private Long accountRelationTypeId;
    private Long sequence;
    private Long centricAccountTypeId;
    private String centricAccountTypeDescription;

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

    public Long getSequence() {
        return sequence;
    }

    public void setSequence(Long sequence) {
        this.sequence = sequence;
    }

    public Long getCentricAccountTypeId() {
        return centricAccountTypeId;
    }

    public void setCentricAccountTypeId(Long centricAccountTypeId) {
        this.centricAccountTypeId = centricAccountTypeId;
    }

    public String getCentricAccountTypeDescription() {
        return centricAccountTypeDescription;
    }

    public void setCentricAccountTypeDescription(String centricAccountTypeDescription) {
        this.centricAccountTypeDescription = centricAccountTypeDescription;
    }

    public static AccountDefaultValueResponse.Builder builder() {
        return new AccountDefaultValueResponse.Builder();
    }

    public static final class Builder {
        private AccountDefaultValueResponse accountDefaultValueResponse;

        private Builder() {
            accountDefaultValueResponse = new AccountDefaultValueResponse();
        }

        public static Builder accountDefaultValueResponse() {
            return new Builder();
        }

        public Builder accountRelationTypeDetailId(Long accountRelationTypeDetailId) {
            accountDefaultValueResponse.setAccountRelationTypeDetailId(accountRelationTypeDetailId);
            return this;
        }

        public Builder centricAccountId(Long centricAccountId) {
            accountDefaultValueResponse.setCentricAccountId(centricAccountId);
            return this;
        }

        public Builder centricAccountName(String centricAccountName) {
            accountDefaultValueResponse.setCentricAccountName(centricAccountName);
            return this;
        }

        public Builder centricAccountCode(String centricAccountCode) {
            accountDefaultValueResponse.setCentricAccountCode(centricAccountCode);
            return this;
        }

        public Builder accountRelationTypeDescription(String accountRelationTypeDescription) {
            accountDefaultValueResponse.setAccountRelationTypeDescription(accountRelationTypeDescription);
            return this;
        }

        public Builder accountRelationTypeId(Long accountRelationTypeId) {
            accountDefaultValueResponse.setAccountRelationTypeId(accountRelationTypeId);
            return this;
        }

        public Builder sequence(Long sequence) {
            accountDefaultValueResponse.setSequence(sequence);
            return this;
        }

        public Builder centricAccountTypeId(Long centricAccountTypeId) {
            accountDefaultValueResponse.setCentricAccountTypeId(centricAccountTypeId);
            return this;
        }

        public Builder centricAccountTypeDescription(String centricAccountTypeDescription) {
            accountDefaultValueResponse.setCentricAccountTypeDescription(centricAccountTypeDescription);
            return this;
        }

        public AccountDefaultValueResponse build() {
            return accountDefaultValueResponse;
        }
    }
}
