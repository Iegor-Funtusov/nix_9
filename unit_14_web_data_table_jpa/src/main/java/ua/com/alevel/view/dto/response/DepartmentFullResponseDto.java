package ua.com.alevel.view.dto.response;

import org.apache.commons.collections4.CollectionUtils;
import ua.com.alevel.persistence.entity.Department;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class DepartmentFullResponseDto extends DepartmentSimpleResponseDto {

    private Set<EmployeeSimpleResponseDto> employees;

    public DepartmentFullResponseDto(Department department) {
        super(department);
        setCreated(department.getCreated());
        setUpdated(department.getUpdated());
        setVisible(department.getVisible());
        employees = new HashSet<>();
        if (CollectionUtils.isNotEmpty(department.getEmployees())) {
            employees = department.getEmployees()
                    .stream()
                    .map(EmployeeSimpleResponseDto::new)
                    .collect(Collectors.toSet());
        }
    }

    public Set<EmployeeSimpleResponseDto> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<EmployeeSimpleResponseDto> employees) {
        this.employees = employees;
    }
}
