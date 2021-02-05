package model.Pieces;

import model.Powerup.PowerupVisitor;
import model.Stances.DefensiveStance;
import model.Stances.OffensiveStance;
import model.Stances.Stance;
import model.Tiles.ValidTiles.ClearTile;
import model.Tiles.ValidTiles.FlagTile;
import model.Tiles.ValidTiles.PowerupTile;

import javax.swing.*;
import java.util.ArrayList;

public class PieceImplementation implements Piece, Cloneable {
    // Class Existence Variables
    private int healthPool;
    private Stance stance;
    private Boolean isAlive;
    private int movementRange;
    private ImageIcon imageIcon, imageFlagIcon;
    private ArrayList<Class<?>> validTilesThisPieceCanMoveOnto;
    private String name;
    private int attackRange, healthPoolBank, damageValue;

    // Powerup visitor
    private PowerupVisitor visitor;

    // Stance Objects
    private DefensiveStance defensiveStance = new DefensiveStance();
    private OffensiveStance offensiveStance = new OffensiveStance();

    // Objective Related Variable
    Boolean isFlagHolder;

    // Default or Constant Piece Values
    protected static final int DEFAULT_DAMAGE_VALUE = 1;
    protected static final int DEFAULT_ATTACK_RANGE = 1;

    private static final int DEFAULT_MOVEMENT_RANGE = 1;

    public PieceImplementation(String name, int healthPool, ImageIcon image, ImageIcon imageFlagIcon) {
        // Builder pattern to avoid setting null Arraylist
        this.healthPool = healthPool;
        this.name = name;
        this.imageIcon = image;
        this.imageFlagIcon = imageFlagIcon;
        this.isAlive = true;
        this.movementRange = DEFAULT_MOVEMENT_RANGE;
        this.stance = defensiveStance; // Pieces start in a defensive stance
        this.damageValue = DEFAULT_DAMAGE_VALUE;
        this.attackRange = DEFAULT_ATTACK_RANGE;
        this.healthPoolBank = healthPool;
        this.isFlagHolder = false; // Pieces don't start carrying the enemy flag

        // Valid Tiles that this piece can move onto
        validTilesThisPieceCanMoveOnto = new ArrayList<>();
        validTilesThisPieceCanMoveOnto.add(ClearTile.class);
        validTilesThisPieceCanMoveOnto.add(PowerupTile.class);
        validTilesThisPieceCanMoveOnto.add(FlagTile.class);
    }

    @Override
    public void giveAbility() {
    }

    @Override
    public void givePowerup(PowerupVisitor visitor) {
        this.visitor = visitor;
        visitor.givePowerUp(this);
    }

    public void undoGivePowerup() {
        if (this.visitor != null) {
            this.visitor.undoPowerUp(this);
        }
    }

    // Cloneable
    public Object clone() {
        Object clone;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            throw new IllegalStateException("Piece could not be cloned");
        }
        return clone;
    }

    // SETTERS
    public void setImageIcon(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStance(Stance stance) {
        this.stance = stance;
    }

    public void setMovementRange(int movementRange) {
        this.movementRange = movementRange;
    }

    public void setFlag(boolean setFlag) {
        this.isFlagHolder = setFlag;
    }

    public void setHealthPool(int healthPool) {
        this.healthPool = healthPool;
    }

    public void setHealthPoolBank(int healthPoolBank) {
        this.healthPoolBank = healthPoolBank;
    }

    public void setNewHealthPool() {
        this.healthPool = healthPoolBank;
    }

    public void setAttackRange(int attackRange) {
        this.attackRange = attackRange;
    }

    public void setDamageValue(int value) {
        this.damageValue = value;
    }

    // GETTERS
    public int getHealthPoolBank() {
        return healthPoolBank;
    }

    public Stance getStance() {
        return this.stance;
    }

    public DefensiveStance getDefensiveStance() {
        return this.defensiveStance;
    }

    public OffensiveStance getOffensiveStance() {
        return this.offensiveStance;
    }

    public ArrayList<Class<?>> getValidTilesThisPieceCanMoveOnto() {
        return this.validTilesThisPieceCanMoveOnto;
    }

    public ImageIcon getImageIcon() {
        if (hasFlag()) {
            return this.imageFlagIcon;
        }
        return this.imageIcon;
    }

    public int getMovementRange() {
        return this.movementRange;
    }

    public String getName() {
        return this.name;
    }

    public boolean hasFlag() {
        return this.isFlagHolder;
    }

    public int getHealthPool() {
        return this.healthPool;
    }

    public int getAttackRange() {
        return attackRange;
    }

    public int getDamageValue() {
        if (this.getStance() instanceof OffensiveStance) {
            return this.damageValue + 1;
        }
        return this.damageValue;
    }

    public int getDefensiveValue() {
        return this.getStance().getDefensiveValue();
    }

    public boolean isPieceAlive() {
        return this.isAlive;
    }
}
