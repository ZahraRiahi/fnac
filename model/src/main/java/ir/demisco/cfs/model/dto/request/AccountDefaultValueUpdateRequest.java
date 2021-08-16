package ir.demisco.cfs.model.dto.request;

import java.util.List;

public class AccountDefaultValueUpdateRequest {
    private Long id;
    private Long accountRelationTypeDetailId;
    private Long centricAccountId;
    private List<AccountDefaultValueUpdateRequest> accountDefaultValueUpdateDtos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountRelationTypeDetailId() {
        return accountRelationTypeDetailId;
    }

    public void setAccountRelationTypeDetailId(Long accountRelationTypeDetailId) {
        this.accountRelationTypeDetailId = accountRelationTypeDetailId;
    }

    public Long getCentricAccountId() {
        return centricAccountId;
    }

    public void setCentricAccountId(Long centricAccountId) {
        this.centricAccountId = centricAccountId;
    }

    public List<AccountDefaultValueUpdateRequest> getAccountDefaultValueUpdateDtos() {
        return accountDefaultValueUpdateDtos;
    }

    public void setAccountDefaultValueUpdateDtos(List<AccountDefaultValueUpdateRequest> accountDefaultValueUpdateDtos) {
        this.accountDefaultValueUpdateDtos = accountDefaultValueUpdateDtos;
    }
}
