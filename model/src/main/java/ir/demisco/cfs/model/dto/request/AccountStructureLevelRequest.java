package ir.demisco.cfs.model.dto.request;

public class AccountStructureLevelRequest {
    private Long id;
    private Long structureLevel;
    private String structureLevelCode;
    private Long FinancialStructureId;
    private Long FinancialAccountId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStructureLevel() {
        return structureLevel;
    }

    public void setStructureLevel(Long structureLevel) {
        this.structureLevel = structureLevel;
    }

    public String getStructureLevelCode() {
        return structureLevelCode;
    }

    public void setStructureLevelCode(String structureLevelCode) {
        this.structureLevelCode = structureLevelCode;
    }

    public Long getFinancialStructureId() {
        return FinancialStructureId;
    }

    public void setFinancialStructureId(Long financialStructureId) {
        FinancialStructureId = financialStructureId;
    }

    public Long getFinancialAccountId() {
        return FinancialAccountId;
    }

    public void setFinancialAccountId(Long financialAccountId) {
        FinancialAccountId = financialAccountId;
    }

}
