package model.Tiles;

import model.Pieces.PieceImplementation;
import model.Exceptions.GeneralException;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

public abstract class Tile implements Cloneable {
    public static Color selectedBackgroundColor = Color.orange;
    public static Color notSelectedBackgroundColor = Color.white;
    private Coordinate coordinate;
    private PieceImplementation occupyingPiece;
    private boolean selected;
    private Icon tileIcon;
    private Color backGroundColor;
    private Border border;

    public Tile() {
        this.coordinate = null;
        this.occupyingPiece = null;
        this.selected = false;
        this.tileIcon = null;
        this.backGroundColor = null;
        this.setBorder(new LineBorder(Color.gray));
    }

    // Cloneable
    public Object clone() {
        Object clone;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            throw new IllegalStateException("Tile prototype could not be cloned");
        }
        return clone;
    }

    // SETTERS
    public void setTileIcon(ImageIcon tileIcon) {
        this.tileIcon = tileIcon;
    }

    public void setPiece(PieceImplementation incomingPiece) {
        occupyingPiece = incomingPiece;
    }

    public void setBackGroundColor(Color backGroundColor) {
        this.backGroundColor = backGroundColor;
    }

    public void setBorder(Border border) {
        this.border = border;
    }

    public void setCoordinates(int y, int x) {
        this.coordinate = new Coordinate(y, x);
    }

    public void setFlag(boolean hadFlag) throws GeneralException {
        throw new GeneralException("Cannot set flag on this tile");
    }

    // GETTERS
    public PieceImplementation getPiece() {
        return occupyingPiece;
    }

    public Icon getTileIcon() {
        return tileIcon;
    }

    public Coordinate getCoordinate() {
        return this.coordinate;
    }

    public Border getBorder() {
        return border;
    }

    public Color getBackGroundColor() {
        return backGroundColor;
    }

    // PUBLIC METHODS
    public boolean hasPiece() {
        if (occupyingPiece == null) {
            return false;
        }
        return true;
    }

    public boolean hasFlag() {
        return false;
    }

    public void select() {
        this.selected = true;
    }

    public void unselect() {
        this.selected = false;
    }

    public boolean isSelected() {
        return this.selected;
    }
}
