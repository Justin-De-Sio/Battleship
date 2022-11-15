package ece.fr.model.ship;

/**
 * un bateau sans specialité
 * 5 cases de long
 * puissance de tir de 4
 */
public class Cruiser extends Ship {
    public Cruiser() {
        super();
        this.length = Length.CRUISER;
        this.shootingPower = ShootingPower.CRUISER;
    }
}
