package model.Pieces.PieceDecorator;

import model.Pieces.PieceImplementation;
import model.Tiles.InvalidTiles.ObstacleTile;

public class ObstacleTraverseAbilityDecorator extends PieceAbilityDecorator {

    public ObstacleTraverseAbilityDecorator(PieceImplementation piece) {
        super(piece);
    }

    @Override
    public void giveAbility() {
        this.piece.getValidTilesThisPieceCanMoveOnto().add(ObstacleTile.class);
    }
}
