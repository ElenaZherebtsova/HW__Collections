package pro.sky.HW_Repeat_Collections.Service;

import org.apache.commons.lang3.StringUtils;
import pro.sky.HW_Repeat_Collections.DTO.Employee;
import pro.sky.HW_Repeat_Collections.Exceptions.EmployeeAlreadyAddedException;
import pro.sky.HW_Repeat_Collections.Exceptions.EmployeeNotFoundException;
import pro.sky.HW_Repeat_Collections.Exceptions.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    // Создаем мапу с сотрудниками и объявляем, что их будет строго три.
    private Map<String, Employee> employees;
    private static final int EMPLOYEE_SIZE = 3;

    // Объявляем тип  -- HashMap
    public EmployeeServiceImpl() {

        this.employees = new HashMap<>();
    }

    // Метод, который добавляет нового сотрудника.
    @Override
    public Employee addEmployee(String firstName,
                                String lastName,
                                int department,
                                double salary) {
// Если превышен размер мапы, то выбрасываем ошибку о переполненном хранилище.
// Если поймается ошибка, сработает if, если нет -- код пойдет дальше.
// После создания сотрудника проверяем, и если он уже есть -- выбрасываем ошибку.
// Создаем ключ из имени и фамилии.
// Проверяем наличие ключа в мапе: если такой уже есть, то выбрасываем сообщение об ошибке.
// Если нет -- добавляем нового сотрудника.

        if (employees.size() == EMPLOYEE_SIZE) {
            throw new EmployeeStorageIsFullException();
        }
        String key = generateKey(firstName, lastName);
        Employee employee = new Employee(
                StringUtils.capitalize(firstName),
                StringUtils.capitalize(lastName) , department, salary);
        if (employees.containsKey(key)) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(key, employee);
        return employee;
    }

// Метод, который удаляет сотрудника.
// Удаляем сотрудника с заданным ключом.
// Если сотрудник был -- то выдадим результат,
// какого именно сотрудника мы удалили.
// А если этот сотрудник ==null,
// то выдаем ошибку, что сотрудник не был найден.

    @Override
    public Employee deleteEmployee(String firstName, String lastName, int department, double salary) {
        String key = generateKey(firstName, lastName );
        Employee employee = employees.remove(key);
        if (employee == null) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

// Метод, который ищет и выдает данные сотрудника.
// В мапе "получаем" сотрудника по ключу.
// Если сотрудник "не содержится" в коллекции, то выбрасываем ошибку.
// Если найден -- возвращаем данные этого сотрудника.

    @Override
    public Employee getEmployee(String firstName, String lastName, int departmet, double salary) {
        String key = generateKey(firstName, lastName);
        Employee employee = employees.get(key);
        if (employee == null) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }


// Выводим "значения" всех сотрудников из мапы.
    @Override
    public Collection<Employee> printAll () {
        return employees.values();
    }

    private String generateKey (String firstName, String lastName) {
       return firstName + lastName;
    }




}
