package model;

import controller.GameController;
import controller.listeners.TileActionListener;
import model.Tiles.Tile;
import model.Tiles.ValidTiles.FlagTile;
import model.Tiles.ValidTiles.PowerupTile;

import javax.swing.JButton;
import javax.swing.*;
import java.awt.*;

public class TileButton extends JButton {
    private Tile tile;

    public TileButton(Tile tile, GameController gameController) {
        super();
        this.tile = tile;
        this.addActionListener(new TileActionListener(gameController, this));
        this.setOpaque(true);
    }

    public void render() {
        // Render based on state of tile
        this.setBorder(tile.getBorder());
        this.setBackground(tile.getBackGroundColor());
        if (tile.getTileIcon() != null) {
            this.setIcon(tile.getTileIcon());
        } else {
            this.setIcon(null);
        }

        if (tile.hasPiece()) {
            this.setIcon(tile.getPiece().getImageIcon());
        } else if (tile.hasFlag()) {
            this.setIcon(((FlagTile) tile).getOriginalFlagIcon());
        } else {
            this.setIcon(null);
        }

        if (PowerupTile.class.isInstance(tile) && tile.hasPiece()) {
            this.setBackground(Color.white);
        }
        if (tile.isSelected()) {
            this.setBackground(Tile.selectedBackgroundColor);
        }
        this.validate();
        this.repaint();
    }

    public Tile getTile() {
        return this.tile;
    }

}
