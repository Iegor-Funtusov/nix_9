package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Employee;
import ua.com.alevel.persistence.repository.EmployeeRepository;
import ua.com.alevel.service.EmployeeService;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final CrudRepositoryHelper<Employee, EmployeeRepository> repositoryHelper;
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(
            CrudRepositoryHelper<Employee, EmployeeRepository> repositoryHelper,
            EmployeeRepository employeeRepository) {
        this.repositoryHelper = repositoryHelper;
        this.employeeRepository = employeeRepository;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void create(Employee entity) {
        repositoryHelper.create(employeeRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void update(Employee entity) {
        repositoryHelper.update(employeeRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void delete(Long id) {
        repositoryHelper.delete(employeeRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Employee> findById(Long id) {
        return repositoryHelper.findById(employeeRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<Employee> findAll(DataTableRequest dataTableRequest) {
        return repositoryHelper.findAll(employeeRepository, dataTableRequest);
    }
}
