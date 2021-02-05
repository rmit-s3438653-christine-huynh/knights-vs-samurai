package model.Powerup;

import model.Pieces.PieceImplementation;

public abstract class PowerupVisitor implements Cloneable {
    public abstract void givePowerUp(PieceImplementation piece);

    public abstract void undoPowerUp(PieceImplementation piece);

    // Cloneable
    public Object clone() {
        Object clone;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            throw new IllegalStateException("PowerupVisitor could not be cloned");
        }
        return clone;
    }
}
