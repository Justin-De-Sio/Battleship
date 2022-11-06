package ece.fr.model.ship;

public enum Length {
    BATTLESHIP(7), CRUISER(5), DESTROYER(3), SUBMARINE(1);

    private final int length;

    Length(int length) {
        this.length = length;
    }

    public int value() {
        return length;
    }
}
