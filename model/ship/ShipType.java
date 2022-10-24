package model.ship;

public enum ShipType {
    BATTLESHIP(Powershot.BATTLESHIP,Length.BATTLESHIP),
    CRUISER(Powershot.CRUISER,Length.CRUISER),
    DESTROYER(Powershot.DESTROYER,Length.DESTROYER),
    SUBMARINE(Powershot.SUBMARINE,Length.SUBMARINE);
    ShipType(Powershot powershot, Length length) {
        this.powershot= powershot;
        this.length = length;
    }
    private Powershot powershot;
    private Length length;
}
