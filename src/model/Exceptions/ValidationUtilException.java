package model.Exceptions;

public class ValidationUtilException extends Exception {

    public ValidationUtilException(String message) {
        super(String.format("Validation Exception Occurred attempting to %s", message));
    }
}
