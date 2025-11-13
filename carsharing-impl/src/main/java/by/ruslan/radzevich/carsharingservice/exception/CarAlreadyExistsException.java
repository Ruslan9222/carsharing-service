package by.ruslan.radzevich.carsharingservice.exception;

/**
 * Исключение, выбрасываемое при попытке создать автомобиль с уже существующим VIN-кодом.
 */
public class CarAlreadyExistsException extends RuntimeException {

    public CarAlreadyExistsException(String message) {
        super(message);
    }
}