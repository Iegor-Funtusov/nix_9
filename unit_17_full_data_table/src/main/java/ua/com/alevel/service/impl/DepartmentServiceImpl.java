package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Department;
import ua.com.alevel.persistence.entitydto.DepEmp;
import ua.com.alevel.persistence.repository.DepartmentRepository;
import ua.com.alevel.persistence.type.DepartmentType;
import ua.com.alevel.service.DepartmentService;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final CrudRepositoryHelper<Department, DepartmentRepository> repositoryHelper;
    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(
            CrudRepositoryHelper<Department, DepartmentRepository> repositoryHelper,
            DepartmentRepository departmentRepository) {
        this.repositoryHelper = repositoryHelper;
        this.departmentRepository = departmentRepository;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.NEVER)
    public void create(Department entity) {
        repositoryHelper.create(departmentRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void update(Department entity) {
        repositoryHelper.update(departmentRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void delete(Long id) {
        repositoryHelper.delete(departmentRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Department> findById(Long id) {
        return repositoryHelper.findById(departmentRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<Department> findAll(DataTableRequest dataTableRequest) {
        return repositoryHelper.findAll(departmentRepository, dataTableRequest, Department.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Department> findByEmployees(Set<Long> employees) {
        return departmentRepository.findByEmployees(employees);
    }

    @Override
    public Set<Department> findByEmployeesIds(Set<Long> employees) {
        return departmentRepository.findByEmployeesIds(employees);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Department> findByDepartmentTypeOrCreatedBetween(DepartmentType departmentType, Date start, Date end) {
        return departmentRepository.findByDepartmentTypeOrCreatedBetween(departmentType, start, end);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Department> findByVisibleTrue() {
        return departmentRepository.findByVisibleTrue();
    }

    @Override
    @Transactional(readOnly = true)
    public Set<DepEmp> findCountByJSAndJava() {
        return departmentRepository.findCountByJSAndJava();
    }
}
