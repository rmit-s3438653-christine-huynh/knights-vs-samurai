package model.Interfaces;

import model.Board;
import model.Exceptions.GeneralException;
import model.Exceptions.InvalidPlayerAttackPieceException;
import model.Exceptions.InvalidPlayerChosenPieceException;
import model.Exceptions.InvalidPlayerPieceMovementException;
import model.Player;
import model.Tiles.Tile;
import model.Tiles.ValidTiles.FlagTile;

import java.util.ArrayList;

public interface GameEngine {

    void startGame(int numberOfPlayers, int boardHeight, int boardWidth, int numberOfObstacles, int piecesPerPlayer)
            throws Exception;

    void movePiece(Player player, Tile originTile, Tile destinationTile)
            throws InvalidPlayerChosenPieceException, InvalidPlayerPieceMovementException;

    void attackPiece(Player player, Tile playerTile, Tile opponentTile) throws InvalidPlayerAttackPieceException;

    boolean addPlayer(Player player);

    boolean removePlayer(Player player);

    Board getGameBoard();

    // Easier to get player turns since order of insertion
    ArrayList<Player> getPlayerList();

    void changePlayerTurn();

    void changePlayerPreviousTurn();

    FlagTile respawnFlagForPlayer(Player player) throws GeneralException;

    Player getCurrentPlayer();

    boolean checkWinCondition() throws GeneralException;

    Player getWinner() throws GeneralException;

    int getTurnNo();
}
