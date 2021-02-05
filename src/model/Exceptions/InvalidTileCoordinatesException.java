package model.Exceptions;

public class InvalidTileCoordinatesException extends Exception {
    private static final String message = "Invalid Tile coordinates provided y: %d, x: %d";

    public InvalidTileCoordinatesException(int yAxis, int xAxis) {
        super(String.format(message, yAxis, xAxis));
    }
}
