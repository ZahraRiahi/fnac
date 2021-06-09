package ir.demisco.cfs.model.dto.response;

public class CentricAccountTypeResponse {
    private Long id;
    private String code;
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static CentricAccountTypeResponse.Builder builder() {
        return new CentricAccountTypeResponse.Builder();
    }

    public static final class Builder {
        private CentricAccountTypeResponse centricAccountTypeResponse;

        private Builder() {
            centricAccountTypeResponse = new CentricAccountTypeResponse();
        }

        public static Builder centricAccountTypeResponse() {
            return new Builder();
        }

        public Builder id(Long id) {
            centricAccountTypeResponse.setId(id);
            return this;
        }

        public Builder code(String code) {
            centricAccountTypeResponse.setCode(code);
            return this;
        }

        public Builder description(String description) {
            centricAccountTypeResponse.setDescription(description);
            return this;
        }

        public CentricAccountTypeResponse build() {
            return centricAccountTypeResponse;
        }
    }
}
