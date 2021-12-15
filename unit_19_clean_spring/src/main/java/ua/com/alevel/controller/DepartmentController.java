package ua.com.alevel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.alevel.dao.DepartmentDao;
import ua.com.alevel.datatable.DataTableRequest;
import ua.com.alevel.entity.Department;
import ua.com.alevel.type.DepartmentType;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentDao departmentDao;

    public DepartmentController(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @GetMapping
    public String all(Model model) {
        DataTableRequest dataTableRequest = new DataTableRequest();
        dataTableRequest.setSort("id");
        dataTableRequest.setOrder("desc");
        dataTableRequest.setPage(1);
        dataTableRequest.setSize(100);

        List<Department> departments = departmentDao.findAll(dataTableRequest).getItems();
        model.addAttribute("departments", departments);
        return "departments/all";
    }

    @GetMapping("/new")
    public String newDep(Model model) {
        model.addAttribute("dep", new Department());
        model.addAttribute("types", DepartmentType.values());
        return "departments/new";
    }

    @PostMapping("/new")
    public String newDep(@ModelAttribute Department department) {
        departmentDao.create(department);
        return "redirect:/departments";
    }
}
