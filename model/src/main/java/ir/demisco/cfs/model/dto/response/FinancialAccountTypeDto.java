package ir.demisco.cfs.model.dto.response;

import java.time.LocalDateTime;

public class FinancialAccountTypeDto {
    private Long id;
    private String description;
    private Long flgExists;
    private LocalDateTime DeletedDate;

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


    public Long getFlgExists() {
        return flgExists;
    }

    public void setFlgExists(Long flgExists) {
        this.flgExists = flgExists;
    }

    public LocalDateTime getDeletedDate() {
        return DeletedDate;
    }

    public void setDeletedDate(LocalDateTime deletedDate) {
        DeletedDate = deletedDate;
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

        public Builder flgExists(Long flgExists) {
            financialAccountTypeDto.setFlgExists(flgExists);
            return this;
        }

        public Builder DeletedDate(LocalDateTime DeletedDate) {
            financialAccountTypeDto.setDeletedDate(DeletedDate);
            return this;
        }

        public FinancialAccountTypeDto build() {
            return financialAccountTypeDto;
        }
    }
}
