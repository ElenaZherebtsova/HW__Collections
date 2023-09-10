package pro.sky.HW_Repeat_Collections.Service;

import pro.sky.HW_Repeat_Collections.DTO.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface DepartmentService {

    Employee maxSalaryEmpl(int department);

    Employee minSalaryEmpl(int department);

    Collection<Employee> getAllByDepartment(int department);

    Map<Integer, List<Employee>> getAll();

}
