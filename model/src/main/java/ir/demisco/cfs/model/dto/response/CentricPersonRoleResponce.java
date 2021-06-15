package ir.demisco.cfs.model.dto.response;

public class CentricPersonRoleResponce {
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
    public static CentricPersonRoleResponce.Builder builder() {
        return new CentricPersonRoleResponce.Builder();
    }

    public static final class Builder {
        private CentricPersonRoleResponce centricPersonRoleResponce;

        private Builder() {
            centricPersonRoleResponce = new CentricPersonRoleResponce();
        }

        public static Builder aCentricPersonRoleResponce() {
            return new Builder();
        }

        public Builder id(Long id) {
            centricPersonRoleResponce.setId(id);
            return this;
        }

        public Builder code(String code) {
            centricPersonRoleResponce.setCode(code);
            return this;
        }

        public Builder name(String name) {
            centricPersonRoleResponce.setName(name);
            return this;
        }

        public CentricPersonRoleResponce build() {
            return centricPersonRoleResponce;
        }
    }
}
