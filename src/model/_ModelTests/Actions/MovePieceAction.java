package model._ModelTests.Actions;

import model.Exceptions.GeneralException;
import model.Exceptions.InvalidPlayerChosenPieceException;
import model.Exceptions.InvalidPlayerPieceMovementException;
import model.Interfaces.GameEngine;
import model.Player;
import model.Tiles.Tile;

public class MovePieceAction extends ActionCommand {

    private GameEngine gameEngine;
    private Player player;
    private Tile originTile, destinationTile;
    private boolean destTileHadFlag;

    public MovePieceAction(GameEngine gameEngine, Player player, Tile originTile, Tile destinationTile){
        //Save previous state
        //Check if destination tile had flag
        this.gameEngine = gameEngine;
        this.player = player;
        this.originTile = originTile;
        this.destinationTile = destinationTile;
        destTileHadFlag = destinationTile.hasFlag();
    }

    public void execute() throws InvalidPlayerPieceMovementException, GeneralException, InvalidPlayerChosenPieceException {
        this.gameEngine.movePiece(player, originTile, destinationTile);
        if (destTileHadFlag) {
            //Set piece to have flag
            //Set tile to not have flag
            originTile.getPiece().setFlag(true);
            destinationTile.setFlag(false);
        }
        if (destTileHadFlag && destinationTile.hasFlag()) {
            throw new GeneralException("Tile should not have flag anymore");
        }
    };

    public void undo() throws InvalidPlayerPieceMovementException, GeneralException, InvalidPlayerChosenPieceException {
        this.gameEngine.movePiece(player, destinationTile, originTile);
        if (destTileHadFlag) {
            destinationTile.setFlag(true);
        }
    }
};
