package model.Tiles.InvalidTiles;

import model.Tiles.Tile;

import javax.swing.border.LineBorder;
import java.awt.*;

public class ObstacleTile extends Tile {

    public ObstacleTile() {
        super();
        this.setBorder(new LineBorder(Color.gray));
        this.setBackGroundColor(Color.red);
    }
}
