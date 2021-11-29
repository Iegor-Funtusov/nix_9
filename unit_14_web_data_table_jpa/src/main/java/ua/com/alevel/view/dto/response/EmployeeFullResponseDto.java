package ua.com.alevel.view.dto.response;

import org.apache.commons.collections4.CollectionUtils;
import ua.com.alevel.persistence.entity.Employee;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class EmployeeFullResponseDto extends EmployeeSimpleResponseDto {

    private Set<DepartmentSimpleResponseDto> departments;

    public EmployeeFullResponseDto(Employee employee) {
        super(employee);
        setCreated(employee.getCreated());
        setUpdated(employee.getUpdated());
        setVisible(employee.getVisible());
        this.departments = new HashSet<>();
        if (CollectionUtils.isNotEmpty(employee.getDepartments())) {
            this.departments = employee.getDepartments()
                    .stream()
                    .map(DepartmentSimpleResponseDto::new)
                    .collect(Collectors.toSet());
        }
    }

    public Set<DepartmentSimpleResponseDto> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<DepartmentSimpleResponseDto> departments) {
        this.departments = departments;
    }
}
