package ir.demisco.cfs.model.dto.request;

public class FinancialAccountStructureNewRequest {
    private Long financialCodingTypeId;
    private Long financialAccountStructureId;
    private Long financialAccountParentId;
    private Boolean flgEditMode;

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

    public Long getFinancialAccountParentId() {
        return financialAccountParentId;
    }

    public void setFinancialAccountParentId(Long financialAccountParentId) {
        this.financialAccountParentId = financialAccountParentId;
    }

    public Boolean getFlgEditMode() {
        return flgEditMode;
    }

    public void setFlgEditMode(Boolean flgEditMode) {
        this.flgEditMode = flgEditMode;
    }
}
