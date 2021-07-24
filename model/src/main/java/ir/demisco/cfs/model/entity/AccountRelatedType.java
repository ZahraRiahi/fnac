package ir.demisco.cfs.model.entity;

import ir.demisco.cloud.basic.model.entity.domain.AuditModel;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "account_related_type", schema = "fnac")
public class AccountRelatedType extends AuditModel<Long> {

    private FinancialAccount financialAccount;
    private FinancialAccountType financialAccountType;
    private LocalDateTime DeletedDate;


    @Override
    @Id
    public Long getId() {
        return super.getId();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FINANCIAL_ACCOUNT_ID")
    public FinancialAccount getFinancialAccount() {
        return financialAccount;
    }

    public void setFinancialAccount(FinancialAccount financialAccount) {
        this.financialAccount = financialAccount;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FINANCIAL_ACCOUNT_TYPE_ID")
    public FinancialAccountType getFinancialAccountType() {
        return financialAccountType;
    }

    public void setFinancialAccountType(FinancialAccountType financialAccountType) {
        this.financialAccountType = financialAccountType;
    }
    @Column(name = "DELETED_DATE")
    public LocalDateTime getDeletedDate() {
        return DeletedDate;
    }

    public void setDeletedDate(LocalDateTime deletedDate) {
        DeletedDate = deletedDate;
    }
}
