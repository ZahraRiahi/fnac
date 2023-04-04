package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.request.FinancialAccountStructureDtoRequest;
import ir.demisco.cfs.model.dto.request.FinancialAccountStructureNewRequest;
import ir.demisco.cfs.model.dto.request.FinancialAccountStructureRequest;
import ir.demisco.cfs.model.dto.response.FinancialAccountStructureDto;
import ir.demisco.cfs.model.dto.response.FinancialAccountStructureDtoResponse;
import ir.demisco.cfs.model.dto.response.FinancialAccountStructureNewResponse;
import ir.demisco.cfs.model.dto.response.FinancialAccountStructureResponse;
import ir.demisco.cfs.model.entity.FinancialAccountStructure;
import ir.demisco.cfs.service.api.FinancialAccountStructureService;
import ir.demisco.cfs.service.repository.FinancialAccountRepository;
import ir.demisco.cfs.service.repository.FinancialAccountStructureRepository;
import ir.demisco.cfs.service.repository.FinancialCodingTypeRepository;
import ir.demisco.cfs.service.repository.FinancialDocumentItemRepository;
import ir.demisco.cloud.core.middle.exception.RuleException;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import ir.demisco.cloud.core.middle.service.business.api.core.GridFilterService;
import org.apache.http.util.Asserts;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;


@Service
public class DefaultFinancialAccountStructure implements FinancialAccountStructureService {
    private final GridFilterService gridFilterService;
    private final FinancialAccountStructureListGridProvider financialAccountStructureListGridProvider;
    private final FinancialAccountStructureRepository financialAccountStructureRepository;
    private final FinancialCodingTypeRepository financialCodingTypeRepository;
    private final FinancialAccountRepository financialAccountRepository;
    private final FinancialDocumentItemRepository financialDocumentItemRepository;

    public DefaultFinancialAccountStructure(GridFilterService gridFilterService, FinancialAccountStructureListGridProvider financialAccountStructureListGridProvider, FinancialAccountStructureRepository financialAccountStructureRepository, FinancialCodingTypeRepository financialCodingTypeRepository1, FinancialAccountRepository financialAccountRepository, FinancialDocumentItemRepository financialDocumentItemRepository) {
        this.gridFilterService = gridFilterService;
        this.financialAccountStructureListGridProvider = financialAccountStructureListGridProvider;
        this.financialAccountStructureRepository = financialAccountStructureRepository;
        this.financialCodingTypeRepository = financialCodingTypeRepository1;
        this.financialAccountRepository = financialAccountRepository;
        this.financialDocumentItemRepository = financialDocumentItemRepository;
    }

    @Override
    @Transactional
    public DataSourceResult getFinancialAccountStructureByFinancialCodingTypeId(Long financialCodingTypeId, DataSourceRequest dataSourceRequest) {
        Asserts.notNull(financialCodingTypeId, "financialCodingTypeId is null");
        dataSourceRequest.getFilter().setLogic("and");
        dataSourceRequest.getFilter().getFilters().add(DataSourceRequest
                .FilterDescriptor.create("financialCodingType.id", financialCodingTypeId, DataSourceRequest.Operators.EQUALS));
        dataSourceRequest.getFilter().getFilters().add(DataSourceRequest.FilterDescriptor.create("deletedDate", null, DataSourceRequest.Operators.IS_NULL));
        return gridFilterService.filter(dataSourceRequest, financialAccountStructureListGridProvider);
    }

    @Override
    @Transactional
    public List<FinancialAccountStructureResponse> getFinancialAccountStructureByFinancialCodingTypeIdLov(Long financialCodingTypeId) {
        List<FinancialAccountStructure> financialAccountStructureList = financialAccountStructureRepository.findByFinancialCodingTypeId(financialCodingTypeId);
        return financialAccountStructureList.stream().map(e -> FinancialAccountStructureResponse.builder().id(e.getId())
                .description(e.getDescription())
                .sequence(e.getSequence())
                .build()).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public Long save(FinancialAccountStructureDto financialAccountStructureDto) throws RuleException {
        if (financialAccountStructureDto.getSequence() <= 0) {
            throw new RuleException("fin.financialAccountStructure.checkSequence");
        }
        Long financialAccountStructureCount = financialAccountStructureRepository.getCountByFinancialAccountStructureSequenceAndIdSave(financialAccountStructureDto.getSequence(), financialAccountStructureDto.getFinancialCodingTypeId());
        if (financialAccountStructureCount > 0) {
            throw new RuleException("fin.financialAccountStructure.Unique");
        }
        String financialAccountStructure = null;
        if (financialAccountStructureDto.getId() != null) {
            financialAccountStructure = "financialAccountStructure";
        } else {
            financialAccountStructureDto.setId(0L);
        }
        if (financialAccountStructureDto.getFlgPermanentStatus().equals(true)) {
            List<Long> financialAccountStructureCoding = financialAccountStructureRepository.getFinancialAccountStructureByCodingAndStructureId(financialAccountStructureDto.getFinancialCodingTypeId(), financialAccountStructure, financialAccountStructureDto.getId());
            if (financialAccountStructureCoding.size() != 0) {
                throw new RuleException("fin.financialAccountStructureCoding.ruleException.save");
            }
        }

        List<Long> financialAccountStructureCoding = financialAccountStructureRepository.getFinancialAccountStructureByCodingAndFlgShow(financialAccountStructureDto.getFinancialCodingTypeId(), financialAccountStructure, financialAccountStructureDto.getId());
        if (financialAccountStructureCoding.size() != 0) {
            throw new RuleException("fin.financialAccountStructureCodingFlag.ruleException.save");
        }
        FinancialAccountStructure financialAccountStructureFlg = financialAccountStructureRepository.findById(financialAccountStructureDto.getId() == null ? 0L : financialAccountStructureDto.getId()).orElse(new FinancialAccountStructure());
        financialAccountStructureFlg.setDescription(financialAccountStructureDto.getDescription());
        financialAccountStructureFlg.setSequence(financialAccountStructureDto.getSequence());
        financialAccountStructureFlg.setDigitCount(financialAccountStructureDto.getDigitCount());
        financialAccountStructureFlg.setSumDigit(financialAccountStructureDto.getSumDigit());
        financialAccountStructureFlg.setColor(financialAccountStructureDto.getColor());
        financialAccountStructureFlg.setFinancialCodingType(financialCodingTypeRepository.findById(financialAccountStructureDto.getFinancialCodingTypeId()).orElseThrow(() -> new RuleException("fin.financialCoding.notFoundId")));
        financialAccountStructureFlg.setFlgShowInAcc(financialAccountStructureDto.getFlgShowInAcc());
        financialAccountStructureFlg.setFlgPermanentStatus(financialAccountStructureDto.getFlgPermanentStatus());
        return financialAccountStructureRepository.save(financialAccountStructureFlg).getId();
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public FinancialAccountStructureDto update(FinancialAccountStructureDto financialAccountStructureDto) {
        Long illigalChange = 0L;
        FinancialAccountStructure financialAccountStructureFlg = financialAccountStructureRepository.findById(financialAccountStructureDto.getId()).orElseThrow(() -> new RuleException("fin.financialAccountStructure.financialPeriodId"));
        List<Object> financialDocument = financialDocumentItemRepository.findByFinancialDocumentAndFinancialAccountStructure(financialAccountStructureDto.getId());

        if (financialAccountStructureDto.getSequence() <= 0) {
            throw new RuleException("fin.financialAccountStructure.checkSequence");
        }

        Long financialAccountStructureCountIlligalChange = financialAccountStructureRepository.getFinancialAccountStructureBySequenceAndFlg(financialAccountStructureDto.getId(), financialAccountStructureDto.getSequence(),
                financialAccountStructureDto.getDigitCount(), financialAccountStructureDto.getDescription(), financialAccountStructureDto.getSumDigit(), financialAccountStructureDto.getFinancialCodingTypeId(),
                financialAccountStructureDto.getFlgShowInAcc(), financialAccountStructureDto.getFlgPermanentStatus());
        if (financialAccountStructureCountIlligalChange != null) {
            illigalChange = 1L;
        }
        List<Long> financialStructure = financialAccountRepository.getFinancialAccountByFinancialAccountStructureId(financialAccountStructureDto.getId());
        if (financialStructure.size() != 0 && illigalChange == 1) {
            throw new RuleException("fin.financialAccountStructure.edit.financialAccountId");
        }

        Long financialAccountStructureCount = financialAccountStructureRepository.getCountByFinancialAccountStructureSequenceAndId(financialAccountStructureDto.getSequence(), financialAccountStructureDto.getFinancialCodingTypeId(), financialAccountStructureFlg.getId());
        if (financialAccountStructureCount > 0) {
            throw new RuleException("fin.financialAccountStructure.Unique");
        }

        Long countFinancialAccount = financialAccountRepository.findByFinancialAccountStructureId(financialAccountStructureDto.getId());
        if (countFinancialAccount != null && illigalChange == 1) {
            throw new RuleException("fin.financialAccount.financialAccountCan'tUpdate");
        }

        String financialAccountStructure = null;
        if (financialAccountStructureDto.getId() != null) {
            financialAccountStructure = "financialAccountStructure";
        } else {
            financialAccountStructureDto.setId(0L);
        }
        if (financialAccountStructureDto.getFlgPermanentStatus().equals(true)) {
            List<Long> financialAccountStructureCoding = financialAccountStructureRepository.getFinancialAccountStructureByCodingAndStructureId(financialAccountStructureDto.getFinancialCodingTypeId(), financialAccountStructure, financialAccountStructureDto.getId());
            if (financialAccountStructureCoding.size() != 0) {
                throw new RuleException("fin.financialAccountStructureCoding.ruleException.save");
            }
        }
        if (illigalChange == 1) {
            List<Long> financialAccountStructureCoding = financialAccountStructureRepository.getFinancialAccountStructureByCodingAndFlgShow(financialAccountStructureDto.getFinancialCodingTypeId(), financialAccountStructure, financialAccountStructureDto.getId());
            if (financialAccountStructureCoding.size() != 0) {
                throw new RuleException("fin.financialAccountStructureCodingFlag.ruleException.save");
            }
        }
        if (financialDocument.size() != 0 && illigalChange == 1) {
            throw new RuleException("fin.updateFinancialAccountStructure.checkFinancialDocument");
        }

        financialAccountStructureFlg.setId(financialAccountStructureDto.getId());
        financialAccountStructureFlg.setDescription(financialAccountStructureDto.getDescription());
        financialAccountStructureFlg.setSequence(financialAccountStructureDto.getSequence());
        financialAccountStructureFlg.setDigitCount(financialAccountStructureDto.getDigitCount());
        financialAccountStructureFlg.setSumDigit(financialAccountStructureDto.getSumDigit());
        financialAccountStructureFlg.setColor(financialAccountStructureDto.getColor());
        financialAccountStructureFlg.setFinancialCodingType(financialCodingTypeRepository.findById(financialAccountStructureDto.getFinancialCodingTypeId()).orElseThrow(() -> new RuleException("fin.financialCoding.notFoundId")));
        financialAccountStructureFlg.setFlgShowInAcc(financialAccountStructureDto.getFlgShowInAcc());
        financialAccountStructureFlg.setFlgPermanentStatus(financialAccountStructureDto.getFlgPermanentStatus());
        financialAccountStructureFlg = financialAccountStructureRepository.save(financialAccountStructureFlg);
        return convertFinancialAccountStructureToDto(financialAccountStructureFlg);
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public Boolean deleteFinancialAccountStructureById(Long financialAccountStructureId) {
        Long countFinancialAccount = financialAccountRepository.findByFinancialAccountStructureId(financialAccountStructureId);
        FinancialAccountStructure financialAccountStructure;
        if (countFinancialAccount != null) {
            throw new RuleException("fin.financialAccount.financialAccountCan'tDeleted");
        } else {
            financialAccountStructure = financialAccountStructureRepository.findById(financialAccountStructureId).orElseThrow(() -> new RuleException("fin.ruleException.notFoundId"));
            List<Object[]> byFinancialAccountStructureIdForDelete = financialAccountRepository.findByFinancialAccountStructureIdForDelete(financialAccountStructureId);
            if (byFinancialAccountStructureIdForDelete.isEmpty()) {
                financialAccountStructureRepository.deleteById(financialAccountStructure.getId());
                return true;
            } else {
                throw new RuleException("fin.financialAccountStructure.check.for.delete");
            }
        }
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public Long getFinancialAccountStructureByFinancialCodingTypeAndFinancialAccountStructure(FinancialAccountStructureRequest financialAccountStructureRequest) {
        String financialAccountStructure = null;
        if (financialAccountStructureRequest.getFinancialAccountStructureId() != null) {
            financialAccountStructure = "financialAccountStructure";
        } else {
            financialAccountStructureRequest.setFinancialAccountStructureId(0L);
        }
        return financialAccountStructureRepository.findByFinancialCodingTypeAndFinancialAccountStructure(financialAccountStructureRequest.getFinancialCodingTypeId(), financialAccountStructureRequest.getFinancialAccountStructureId(), financialAccountStructure);
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public FinancialAccountStructureNewResponse getFinancialAccountStructureByCodingAndParentAndId(FinancialAccountStructureNewRequest financialAccountStructureNewRequest) {
        String financialAccountStructure = checkFinancialAccountStructureIsNull(financialAccountStructureNewRequest);

        checkFinancialAccountStructure(financialAccountStructureNewRequest);
        FinancialAccountStructureNewResponse financialAccountStructureNewResponse = new FinancialAccountStructureNewResponse();
        checkFinancialAccountStructure(financialAccountStructureNewRequest);
        List<Long> financialAccountStructureCoding = financialAccountStructureRepository.getFinancialAccountStructureByCoding(financialAccountStructureNewRequest.getFinancialCodingTypeId());
        if (financialAccountStructureCoding.size() == 0) {
            throw new RuleException("fin.financialAccountStructure.flg.getPermanentStatus");
        }

        Long financialAccountStructureFlg = financialAccountStructureRepository.findByFinancialCodingTypeAndFinancialAccountStructureId(financialAccountStructureNewRequest.getFinancialCodingTypeId(), financialAccountStructure, financialAccountStructureNewRequest.getFinancialAccountStructureId());

        if (financialAccountStructureFlg == 1) {
            financialAccountStructureNewResponse.setFlgPermanentStatus(1L);
            financialAccountStructureNewResponse.setAccountPermanentStatusId(null);
            return financialAccountStructureNewResponse;
        } else if (financialAccountStructureFlg == 0 && financialAccountStructureNewRequest.getFinancialAccountParentId() == null) {
            throw new RuleException("fin.financialAccountStructureFlg.getPermanentStatus");
        } else {
            List<Object[]> financialAccount = financialAccountRepository.findByFinancialAccountByParentId(financialAccountStructureNewRequest.getFinancialAccountParentId());
            AtomicReference<Long> accountPermanentStatusId = new AtomicReference<>();
            AtomicReference<String> accountPermanentStatusDescription = new AtomicReference<>();
            financialAccount.stream().filter(objects -> Long.parseLong(objects[2].toString()) == 1L).findAny().ifPresent((Object[] objects) -> {
                        accountPermanentStatusDescription.set(objects[3] == null ? null : objects[3].toString());
                        accountPermanentStatusId.set(objects[1] == null ? null : Long.parseLong(objects[1].toString()));
                    }
            );
            if (accountPermanentStatusId.get() != null) {
                financialAccountStructureNewResponse.setFlgPermanentStatus(0L);
                financialAccountStructureNewResponse.setAccountPermanentStatusId(accountPermanentStatusId.get());
                financialAccountStructureNewResponse.setAccountPermanentStatusDescription(accountPermanentStatusDescription.get());
                return financialAccountStructureNewResponse;
            } else {
                throw new RuleException("fin.financialAccountStructureFlg.getPermanentStatus");

            }
        }
    }

    private String checkFinancialAccountStructureIsNull(FinancialAccountStructureNewRequest financialAccountStructureNewRequest) {
        String financialAccountStructure = null;
        if (financialAccountStructureNewRequest.getFinancialAccountStructureId() != null) {
            financialAccountStructure = "financialAccountStructure";
        } else {
            financialAccountStructureNewRequest.setFinancialAccountStructureId(0L);
        }
        return financialAccountStructure;
    }

    private void checkFinancialAccountStructure(FinancialAccountStructureNewRequest financialAccountStructureNewRequest) {
        FinancialAccountStructureNewResponse financialAccountStructureNewResponse = new FinancialAccountStructureNewResponse();
        if (financialAccountStructureNewRequest.getFlgEditMode().equals(true)) {
            Long financialAccountStructureId = financialAccountStructureRepository.getFinancialAccountStructureById(financialAccountStructureNewRequest.getFinancialAccountStructureId());

            if (financialAccountStructureId != null) {
                financialAccountStructureNewResponse.setFlgPermanentStatus(1L);
                financialAccountStructureNewResponse.setAccountPermanentStatusId(null);
            }
        }
    }

    @Override
    @Transactional
    public List<FinancialAccountStructureDtoResponse> getSumDigitAndSequence(FinancialAccountStructureDtoRequest financialAccountStructureDtoRequest) {
        List<Object[]> financialAccountStructureList = financialAccountStructureRepository.findByFinancialCodingType(financialAccountStructureDtoRequest.getFinancialCodingTypeId());
        if (financialAccountStructureList.isEmpty()) {
            List<FinancialAccountStructureDtoResponse> result = new ArrayList<>();

            result.add(FinancialAccountStructureDtoResponse.builder()
                    .sequence(0L)
                    .sumDigit(0L)
                    .build());
            return result;
        }
        return financialAccountStructureList.stream().map(e -> FinancialAccountStructureDtoResponse.builder()
                .sequence(Long.parseLong(e[0].toString()))
                .sumDigit(Long.parseLong(e[1].toString()))
                .build()).collect(Collectors.toList());
    }

    private FinancialAccountStructureDto convertFinancialAccountStructureToDto(FinancialAccountStructure
                                                                                       financialAccountStructure) {
        return FinancialAccountStructureDto.builder().description(financialAccountStructure.getDescription())
                .sequence(financialAccountStructure.getSequence())
                .digitCount(financialAccountStructure.getDigitCount())
                .sumDigit(financialAccountStructure.getSumDigit())
                .color(financialAccountStructure.getColor())
                .financialCodingTypeId(financialAccountStructure.getFinancialCodingType().getId())
                .id(financialAccountStructure.getId())
                .build();

    }
}
