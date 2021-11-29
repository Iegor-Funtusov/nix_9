package ua.com.alevel.persistence.entity;

import ua.com.alevel.persistence.type.DepartmentType;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "departments")
public class Department extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "department_type")
    private DepartmentType departmentType;

    private String name;

    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE }, fetch = FetchType.LAZY)
    @JoinTable(
            name = "dep_emp",
            joinColumns = @JoinColumn(name = "dep_id"),
            inverseJoinColumns = @JoinColumn(name = "emp_id"))
    private Set<Employee> employees;

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

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}
