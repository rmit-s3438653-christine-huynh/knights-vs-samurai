package model.Pieces.PieceDecorator;

import model.Pieces.PieceImplementation;

public class AdditionalHealthAbilityDecorator extends PieceAbilityDecorator {

    public AdditionalHealthAbilityDecorator(PieceImplementation piece) {
        super(piece);
    }

    @Override
    public void giveAbility() {
        int newHealth = this.piece.getHealthPool() + 1;
        this.piece.setHealthPool(newHealth);
        this.piece.setHealthPoolBank(newHealth);
    }

}
