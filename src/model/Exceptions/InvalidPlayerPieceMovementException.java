package model.Exceptions;

import model.Tiles.Tile;

public class InvalidPlayerPieceMovementException extends Exception {

    private static final String message = "Player tried to move piece to an invalid tile";
    public InvalidPlayerPieceMovementException(Tile destinationTile) {
        super(message + destinationTile.toString());
    }
}
