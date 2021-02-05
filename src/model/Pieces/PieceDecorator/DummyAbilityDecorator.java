package model.Pieces.PieceDecorator;

import model.Pieces.PieceImplementation;

public class DummyAbilityDecorator extends PieceAbilityDecorator {

    public DummyAbilityDecorator(PieceImplementation piece) {
        super(piece);
    }

    public void giveAbility() {
    }
}
