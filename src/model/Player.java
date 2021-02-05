package model;

import java.util.ArrayList;

import model.Pieces.Piece;
import model.Pieces.PieceImplementation;
import model.Tiles.ValidTiles.FlagTile;

public class Player {
    private ArrayList<PieceImplementation> playerPieces;
    private FlagTile flagTile;
    private String teamName;
    private boolean canUndo;

    public Player() {
        this.canUndo = true;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    };

    public String getTeamName() {
        return this.teamName;
    }

    public void setPlayerPieces(ArrayList<PieceImplementation> pieces) {
        this.playerPieces = pieces;
    }

    public ArrayList<PieceImplementation> getPlayerPieces() {
        return playerPieces;
    }

    public boolean ownPiece(Piece checkPiece) {
        if (playerPieces.contains(checkPiece)) {
            return true;
        }
        return false;
    }

    public void setFlagTile(FlagTile flagTile) {
        this.flagTile = flagTile;
    }

    public FlagTile getFlagTile() {
        return flagTile;
    }

    public boolean hasFlagTile(FlagTile flagTile) {
        if (this.flagTile == flagTile) {
            return true;
        }
        return false;
    }

    public boolean canUndo() {
        return this.canUndo;
    }

    public void didUndo() {
        this.canUndo = false;
    }
}
