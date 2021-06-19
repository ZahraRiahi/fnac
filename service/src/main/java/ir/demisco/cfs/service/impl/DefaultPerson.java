package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.service.api.PersonService;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import ir.demisco.cloud.core.middle.service.business.api.core.GridFilterService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class DefaultPerson implements PersonService {
    private final GridFilterService gridFilterService;
    private final PersonListProvider personListProvider;


    public DefaultPerson(GridFilterService gridFilterService, PersonListProvider personListProvider) {
        this.gridFilterService = gridFilterService;
        this.personListProvider = personListProvider;
    }

    @Override
    @Transactional
    public DataSourceResult getPersonByIdAndName(DataSourceRequest dataSourceRequest) {
        return gridFilterService.filter(dataSourceRequest, personListProvider);
    }
}
