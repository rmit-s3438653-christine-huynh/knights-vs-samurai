package model.Tiles;

import model.Tiles.InvalidTiles.NullTile;
import model.Tiles.InvalidTiles.ObstacleTile;
import model.Tiles.ValidTiles.ClearTile;
import model.Tiles.ValidTiles.FlagTile;
import model.Tiles.ValidTiles.PowerupTile;

import java.util.Hashtable;

public class TileCache {
    private static Hashtable<String, Tile> tileMap = new Hashtable<String, Tile>();

    public static Tile getTile(String tileName) {
        Tile cachedTile = tileMap.get(tileName);
        return (Tile) cachedTile.clone();
    }

    // Run This as part of the board construction
    public static void loadCache() {
        // Default Valid Tiles
        ClearTile clearTile = new ClearTile();
        tileMap.put(ClearTile.class.getSimpleName(), clearTile);

        FlagTile flagTile = new FlagTile();
        tileMap.put(FlagTile.class.getSimpleName(), flagTile);

        PowerupTile powerupTile = new PowerupTile();
        tileMap.put(PowerupTile.class.getSimpleName(), powerupTile);

        // Default Invalid Tiles
        NullTile nullTile = new NullTile();
        tileMap.put(NullTile.class.getSimpleName(), nullTile);

        ObstacleTile obstacleTile = new ObstacleTile();
        tileMap.put(ObstacleTile.class.getSimpleName(), obstacleTile);
    }
}
