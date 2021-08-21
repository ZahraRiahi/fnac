package ir.demisco.cfs.service.api;

import ir.demisco.cfs.model.dto.request.AccountRelatedDescriptionRequest;
import ir.demisco.cfs.model.dto.response.AccountRelatedDescriptionDto;

public interface AccountRelatedDescriptionService {

    boolean deleteAccountRelatedDescriptionById(Long accountRelatedDescriptionId);

    AccountRelatedDescriptionDto save(AccountRelatedDescriptionRequest accountRelatedDescriptionRequest);
}
