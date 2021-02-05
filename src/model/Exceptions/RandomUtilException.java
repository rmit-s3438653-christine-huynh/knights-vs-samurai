package model.Exceptions;

public class RandomUtilException extends Exception {

    public RandomUtilException(String message) {
        super(String.format("Random Util Exception Occurred attempting to %s", message));
    }

}
