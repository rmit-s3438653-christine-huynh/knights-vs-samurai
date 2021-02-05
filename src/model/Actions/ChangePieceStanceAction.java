package model.Actions;

import model.Pieces.PieceImplementation;
import model.Stances.DefensiveStance;
import model.Stances.Stance;
import model.TileButton;

import java.util.ArrayList;

public class ChangePieceStanceAction extends ActionCommand {

    private ArrayList<TileButton> updateButtonArray;
    private PieceImplementation changedPiece;
    private Stance oldStance, newStance;

    public ChangePieceStanceAction(TileButton selectedButton) {
        // Save previous state
        changedPiece = selectedButton.getTile().getPiece();
        oldStance = changedPiece.getStance();
        if (oldStance instanceof DefensiveStance) {
            this.newStance = changedPiece.getOffensiveStance();
        } else {
            this.newStance = changedPiece.getDefensiveStance();
        }
        updateButtonArray = new ArrayList<>();
        updateButtonArray.add(selectedButton);
    }

    public ArrayList<TileButton> execute() {
        changedPiece.setStance(this.newStance);
        System.out.println("Old stance: " + oldStance.getClass().getName());
        System.out.println("Current stance: " + newStance.getClass().getName());
        return updateButtonArray;
    };

    public ArrayList<TileButton> undo() {
        changedPiece.setStance(this.oldStance);
        System.out.println("Current stance: " + changedPiece.getStance().getClass().getName());
        return updateButtonArray;
    }
};
