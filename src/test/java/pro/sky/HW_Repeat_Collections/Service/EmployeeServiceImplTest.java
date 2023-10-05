package pro.sky.HW_Repeat_Collections.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.HW_Repeat_Collections.DTO.Employee;
import pro.sky.HW_Repeat_Collections.Exceptions.EmployeeAlreadyAddedException;
import pro.sky.HW_Repeat_Collections.Exceptions.EmployeeNotFoundException;
import pro.sky.HW_Repeat_Collections.Exceptions.EmployeeStorageIsFullException;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImplTest {
    private EmployeeServiceImpl underTest;

    @BeforeEach
    void beforeEach() {
        underTest = new EmployeeServiceImpl();
    }


    private Employee expectedEmployee = new Employee("Kate",
            "Ivanova", 4, 50_000);


    @Test
//    Тест, который проверяет, что сотрудник добавился в мапу.
//    И после добавления возвращается результат - данный сотрудник.
    void addEmployee_shouldAddEmployeeToMapAndReturn() {
//        Прописываем, что как результат мы ожидаем employee с такими вот полученными данными
        Employee result = underTest.addEmployee(expectedEmployee.getFirstName(),
                expectedEmployee.getLastName(),
                expectedEmployee.getDepartment(),
                expectedEmployee.getSalary());
//      Проверяем, что при получении списка всех сотрудников,
//      наш добавленный  -- содержится в этом списке.
//      Проверяем, что "ожидаемый employee" совпадает с полученным "результатом".
        assertTrue(underTest.printAll().contains(expectedEmployee));
        assertEquals(expectedEmployee, result);

    }

    @Test
//    Тест, который проверяет, что при превышении количества сотрудников
//    -- выбрасывается ошибка.
//    Через цикл забиваем мапу тремя значениями и добавляем 4-го.
    void addEmployee_shouldThrowExceptionIfNotEnoughMapSize() {
        for (int i = 0; i < 3; i++) {
            underTest.addEmployee((expectedEmployee.getFirstName() + i),
                    (expectedEmployee.getLastName() + i),
                    (expectedEmployee.getDepartment()),
                    (expectedEmployee.getSalary()));
        }

        assertThrows(EmployeeStorageIsFullException.class,
                () -> underTest.addEmployee(expectedEmployee.getFirstName(),
                        expectedEmployee.getLastName(),
                        expectedEmployee.getDepartment(),
                        expectedEmployee.getSalary()));
    }

    @Test
//    Тест, который проверяет, что такой именно сотрудник уже есть в мапе.
//    Добавляем сотрудника, а потом пытаемся добавить его же еще раз,
//    и проверяем наличие ошибки.
    void addEmployee_shoudThrowExceptionIfEqualEmployeInMap() {
        underTest.addEmployee(expectedEmployee.getFirstName(),
                expectedEmployee.getLastName(),
                expectedEmployee.getDepartment(),
                expectedEmployee.getSalary());
        assertThrows(EmployeeAlreadyAddedException.class,
                () -> underTest.addEmployee(expectedEmployee.getFirstName(),
                        expectedEmployee.getLastName(),
                        expectedEmployee.getDepartment(),
                        expectedEmployee.getSalary()));
    }

    //Тест, который проверяет, что удалился нужный сотрудник.
    @Test
    void deleteEmployee_shouldDeleteEmployeeAndReturn() {
        Employee result = underTest.addEmployee(expectedEmployee.getFirstName(),
                expectedEmployee.getLastName(),
                expectedEmployee.getDepartment(),
                expectedEmployee.getSalary());
        underTest.deleteEmployee(expectedEmployee.getFirstName(),
                expectedEmployee.getLastName(),
                expectedEmployee.getDepartment(),
                expectedEmployee.getSalary());
        assertEquals(expectedEmployee, result);


    }

    //Тест, проверяющий ошибку, если удаляемый сотрудник не найден
    @Test
    void deleteEmployee_shouldThrowExceptionIfEmployeeNotFound() {
        underTest.addEmployee(expectedEmployee.getFirstName(),
                expectedEmployee.getLastName(),
                expectedEmployee.getDepartment(),
                expectedEmployee.getSalary());
        assertThrows(EmployeeNotFoundException.class,
                () -> underTest.deleteEmployee(expectedEmployee.getFirstName() + "a",
                        expectedEmployee.getLastName() + "b",
                        expectedEmployee.getDepartment(),
                        expectedEmployee.getSalary()));
    }

    //Тест, проверяющий что добавленный сотрудник получен и совпадает с ожидаемым результатом.
    @Test
    void getEmployee_shouldGetEmployeeAndReturn() {
        Employee result = underTest.addEmployee(expectedEmployee.getFirstName(),
                expectedEmployee.getLastName(),
                expectedEmployee.getDepartment(),
                expectedEmployee.getSalary());
        underTest.getEmployee(expectedEmployee.getFirstName(),
                expectedEmployee.getLastName(),
                expectedEmployee.getDepartment(),
                expectedEmployee.getSalary());
        assertEquals(expectedEmployee, result);
    }

    //    Тест, проверяющий наличие ошибки, если получаемый сотрудник не найден.
    @Test
    void getEmployee_shouldThrowExceptionIfEmployeeNotFound() {
        underTest.addEmployee(expectedEmployee.getFirstName(),
                expectedEmployee.getLastName(),
                expectedEmployee.getDepartment(),
                expectedEmployee.getSalary());
        assertThrows(EmployeeNotFoundException.class,
                () -> underTest.getEmployee(expectedEmployee.getFirstName() + "a",
                        expectedEmployee.getLastName() + "b",
                        expectedEmployee.getDepartment(),
                        expectedEmployee.getSalary()));
    }

//    Проверяем, что список всех сотрудников выводистя правильно.
    @Test
    void printAll_shoudReturnEmployesList() {
        Employee employee2 = new Employee("Hank", "Moody", 2, 70_000);
        underTest.addEmployee(expectedEmployee.getFirstName(),
                expectedEmployee.getLastName(),
                expectedEmployee.getDepartment(),
                expectedEmployee.getSalary());
        underTest.addEmployee(employee2.getFirstName(),
                employee2.getLastName(),
                employee2.getDepartment(),
                employee2.getSalary());
        Collection<Employee> result = underTest.printAll();
        assertTrue(result.containsAll(List.of(expectedEmployee, employee2)));
    }
// Проверяем, сто при пустом списке -- нет ошибок,
// и возвращается пустой список.
    @Test
    void printAll_shouldReturnEmptyListIfEmployeesNotInMap() {
        Collection<Employee> all = underTest.printAll();
        assertTrue(all.isEmpty());

    }
}