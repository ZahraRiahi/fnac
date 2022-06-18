package ir.demisco.cfs.model.entity;

import ir.demisco.cloud.basic.model.entity.domain.AuditModel;
import ir.demisco.cloud.basic.model.entity.org.Organization;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "coding_type_org_rel", schema = "fnac")
public class CodingTypeOrgRel extends AuditModel<Long> {
    private Long id;
    private FinancialCodingType financialCodingType;
    private Organization organization;
    private Long activeFlag;
    @Override
    @Id
    @SequenceGenerator(schema = "fnac", name = "coding_type_org_rel_generator", sequenceName = "sq_coding_type_org_rel", allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coding_type_org_rel_generator")
    public Long getId() {
        return id;
    }
    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FINANCIAL_CODING_TYPE_ID")
    public FinancialCodingType getFinancialCodingType() {
        return financialCodingType;
    }

    public void setFinancialCodingType(FinancialCodingType financialCodingType) {
        this.financialCodingType = financialCodingType;
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
