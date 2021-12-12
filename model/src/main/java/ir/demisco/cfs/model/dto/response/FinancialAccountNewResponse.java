package ir.demisco.cfs.model.dto.response;

public class FinancialAccountNewResponse {
    private Long id;
    private Long digitCount;
    private String preCode;
    private String suggestedCode;
    private Long flgShowInAcc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDigitCount() {
        return digitCount;
    }

    public void setDigitCount(Long digitCount) {
        this.digitCount = digitCount;
    }

    public String getPreCode() {
        return preCode;
    }

    public void setPreCode(String preCode) {
        this.preCode = preCode;
    }

    public String getSuggestedCode() {
        return suggestedCode;
    }

    public void setSuggestedCode(String suggestedCode) {
        this.suggestedCode = suggestedCode;
    }

    public Long getFlgShowInAcc() {
        return flgShowInAcc;
    }

    public void setFlgShowInAcc(Long flgShowInAcc) {
        this.flgShowInAcc = flgShowInAcc;
    }

    public static FinancialAccountNewResponse.Builder builder() {
        return new FinancialAccountNewResponse.Builder();
    }

    public static final class Builder {
        private FinancialAccountNewResponse financialAccountNewResponse;

        private Builder() {
            financialAccountNewResponse = new FinancialAccountNewResponse();
        }

        public static Builder aFinancialAccountNewResponse() {
            return new Builder();
        }

        public Builder id(Long id) {
            financialAccountNewResponse.setId(id);
            return this;
        }

        public Builder digitCount(Long digitCount) {
            financialAccountNewResponse.setDigitCount(digitCount);
            return this;
        }

        public Builder preCode(String preCode) {
            financialAccountNewResponse.setPreCode(preCode);
            return this;
        }

        public Builder suggestedCode(String suggestedCode) {
            financialAccountNewResponse.setSuggestedCode(suggestedCode);
            return this;
        }
        public Builder flgShowInAcc(Long flgShowInAcc) {
            financialAccountNewResponse.setFlgShowInAcc(flgShowInAcc);
            return this;
        }
        public FinancialAccountNewResponse build() {
            return financialAccountNewResponse;
        }
    }
}
