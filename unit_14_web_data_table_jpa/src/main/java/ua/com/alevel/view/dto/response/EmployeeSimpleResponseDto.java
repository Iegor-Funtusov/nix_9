package ua.com.alevel.view.dto.response;

import org.apache.commons.collections4.CollectionUtils;
import ua.com.alevel.persistence.entity.Employee;

public class EmployeeSimpleResponseDto extends ResponseDto {

    private String firstName;
    private String lastName;
    private int age;
    private long departmentsCount;

    public EmployeeSimpleResponseDto(Employee employee) {
        setId(employee.getId());
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.age = employee.getAge();
        if (CollectionUtils.isNotEmpty(employee.getDepartments())) {
            departmentsCount = employee.getDepartments().size();
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getDepartmentsCount() {
        return departmentsCount;
    }

    public void setDepartmentsCount(long departmentsCount) {
        this.departmentsCount = departmentsCount;
    }
}
