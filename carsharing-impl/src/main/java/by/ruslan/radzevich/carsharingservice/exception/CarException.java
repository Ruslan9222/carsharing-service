package by.ruslan.radzevich.carsharingservice.exception;

/**
 * Исключение, выбрасываемое при попытке создать автомобиль с уже существующим VIN-кодом.
 */
public class CarException extends RuntimeException {

    public CarException(String message) {
        super(message);
    }
}