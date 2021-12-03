package ua.com.alevel.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ua.com.alevel.persistence.entity.BaseEntity;

@NoRepositoryBean
public interface AbstractRepository<E extends BaseEntity> extends JpaRepository<E, Long> { }
