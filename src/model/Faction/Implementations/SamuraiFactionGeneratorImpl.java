package model.Faction.Implementations;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import model.Exceptions.DBCError;
import model.Faction.FactionGenerator;
import model.Pieces.PieceDecorator.IncreasedDamageAbilityDecorator;
import model.Pieces.PieceDecorator.ObstacleTraverseAbilityDecorator;
import model.Pieces.PieceDecorator.PieceAbilityDecorator;
import model.Pieces.PieceDecorator.StrongerStanceAbilityDecorator;
import model.Pieces.PieceImplementation;
import model.Tiles.InvalidTiles.ObstacleTile;
import model.Utils.ImageFactory;

public class SamuraiFactionGeneratorImpl extends FactionGenerator {

    public ArrayList<PieceImplementation> generateFaction() {
        ImageFactory imgFactory = new ImageFactory();
        ArrayList<PieceImplementation> samuraiFaction = new ArrayList<>();

        ArrayList<PieceAbilityDecorator> pieceDecoratorArray = new ArrayList<>();

        pieceDecoratorArray.add(new ObstacleTraverseAbilityDecorator(new PieceImplementation("Ninja", 1, imgFactory.getNinjaImage(), imgFactory.getFlaggedNinjaImage())));
        pieceDecoratorArray.add(new StrongerStanceAbilityDecorator(new PieceImplementation("Warlord", 4, imgFactory.getWarlordImage(), imgFactory.getFlaggedWarlordImage())));
        pieceDecoratorArray.add(new IncreasedDamageAbilityDecorator(new PieceImplementation("Warrior", 3, imgFactory.getWarriorImage(), imgFactory.getFlaggedWarriorImage())));

        for (PieceAbilityDecorator piece : pieceDecoratorArray) {
            samuraiFaction.add(piece.getPieceImplementation());
        }

        return samuraiFaction;
    }
}
