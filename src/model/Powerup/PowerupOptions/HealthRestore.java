package model.Powerup.PowerupOptions;

import model.Pieces.PieceImplementation;
import model.Powerup.PowerupVisitor;

public class HealthRestore extends PowerupVisitor {

    private int priorHealth;

    @Override
    public void givePowerUp(PieceImplementation piece) {
        priorHealth = piece.getHealthPool();
        piece.setHealthPool(piece.getHealthPoolBank());
    }

    @Override
    public void undoPowerUp(PieceImplementation piece) {
        piece.setHealthPool(priorHealth);
    }
}
