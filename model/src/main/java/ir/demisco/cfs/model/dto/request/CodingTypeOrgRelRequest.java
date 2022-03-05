package ir.demisco.cfs.model.dto.request;

public class CodingTypeOrgRelRequest {
    private Long codingTypeId;
    private Long id;
    private Long organizationId;
    private Long activeFlag;

    public Long getCodingTypeId() {
        return codingTypeId;
    }

    public void setCodingTypeId(Long codingTypeId) {
        this.codingTypeId = codingTypeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public Long getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(Long activeFlag) {
        this.activeFlag = activeFlag;
    }
}
