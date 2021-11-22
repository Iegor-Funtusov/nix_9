package ua.com.alevel.entity;

import ua.com.alevel.type.DepartmentType;

public class Department extends BaseEntity {

    private DepartmentType departmentType;
    private String name;

    public Department() {
        super();
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
}
