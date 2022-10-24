package model.ship;

public class Cruiser extends Ship {
    public Cruiser() {
        super();
        this.length = Length.CRUISER;
        this.shootingPower = ShootingPower.CRUISER;
        this.shipType = ShipType.CRUISER;
    }
}
