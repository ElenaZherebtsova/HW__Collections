package pro.sky.HW_Repeat_Collections.Service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.HW_Repeat_Collections.DTO.Employee;
import pro.sky.HW_Repeat_Collections.Exceptions.EmployeeNotFoundException;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {
    @Mock
    private EmployeeService employeeService;
    @InjectMocks
    DepartmentServiceImpl departmentService;

    private List<Employee> employees = List.of(
            new Employee("Hank", "Moody", 2, 70_000),
            new Employee("Lara", "Croft", 2, 100_000),
            new Employee("Captain", "Amerika", 4, 80_000));

    //    Тест, проверяющий правильного сотрудника с МАХ з\п внутри отдела.
    @Test
    void maxSalaryEmpl_shouldReturnEmployeeWithMaxSalaryIfEmployeesInDepartment() {
        Mockito.when(employeeService.printAll()).thenReturn(employees);

        Employee result = departmentService.maxSalaryEmpl(employees.get(0).getDepartment());

        assertEquals(employees.get(1), result);
    }

    //    Тест, который проверяет наличие ошибки, если в заданном отделе нет сотрудников.
    @Test
    void maxSalaryEmpl_shouldThrowExceptionIfNotEmployeesInDepartment() {
        Mockito.when(employeeService.printAll()).thenReturn(Collections.emptyList());

        assertThrows(EmployeeNotFoundException.class, () -> departmentService.maxSalaryEmpl(3));
    }

    // Аналогично проверяем на МИН з\п.
    @Test
    void minSalaryEmpl_shouldReturnEmployeeWithMaxSalaryIfEmployeesInDepartment() {
        Mockito.when(employeeService.printAll()).thenReturn(employees);

        Employee result = departmentService.minSalaryEmpl(employees.get(0).getDepartment());

        assertEquals(employees.get(0), result);

    }

    @Test
    void minSalaryEmpl_shouldThrowExceptionIfNotEmployeesInDepartment() {

        Mockito.when(employeeService.printAll()).thenReturn(Collections.emptyList());

        assertThrows(EmployeeNotFoundException.class, () -> departmentService.minSalaryEmpl(3));
    }

    // Тест, который проверяет, что выводятся все сотрудники по заданному отделу.
    @Test
    void getAllByDepartment_shouldReturnEmployeesListIfEmployeesInDepartment() {
        Mockito.when(employeeService.printAll()).thenReturn(employees);

        Collection<Employee> result = departmentService.getAllByDepartment(employees.get(0).getDepartment());

        assertEquals(List.of(employees.get(0), employees.get(1)), result);
    }

    // Тест, проверяющий пустой список, если в отделе нет сотрудников.
    @Test
    void getAllByDepartment_shouldReturnEmptyListIfNotEmployeesInDepartment() {
        Mockito.when(employeeService.printAll()).thenReturn(employees);

        Collection<Employee> result = departmentService.getAllByDepartment(3);

        assertEquals(Collections.emptyList(), result);
    }

// Тест, который проверяет правильный вывод списка сотрудников по отделам.
    @Test
    void getAll_shouldReturnMapWithEmployessIfEmployeesInDepartment() {
        Mockito.when(employeeService.printAll()).thenReturn(employees);
        Map<Integer, List<Employee>> expectedMap = Map.of(
                2, List.of(employees.get(0), employees.get(1)),
                4, List.of(employees.get(2)));

        Map<Integer, List<Employee>> result = departmentService.getAll();
        assertEquals(expectedMap, result);
    }


    @Test
    void getAll_should() {
        Mockito.when(employeeService.printAll()).thenReturn(Collections.emptyList());
        Map<Integer, List<Employee>> expectedMap = Map.of();

        Map<Integer, List<Employee>> result = departmentService.getAll();
        assertEquals(expectedMap, result);
    }


}