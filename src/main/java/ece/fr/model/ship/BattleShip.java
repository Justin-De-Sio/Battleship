package ece.fr.model.ship;

/**
 * Création du battleShip
 */
public class BattleShip extends Ship {
    public BattleShip() {
        super();
        this.length = Length.BATTLESHIP;
        this.shootingPower = ShootingPower.BATTLESHIP;

    }
}
