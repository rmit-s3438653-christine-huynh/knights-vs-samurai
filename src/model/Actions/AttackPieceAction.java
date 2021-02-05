package model.Actions;

import controller.GameController;
import model.Exceptions.GeneralException;
import model.Exceptions.InvalidPlayerAttackPieceException;
import model.Interfaces.GameEngine;
import model.Pieces.PieceImplementation;
import model.Player;
import model.TileButton;
import model.Tiles.Tile;
import model.Tiles.ValidTiles.FlagTile;
import view.AppFrame;

import java.util.ArrayList;

public class AttackPieceAction extends ActionCommand {
    private GameEngine gameEngine;
    private GameController gameController;
    private AppFrame view;
    private Player player;
    private ArrayList<TileButton> updateButtonArray;
    private TileButton playerTileButton, opponentTileButton, respawnTileButton;
    private boolean attackedPieceDied, pieceThatDiedHadFlag;
    private int oldHealth;
    private PieceImplementation attackedPiece;
    private FlagTile respawnFlagTile;

    public AttackPieceAction(GameController gameController, GameEngine gameEngine, AppFrame view, Player player,
            TileButton playerTileButton, TileButton opponentTileButton) {
        this.gameController = gameController;
        this.gameEngine = gameEngine;
        this.view = view;
        this.player = player;
        this.playerTileButton = playerTileButton;
        this.opponentTileButton = opponentTileButton;
        this.respawnTileButton = null;
        this.respawnFlagTile = null;
        this.attackedPieceDied = false;
        this.pieceThatDiedHadFlag = false;
        updateButtonArray = new ArrayList<TileButton>();
        updateButtonArray.add(playerTileButton);
        updateButtonArray.add(opponentTileButton);
    }

    public ArrayList<TileButton> execute() throws InvalidPlayerAttackPieceException, GeneralException {
        Tile opponentTile = opponentTileButton.getTile();
        Tile playerTile = playerTileButton.getTile();
        attackedPiece = opponentTile.getPiece();
        if (attackedPiece == null) {
            throw new InvalidPlayerAttackPieceException("No piece to attack");
        } else {
            if (player.ownPiece(attackedPiece)) {
                throw new InvalidPlayerAttackPieceException("Cannot attack your own piece");
            }
            this.oldHealth = attackedPiece.getHealthPool();
        }

        this.gameEngine.attackPiece(player, playerTile, opponentTile);
        if (attackedPiece.getHealthPool() <= 0) {
            attackedPieceDied = true;
            pieceThatDiedHadFlag = attackedPiece.hasFlag();
            if (pieceThatDiedHadFlag) {
                respawnFlagTile = gameEngine.respawnFlagForPlayer(player);
                TileButton respawnFlagTileButton = view.getTileButtonByCoord(respawnFlagTile.getCoordinate());
                updateButtonArray.add(respawnFlagTileButton);
                attackedPiece.setFlag(false);
            }
            respawnTileButton = gameController.respawnOpponentPiece(opponentTile);
            updateButtonArray.add(respawnTileButton);
            opponentTile.setPiece(null);
        }
        return updateButtonArray;
    }

    public ArrayList<TileButton> undo() {
        attackedPiece.setHealthPool(oldHealth);
        if (attackedPieceDied) {
            opponentTileButton.getTile().setPiece(attackedPiece);
            respawnTileButton.getTile().setPiece(null);
            if (pieceThatDiedHadFlag) {
                respawnFlagTile.setFlag(false);
                attackedPiece.setFlag(true);
            }
        }
        return updateButtonArray;
    }
}
