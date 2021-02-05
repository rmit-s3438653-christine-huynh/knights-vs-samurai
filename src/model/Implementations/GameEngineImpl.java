package model.Implementations;

import model.Board;
import model.Exceptions.*;
import model.Faction.FactionGenerator;
import model.Faction.FactionProvider;
import model.Interfaces.GameEngine;
import model.Interfaces.PieceActions;
import model.Pieces.Piece;
import model.Pieces.PieceImplementation;
import model.Player;
import model.Powerup.PowerupCache;
import model.Tiles.Tile;
import model.Tiles.ValidTiles.FlagTile;
import model.Utils.ImageFactory;
import model.Utils.PiecePlacementUtils;

import java.awt.*;
import java.util.ArrayList;

public class GameEngineImpl implements GameEngine {

    // Error Logging
    private static String methodName;

    // Piece Actions
    private static PieceActions pieceAction;

    // Instance Variables
    private Board gameBoard;
    private ArrayList<Player> playerList;
    private Player currentPlayer, winner;
    private int turnNo;
    private FactionProvider factionProvider = new FactionProvider();

    // Too many parameters, need to reduce this , limit powerups to 2? Remove powerups for now
    public void startGame(int numberOfPlayers, int boardHeight, int boardWidth, int numberOfObstacles, int piecesPerPlayer) throws Exception {
        // Powerup Cache Init
        PowerupCache.loadCache();
        // Piece Actions Init
        pieceAction = new PieceActionImpl(this);
        turnNo = 1;
        // Game Board Setup
        this.gameBoard = new Board(boardHeight, boardWidth, numberOfObstacles);

        // Populate Player List
        playerList = new ArrayList<>();
        for (int playerCount = 0; playerCount < numberOfPlayers; playerCount++) {
            // Insert Player with their assigned Flag Tile
            playerList.add(new Player());
            assignPlayerFlagTile(playerList.get(playerCount));
        }

        // Take player 1 as current player
        currentPlayer = this.getFirstPlayer();

        // Assign Pieces to the Players
        try {
            // Player 1 samurai pieces
            playerList.get(0).setTeamName("Samurais");
            FactionGenerator samuraiFactionGenerator = factionProvider.getSamuraiFactionGenerator();
            playerList.get(0).setPlayerPieces(samuraiFactionGenerator.generateFaction(piecesPerPlayer));
            PiecePlacementUtils.placePlayerPiecesRandomly(playerList.get(0), gameBoard);
            // Player 2 knight pieces
            playerList.get(1).setTeamName("Knights");
            FactionGenerator knightFactionGenerator = factionProvider.getKnightFactionGenerator();
            playerList.get(1).setPlayerPieces(knightFactionGenerator.generateFaction(piecesPerPlayer));
            PiecePlacementUtils.placePlayerPiecesRandomly(playerList.get(1), gameBoard);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
            throw new GameEngineException(methodName);
        }
    }

    public void movePiece(Player player, Tile originTile, Tile destinationTile)
            throws InvalidPlayerChosenPieceException, InvalidPlayerPieceMovementException {
        methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

        // pre: player != null, piece != null, destinationTile != null
        // Pre condition valid move was made
        if (player == null || originTile == null || destinationTile == null) {
            throw new IllegalArgumentException(DBCError.getPreConditionErrorMessage(methodName));
        }

        ArrayList<PieceImplementation> playerPieces = player.getPlayerPieces();
        if (playerPieces.contains(originTile.getPiece())) {
            pieceAction.movePiece(player, originTile, destinationTile);
        } else {
            throw new InvalidPlayerChosenPieceException(originTile.getPiece());
        }

        // post: !originTile.hasPiece && destinationTile.hasPiece()
        if (originTile.hasPiece() || !destinationTile.hasPiece()) {
            throw new AssertionError(DBCError.getPostConditionErrorMessage(methodName));
        }
    }

    // CH 140519
    public void attackPiece(Player player, Tile playerTile, Tile opponentTile)
            throws InvalidPlayerAttackPieceException {
        methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        if (player == null || playerTile.getPiece() == null || opponentTile.getPiece() == null) {
            throw new IllegalArgumentException(DBCError.getPreConditionErrorMessage(methodName));
        }
        pieceAction.attackPiece(playerTile, opponentTile);
    }

    public boolean addPlayer(Player player) {
        return playerList.add(player);
    }

    public boolean removePlayer(Player player) {
        return playerList.remove(player);
    }

    public Board getGameBoard() {
        return this.gameBoard;
    }

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    private Player getFirstPlayer() {
        return playerList.get(0);
    }

    private Player getLastPlayer() {
        return playerList.get(playerList.size() - 1);
    }

    private Player getNextPlayer() {
        int indexOfCurrentPlayer = playerList.indexOf(currentPlayer);
        if (indexOfCurrentPlayer == -1) {
            throw new IndexOutOfBoundsException("getNextPlayer: something went wrong");
        } else {
            if (playerList.size() == indexOfCurrentPlayer + 1) {
                return getFirstPlayer();
            } else {
                return playerList.get(indexOfCurrentPlayer + 1);
            }
        }
    }

    private Player getPreviousPlayer() {
        int indexOfCurrentPlayer = playerList.indexOf(currentPlayer);
        if (indexOfCurrentPlayer == -1) {
            throw new IndexOutOfBoundsException("getNextPlayer: something went wrong");
        } else {
            if (indexOfCurrentPlayer == 0) {
                return getLastPlayer();
            } else {
                return playerList.get(indexOfCurrentPlayer - 1);
            }
        }
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void changePlayerTurn() {
        currentPlayer = getNextPlayer();
        turnNo++;
    }

    public void changePlayerPreviousTurn() {
        currentPlayer = getPreviousPlayer();
        turnNo--;
    }

    public int getTurnNo() {
        return turnNo;
    }

    public boolean checkWinCondition() throws GeneralException {
        boolean winnerFound = false;
        for (Player player : playerList) {
            FlagTile flagTile = player.getFlagTile();
            if (flagTile.hasPiece()) {
                Piece piece = flagTile.getPiece();
                if (player.ownPiece(piece)) {
                    if (flagTile.hasFlag()) {
                        winner = player;
                        winnerFound = true;
                    }
                }
            }
        }
        return winnerFound;
    }

    public Player getWinner() throws GeneralException {
        if (this.winner != null) {
            return this.winner;
        } else {
            throw new GeneralException("No winner has been found yet");
        }
    }

    // Generate a FlagTile for a player
    private void assignPlayerFlagTile(Player player) throws Exception {
        ImageFactory imgFac = new ImageFactory();
        int playerIndex = playerList.indexOf(player);
        if ((playerIndex % playerList.size()) == 0) {
            player.setFlagTile(gameBoard.generateAFlagTile(imgFac.getSamuraiFlagImage(), Color.yellow));
        } else if ((playerIndex % playerList.size()) == 1) {
            player.setFlagTile(gameBoard.generateAFlagTile(imgFac.getKnightFlagImage(), Color.green));
        }
    }

    public FlagTile respawnFlagForPlayer(Player player) throws GeneralException {
        FlagTile flagTile = player.getFlagTile();
        if (flagTile.hasFlag()) {
            throw new GeneralException("Flag tile already has flag for player " + player.getTeamName());
        } else {
            flagTile.setFlag(true);
        }
        return flagTile;
    }
}