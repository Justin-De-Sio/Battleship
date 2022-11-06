package ece.fr.model.ship;

import java.util.ArrayList;

public abstract class Ship {

    protected boolean isVertical;
    protected ShootingPower shootingPower;
    protected Length length;

    protected ArrayList<int[]> coordinates;

    protected int strikeCount;

    public Ship() {
        this.strikeCount = 0;
        this.coordinates = new ArrayList<int[]>();
    }

    public void addCoordinates(int x, int y) {
        int[] coordinate = {x, y};
        coordinates.add(coordinate);
    }

    public void setIsVertical(boolean isVertical){
        this.isVertical = isVertical;
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


    public boolean isSunk() {
        return strikeCount == length.value();
    }


    public int getStrikeCount() {
        return strikeCount;
    }

    public void strike() {
        //TODO marker les parties du bateau touché
        this.strikeCount++;
    }

    public boolean shoot(Ship target) {
        Length submarine = Length.SUBMARINE;
        if (target != null) {
            if (this.length == submarine) {
                target.strike();
                return true;
            } else if (target.getLength() != submarine) {
                target.strike();
                return true;
            }
        }
        return false;
    }


}

