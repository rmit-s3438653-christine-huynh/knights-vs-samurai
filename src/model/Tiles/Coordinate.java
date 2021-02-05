package model.Tiles;

public class Coordinate {
    private int x, y;

    public Coordinate(int y, int x) {
        this.x = x;
        this.y = y;
    }

    public int x() {
        return this.x;
    }

    public int y() {
        return this.y;
    }

    public boolean equals(Coordinate coord){
        if (coord.x() == this.x() && coord.y() == this.y()) {
            return true;
        }
        return false;
    }

    public void print() {
        System.out.printf("Tile with coordinates X:%d | Y:%d\n", x, y);
    }
}
