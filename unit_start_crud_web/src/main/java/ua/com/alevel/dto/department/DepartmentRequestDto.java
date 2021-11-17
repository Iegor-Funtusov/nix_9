package ua.com.alevel.dto.department;

import ua.com.alevel.dto.RequestDto;
import ua.com.alevel.type.DepartmentType;

public class DepartmentRequestDto extends RequestDto {

    private DepartmentType departmentType;
    private String name;

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
