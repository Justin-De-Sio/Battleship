package ece.fr.model.ship;

/**
 * Le destroyer est un beateau ayant comme spécialité une fusee eclairante
 * Son tir a une puissance de 1 et le bateau fait 3 cases
 */
public class Destroyer extends Ship {
    private boolean fusee;

    public Destroyer() {
        super();
        this.fusee = true;
        this.length = Length.DESTROYER;
        this.shootingPower = ShootingPower.DESTROYER;

    }

    public boolean isFusee() {
        return fusee;
    }

    public void setFusee(boolean fusee) {
        this.fusee = fusee;
    }

}
