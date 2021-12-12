package ir.demisco.cfs.model.dto.response;

public class AccountDefaultValueDto {
    private Long accountRelationTypeDetailId;
    private String accountRelationTypeDescription;
    private Long accountRelationTypeId;
    private Long sequence;

    public Long getAccountRelationTypeDetailId() {
        return accountRelationTypeDetailId;
    }

    public void setAccountRelationTypeDetailId(Long accountRelationTypeDetailId) {
        this.accountRelationTypeDetailId = accountRelationTypeDetailId;
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

    public static AccountDefaultValueDto.Builder builder() {
        return new AccountDefaultValueDto.Builder();
    }

    public static final class Builder {
        private AccountDefaultValueDto accountDefaultValueDto;

        private Builder() {
            accountDefaultValueDto = new AccountDefaultValueDto();
        }

        public static Builder accountDefaultValueDto() {
            return new Builder();
        }

        public Builder accountRelationTypeDetailId(Long accountRelationTypeDetailId) {
            accountDefaultValueDto.setAccountRelationTypeDetailId(accountRelationTypeDetailId);
            return this;
        }

        public Builder accountRelationTypeDescription(String accountRelationTypeDescription) {
            accountDefaultValueDto.setAccountRelationTypeDescription(accountRelationTypeDescription);
            return this;
        }

        public Builder accountRelationTypeId(Long accountRelationTypeId) {
            accountDefaultValueDto.setAccountRelationTypeId(accountRelationTypeId);
            return this;
        }

        public Builder sequence(Long sequence) {
            accountDefaultValueDto.setSequence(sequence);
            return this;
        }

        public AccountDefaultValueDto build() {
            return accountDefaultValueDto;
        }
    }
}
