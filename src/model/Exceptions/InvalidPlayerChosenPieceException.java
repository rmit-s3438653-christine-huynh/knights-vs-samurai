package model.Exceptions;

import model.Pieces.Piece;

public class InvalidPlayerChosenPieceException extends Exception {

    private static final String message = "Player has chosen an invalid Piece %s";

    public InvalidPlayerChosenPieceException(Piece piece) {
        super(String.format(message, piece.getClass().toString()));
    }
}
