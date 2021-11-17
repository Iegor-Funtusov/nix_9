package ua.com.alevel.dto.department;

import ua.com.alevel.dto.ResponseDto;
import ua.com.alevel.entity.Department;
import ua.com.alevel.type.DepartmentType;

public class DepartmentResponseDto extends ResponseDto {

    private DepartmentType departmentType;
    private String name;

    public DepartmentResponseDto() { }

    public DepartmentResponseDto(Department department) {
        this.departmentType = department.getDepartmentType();
        this.name = department.getName();
        super.setId(department.getId());
        super.setCreated(department.getCreated());
        super.setUpdated(department.getUpdated());
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
