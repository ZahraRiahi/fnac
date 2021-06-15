package ir.demisco.cfs.service.api;

import ir.demisco.cfs.model.dto.request.CentricPersonRoleRequest;
import ir.demisco.cfs.model.dto.response.CentricPersonRoleResponce;

import java.util.List;

public interface CentricPersonRoleService {

    List<CentricPersonRoleResponce> getCentricPersonRoleByOrganAndPersonRoleTypeAndPersonId(CentricPersonRoleRequest centricPersonRoleRequest);
}
