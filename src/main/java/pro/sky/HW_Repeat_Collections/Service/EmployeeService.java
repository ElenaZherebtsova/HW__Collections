package pro.sky.HW_Repeat_Collections.Service;

import pro.sky.HW_Repeat_Collections.DTO.Employee;

import java.util.Collection;

public interface EmployeeService {
    // Метод, который добавляет нового сотрудника.
    Employee addEmployee(String firstName, String lastName, int department, double salary);

    // Метод, который удаляет сотрудника.
    Employee deleteEmployee(String firstName, String lastName, int department, int salary);

    // Метод, который ищет и выдает данные сотрудника
    Employee getEmployee(String firstName, String lastName, int department, double salary);

    // Выводим "значения" всех сотрудников из мапы.
    Collection<Employee> printAll();
}
