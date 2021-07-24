package ir.demisco.cfs.model.entity;

import ir.demisco.cloud.basic.model.entity.domain.AuditModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "money_type", schema = "fncr")
public class MoneyType extends AuditModel<Long> {
    private Long id;
    private String code;
    private String description;
    private String shortDescription;
    private Long activeFlag;
    private Long nationalCurrencyFlag;
    private String symbol;
    private Long isBaseFlag;
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

    @Column(name = "SHORT_DESCRIPTION")
    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    @Column(name = "ACTIVE_FLAG")
    public Long getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(Long activeFlag) {
        this.activeFlag = activeFlag;
    }

    @Column(name = "NATIONAL_CURRENCY_FLAG")
    public Long getNationalCurrencyFlag() {
        return nationalCurrencyFlag;
    }

    public void setNationalCurrencyFlag(Long nationalCurrencyFlag) {
        this.nationalCurrencyFlag = nationalCurrencyFlag;
    }

    @Column(name = "SYMBOL")
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Column(name = "IS_BASE_FLAG")
    public Long getIsBaseFlag() {
        return isBaseFlag;
    }

    public void setIsBaseFlag(Long isBaseFlag) {
        this.isBaseFlag = isBaseFlag;
    }

    @Column(name = "DELETED_DATE")
    public LocalDateTime getDeletedDate() {
        return DeletedDate;
    }

    public void setDeletedDate(LocalDateTime deletedDate) {
        DeletedDate = deletedDate;
    }
}
