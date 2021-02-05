package model.Stances;

import model.Pieces.PieceImplementation;

public abstract class Stance {

    private int offensiveValue;
    private int defensiveValue;
    private String stanceName;

    protected Stance(int offensiveValue, int defensiveValue, String stanceName) {
        this.offensiveValue = offensiveValue;
        this.defensiveValue = defensiveValue;
        this.stanceName = stanceName;
    }

    abstract void toggleStance(PieceImplementation piece);

    public int getOffensiveValue() {
        return offensiveValue;
    }

    public int getDefensiveValue() {
        return defensiveValue;
    }

    public void setDefensiveValue(int value) {
        this.defensiveValue = value;
    }

    public void setOffensiveValue(int value) {
        this.offensiveValue = value;
    }

    public String getStanceName() {
        return this.stanceName;
    }
}
