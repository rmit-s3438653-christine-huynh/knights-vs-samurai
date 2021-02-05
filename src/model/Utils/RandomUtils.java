package model.Utils;

import model.Board;
import model.Powerup.PowerupCache;
import model.Powerup.PowerupVisitor;
import model.Tiles.Tile;
import model.Tiles.ValidTiles.ClearTile;

import java.util.ArrayList;
import java.util.Random;

public class RandomUtils {

    private static Random rand = new Random();

    public static Tile getRandomUnoccupiedClearTile(Board gameBoard) {
        Tile returnTile;

        ArrayList<Tile> clearUnpopulatedTiles = new ArrayList<>();
        Tile[][] board = gameBoard.getBoard();

        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                Tile tile = board[y][x];
                if (ClearTile.class.isInstance(tile)) {
                    if (tile.getPiece() == null) {
                        clearUnpopulatedTiles.add(tile);
                    }
                }
            }
        }

        int randIndex = rand.nextInt(clearUnpopulatedTiles.size());
        returnTile = clearUnpopulatedTiles.get(randIndex);
        return returnTile;
	}

	public static PowerupVisitor getRandomPowerup() {
        ArrayList<String> powerUpList = PowerupCache.getPowerupList();
        String randomPowerup = powerUpList.get(rand.nextInt(powerUpList.size()));

        PowerupVisitor powerup;

        powerup = PowerupCache.getPowerup(randomPowerup);

        return powerup;
    }
}
