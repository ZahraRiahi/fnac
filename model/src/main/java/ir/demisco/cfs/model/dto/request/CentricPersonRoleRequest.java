package ir.demisco.cfs.model.dto.request;

public class CentricPersonRoleRequest {
    private Long personRoleTypeId;
    private Long organizationId;
    private Long personId;

    public Long getPersonRoleTypeId() {
        return personRoleTypeId;
    }

    public void setPersonRoleTypeId(Long personRoleTypeId) {
        this.personRoleTypeId = personRoleTypeId;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

}
