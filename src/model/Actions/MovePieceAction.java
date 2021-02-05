package model.Actions;

import model.Exceptions.GeneralException;
import model.Exceptions.InvalidPlayerChosenPieceException;
import model.Exceptions.InvalidPlayerPieceMovementException;
import model.Interfaces.GameEngine;
import model.Player;
import model.TileButton;
import model.Tiles.Tile;
import model.Tiles.ValidTiles.FlagTile;
import model.Tiles.ValidTiles.PowerupTile;

import java.util.ArrayList;

public class MovePieceAction extends ActionCommand {

    private GameEngine gameEngine;
    private Player player;
    private ArrayList<TileButton> updateButtonArray;
    private Tile originTile, destinationTile;
    private boolean destTileHadFlag, pieceHadFlag;

    public MovePieceAction(GameEngine gameEngine, Player player, TileButton originButton,
            TileButton destinationButton) {
        // Save previous state
        // Check if destination tile had flag
        this.gameEngine = gameEngine;
        this.player = player;
        this.originTile = originButton.getTile();
        this.destinationTile = destinationButton.getTile();
        this.pieceHadFlag = this.originTile.getPiece().hasFlag();
        destTileHadFlag = destinationTile.hasFlag();
        updateButtonArray = new ArrayList<TileButton>();
        updateButtonArray.add(originButton);
        updateButtonArray.add(destinationButton);
    }

    public ArrayList<TileButton> execute()
            throws InvalidPlayerPieceMovementException, GeneralException, InvalidPlayerChosenPieceException {
        this.gameEngine.movePiece(player, originTile, destinationTile);
        if (destTileHadFlag) {
            // Set piece to have flag
            // Set tile to not have flag
            // Players cannot pick up their own flag
            if (!player.hasFlagTile((FlagTile) destinationTile)) {
                destinationTile.getPiece().setFlag(true);
                destinationTile.setFlag(false);
                if (destTileHadFlag && destinationTile.hasFlag()) {
                    throw new GeneralException("Tile should not have flag anymore");
                }
            } else {
                throw new GeneralException("Cannot pick up own flag");
            }
        }
        // Give the player the powerup from the tile
        if (PowerupTile.class.isInstance(destinationTile)) {
            PowerupTile tile = (PowerupTile) destinationTile;
            tile.getPiece().givePowerup(tile.getPowerupVisitor());
            tile.takenPowerup(true);
        }
        return updateButtonArray;
    };

    public ArrayList<TileButton> undo()
            throws InvalidPlayerPieceMovementException, GeneralException, InvalidPlayerChosenPieceException {
        this.gameEngine.movePiece(player, destinationTile, originTile);
        if (destTileHadFlag) {
            destinationTile.setFlag(true);
        }
        originTile.getPiece().setFlag(this.pieceHadFlag);
        // Undo the effects of the powerup if any
        if (PowerupTile.class.isInstance(destinationTile)) {
            PowerupTile tile = (PowerupTile) destinationTile;
            tile.takenPowerup(false);
            originTile.getPiece().undoGivePowerup();
        }
        return updateButtonArray;
    }
};
