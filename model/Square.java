package model;

import model.ship.Ship;
import model.ship.ShipType;

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


    public void hit(Square target) {
        this.isHit = true;
        this.ship.hit(target);
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
    public void IncrementStrikeCount(){//je suis pa s très sur
        ship.incrementStrikeCount();
    }
    public void shoot()
    {// fonction shoot
        this.hit();//je hit la casse
        if (this.isContainShip)// je regarde si elle contien un ship
        {
          this.IncrementStrikeCount();//si oui j'increment (voir méthode aux dessu)
          return;
        }
        else{return;}

    }
}
