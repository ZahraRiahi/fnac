package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.response.PersonRoleTypeDto;
import ir.demisco.cfs.service.api.PersonRoleTypeService;
import ir.demisco.cfs.service.repository.PersonRoleTypeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultPersonRoleType implements PersonRoleTypeService {
    private final PersonRoleTypeRepository personRoleTypeRepository;

    public DefaultPersonRoleType(PersonRoleTypeRepository personRoleTypeRepository) {
        this.personRoleTypeRepository = personRoleTypeRepository;
    }

    @Override
    @Transactional
    public List<PersonRoleTypeDto> getPersonRoleType() {
        List<Object[]> personRoleTypeList = personRoleTypeRepository.findByPersonRoleTypeListObject();
        List<PersonRoleTypeDto> personRoleTypeDtos = new ArrayList<>();
        personRoleTypeList.forEach(objects -> {
            PersonRoleTypeDto personRoleTypeDto = PersonRoleTypeDto.builder().id(Long.parseLong(objects[0].toString()))
                    .description(objects[1].toString()).flagExist(Long.parseLong(objects[2].toString())).build();
            personRoleTypeDtos.add(personRoleTypeDto);
        });
        return personRoleTypeDtos;

    }
}
