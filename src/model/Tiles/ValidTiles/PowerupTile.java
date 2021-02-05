package model.Tiles.ValidTiles;

import model.Tiles.Tile;

import model.Powerup.PowerupVisitor;
import model.Utils.RandomUtils;

import java.awt.*;

public class PowerupTile extends Tile {

    private PowerupVisitor powerupVisitor = RandomUtils.getRandomPowerup();
    private boolean powerupTaken;

    public PowerupTile() {
        super();
        powerupTaken = false;
        this.setBackGroundColor(Color.blue);
    }

    public PowerupVisitor getPowerupVisitor() {
        return this.powerupVisitor;
    }

    public void takenPowerup(boolean bool) {
        this.powerupTaken = bool;
        if (bool) {
            this.setBackGroundColor(Color.white);
        } else {
            this.setBackGroundColor(Color.blue);
        }
    }

    public boolean isPowerupTaken() {
        return powerupTaken;
    }
}
