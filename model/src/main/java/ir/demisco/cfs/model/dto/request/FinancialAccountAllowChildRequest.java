package ir.demisco.cfs.model.dto.request;

public class FinancialAccountAllowChildRequest {
    private Long id;
    private Long financialAccountStructureId;
    private Long financialCodingTypeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFinancialAccountStructureId() {
        return financialAccountStructureId;
    }

    public void setFinancialAccountStructureId(Long financialAccountStructureId) {
        this.financialAccountStructureId = financialAccountStructureId;
    }

    public Long getFinancialCodingTypeId() {
        return financialCodingTypeId;
    }

    public void setFinancialCodingTypeId(Long financialCodingTypeId) {
        this.financialCodingTypeId = financialCodingTypeId;
    }

}
