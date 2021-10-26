package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.request.AccountDefaultValueUpdateRequest;
import ir.demisco.cfs.model.dto.response.AccountDefaultValueOutPutResponse;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DefaultAccountDefaultValueTest {

    @Mock
     private DefaultAccountDefaultValue defaultAccountDefaultValue;
    @Test
    public void save() {

    }

    @Test
    public void updateAccountDefaultValueByIdSuccess() {
        AccountDefaultValueUpdateRequest t=new AccountDefaultValueUpdateRequest();
        List<AccountDefaultValueUpdateRequest> list=new ArrayList<>();
        list.add(new AccountDefaultValueUpdateRequest());
        t.setCentricAccountId(1L);
        t.setAccountDefaultValueUpdateDtos(list);

        List<AccountDefaultValueOutPutResponse> accountDefaultValueOutPutResponses = defaultAccountDefaultValue.updateAccountDefaultValueById(t);
        assertNotNull(accountDefaultValueOutPutResponses);
        assertEquals(java.util.Optional.ofNullable(accountDefaultValueOutPutResponses.get(0).getCentricAccountId()),10L);

    }

    @org.junit.Test
    public void updateAccountDefaultValueByIdError() {
        AccountDefaultValueUpdateRequest t=new AccountDefaultValueUpdateRequest();
        List<AccountDefaultValueUpdateRequest> list=new ArrayList<>();
        list.add(new AccountDefaultValueUpdateRequest());
        t.setCentricAccountId(10L);
        t.setAccountDefaultValueUpdateDtos(list);

        List<AccountDefaultValueOutPutResponse> accountDefaultValueOutPutResponses = defaultAccountDefaultValue.updateAccountDefaultValueById(t);
        assertNotNull(accountDefaultValueOutPutResponses);
        assertEquals(java.util.Optional.ofNullable(accountDefaultValueOutPutResponses.get(0).getCentricAccountId()),10L);
        assertEquals(java.util.Optional.ofNullable(accountDefaultValueOutPutResponses.get(0).getCentricAccountCode()),"Test");
        assertEquals(java.util.Optional.ofNullable(accountDefaultValueOutPutResponses.get(0).getAccountRelationTypeId()),3L);

    }
}