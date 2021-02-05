package model.Tiles.InvalidTiles;

import model.Tiles.Tile;

import javax.swing.border.LineBorder;
import java.awt.*;

public class NullTile extends Tile {

    public NullTile() {
        super();
        this.setBorder(new LineBorder(Color.black));
        this.setBackGroundColor(Color.black);
    }
}
