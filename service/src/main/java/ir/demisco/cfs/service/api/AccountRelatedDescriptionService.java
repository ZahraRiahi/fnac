package ir.demisco.cfs.service.api;

import ir.demisco.cfs.model.dto.request.AccountRelatedDescriptionRequest;
import ir.demisco.cfs.model.dto.response.AccountRelatedDescriptionDto;
import ir.demisco.cfs.model.entity.AccountRelatedDescription;

public interface AccountRelatedDescriptionService {

    boolean deleteAccountRelatedDescriptionById(Long accountRelatedDescriptionId);

    AccountRelatedDescriptionDto save(AccountRelatedDescriptionRequest accountRelatedDescriptionRequest);

    AccountRelatedDescriptionDto convertAccountRelatedDescriptionDto(AccountRelatedDescription accountRelatedDescription);
}
