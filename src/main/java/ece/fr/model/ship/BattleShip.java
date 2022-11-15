package ece.fr.model.ship;

/**
 * Un bateau sans specialité
 * 7 cases de long
 * puissance de tir de 9
 */
public class BattleShip extends Ship {
    public BattleShip() {
        super();
        this.length = Length.BATTLESHIP;
        this.shootingPower = ShootingPower.BATTLESHIP;

    }
}
