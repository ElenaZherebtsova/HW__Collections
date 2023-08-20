package pro.sky.HW_Repeat_Collections;

import DTO.Employee;
import Exceptions.EmployeeAlreadyAddedException;
import Exceptions.EmployeeNotFoundException;
import Exceptions.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    // Создаем лист с сотрудниками и объявляем, что их будет строго три.
    private List<Employee> employees;
    private static final int EMPLOYEE_SIZE = 3;

    // Объявляем тип листа -- ArrayList
    public EmployeeServiceImpl() {
        this.employees = new ArrayList<>();
    }

    // Метод, который добавляет нового сотрудника.
   @Override
    public Employee addEmployee(String firstName, String lastName) {
//        Если превышен размер листа, то выбрасываем ошибку о переполненном хранилище.
//        Если поймается ошибка, сработает if, если нет -- код пойдет дальше.
//        После создания сотрудника проверяем, и если он уже есть -- выбрасываем ошибку.

        if (employees.size() == EMPLOYEE_SIZE) {
            throw new EmployeeStorageIsFullException();
        }
        Employee employee = new Employee(firstName, lastName);
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.add(employee);
       return employee;
   }

    // Метод, который удаляет сотрудника.
    @Override
    public Employee deleteEmploye(String firstName, String lastName) {

        Employee employee = new Employee(firstName, lastName);
// Проверяем наличие сотрудника, и если сотрудника не получилось удалить,
// тк он "не содержится", то выбрасываем ошибку.
        if (!employees.remove(employee)) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    // Метод, который ищет и выдает данные сотрудника
    @Override
    public Employee getEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
//       Если сотрудник "не содержится" в коллекции, то выбрасываем ошибку.
//       Если найден -- возвращаем данные этого сотрудника.
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException();
        }

        return employee;
    }
}
