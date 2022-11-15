package ece.fr.model.ship;

/**
 * Création du Destroyer
 */
public class Destroyer extends Ship {
    public Destroyer() {
        super();
        this.fusee=true;
        this.length = Length.DESTROYER;
        this.shootingPower = ShootingPower.DESTROYER;

    }

}
