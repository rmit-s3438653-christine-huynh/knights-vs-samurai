package model.Utils;

import model.Board;
import model.Pieces.PieceImplementation;
import model.Player;
import model.Tiles.Coordinate;
import model.Tiles.Tile;

import java.util.ArrayList;

public class PiecePlacementUtils {

    public static void placePlayerPiecesRandomly(Player player, Board gameBoard) {
        ArrayList<PieceImplementation> pieces = player.getPlayerPieces();

        for (PieceImplementation piece : pieces) {
            spawnPieceRandomlyOnBoard(piece, gameBoard);
        }
    }

    public static Coordinate spawnPieceRandomlyOnBoard(PieceImplementation piece, Board gameBoard) {
        Tile tile = RandomUtils.getRandomUnoccupiedClearTile(gameBoard);
        Coordinate coord = tile.getCoordinate();
        int yAxis = coord.y();
        int xAxis = coord.x();
        gameBoard.getBoard()[yAxis][xAxis].setPiece(piece);
        return coord;
    }
}
