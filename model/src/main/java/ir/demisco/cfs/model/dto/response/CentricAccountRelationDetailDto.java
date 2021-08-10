package ir.demisco.cfs.model.dto.response;

import java.time.LocalDateTime;

public class CentricAccountRelationDetailDto {
    private Long accountDefaultValuesRelationTypeDetailsId;
    private Long id;
    private String code;
    private String name;
    private String accountDefaultValuesRelationTypeDescription;
    private Long accountRelationTypeId;
    private Long accountRelationTypeDetailSequence;
    private Long centricAccountTypeId;
    private String centricAccountTypeName;
    private LocalDateTime deletedDate;

    public Long getAccountDefaultValuesRelationTypeDetailsId() {
        return accountDefaultValuesRelationTypeDetailsId;
    }

    public void setAccountDefaultValuesRelationTypeDetailsId(Long accountDefaultValuesRelationTypeDetailsId) {
        this.accountDefaultValuesRelationTypeDetailsId = accountDefaultValuesRelationTypeDetailsId;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountDefaultValuesRelationTypeDescription() {
        return accountDefaultValuesRelationTypeDescription;
    }

    public void setAccountDefaultValuesRelationTypeDescription(String accountDefaultValuesRelationTypeDescription) {
        this.accountDefaultValuesRelationTypeDescription = accountDefaultValuesRelationTypeDescription;
    }

    public Long getAccountRelationTypeId() {
        return accountRelationTypeId;
    }

    public void setAccountRelationTypeId(Long accountRelationTypeId) {
        this.accountRelationTypeId = accountRelationTypeId;
    }

    public Long getAccountRelationTypeDetailSequence() {
        return accountRelationTypeDetailSequence;
    }

    public void setAccountRelationTypeDetailSequence(Long accountRelationTypeDetailSequence) {
        this.accountRelationTypeDetailSequence = accountRelationTypeDetailSequence;
    }

    public Long getCentricAccountTypeId() {
        return centricAccountTypeId;
    }

    public void setCentricAccountTypeId(Long centricAccountTypeId) {
        this.centricAccountTypeId = centricAccountTypeId;
    }

    public String getCentricAccountTypeName() {
        return centricAccountTypeName;
    }

    public void setCentricAccountTypeName(String centricAccountTypeName) {
        this.centricAccountTypeName = centricAccountTypeName;
    }

    public LocalDateTime getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(LocalDateTime deletedDate) {
        this.deletedDate = deletedDate;
    }

    public static CentricAccountRelationDetailDto.Builder builder() {
        return new CentricAccountRelationDetailDto.Builder();
    }

    public static final class Builder {
        private CentricAccountRelationDetailDto centricAccountRelationDetailDto;

        private Builder() {
            centricAccountRelationDetailDto = new CentricAccountRelationDetailDto();
        }

        public static Builder centricAccountRelationDetailDto() {
            return new Builder();
        }

        public Builder accountDefaultValuesRelationTypeDetailsId(Long accountDefaultValuesRelationTypeDetailsId) {
            centricAccountRelationDetailDto.setAccountDefaultValuesRelationTypeDetailsId(accountDefaultValuesRelationTypeDetailsId);
            return this;
        }

        public Builder id(Long id) {
            centricAccountRelationDetailDto.setId(id);
            return this;
        }

        public Builder code(String code) {
            centricAccountRelationDetailDto.setCode(code);
            return this;
        }

        public Builder name(String name) {
            centricAccountRelationDetailDto.setName(name);
            return this;
        }

        public Builder accountDefaultValuesRelationTypeDescription(String accountDefaultValuesRelationTypeDescription) {
            centricAccountRelationDetailDto.setAccountDefaultValuesRelationTypeDescription(accountDefaultValuesRelationTypeDescription);
            return this;
        }

        public Builder accountRelationTypeId(Long accountRelationTypeId) {
            centricAccountRelationDetailDto.setAccountRelationTypeId(accountRelationTypeId);
            return this;
        }

        public Builder accountRelationTypeDetailSequence(Long accountRelationTypeDetailSequence) {
            centricAccountRelationDetailDto.setAccountRelationTypeDetailSequence(accountRelationTypeDetailSequence);
            return this;
        }

        public Builder centricAccountTypeId(Long centricAccountTypeId) {
            centricAccountRelationDetailDto.setCentricAccountTypeId(centricAccountTypeId);
            return this;
        }

        public Builder centricAccountTypeName(String centricAccountTypeName) {
            centricAccountRelationDetailDto.setCentricAccountTypeName(centricAccountTypeName);
            return this;
        }

        public Builder deletedDate(LocalDateTime deletedDate) {
            centricAccountRelationDetailDto.setDeletedDate(deletedDate);
            return this;
        }

        public CentricAccountRelationDetailDto build() {
            return centricAccountRelationDetailDto;
        }
    }
}
