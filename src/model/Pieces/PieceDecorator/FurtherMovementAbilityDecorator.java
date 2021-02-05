package model.Pieces.PieceDecorator;

import model.Pieces.PieceImplementation;

public class FurtherMovementAbilityDecorator extends PieceAbilityDecorator {

    public FurtherMovementAbilityDecorator(PieceImplementation piece) {
        super(piece);
    }

    @Override
    public void giveAbility() {
        this.piece.setMovementRange(this.piece.getMovementRange() + 1);
    }

}
