package ua.com.alevel.view.dto.response;

import org.apache.commons.collections4.CollectionUtils;
import ua.com.alevel.persistence.entity.Department;
import ua.com.alevel.persistence.type.DepartmentType;

public class DepartmentSimpleResponseDto extends ResponseDto {

    private DepartmentType departmentType;
    private String name;
    private long employeesCount;

    public DepartmentSimpleResponseDto(Department department) {
        setId(department.getId());
        this.departmentType = department.getDepartmentType();
        this.name = department.getName();
        if (CollectionUtils.isNotEmpty(department.getEmployees())) {
            this.employeesCount = department.getEmployees().size();
        }
    }

    public DepartmentType getDepartmentType() {
        return departmentType;
    }

    public void setDepartmentType(DepartmentType departmentType) {
        this.departmentType = departmentType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getEmployeesCount() {
        return employeesCount;
    }

    public void setEmployeesCount(long employeesCount) {
        this.employeesCount = employeesCount;
    }
}
