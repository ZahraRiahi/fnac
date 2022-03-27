package ir.demisco.cfs.model.entity;

import ir.demisco.cloud.basic.model.entity.domain.AuditModel;
import ir.demisco.cloud.basic.model.entity.org.Organization;

import javax.persistence.*;

@Entity
@Table(name = "centric_org_rel", schema = "fnac")
public class CentricOrgRel extends AuditModel<Long> {
    private Long id;
    private CentricAccount centricAccount;
    private Organization organization;
    private Long activeFlag;

    @Id
    @SequenceGenerator(schema = "fnac", name = "centric_org_rel_generator", sequenceName = "sq_centric_org_rel", allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "centric_org_rel_generator")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CENTRIC_ACCOUNT_ID")
    public CentricAccount getCentricAccount() {
        return centricAccount;
    }

    public void setCentricAccount(CentricAccount centricAccount) {
        this.centricAccount = centricAccount;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORGANIZATION_ID")
    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    @Column(name = "ACTIVE_FLAG")
    public Long getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(Long activeFlag) {
        this.activeFlag = activeFlag;
    }
}
