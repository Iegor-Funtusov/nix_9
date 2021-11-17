package ua.com.alevel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ua.com.alevel.dto.department.DepartmentRequestDto;
import ua.com.alevel.dto.department.DepartmentResponseDto;
import ua.com.alevel.facade.DepartmentFacade;
import ua.com.alevel.type.DepartmentType;

import java.util.List;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentFacade departmentFacade;

    public DepartmentController(DepartmentFacade departmentFacade) {
        this.departmentFacade = departmentFacade;
    }

    @GetMapping
    public String findAll(Model model) {
        List<DepartmentResponseDto> departments = departmentFacade.findAll();
        model.addAttribute("departments", departments);
        return "pages/department/department_all";
    }

    @GetMapping("/new")
    public String redirectToNewDepartmentPage(Model model) {
        model.addAttribute("department", new DepartmentRequestDto());
        model.addAttribute("types", DepartmentType.values());
        return "pages/department/department_new";
    }

    @PostMapping("/new")
    public String createNewDepartment(@ModelAttribute("department") DepartmentRequestDto departmentRequestDto) {
        departmentFacade.create(departmentRequestDto);
        return "redirect:/departments";
    }

    @GetMapping("/details/{id}")
    public String findById(@PathVariable Long id, Model model) {
        model.addAttribute("department", departmentFacade.findById(id));
        return "pages/department/department_details";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        departmentFacade.delete(id);
        return "redirect:/departments";
    }
}
