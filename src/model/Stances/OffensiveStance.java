package model.Stances;

import model.Pieces.PieceImplementation;

public class OffensiveStance extends Stance {

    public OffensiveStance() {
        super(1, 0, "Offense");
    }

    @Override
    void toggleStance(PieceImplementation piece) {
    }
}
