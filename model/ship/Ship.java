package model.ship;

public abstract class Ship {
    protected boolean isVertical;
    protected Powershot powershot;
    protected Length length;
    protected Direction direction;
    protected ShipType shipType;
    protected int x;
    protected int y;

    protected int strikeCount;

    public Ship(int x, int y, boolean isVertical, Powershot powershot, Length length) {
        this.x = x;
        this.y = y;
        this.isVertical = isVertical;
        this.powershot = powershot;
        this.length = length;
    }


    public boolean isInZone(int x, int y) {
        return x < 15 && y < 15;
    }

    public boolean isSunk() {
        return strikeCount == length.getLength();
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getStrikeCount() {
        return strikeCount;
    }

    public void incrementStrikeCount() {
        this.strikeCount++;
    }
}

