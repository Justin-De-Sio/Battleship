package ece.fr.model.ship;

import java.util.ArrayList;

public abstract class Ship implements java.io.Serializable {

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


    public void clearCoordinates() {
        coordinates.clear();
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
        this.strikeCount++;
    }

    public boolean isFusee() {
        return fusee;
    }

    public void setFusee() {
        this.fusee = false;
    }

    protected boolean fusee=false;

}

