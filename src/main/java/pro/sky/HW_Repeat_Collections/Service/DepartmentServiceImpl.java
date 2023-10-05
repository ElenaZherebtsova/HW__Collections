package pro.sky.HW_Repeat_Collections.Service;

import pro.sky.HW_Repeat_Collections.DTO.Employee;
import org.springframework.stereotype.Service;
import pro.sky.HW_Repeat_Collections.Exceptions.EmployeeNotFoundException;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service

public class DepartmentServiceImpl implements DepartmentService {
    private EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
// Ищем сотрудника с максимаьной ЗП на основании отдела.
// Нам надо вернуть сотрудника, которого мы получаем из метода
// распечатки все имеющихся сотрудников.
// Затем через стрим начинаем фильтровать тех сотрудников, у которых нужный нам отдел.
// Затем среди всех отфильтрованных сотрудников выбираем с максимальной ЗП.
// Если вообще никого не найдено по заданному отделу -- выбрасываем сообщение об ошибке.
    @Override
    public Employee maxSalaryEmpl(int department) {

        return employeeService.printAll().stream()
                .filter(emp -> emp.getDepartment() == department)
                .max(Comparator.comparingDouble(empl -> empl.getSalary()))
                .orElseThrow(() -> new EmployeeNotFoundException());
    }
// аналогично ищем сотрудника с минимальной зп.
    @Override
    public Employee minSalaryEmpl(int department) {

        return employeeService.printAll().stream()
                .filter(emp -> emp.getDepartment() == department)
                .min(Comparator.comparingDouble(empl -> empl.getSalary()))
                .orElseThrow(() -> new EmployeeNotFoundException());
    }
// Получаем список все сотрудников из отдела.
// Фильтруем сотрудников по отделу, потом "собираем" их в лист.
    @Override
    public Collection<Employee> getAllByDepartment(int department) {

        return employeeService.printAll().stream()
                .filter(emp -> emp.getDepartment() == department)
                .collect(Collectors.toList());
    }
// Выводим всех сотрудников и группируем их на основании отделов.
    @Override
    public Map<Integer, List<Employee>> getAll() {

        return employeeService.printAll().stream()
                .collect(Collectors.groupingBy(emp -> emp.getDepartment()));
    }
}
