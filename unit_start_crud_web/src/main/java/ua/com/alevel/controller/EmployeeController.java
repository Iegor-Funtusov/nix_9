package ua.com.alevel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.dto.employee.EmployeeRequestDto;
import ua.com.alevel.dto.employee.EmployeeResponseDto;
import ua.com.alevel.facade.EmployeeFacade;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeFacade employeeFacade;

    public EmployeeController(EmployeeFacade employeeFacade) {
        this.employeeFacade = employeeFacade;
    }

    @GetMapping
    public String findAll(Model model) {
        List<EmployeeResponseDto> employees = employeeFacade.findAll();
        model.addAttribute("employees", employees);
        return "pages/employee/employees_all";
    }

    @GetMapping("/departments/{id}")
    public String findAll(Model model, @PathVariable Long id) {
        List<EmployeeResponseDto> employees = employeeFacade.findAllByDepartment(id);
        model.addAttribute("employees", employees);
        return "pages/employee/employees_all";
    }

    @GetMapping("/new/{departmentId}")
    public String redirectToNewEmployeePage(@PathVariable Long departmentId, Model model) {
        System.out.println("departmentId = " + departmentId);
        EmployeeRequestDto dto = new EmployeeRequestDto();
        dto.setDepartmentId(departmentId);
        model.addAttribute("employee", dto);
        model.addAttribute("departmentId", departmentId);
        return "pages/employee/employees_new";
    }

    @PostMapping("/new")
    public String createNewEmployee(@ModelAttribute("employee") EmployeeRequestDto dto) {
        employeeFacade.create(dto);
        return "redirect:/employees";
    }

    @GetMapping("/details/{id}")
    public String findById(@PathVariable Long id, Model model) {
        model.addAttribute("employee", employeeFacade.findById(id));
        return "pages/employee/employees_details";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        employeeFacade.delete(id);
        return "redirect:/employees";
    }
}
