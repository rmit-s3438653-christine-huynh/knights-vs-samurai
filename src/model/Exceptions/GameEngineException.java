package model.Exceptions;

public class GameEngineException extends Exception {

    public GameEngineException(String message) {
            super(String.format("Game Engine Exception Occurred attempting to %s", message));
        }
}
