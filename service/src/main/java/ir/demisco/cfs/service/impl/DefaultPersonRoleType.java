package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.request.PersonRoleListRequest;
import ir.demisco.cfs.model.dto.response.PersonRoleTypeDto;
import ir.demisco.cfs.service.api.PersonRoleTypeService;
import ir.demisco.cfs.service.repository.PersonRoleTypeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultPersonRoleType implements PersonRoleTypeService {
    private final PersonRoleTypeRepository personRoleTypeRepository;

    public DefaultPersonRoleType(PersonRoleTypeRepository personRoleTypeRepository) {
        this.personRoleTypeRepository = personRoleTypeRepository;
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public List<PersonRoleTypeDto> getPersonRoleType(PersonRoleListRequest personRoleListRequest) {
        Object centricAccount;
        if (personRoleListRequest.getCentricAccountId() != null) {
            centricAccount = "centricAccount";
        } else {
            personRoleListRequest.setCentricAccountId(0L);
            centricAccount = null;
        }
        List<Object[]> centricAccountTypeListObject = personRoleTypeRepository.findByPersonRoleTypeListObject(centricAccount, personRoleListRequest.getCentricAccountId());
        return centricAccountTypeListObject.stream().map(objects -> PersonRoleTypeDto.builder().id(Long.parseLong(objects[0].toString()))
                .description(objects[1].toString())
                .flagExist(Long.parseLong(objects[2].toString()))
                .build()).collect(Collectors.toList());
    }


//    @Override
//    @Transactional
//    public List<PersonRoleTypeDto> getPersonRoleType() {
//        List<Object[]> personRoleTypeList = personRoleTypeRepository.findByPersonRoleTypeListObject();
//        List<PersonRoleTypeDto> personRoleTypeDtos = new ArrayList<>();
//        personRoleTypeList.forEach(objects -> {
//            PersonRoleTypeDto personRoleTypeDto = PersonRoleTypeDto.builder().id(Long.parseLong(objects[0].toString()))
//                    .description(objects[1].toString()).flagExist(Long.parseLong(objects[2].toString())).build();
//            personRoleTypeDtos.add(personRoleTypeDto);
//        });
//        return personRoleTypeDtos;
//
//    }
}
