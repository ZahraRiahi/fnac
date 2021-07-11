package ir.demisco.cfs.model.entity;

import ir.demisco.cloud.basic.model.entity.domain.AuditModel;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "account_relation_type", schema = "fnac")
public class AccountRelationType extends AuditModel<Long> {
    private Long id;
    private String description;
    private LocalDateTime DeletedDate;

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "DELETED_DATE")
    public LocalDateTime getDeletedDate() {
        return DeletedDate;
    }

    public void setDeletedDate(LocalDateTime deletedDate) {
        DeletedDate = deletedDate;
    }
}