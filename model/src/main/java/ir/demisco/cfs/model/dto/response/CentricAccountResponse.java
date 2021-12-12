package ir.demisco.cfs.model.dto.response;

public class CentricAccountResponse {
    private Long centricAccountTypeId;
    private String name;
    private Long organizationId;

    public Long getCentricAccountTypeId() {
        return centricAccountTypeId;
    }

    public void setCentricAccountTypeId(Long centricAccountTypeId) {
        this.centricAccountTypeId = centricAccountTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }
    public static CentricAccountResponse.Builder builder() {
        return new CentricAccountResponse.Builder();
    }

    public static final class Builder {
        private CentricAccountResponse centricAccountResponse;

        private Builder() {
            centricAccountResponse = new CentricAccountResponse();
        }

        public static Builder aCentricAccountResponse() {
            return new Builder();
        }

        public Builder centricAccountTypeId(Long centricAccountTypeId) {
            centricAccountResponse.setCentricAccountTypeId(centricAccountTypeId);
            return this;
        }

        public Builder name(String name) {
            centricAccountResponse.setName(name);
            return this;
        }

        public Builder organizationId(Long organizationId) {
            centricAccountResponse.setOrganizationId(organizationId);
            return this;
        }

        public CentricAccountResponse build() {
            return centricAccountResponse;
        }
    }
}
