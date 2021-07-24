package ir.demisco.cfs.model.entity;

import ir.demisco.cloud.basic.model.entity.domain.AuditModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "financial_account_type", schema = "fnac")
public class FinancialAccountType extends AuditModel<Long> {
    private Long id;
    private String code;
    private String description;
    private Boolean uniqueUsedFlag;
    private LocalDateTime DeletedDate;

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "CODE")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "UNIQUE_USED_FLAG")
    public Boolean getUniqueUsedFlag() {
        return uniqueUsedFlag;
    }

    public void setUniqueUsedFlag(Boolean uniqueUsedFlag) {
        this.uniqueUsedFlag = uniqueUsedFlag;
    }

    @Column(name = "DELETED_DATE")
    public LocalDateTime getDeletedDate() {
        return DeletedDate;
    }

    public void setDeletedDate(LocalDateTime deletedDate) {
        DeletedDate = deletedDate;
    }
}
