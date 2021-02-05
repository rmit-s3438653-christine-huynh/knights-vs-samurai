package model.Implementations;

import model.Exceptions.DBCError;
import model.Exceptions.InvalidPlayerAttackPieceException;
import model.Exceptions.InvalidPlayerPieceMovementException;
import model.Interfaces.GameEngine;
import model.Interfaces.PieceActions;
import model.Pieces.PieceImplementation;
import model.Player;
import model.Powerup.PowerupVisitor;
import model.Tiles.InvalidTiles.ObstacleTile;
import model.Tiles.Tile;
import model.Tiles.ValidTiles.PowerupTile;
import model.Utils.ValidationUtils;

public class PieceActionImpl implements PieceActions {

    private GameEngine gameEngine;

    public PieceActionImpl(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        invariant();
    }

    // class invariant
    private void invariant() {
        if (this.gameEngine == null) {
            throw new IllegalStateException(DBCError.getInvariantErrorMessage("gameEngine"));
        }
    }

    public void movePiece(Player player, Tile originTile, Tile destinationTile)
            throws InvalidPlayerPieceMovementException {
        if (ValidationUtils.validatePieceMovement(player, originTile, destinationTile)) {
            destinationTile.setPiece(originTile.getPiece());
            originTile.setPiece(null);
        } else {
            throw new InvalidPlayerPieceMovementException(destinationTile);
        }
    }

    // CH 140519
    public void attackPiece(Tile playerTile, Tile opponentTile) throws InvalidPlayerAttackPieceException {
        if (ValidationUtils.validatePieceAttack(playerTile, opponentTile)) {
            PieceImplementation attackingPiece = playerTile.getPiece();
            PieceImplementation defendingPiece = opponentTile.getPiece();

            int attackValue = attackingPiece.getDamageValue();
            int defenceValue = defendingPiece.getDefensiveValue();

            attackValue = attackValue - defenceValue;
            if (attackValue < 0) {
                attackValue = 0;
            }

            if (opponentTile.getPiece().getHealthPool() > 0) {
                int newHealthPool = opponentTile.getPiece().getHealthPool() - attackValue;
                opponentTile.getPiece().setHealthPool(newHealthPool);
            }
        } else {
            throw new InvalidPlayerAttackPieceException("Tried to attack invalid piece: " + opponentTile.getPiece().getName());
        }
    }

}
