package model.Utils;

import model.Pieces.PieceImplementation;
import model.Player;
import model.Tiles.Tile;
import model.Tiles.ValidTiles.FlagTile;

import java.util.ArrayList;

public class ValidationUtils {

    public static boolean validatePieceMovement(Player player, Tile originTile, Tile destinationTile) {
        boolean validFlagMove = false;
        if (destinationTile instanceof FlagTile) {
            if (player.hasFlagTile((FlagTile) destinationTile)) {
                if (originTile.getPiece().hasFlag()) {
                    validFlagMove = true;
                }
            } else {
                validFlagMove = true;
            }
        } else {
            validFlagMove = true;
        }
        return (originTile.hasPiece() && !destinationTile.hasPiece()
                && validTileForPieceToMove(originTile.getPiece(), destinationTile)
                && validateMovementRange(originTile, destinationTile) && validFlagMove);
    }

    private static boolean validateMovementRange(Tile originTile, Tile destinationTile) {
        int pieceMovementRange = originTile.getPiece().getMovementRange();

        int xAxisDifference = Math.abs(originTile.getCoordinate().x() - destinationTile.getCoordinate().x());
        int yAxisDifference = Math.abs(originTile.getCoordinate().y() - destinationTile.getCoordinate().y());

        return (withinValidMovementRange(yAxisDifference, xAxisDifference, pieceMovementRange));
    }

    private static boolean withinValidMovementRange(int distanceBetweenAxisY, int distanceBetweenAxisX,
            int pieceMovementRange) {
        if (distanceBetweenAxisY >= 0 && distanceBetweenAxisY <= pieceMovementRange) {
            return (distanceBetweenAxisX >= 0 && distanceBetweenAxisX <= pieceMovementRange);
        }
        return false;
    }

    private static boolean validTileForPieceToMove(PieceImplementation piece, Tile destinationTile) {
        ArrayList<Class<?>> pieceTiles = piece.getValidTilesThisPieceCanMoveOnto();

        for (Class tileClass : pieceTiles) {
            if (tileClass.isInstance(destinationTile)) {
                return true;
            }
        }
        return false;
    }

    public static boolean validatePieceAttack(Tile playerTile, Tile opponentTile) {
        return (playerTile.hasPiece() && opponentTile.hasPiece() && validateAttackRange(playerTile, opponentTile));
    }

    private static boolean validateAttackRange(Tile playerTile, Tile opponentTile) {
        int pieceAttackRange = playerTile.getPiece().getAttackRange();

        int xAxisDifference = Math.abs(playerTile.getCoordinate().x() - opponentTile.getCoordinate().x());
        int yAxisDifference = Math.abs(playerTile.getCoordinate().y() - opponentTile.getCoordinate().y());

        return (withinValidMovementRange(yAxisDifference, xAxisDifference, pieceAttackRange));
    }

    private static boolean withinValidAttackRange(int distanceBetweenAxisY, int distanceBetweenAxisX,
            int pieceAttackRange) {
        if (distanceBetweenAxisY >= 0 && distanceBetweenAxisY <= pieceAttackRange) {
            return (distanceBetweenAxisX >= 0 && distanceBetweenAxisX <= pieceAttackRange);
        }
        return false;
    }
}
