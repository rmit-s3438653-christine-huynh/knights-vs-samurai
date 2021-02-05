package model;

import model.Exceptions.InvalidTileCoordinatesException;
import model.Tiles.InvalidTiles.NullTile;
import model.Tiles.InvalidTiles.ObstacleTile;
import model.Tiles.Tile;
import model.Tiles.TileCache;
import model.Tiles.ValidTiles.ClearTile;
import model.Tiles.ValidTiles.FlagTile;
import model.Tiles.ValidTiles.PowerupTile;
import model.Utils.RandomUtils;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Board {
    private static final String CLEAR_TILE = "ClearTile";
    private static final String OBSTACLE_TILE = "ObstacleTile";
    private static final String NULL_TILE = "NullTile";
    private static final String FLAG_TILE = "FlagTile";
    private static final String POWERUP_TILE = "PowerupTile";
    // Limit 3 per board
    private static final int POWER_UP_LIMIT = 3;

    private int height;
    private int width;
    private int numberOfObstacles;
    private int nullTileCount;
    private Tile[][] board;

    public Board(int height, int width, int numberOfObstacles) {
        TileCache.loadCache();
        //TODO Add in variable and logic in the case there are more than 2 players
        this.height = height;
        this.width = width;
        this.numberOfObstacles = numberOfObstacles;
        this.nullTileCount = (height * width) / 8; //TODO better calculation for Null to valid tile ratio
        this.board = new Tile[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                /*
                 * TODO
                 *  - add logic for safety barrier & type changing
                 *  - Randomisation of obstacles (types)
                 */
                // Generate Clone Of ClearTile
                ClearTile clearTile = (ClearTile) TileCache.getTile(CLEAR_TILE);
                clearTile.setCoordinates(y, x);
                if (y == 0 || y == height - 1 || x == 0 || x == width - 1) {
                    if ((y == 0 && x == 0) ||
                            (y == 0 && x == width - 1) ||
                            (y == height - 1 && x == 0) ||
                            (y == height - 1 && x == width - 1)
                    ) {
                        board[y][x] = clearTile;
                    } else {
                        board[y][x] = clearTile;
                    }
                } else {
                    board[y][x] = clearTile;
                }
            }
        }
        setRandomNullTiles();
        setRandomObstacles();
        setRandomPowerupTiles();
    }

    private void setRandomObstacles() {
        if (this.numberOfObstacles < 0) {
            throw new IllegalArgumentException("Cannot have negative number of obstacles");
        }
        for (int i = 0; i < this.numberOfObstacles; i++) {
            boolean tileSet = false;
            while (!tileSet) {
                int randX = getRandomInt(this.width);
                int randY = getRandomInt(this.height);
                if (ClearTile.class.isInstance(board[randY][randX])) {
                    ObstacleTile obstacleTile = (ObstacleTile) TileCache.getTile(OBSTACLE_TILE);
                    obstacleTile.setCoordinates(randY, randX);
                    board[randY][randX] = obstacleTile;
                    tileSet = true;
                }
            }
        }
    }


    //TODO Remove this, will be replaced with setDifficulty() in assignment 2
    private void setRandomNullTiles() {
        if (this.nullTileCount < 0) {
            throw new IllegalArgumentException("Cannot have negative number of null tiles");
        }
        for (int i = 0; i < this.nullTileCount; i++) {
            boolean tileSet = false;
            while (!tileSet) {
                int randX = getRandomInt(this.width);
                int randY = getRandomInt(this.height);
                if (ClearTile.class.isInstance(board[randY][randX])) {
                    NullTile nullTile = (NullTile) TileCache.getTile(NULL_TILE);
                    nullTile.setCoordinates(randY, randX);
                    board[randY][randX] = nullTile;
                    tileSet = true;
                }
            }
        }
    }

    private void setRandomPowerupTiles() {
        // Implement at most 3 per game
        for (int i = 0; i < POWER_UP_LIMIT; i++) {
            Tile randomClearTile = RandomUtils.getRandomUnoccupiedClearTile(this);
            PowerupTile powerupTile = (PowerupTile) TileCache.getTile(POWERUP_TILE);
            int yCoordinate = randomClearTile.getCoordinate().y();
            int xCoordinate = randomClearTile.getCoordinate().x();
            powerupTile.setCoordinates(yCoordinate, xCoordinate);
            board[yCoordinate][xCoordinate] = powerupTile;
        }
    }

    private int getRandomInt(int limit) {
        Random r = new Random();
        return r.nextInt(limit);
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public Tile[][] getBoard() {
        return this.board;
    }

    public Tile getTileByCoordinates(int yAxis, int xAxis) throws InvalidTileCoordinatesException {
        if (intBetweenCoordinateRange(this.height, yAxis) &&
                intBetweenCoordinateRange(this.width, xAxis)) {
            return board[yAxis][xAxis];
        } else {
            throw new InvalidTileCoordinatesException(yAxis, xAxis);
        }
    }

    public FlagTile generateAFlagTile(ImageIcon flagImage, Color teamColor) {
        Tile potentialFlagTile;
        potentialFlagTile = RandomUtils.getRandomUnoccupiedClearTile(this);

        FlagTile flagTile = (FlagTile) TileCache.getTile(FLAG_TILE);
        flagTile.setTileIcon(flagImage);
        flagTile.setOriginalFlagIcon(flagImage);
        flagTile.setBackGroundColor(teamColor);
        flagTile.setCoordinates(potentialFlagTile.getCoordinate().y(), potentialFlagTile.getCoordinate().x());

        this.overwriteTileOnBoard(potentialFlagTile.getCoordinate().y(), potentialFlagTile.getCoordinate().x(), flagTile);
        return flagTile;
    }

    private void overwriteTileOnBoard(int yAxis, int xAxis, Tile newTile) {
        board[yAxis][xAxis] = newTile;
    }

    private boolean intBetweenCoordinateRange(int maximum, int number) {
        int minimum = 0;
        if (number >= minimum || number <= maximum) {
            return true;
        }
        return false;
    }
}
