package model.ship;

public class BattleShip extends Ship {
    public BattleShip() {
        super();
        this.length = Length.BATTLESHIP;
        this.shootingPower = ShootingPower.BATTLESHIP;

    }
}
