package ir.demisco.cfs.model.dto.request;

public class FinancialAccountStructureRequest {
    private Long financialCodingTypeId;
    private Long financialAccountStructureId;

    public Long getFinancialCodingTypeId() {
        return financialCodingTypeId;
    }

    public void setFinancialCodingTypeId(Long financialCodingTypeId) {
        this.financialCodingTypeId = financialCodingTypeId;
    }

    public Long getFinancialAccountStructureId() {
        return financialAccountStructureId;
    }

    public void setFinancialAccountStructureId(Long financialAccountStructureId) {
        this.financialAccountStructureId = financialAccountStructureId;
    }

}
