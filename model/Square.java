package model;

import model.ship.Ship;

public class Square {
    private boolean isHit;
    private boolean isContainShip = false;
    private Ship ship = null;

    Square() {
        isHit = false;
        isContainShip = false;
        ship = null;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean isHit) {
        this.isHit = isHit;
    }

    public boolean isContainShip() {
        return isContainShip;
    }

    public void setIsContainShip(boolean isContainShip) {
        this.isContainShip = isContainShip;
    }


    public void hit() {
        isHit = true;
        ship.incrementStrikeCount();
    }

    public void containShip() {
        isContainShip = true;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }


}
