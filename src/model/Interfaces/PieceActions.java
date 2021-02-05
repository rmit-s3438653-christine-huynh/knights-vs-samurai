package model.Interfaces;

import model.Exceptions.InvalidPlayerAttackPieceException;
import model.Exceptions.InvalidPlayerPieceMovementException;
import model.Player;
import model.Tiles.Tile;

public interface PieceActions {

    void attackPiece(Tile playerTile, Tile opponentTile) throws InvalidPlayerAttackPieceException;

    void movePiece(Player player, Tile originTile, Tile destinationTile) throws InvalidPlayerPieceMovementException;
}
