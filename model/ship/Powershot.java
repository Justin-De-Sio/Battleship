package model.ship;

public enum Powershot {
    BATTLESHIP(9),
    CRUISER(4),
    DESTROYER(1),
    SUBMARINE(1);
    private final int powershot;

    Powershot(int powershot) {
        this.powershot = powershot;
    }

    public int getPowershot() {
        return powershot;
    }
}