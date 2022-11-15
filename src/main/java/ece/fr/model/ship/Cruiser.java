package ece.fr.model.ship;

/**
 * Création du Cruiser
 */
public class Cruiser extends Ship {
    public Cruiser() {
        super();
        this.length = Length.CRUISER;
        this.shootingPower = ShootingPower.CRUISER;
    }
}
