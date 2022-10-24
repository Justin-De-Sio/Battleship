package model.ship;

public enum ShipType {
    BATTLESHIP(ShootingPower.BATTLESHIP,Length.BATTLESHIP),
    CRUISER(ShootingPower.CRUISER,Length.CRUISER),
    DESTROYER(ShootingPower.DESTROYER,Length.DESTROYER),
    SUBMARINE(ShootingPower.SUBMARINE,Length.SUBMARINE);
    ShipType(ShootingPower shootingPower, Length length) {
        this.shootingPower = shootingPower;
        this.length = length;
    }
    private ShootingPower shootingPower;
    private Length length;

    public ShootingPower getShootingPower() {
        return shootingPower;
    }

    public Length getLength() {
        return length;
    }


}
