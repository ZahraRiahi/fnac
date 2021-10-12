package ir.demisco.cfs.model.dto.response;

import java.time.LocalDateTime;

public class FinancialAccountStructureDto {
    private Long id;
    private Long sequence;
    private Long digitCount;
    private String description;
    private Long sumDigit;
    private Long financialCodingTypeId;
    private String color;
    private LocalDateTime deletedDate;
    private Long flgShowInAcc;
    private Long flgPermanentStatus;

    public FinancialAccountStructureDto(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public FinancialAccountStructureDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSequence() {
        return sequence;
    }

    public void setSequence(Long sequence) {
        this.sequence = sequence;
    }

    public Long getDigitCount() {
        return digitCount;
    }

    public void setDigitCount(Long digitCount) {
        this.digitCount = digitCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getSumDigit() {
        return sumDigit;
    }

    public void setSumDigit(Long sumDigit) {
        this.sumDigit = sumDigit;
    }

    public Long getFinancialCodingTypeId() {
        return financialCodingTypeId;
    }

    public void setFinancialCodingTypeId(Long financialCodingTypeId) {
        this.financialCodingTypeId = financialCodingTypeId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public LocalDateTime getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(LocalDateTime deletedDate) {
        this.deletedDate = deletedDate;
    }

    public Long getFlgShowInAcc() {
        return flgShowInAcc;
    }

    public void setFlgShowInAcc(Long flgShowInAcc) {
        this.flgShowInAcc = flgShowInAcc;
    }

    public Long getFlgPermanentStatus() {
        return flgPermanentStatus;
    }

    public void setFlgPermanentStatus(Long flgPermanentStatus) {
        this.flgPermanentStatus = flgPermanentStatus;
    }

    public static FinancialAccountStructureDto.Builder builder() {
        return new FinancialAccountStructureDto.Builder();
    }

    public static final class Builder {
        private FinancialAccountStructureDto financialAccountStructureDto;

        private Builder() {
            financialAccountStructureDto = new FinancialAccountStructureDto();
        }

        public static Builder financialAccountStructureDto() {
            return new Builder();
        }

        public Builder id(Long id) {
            financialAccountStructureDto.setId(id);
            return this;
        }

        public Builder sequence(Long sequence) {
            financialAccountStructureDto.setSequence(sequence);
            return this;
        }

        public Builder digitCount(Long digitCount) {
            financialAccountStructureDto.setDigitCount(digitCount);
            return this;
        }

        public Builder description(String description) {
            financialAccountStructureDto.setDescription(description);
            return this;
        }

        public Builder sumDigit(Long sumDigit) {
            financialAccountStructureDto.setSumDigit(sumDigit);
            return this;
        }

        public Builder financialCodingTypeId(Long financialCodingTypeId) {
            financialAccountStructureDto.setFinancialCodingTypeId(financialCodingTypeId);
            return this;
        }

        public Builder color(String color) {
            financialAccountStructureDto.setColor(color);
            return this;
        }

        public Builder deletedDate(LocalDateTime deletedDate) {
            financialAccountStructureDto.setDeletedDate(deletedDate);
            return this;
        }
        public Builder flgShowInAcc(Long flgShowInAcc) {
            financialAccountStructureDto.setFlgShowInAcc(flgShowInAcc);
            return this;
        }
        public Builder flgPermanentStatus(Long flgPermanentStatus) {
            financialAccountStructureDto.setFlgPermanentStatus(flgPermanentStatus);
            return this;
        }
        public FinancialAccountStructureDto build() {
            return financialAccountStructureDto;
        }
    }
}
