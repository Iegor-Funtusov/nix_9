package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.persistence.dao.DepartmentDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Department;
import ua.com.alevel.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentDao departmentDao;

    public DepartmentServiceImpl(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Override
    public void create(Department entity) {
        departmentDao.create(entity);
    }

    @Override
    public void update(Department entity) {
        if (!departmentDao.existById(entity.getId())) {
            throw new EntityNotFoundException("department not found");
        }
        departmentDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        if (!departmentDao.existById(id)) {
            throw new EntityNotFoundException("department not found");
        }
        departmentDao.delete(id);
    }

    @Override
    public Department findById(Long id) {
        if (!departmentDao.existById(id)) {
            throw new EntityNotFoundException("department not found");
        }
        return departmentDao.findById(id);
    }

    @Override
    public DataTableResponse<Department> findAll(DataTableRequest request) {
        return departmentDao.findAll(request);
    }
}
