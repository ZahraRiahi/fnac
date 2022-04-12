package ir.demisco.cfs.model.entity;


import ir.demisco.cloud.basic.model.entity.domain.AuditModel;

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
import java.time.LocalDateTime;

@Entity
@Table(name = "account_structure_level", schema = "fnac")
public class AccountStructureLevel extends AuditModel<Long> {
    private Long id;
    private Long structureLevel;
    private String structureLevelCode;
    private FinancialAccountStructure financialAccountStructure;
    private FinancialAccount financialAccount;
    private Long relatedAccountId;
    private LocalDateTime deletedDate;

    @Override
    @Id
    @SequenceGenerator(schema = "fnac", name = "account_structure_level_generator", sequenceName = "sq_account_structure_level", allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_structure_level_generator")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "STRUCTURE_LEVEL")
    public Long getStructureLevel() {
        return structureLevel;
    }

    public void setStructureLevel(Long structureLevel) {
        this.structureLevel = structureLevel;
    }

    @Column(name = "STRUCTURE_LEVEL_CODE")
    public String getStructureLevelCode() {
        return structureLevelCode;
    }

    public void setStructureLevelCode(String structureLevelCode) {
        this.structureLevelCode = structureLevelCode;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FINANCIAL_STRUCTURE_ID")
    public FinancialAccountStructure getFinancialAccountStructure() {
        return financialAccountStructure;
    }

    public void setFinancialAccountStructure(FinancialAccountStructure financialAccountStructure) {
        this.financialAccountStructure = financialAccountStructure;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FINANCIAL_ACCOUNT_ID")
    public FinancialAccount getFinancialAccount() {
        return financialAccount;
    }

    public void setFinancialAccount(FinancialAccount financialAccount) {
        this.financialAccount = financialAccount;
    }

    @Column(name = "RELATED_ACCOUNT_ID")
    public Long getRelatedAccountId() {
        return relatedAccountId;
    }

    public void setRelatedAccountId(Long relatedAccountId) {
        this.relatedAccountId = relatedAccountId;
    }

    @Column(name = "DELETED_DATE")
    public LocalDateTime getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(LocalDateTime deletedDate) {
        this.deletedDate = deletedDate;
    }
}
