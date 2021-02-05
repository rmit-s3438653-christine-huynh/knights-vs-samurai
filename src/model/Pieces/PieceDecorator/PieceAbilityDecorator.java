package model.Pieces.PieceDecorator;

import model.Pieces.Piece;
import model.Pieces.PieceImplementation;
import model.Powerup.PowerupVisitor;

public abstract class PieceAbilityDecorator implements Piece {
    protected PieceImplementation piece;

    public PieceAbilityDecorator(PieceImplementation piece) {
        this.piece = piece;
        giveAbility();
    }

    public abstract void giveAbility();

    public void givePowerup(PowerupVisitor visitor) {
        // Do Nothing
    }

    public PieceImplementation getPieceImplementation() {
        return this.piece;
    }
}
