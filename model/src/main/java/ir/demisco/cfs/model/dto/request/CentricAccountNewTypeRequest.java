package ir.demisco.cfs.model.dto.request;

import java.util.List;

public class CentricAccountNewTypeRequest {
    private List<Long> centricAccountTypeListId;
    private Long organizationId;

    public List<Long> getCentricAccountTypeListId() {
        return centricAccountTypeListId;
    }

    public void setCentricAccountTypeListId(List<Long> centricAccountTypeListId) {
        this.centricAccountTypeListId = centricAccountTypeListId;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }
}
