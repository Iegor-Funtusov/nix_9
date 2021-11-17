package ua.com.alevel.entity;

import ua.com.alevel.type.DepartmentType;

import javax.persistence.*;

@Entity
@Table(name = "departments")
public class Department extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "department_type", nullable = false, updatable = false)
    private DepartmentType departmentType;

    @Column(nullable = false, unique = true)
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
