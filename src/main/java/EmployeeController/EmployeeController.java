package EmployeeController;

import DTO.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.HW_Repeat_Collections.EmployeeService;

import java.util.Collection;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {

        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee addEmployee(@RequestParam String firstName,
                                @RequestParam String lastName) {
        return employeeService.addEmployee(firstName, lastName);

    }

    @GetMapping("/remove")
    public Employee deleteEmployee(@RequestParam String firstName,
                                   @RequestParam String lastName) {
        return employeeService.deleteEmployee(firstName, lastName);
    }
    @GetMapping("/get")
    public Employee getEmployee(@RequestParam String firstName,
                                   @RequestParam String lastName) {
        return employeeService.getEmployee(firstName, lastName);
    }

    @GetMapping
    public Collection<Employee> printAll () {
        return employeeService.printAll();
    }

}
