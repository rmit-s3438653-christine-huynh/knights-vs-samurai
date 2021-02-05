
package model.Pieces.PieceDecorator;

import model.Pieces.PieceImplementation;

public class LongRangeAttackAbilityDecorator extends PieceAbilityDecorator {

    public LongRangeAttackAbilityDecorator(PieceImplementation piece) {
        super(piece);
    }

    @Override
    public void giveAbility() {
        this.piece.setAttackRange(this.piece.getAttackRange() + 1);
    }
}