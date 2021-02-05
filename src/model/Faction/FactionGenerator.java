package model.Faction;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import model.Exceptions.DBCError;
import model.Pieces.PieceImplementation;

public abstract class FactionGenerator {

    private Random rand = new Random();

    // Error Logging
    private static String methodName;

    public abstract ArrayList<PieceImplementation> generateFaction();

    // maximum of 6 pieces per player
    // @ensures (pieces.size() == pieceCount)
    public ArrayList<PieceImplementation> generateFaction(int pieceCount) {
        methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

        ArrayList<PieceImplementation> pieces = generateFaction();
        AtomicInteger originalTeamSize = new AtomicInteger(pieces.size());
        int piecesToMake = pieceCount - originalTeamSize.get();

        if (piecesToMake > 0) {
            for (int i = 0; i < piecesToMake; i++) {
                PieceImplementation randPieceToDuplicate = pieces.get(rand.nextInt(originalTeamSize.get()));
                pieces.add((PieceImplementation) randPieceToDuplicate.clone());
            }
        }

        if (pieces.size() != pieceCount) {
            throw new AssertionError(DBCError.getPostConditionErrorMessage(methodName));
        }
        return pieces;
    }
}
