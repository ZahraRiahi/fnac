package ir.demisco.cfs.model.dto.response;

public class CentricAccountResponse {
    private Long id;
    private String code;
    private String name;
    private Long parentCentricAccountId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentCentricAccountId() {
        return parentCentricAccountId;
    }

    public void setParentCentricAccountId(Long parentCentricAccountId) {
        this.parentCentricAccountId = parentCentricAccountId;
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

        public Builder id(Long id) {
            centricAccountResponse.setId(id);
            return this;
        }

        public Builder code(String code) {
            centricAccountResponse.setCode(code);
            return this;
        }

        public Builder name(String name) {
            centricAccountResponse.setName(name);
            return this;
        }

        public Builder parentCentricAccountId(Long parentCentricAccountId) {
            centricAccountResponse.setParentCentricAccountId(parentCentricAccountId);
            return this;
        }

        public CentricAccountResponse build() {
            return centricAccountResponse;
        }
    }
}
