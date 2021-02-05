package model.Pieces;

import model.Powerup.PowerupVisitor;

public interface Piece {

    void giveAbility();

    void givePowerup(PowerupVisitor visitor);
}
