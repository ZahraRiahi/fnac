package ir.demisco.cfs.model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "account_permanent_status", schema = "fnac")
public class AccountPermanentStatus {
    private Long id;
    private String code;
    private String description;
    private LocalDateTime deletedDate;

    @Id
    @SequenceGenerator(schema = "fnac", name = "account_status_generator", sequenceName = "sq_account_status", allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_status_generator")
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

    @Column(name = "DELETED_DATE")
    public LocalDateTime getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(LocalDateTime deletedDate) {
        this.deletedDate = deletedDate;
    }
}
