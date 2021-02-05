package model.Stances;

import model.Pieces.PieceImplementation;

public class DefensiveStance extends Stance {

    public DefensiveStance() {
        super(0, 1, "Defense");
    }

    @Override
    void toggleStance(PieceImplementation piece) {
    }
}
