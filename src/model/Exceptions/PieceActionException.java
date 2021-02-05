package model.Exceptions;

public class PieceActionException extends Exception {

    public PieceActionException(String message) {
        super(String.format("Piece Action Exception Occurred attempting to %s", message));
    }
}
