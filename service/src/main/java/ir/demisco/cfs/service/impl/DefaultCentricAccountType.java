package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.response.CentricAccountTypeResponse;
import ir.demisco.cfs.model.entity.CentricAccountType;
import ir.demisco.cfs.service.api.CentricAccountTypeService;
import ir.demisco.cfs.service.repository.CentricAccountTypeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultCentricAccountType implements CentricAccountTypeService {
    private final CentricAccountTypeRepository centricAccountTypeRepository;

    public DefaultCentricAccountType(CentricAccountTypeRepository centricAccountTypeRepository) {
        this.centricAccountTypeRepository = centricAccountTypeRepository;
    }

    @Override
    @Transactional
    public List<CentricAccountTypeResponse> getCentricAccountType() {

        List<CentricAccountType> centricAccountTypeList = centricAccountTypeRepository.findByCentricAccountType();
        return centricAccountTypeList.stream().map(e -> CentricAccountTypeResponse.builder().id(e.getId())
                .description(e.getDescription())
                .code(e.getCode())
                .build()).collect(Collectors.toList());

    }
}
