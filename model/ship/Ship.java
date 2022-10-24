package model.ship;

import java.util.ArrayList;
import java.util.Map;

public abstract class Ship {

    protected boolean isVertical;
    protected ShootingPower shootingPower;
    protected Length length;

    protected ShipType shipType;
    protected ArrayList<int[]> coordinates;

    protected int strikeCount;

    public Ship() {

        this.isVertical = Math.random() < 0.5;
        this.strikeCount = 0;
        this.coordinates = new ArrayList<int[]>();
    }
    public void addCoordinates(int x, int y) {
        int[] coordinate = {x, y};
        coordinates.add(coordinate);
    }

    public ArrayList<int[]> getCoordinates() {
        return coordinates;
    }
    public boolean isVertical() {
        return isVertical;
    }

    public ShootingPower getPowershot() {
        return shootingPower;
    }

    public Length getLength() {
        return length;
    }

    // add coordinates to the ship




    public ShipType getShipType() {
        return shipType;
    }

    public boolean isInZone(int x, int y) {
        return x < 15 && y < 15;
    }

    public boolean isSunk() {
        return strikeCount == length.value();
    }


    public int getStrikeCount() {
        return strikeCount;
    }

    public void incrementStrikeCount() {
        this.strikeCount++;
    }
}

