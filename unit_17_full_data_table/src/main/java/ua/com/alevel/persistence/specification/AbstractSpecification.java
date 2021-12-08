package ua.com.alevel.persistence.specification;

import org.springframework.data.jpa.domain.Specification;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.entity.BaseEntity;

public interface AbstractSpecification<E extends BaseEntity> {

    Specification<E> generateCriteriaPredicate(DataTableRequest request, Class<E> entityClass);
}
