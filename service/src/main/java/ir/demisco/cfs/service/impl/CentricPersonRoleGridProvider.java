package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.entity.CentricPersonRole;
import ir.demisco.cloud.core.middle.service.business.api.core.GridDataProvider;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Selection;

@Component
public class CentricPersonRoleGridProvider implements GridDataProvider {

    @Override
    public Class<?> getRootEntityClass() {
        return CentricPersonRole.class;
    }

    @Override
    public Predicate getCustomRestriction(FilterContext filterContext) {
        return null;
    }

    @Override
    public Selection<?> getCustomSelection(FilterContext filterContext) {
        CriteriaBuilder criteriaBuilder = filterContext.getCriteriaBuilder();
        return criteriaBuilder.array(
                filterContext.getPath("id"),
                filterContext.getPath("name"),
                filterContext.getPath("code")
                );
    }


}
