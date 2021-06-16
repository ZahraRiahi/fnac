package ir.demisco.cfs.model.dto.response;

public class PersonRoleTypeDto {
    private Long id;
    private String description;
    private Long FlagExist;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public static PersonRoleTypeDto.Builder builder() {
        return new PersonRoleTypeDto.Builder();
    }

    public Long getFlagExist() {
        return FlagExist;
    }

    public void setFlagExist(Long flagExist) {
        FlagExist = flagExist;
    }

    public static final class Builder {
        private PersonRoleTypeDto personRoleTypeDto;

        private Builder() {
            personRoleTypeDto = new PersonRoleTypeDto();
        }

        public static Builder aPersonRoleTypeDto() {
            return new Builder();
        }

        public Builder id(Long id) {
            personRoleTypeDto.setId(id);
            return this;
        }

        public Builder description(String description) {
            personRoleTypeDto.setDescription(description);
            return this;
        }

        public Builder flagExist(Long flagExist){
            personRoleTypeDto.FlagExist = flagExist;
            return this;
        }
        public PersonRoleTypeDto build() {
            return personRoleTypeDto;
        }
    }
}
