package ir.demisco.cfs.model.dto.request;
import java.util.List;

public class CentricAccountRequest {
    private Long id;
    private String code;
    private String name;
    private String centricAccountTypeCode;
    private Long centricAccountTypeId;
    private Long organizationId;
    private Long personId;
    private Boolean activeFlag;
    private List<Long> centricPersonRoleListId;
    private Long parentCentricAccountId;
    private String parentCentricAccountCode;
    private String parentCentricAccountName;

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

    public Long getCentricAccountTypeId() {
        return centricAccountTypeId;
    }

    public void setCentricAccountTypeId(Long centricAccountTypeId) {
        this.centricAccountTypeId = centricAccountTypeId;
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

    public Boolean getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(Boolean activeFlag) {
        this.activeFlag = activeFlag;
    }

    public List<Long> getCentricPersonRoleListId() {
        return centricPersonRoleListId;
    }

    public void setCentricPersonRoleList(List<Long> centricPersonRoleListId) {
        this.centricPersonRoleListId = centricPersonRoleListId;
    }

    public Long getParentCentricAccountId() {
        return parentCentricAccountId;
    }

    public void setParentCentricAccountId(Long parentCentricAccountId) {
        this.parentCentricAccountId = parentCentricAccountId;
    }

    public String getParentCentricAccountCode() {
        return parentCentricAccountCode;
    }

    public void setParentCentricAccountCode(String parentCentricAccountCode) {
        this.parentCentricAccountCode = parentCentricAccountCode;
    }

    public String getParentCentricAccountName() {
        return parentCentricAccountName;
    }

    public void setParentCentricAccountName(String parentCentricAccountName) {
        this.parentCentricAccountName = parentCentricAccountName;
    }
}
