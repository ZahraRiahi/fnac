package ir.demisco.cfs.model.entity;

import ir.demisco.cloud.basic.model.entity.domain.AuditModel;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "account_related_description", schema = "fnac")
public class AccountRelatedDescription extends AuditModel<Long> {

    private Long id;
    private FinancialAccountDescription financialAccountDescription;
    private FinancialAccount financialAccount;
    private LocalDateTime deletedDate;

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FINANCIAL_ACCOUNT_DES_ID")
    public FinancialAccountDescription getFinancialAccountDescription() {
        return financialAccountDescription;
    }

    public void setFinancialAccountDescription(FinancialAccountDescription financialAccountDescription) {
        this.financialAccountDescription = financialAccountDescription;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FINANCIAL_ACCOUNT_ID")
    public FinancialAccount getFinancialAccount() {
        return financialAccount;
    }

    public void setFinancialAccount(FinancialAccount financialAccount) {
        this.financialAccount = financialAccount;
    }

    @Column(name = "DELETED_DATE")
    public LocalDateTime getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(LocalDateTime deletedDate) {
        this.deletedDate = deletedDate;
    }


}
