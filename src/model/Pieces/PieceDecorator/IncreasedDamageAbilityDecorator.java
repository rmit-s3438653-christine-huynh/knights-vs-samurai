package model.Pieces.PieceDecorator;

import model.Pieces.PieceImplementation;

public class IncreasedDamageAbilityDecorator extends PieceAbilityDecorator {

    public IncreasedDamageAbilityDecorator(PieceImplementation piece) {
        super(piece);
    }

    @Override
    public void giveAbility() {
        this.piece.setDamageValue(this.piece.getDamageValue() + 1);
    }

}
