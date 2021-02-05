package model.Pieces.PieceDecorator;

import model.Pieces.PieceImplementation;
import model.Stances.DefensiveStance;
import model.Stances.OffensiveStance;

public class StrongerStanceAbilityDecorator extends PieceAbilityDecorator {

    public StrongerStanceAbilityDecorator(PieceImplementation piece) {
        super(piece);
    }

    @Override
    public void giveAbility() {
        PieceImplementation piece = this.piece;
        DefensiveStance dStance = piece.getDefensiveStance();
        OffensiveStance oStance = piece.getOffensiveStance();
        dStance.setDefensiveValue(dStance.getDefensiveValue() + 1);
        oStance.setOffensiveValue(oStance.getOffensiveValue() + 1);
    }
}