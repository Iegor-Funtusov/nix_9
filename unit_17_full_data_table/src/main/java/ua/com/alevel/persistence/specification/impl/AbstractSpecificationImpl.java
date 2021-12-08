package ua.com.alevel.persistence.specification.impl;

import org.springframework.data.jpa.domain.Specification;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.specification.AbstractSpecification;
import ua.com.alevel.util.SpecificationUtil;

import javax.persistence.criteria.Predicate;
import java.util.List;

public class AbstractSpecificationImpl<E extends BaseEntity> implements AbstractSpecification<E> {

    @Override
    public Specification<E> generateCriteriaPredicate(DataTableRequest request, Class<E> entityClass) {
        return (Specification<E>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = SpecificationUtil.generateSpecificationPredicates(request, entityClass, root, criteriaBuilder);
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
