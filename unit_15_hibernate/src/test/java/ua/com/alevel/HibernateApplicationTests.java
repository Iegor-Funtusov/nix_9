package ua.com.alevel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.com.alevel.persistence.dao.DepartmentDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Department;
import ua.com.alevel.persistence.type.DepartmentType;

@SpringBootTest
class HibernateApplicationTests {

    @Autowired
    private DepartmentDao departmentDao;

    private static final DataTableRequest dataTableRequest = new DataTableRequest();

    @BeforeAll
    static void init() {
        dataTableRequest.setOrder("asc");
        dataTableRequest.setSort("id");
        dataTableRequest.setPage(0);
        dataTableRequest.setSize(10);
    }

    @Test
    void contextLoads() { }

    @Test
    void createDepartment() {
        Department department = new Department();
        department.setDepartmentType(DepartmentType.DEV_OPS);
        department.setName("name");
        departmentDao.create(department);

        DataTableResponse<Department> dataTableResponse = departmentDao.findAll(dataTableRequest);
        Assertions.assertEquals(dataTableResponse.getItems().size(), 1);
    }
}
