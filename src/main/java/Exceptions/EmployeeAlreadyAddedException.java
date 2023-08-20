package Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Создаем ошибку, которая выпадает, если сотрудник с таким именем уже есть.
// Через @ResponseStatus - выбираем статус,
// который выдаст браузер при выпадании ошибки.
// Для этого в файл Application.properties надо добавить строчку
// server.error.include-message=always, чтобы
// вместе с типом ошибки выходило еще и сообщение.
@ResponseStatus(code = HttpStatus.BAD_REQUEST,
        reason = "Такой сотрудник уже есть.")
public class EmployeeAlreadyAddedException extends RuntimeException{
}
