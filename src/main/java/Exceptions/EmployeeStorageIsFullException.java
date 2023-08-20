package Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Создаем ошибку, которая выпадает, если превышен лимит сотрудников.
// Через @ResponseStatus - выбираем статус,
// который выдаст браузер при выпадании ошибки.
// Для этого в файл Application.properties надо добавить строчку
// server.error.include-message=always, чтобы
// вместе с типом ошибки выходило еще и сообщение.
@ResponseStatus(code = HttpStatus.BAD_REQUEST,
        reason = "Превышен лимит сотрудников.")
public class EmployeeStorageIsFullException extends RuntimeException{
}
