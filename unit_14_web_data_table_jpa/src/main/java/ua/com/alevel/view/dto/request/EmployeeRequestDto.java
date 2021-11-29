package ua.com.alevel.view.dto.request;

import java.util.List;

public class EmployeeRequestDto extends RequestDto {

    private String firstName;
    private String lastName;
    private int age;
    private List<Long> departmentsIds;

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

    public List<Long> getDepartmentsIds() {
        return departmentsIds;
    }

    public void setDepartmentsIds(List<Long> departmentsIds) {
        this.departmentsIds = departmentsIds;
    }
}
