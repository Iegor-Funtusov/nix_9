package ua.com.alevel.dto.employee;

import ua.com.alevel.dto.ResponseDto;
import ua.com.alevel.entity.Department;
import ua.com.alevel.entity.Employee;

public class EmployeeResponseDto extends ResponseDto {

    private String firstName;
    private String lastName;
    private Integer age;
    private Department department;

    public EmployeeResponseDto() { }

    public EmployeeResponseDto(Employee employee) {
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.age = employee.getAge();
        if (employee.getDepartment() != null) {
            this.department = employee.getDepartment();
        }
        super.setId(employee.getId());
        super.setCreated(employee.getCreated());
        super.setUpdated(employee.getUpdated());
    }
}
