/*package model._ModelTests;

import model.Exceptions.InvalidPlayerChosenPieceException;
import model.Exceptions.InvalidPlayerPieceMovementException;
import model.Implementations.GameEngineImpl;
import model.Implementations.PieceActionImpl;
import model.Faction.Implementations.SamuraiFactionGeneratorImpl;
import model.Interfaces.GameEngine;
import model.Interfaces.PieceActions;
import model.Pieces.PieceImplementation;
import model.Player;
import model.Tiles.InvalidTiles.ObstacleTile;
import model.Tiles.Tile;
import model.Tiles.ValidTiles.ClearTile;
import model.Utils.ImageFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class moveTest {

    private GameEngine gameEngine;
    private PieceActions pieceActions;
    private Player player;
    private Tile originTile;
    private Tile destinationTile;
    @Before
    public void before() {
        gameEngine = new GameEngineImpl();
        pieceActions = new PieceActionImpl(gameEngine);
        player = new Player();
        player.setPlayerPieces(new SamuraiFactionGeneratorImpl().generateFaction());
        originTile = new ClearTile(0, 0);
        destinationTile = new ClearTile (1, 1);
    }

    // Piece Actions tests

    @Test
    public void pieceActionMovePiece() throws Exception {
        ImageFactory imgFactory = new ImageFactory();
        ArrayList<Class> ninjaValidTiles = new ArrayList<>();
        ninjaValidTiles.add(ObstacleTile.class);

        originTile.setPiece(new PieceImplementation("Ninja", 1, imgFactory.getNinjaImage(), ninjaValidTiles));

        pieceActions.movePiece(originTile, destinationTile);

        assert (!originTile.hasPiece());
        assert (destinationTile.hasPiece());
    }

    @Test(expected = IllegalStateException.class)
    public void pieceActionsInvariantDBC() {

        pieceActions = new PieceActionImpl(null);
    }

    @Test(expected = InvalidPlayerPieceMovementException.class)
    public void pieceActionInvalidMoveOutOfRange() throws Exception {

        // This unit has a movement range of 1 tile away
        ImageFactory imgFactory = new ImageFactory();
        ArrayList<Class> ninjaValidTiles = new ArrayList<>();
        ninjaValidTiles.add(ObstacleTile.class);

        originTile.setPiece(new PieceImplementation("Ninja", 1, imgFactory.getNinjaImage(), ninjaValidTiles));
        destinationTile = new ClearTile(10, 10);

        pieceActions.movePiece(originTile, destinationTile);
    }

    // Game Engine Tests

    @Test(expected = IllegalArgumentException.class)
    public void gameEngineMovePreconditionsDBC() throws Exception {
        gameEngine.movePiece(null,null, null);
    }

    @Test(expected = InvalidPlayerChosenPieceException.class)
    public void gameEnginePlayerChoseInvalidPieceToMove() throws Exception {
        // Using a knight faction piece where the player is of a samurai faction

        ImageFactory imgFactory = new ImageFactory();

        ArrayList<Class> noValidTiles = new ArrayList<>();

        originTile.setPiece(new PieceImplementation("Spearman", 3, imgFactory.getSpearmanImage(), noValidTiles));

        destinationTile = new ClearTile(1,1);
        gameEngine.movePiece(player, originTile, destinationTile);
    }

}*/
