package ir.demisco.cfs.model.dto.response;

public class AccountMoneyTypeDtoResponse {
    private Long id;
    private String moneyTypeDescription;
    private Long moneyTypeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMoneyTypeDescription() {
        return moneyTypeDescription;
    }

    public void setMoneyTypeDescription(String moneyTypeDescription) {
        this.moneyTypeDescription = moneyTypeDescription;
    }

    public Long getMoneyTypeId() {
        return moneyTypeId;
    }

    public void setMoneyTypeId(Long moneyTypeId) {
        this.moneyTypeId = moneyTypeId;
    }
}
