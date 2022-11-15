package ece.fr.model.ship;

/**
 * Un sous marrin pouvant être touché que par d'autres sous marrins
 * 1 case
 * puissance de tir de 1
 */
public class Submarine extends Ship {
    public Submarine() {
        super();
        this.length = Length.SUBMARINE;
        this.shootingPower = ShootingPower.SUBMARINE;

    }


}
