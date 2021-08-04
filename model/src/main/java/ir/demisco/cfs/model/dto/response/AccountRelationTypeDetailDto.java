package ir.demisco.cfs.model.dto.response;

import java.time.LocalDateTime;

public class AccountRelationTypeDetailDto {
    private Long id;
    private Long centricAccountTypeId;
    private String centricAccountTypeDescription;
    private Long sequence;
    private LocalDateTime deletedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getSequence() {
        return sequence;
    }

    public void setSequence(Long sequence) {
        this.sequence = sequence;
    }

    public LocalDateTime getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(LocalDateTime deletedDate) {
        this.deletedDate = deletedDate;
    }
    public static AccountRelationTypeDetailDto.Builder builder() {
        return new AccountRelationTypeDetailDto.Builder();
    }

    public static final class Builder {
        private AccountRelationTypeDetailDto accountRelationTypeDetailDto;

        private Builder() {
            accountRelationTypeDetailDto = new AccountRelationTypeDetailDto();
        }

        public static Builder accountRelationTypeDetailDto() {
            return new Builder();
        }

        public Builder id(Long id) {
            accountRelationTypeDetailDto.setId(id);
            return this;
        }

        public Builder centricAccountTypeId(Long centricAccountTypeId) {
            accountRelationTypeDetailDto.setCentricAccountTypeId(centricAccountTypeId);
            return this;
        }

        public Builder centricAccountTypeDescription(String centricAccountTypeDescription) {
            accountRelationTypeDetailDto.setCentricAccountTypeDescription(centricAccountTypeDescription);
            return this;
        }

        public Builder sequence(Long sequence) {
            accountRelationTypeDetailDto.setSequence(sequence);
            return this;
        }

        public Builder deletedDate(LocalDateTime deletedDate) {
            accountRelationTypeDetailDto.setDeletedDate(deletedDate);
            return this;
        }

        public AccountRelationTypeDetailDto build() {
            return accountRelationTypeDetailDto;
        }
    }
}
