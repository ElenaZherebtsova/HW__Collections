package pro.sky.HW_Repeat_Collections.EmployeeController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.HW_Repeat_Collections.DTO.Employee;
import pro.sky.HW_Repeat_Collections.Service.DepartmentService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/all")
    public Map<Integer, List<Employee>> getAll() {
        return departmentService.getAll();
    }

    @GetMapping(value = "/all", params = "department")
    public Collection<Employee> getAllByDepartment(@RequestParam int department) {
        return departmentService.getAllByDepartment(department);
    }

    @GetMapping("/min-salary")
    public Employee getMinSalary(@RequestParam int department) {
        return departmentService.minSalaryEmpl(department);
    }

    @GetMapping("/max-salary")
    public Employee getMaxSalary(@RequestParam int department) {
        return departmentService.maxSalaryEmpl(department);
    }
}
