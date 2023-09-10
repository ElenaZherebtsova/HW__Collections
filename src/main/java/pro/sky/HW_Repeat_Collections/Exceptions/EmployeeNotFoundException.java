package pro.sky.HW_Repeat_Collections.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Создаем ошибку, которая выпадает, если сотрудник не найден.
// Через @ResponseStatus - выбираем статус,
// который выдаст браузер при выпадании ошибки.
// Для этого в файл Application.properties надо добавить строчку
// server.error.include-message=always, чтобы
// вместе с типом ошибки выходило еще и сообщение.
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Работник не найден.")
public class EmployeeNotFoundException extends RuntimeException{
    }
