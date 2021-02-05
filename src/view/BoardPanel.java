package view;

import controller.GameController;
import controller.listeners.TileActionListener;
import model.Board;
import model.TileButton;
import model.Tiles.Coordinate;
import model.Tiles.Tile;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {
    private TileButton[][] tilePieces;
    private Tile tile;

    private Board gameBoard;

    public BoardPanel(GameController gameController) {
        this.gameBoard = gameController.getGameEngine().getGameBoard();
        int boardHeight = gameBoard.getHeight();
        int boardWidth = gameBoard.getWidth();

        setLayout(new GridLayout(boardHeight, boardWidth));
        setBackground(Color.black);

        tilePieces = new TileButton[boardHeight][boardWidth];

        // generates a square board using JButton array, should connect to Board via
        // view controller
        for (int i = 0; i < tilePieces.length; i++) {
            for (int j = 0; j < tilePieces.length; j++) {
                // try/catch to check what type of tile it is
                try {
                    tile = gameBoard.getTileByCoordinates(i, j);
                    tilePieces[i][j] = new TileButton(tile, gameController);
                    tilePieces[i][j].setBorder(tile.getBorder());
                    tilePieces[i][j].setBackground(tile.getBackGroundColor());
                    if (tile.getTileIcon() != null) {
                        tilePieces[i][j].setIcon(tile.getTileIcon());
                    }
                    if (tile.hasPiece()) {
                        tilePieces[i][j].setIcon(tile.getPiece().getImageIcon());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                add(tilePieces[i][j]);
            }

        }
    }

    // GETTERS
    public TileButton[][] getTilePieces() {
        return tilePieces;
    }

    public TileButton getTileButtonByCoord(Coordinate coord) {
        return tilePieces[coord.y()][coord.x()];
    }
}
