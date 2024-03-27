package model;

/**
 * Custom exception class for representing invalid values in fraction
 * operations.
 *
 * @author Remigiusz Kocialski
 * @version 1.0
 */
public class InvalidValueException extends Exception {

    /**
     * Constructs an InvalidValueException with the specified detail message.
     *
     * @param message the detail message.
     */
    public InvalidValueException(String message) {
        super(message);
    }
}
