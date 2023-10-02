package pro.sky.HW_Repeat_Collections.Service;

import org.junit.jupiter.api.Test;
import pro.sky.HW_Repeat_Collections.DTO.Employee;
import pro.sky.HW_Repeat_Collections.Exceptions.EmployeeAlreadyAddedException;
import pro.sky.HW_Repeat_Collections.Exceptions.EmployeeStorageIsFullException;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImplTest {
    private EmployeeServiceImpl underTest = new EmployeeServiceImpl();
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
            underTest.addEmployee((expectedEmployee.getFirstName()+i),
                                  (expectedEmployee.getLastName()+i),
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



    @Test
    void deleteEmployee() {
    }

    @Test
    void getEmployee() {
    }

    @Test
    void printAll() {
    }
}