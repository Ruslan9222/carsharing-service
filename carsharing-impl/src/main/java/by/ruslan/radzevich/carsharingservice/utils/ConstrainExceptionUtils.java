package by.ruslan.radzevich.carsharingservice.utils;

import lombok.experimental.UtilityClass;

/**
 * Класс для хранения текстовых констант сообщений об ошибках.
 */

@UtilityClass
public class ConstrainExceptionUtils {

    public static final String VIN_ALREADY_EXISTS = "Автомобиль с таким VIN уже существует";
    public static final String EMAIL_ALREADY_EXISTS = "Пользователь с таким Email уже существует";
}
