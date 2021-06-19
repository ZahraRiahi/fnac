package ir.demisco.cfs.model.dto.response;

public class PersonDto {
    private Long id;
    private String personName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public static PersonDto.Builder builder() {
        return new PersonDto.Builder();
    }

    public static final class Builder {
        private PersonDto personDto;

        private Builder() {
            personDto = new PersonDto();
        }

        public static Builder personDto() {
            return new Builder();
        }

        public Builder id(Long id) {
            personDto.setId(id);
            return this;
        }

        public Builder personName(String personName) {
            personDto.setPersonName(personName);
            return this;
        }

        public PersonDto build() {
            return personDto;
        }
    }
}
