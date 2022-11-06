package src.model.ship;

public enum ShootingPower {
    BATTLESHIP(9),
    CRUISER(4),
    DESTROYER(1),
    SUBMARINE(1);
    private final int shootingPower;

    ShootingPower(int shootingPower) {
        this.shootingPower = shootingPower;
    }

    public int value() {
        return shootingPower;
    }
}