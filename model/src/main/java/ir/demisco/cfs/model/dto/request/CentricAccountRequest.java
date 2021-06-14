package ir.demisco.cfs.model.dto.request;

public class CentricAccountRequest {
    private Long id;
    private String code;
    private String name;
    private String centricAccountTypeCode;
    private Long organizationId;
    private Long personId;
    private Long activeFlag;
    private Long peraonRoleTypeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCentricAccountTypeCode() {
        return centricAccountTypeCode;
    }

    public void setCentricAccountTypeCode(String centricAccountTypeCode) {
        this.centricAccountTypeCode = centricAccountTypeCode;
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

    public Long getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(Long activeFlag) {
        this.activeFlag = activeFlag;
    }

    public Long getPeraonRoleTypeId() {
        return peraonRoleTypeId;
    }

    public void setPeraonRoleTypeId(Long peraonRoleTypeId) {
        this.peraonRoleTypeId = peraonRoleTypeId;
    }
}
