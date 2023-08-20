package pro.sky.HW_Repeat_Collections;

import DTO.Employee;

public interface EmployeeService {
    // Метод, который добавляет нового сотрудника.
    Employee addEmployee(String firstName, String lastName);

    // Метод, который удаляет сотрудника.
    Employee deleteEmploye(String firstName, String lastName);

    // Метод, который ищет и выдает данные сотрудника
    Employee getEmployee(String firstName, String lastName);
}
