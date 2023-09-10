package pro.sky.HW_Repeat_Collections.Service;

import org.apache.commons.lang3.StringUtils;
import pro.sky.HW_Repeat_Collections.Exceptions.IllegalNameException;

import java.util.List;

public class NameValidator {
    public static void validateName(String... names) {
        for (String name : names) {
            if (!StringUtils.isAlpha(name)) {
                throw new IllegalNameException();
            }
        }
    }
}
