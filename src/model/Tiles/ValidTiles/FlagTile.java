package model.Tiles.ValidTiles;

import model.Tiles.Tile;

import javax.swing.*;
import java.awt.*;

public class FlagTile extends Tile {

    private boolean hasFlag;
    private ImageIcon originalFlagIcon;

    public FlagTile() {
        super();
        this.hasFlag = true;
    }

    public boolean hasFlag() {
        return hasFlag;
    }

    @Override
    public void setFlag(boolean hasFlag) {
        this.hasFlag = hasFlag;
    }

    public void setOriginalFlagIcon(ImageIcon image) { this.originalFlagIcon = image; }

    public ImageIcon getOriginalFlagIcon() {return this.originalFlagIcon;}

}
