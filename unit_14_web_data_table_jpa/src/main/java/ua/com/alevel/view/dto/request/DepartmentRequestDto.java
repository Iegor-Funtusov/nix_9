package ua.com.alevel.view.dto.request;

import ua.com.alevel.persistence.type.DepartmentType;

import java.util.List;

public class DepartmentRequestDto extends RequestDto {

    private DepartmentType departmentType;
    private String name;
    private List<Long> employeesIds;

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

    public List<Long> getEmployeesIds() {
        return employeesIds;
    }

    public void setEmployeesIds(List<Long> employeesIds) {
        this.employeesIds = employeesIds;
    }
}
