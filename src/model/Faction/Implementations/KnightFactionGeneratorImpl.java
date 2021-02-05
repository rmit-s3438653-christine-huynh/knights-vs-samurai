package model.Faction.Implementations;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import model.Exceptions.DBCError;
import model.Faction.FactionGenerator;
import model.Pieces.PieceDecorator.AdditionalHealthAbilityDecorator;
import model.Pieces.PieceDecorator.FurtherMovementAbilityDecorator;
import model.Pieces.PieceDecorator.LongRangeAttackAbilityDecorator;
import model.Pieces.PieceDecorator.PieceAbilityDecorator;
import model.Pieces.PieceImplementation;
import model.Utils.ImageFactory;

public class KnightFactionGeneratorImpl extends FactionGenerator {


    public ArrayList<PieceImplementation> generateFaction() {
        ImageFactory imgFactory = new ImageFactory();
        ArrayList<PieceImplementation> knightFaction = new ArrayList<>();

        ArrayList<PieceAbilityDecorator> pieceDecoratorArray = new ArrayList<>();
        pieceDecoratorArray.add(new LongRangeAttackAbilityDecorator(new PieceImplementation("Archer", 2, imgFactory.getArcherImage(), imgFactory.getFlaggedArcherImage())));
        pieceDecoratorArray.add(new AdditionalHealthAbilityDecorator(new PieceImplementation("Spearman", 3, imgFactory.getSpearmanImage(), imgFactory.getFlaggedSpearmanImage())));
        pieceDecoratorArray.add(new FurtherMovementAbilityDecorator(new PieceImplementation("Cavalry", 4, imgFactory.getCavalryImage(), imgFactory.getFlaggedCavalryImage())));

        for (PieceAbilityDecorator piece : pieceDecoratorArray) {
            knightFaction.add(piece.getPieceImplementation());
        }

        return knightFaction;
    }
}
