package model.Exceptions;

import model.Tiles.Tile;

public class InvalidTileException extends Exception {

    private static final String message = "Invalid Tile provided";

    public InvalidTileException(Tile tile) {
        super(message + tile.toString());
    }
}
