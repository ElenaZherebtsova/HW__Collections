package pro.sky.HW_Repeat_Collections.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Имя содержит недопустимые символы!")
public class IllegalNameException extends IllegalArgumentException{
}
