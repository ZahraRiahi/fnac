package ir.demisco.cfs.model.dto.response;

public class FinancialAccountStructureDtoResponse {
    private Long sequence;
    private Long sumDigit;

    public Long getSequence() {
        return sequence;
    }

    public void setSequence(Long sequence) {
        this.sequence = sequence;
    }

    public Long getSumDigit() {
        return sumDigit;
    }

    public void setSumDigit(Long sumDigit) {
        this.sumDigit = sumDigit;
    }
    public static FinancialAccountStructureDtoResponse.Builder builder() {
        return new FinancialAccountStructureDtoResponse.Builder();
    }

    public static final class Builder {
        private FinancialAccountStructureDtoResponse financialAccountStructureDtoResponse;

        private Builder() {
            financialAccountStructureDtoResponse = new FinancialAccountStructureDtoResponse();
        }

        public static Builder aFinancialAccountStructureDtoResponse() {
            return new Builder();
        }

        public Builder sequence(Long sequence) {
            financialAccountStructureDtoResponse.setSequence(sequence);
            return this;
        }

        public Builder sumDigit(Long sumDigit) {
            financialAccountStructureDtoResponse.setSumDigit(sumDigit);
            return this;
        }

        public FinancialAccountStructureDtoResponse build() {
            return financialAccountStructureDtoResponse;
        }
    }
}
