package ir.demisco.cfs.model.dto.response;

public class CentricAccountNewResponse {
    private Long id;
    private String code;
    private String name;

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

    public static CentricAccountNewResponse.Builder builder() {
        return new CentricAccountNewResponse.Builder();
    }

    public static final class Builder {
        private CentricAccountNewResponse centricAccountNewResponse;

        private Builder() {
            centricAccountNewResponse = new CentricAccountNewResponse();
        }

        public static Builder centricAccountNewResponse() {
            return new Builder();
        }

        public Builder id(Long id) {
            centricAccountNewResponse.setId(id);
            return this;
        }

        public Builder code(String code) {
            centricAccountNewResponse.setCode(code);
            return this;
        }

        public Builder name(String name) {
            centricAccountNewResponse.setName(name);
            return this;
        }

        public CentricAccountNewResponse build() {
            return centricAccountNewResponse;
        }
    }
}
