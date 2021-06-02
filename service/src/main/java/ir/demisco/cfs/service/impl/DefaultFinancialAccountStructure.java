package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.response.FinancialAccountStructureResponse;
import ir.demisco.cfs.model.entity.FinancialAccountStructure;
import ir.demisco.cfs.service.api.FinancialAccountStructureService;
import ir.demisco.cfs.service.repository.FinancialAccountStructureRepository;
import ir.demisco.cfs.service.repository.FinancialCodingTypeRepository;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import ir.demisco.cloud.core.middle.service.business.api.core.GridFilterService;
import org.apache.http.util.Asserts;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class DefaultFinancialAccountStructure implements FinancialAccountStructureService {
    private final GridFilterService gridFilterService;
    private final FinancialAccountStructureListGridProvider financialAccountStructureListGridProvider;
    private final FinancialAccountStructureRepository financialAccountStructureRepository;


    public DefaultFinancialAccountStructure(GridFilterService gridFilterService, FinancialAccountStructureListGridProvider financialAccountStructureListGridProvider, FinancialCodingTypeRepository financialCodingTypeRepository, FinancialAccountStructureRepository financialAccountStructureRepository) {
        this.gridFilterService = gridFilterService;
        this.financialAccountStructureListGridProvider = financialAccountStructureListGridProvider;
        this.financialAccountStructureRepository = financialAccountStructureRepository;
    }

    @Override
    @Transactional
    public DataSourceResult getFinancialAccountStructureByFinancialCodingTypeId(Long financialCodingTypeId, DataSourceRequest dataSourceRequest) {
        Asserts.notNull(financialCodingTypeId, "financialCodingTypeId is null");
        dataSourceRequest.getFilter().setLogic("and");
        dataSourceRequest.getFilter().getFilters().add(DataSourceRequest
                .FilterDescriptor.create("financialCodingType.id", financialCodingTypeId, DataSourceRequest.Operators.EQUALS));
        return gridFilterService.filter(dataSourceRequest, financialAccountStructureListGridProvider);
    }

    @Override
    @Transactional
    public List<FinancialAccountStructureResponse> getFinancialAccountStructureByFinancialCodingTypeIdLov(Long financialCodingTypeId) {
        List<FinancialAccountStructure> financialAccountStructureList = financialAccountStructureRepository.findByFinancialCodingTypeId(financialCodingTypeId);
        return financialAccountStructureList.stream().map(e -> FinancialAccountStructureResponse.builder().id(e.getId())
                .description(e.getDescription())
                .build()).collect(Collectors.toList());

    }


}
