package ir.demisco.cfs.model.dto.response;

import java.time.LocalDateTime;

public class FinancialAccountTypeDto {
    private Long id;
    private String description;
    private String code;
    private Long flgExists;
    private LocalDateTime deletedDate;

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

    public Long getFlgExists() {
        return flgExists;
    }

    public void setFlgExists(Long flgExists) {
        this.flgExists = flgExists;
    }

    public LocalDateTime getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(LocalDateTime deletedDate) {
        this.deletedDate = deletedDate;
    }

    public static FinancialAccountTypeDto.Builder builder() {
        return new FinancialAccountTypeDto.Builder();
    }

    public static final class Builder {
        private FinancialAccountTypeDto financialAccountTypeDto;

        private Builder() {
            financialAccountTypeDto = new FinancialAccountTypeDto();
        }

        public static Builder financialAccountTypeDto() {
            return new Builder();
        }

        public Builder id(Long id) {
            financialAccountTypeDto.setId(id);
            return this;
        }

        public Builder description(String description) {
            financialAccountTypeDto.setDescription(description);
            return this;
        }
        public Builder code(String code) {
            financialAccountTypeDto.setCode(code);
            return this;
        }

        public Builder flgExists(Long flgExists) {
            financialAccountTypeDto.setFlgExists(flgExists);
            return this;
        }

        public Builder deletedDate(LocalDateTime deletedDate) {
            financialAccountTypeDto.setDeletedDate(deletedDate);
            return this;
        }

        public FinancialAccountTypeDto build() {
            return financialAccountTypeDto;
        }
    }
}
