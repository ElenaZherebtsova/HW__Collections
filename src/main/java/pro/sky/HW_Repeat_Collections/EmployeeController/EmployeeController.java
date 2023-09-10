package pro.sky.HW_Repeat_Collections.EmployeeController;

import pro.sky.HW_Repeat_Collections.DTO.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.HW_Repeat_Collections.Service.EmployeeService;
import pro.sky.HW_Repeat_Collections.Service.NameValidator;

import java.util.Collection;
import java.util.NavigableMap;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {

        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee addEmployee(@RequestParam String firstName,
                                @RequestParam String lastName,
                                @RequestParam int department,
                                @RequestParam double salary) {
        NameValidator.validateName(firstName, lastName);
       // NameValidator.validateName(lastName);
        return employeeService.addEmployee(firstName, lastName, department, salary);

    }

    @GetMapping("/remove")
    public Employee deleteEmployee(@RequestParam String firstName,
                                   @RequestParam String lastName,
                                   @RequestParam int department,
                                   @RequestParam double salary) {
        NameValidator.validateName(firstName, lastName);

        return employeeService.deleteEmployee(firstName, lastName, department, salary);
    }
    @GetMapping("/get")
    public Employee getEmployee(@RequestParam String firstName,
                                @RequestParam String lastName,
                                @RequestParam int department,
                                @RequestParam double salary) {
        NameValidator.validateName(firstName, lastName);

        return employeeService.getEmployee(firstName, lastName, department, salary);
    }

    @GetMapping
    public Collection<Employee> printAll () {
        return employeeService.printAll();
    }

}
