package ece.fr.model.ship;

import java.util.ArrayList;

/**
 * Création de la classe ship mère abstraite
 */
public abstract class Ship implements java.io.Serializable {

    protected boolean isVertical;
    protected ShootingPower shootingPower;
    protected Length length;

    protected ArrayList<int[]> coordinates;

    protected int strikeCount;
    protected boolean fusee = false;

    public Ship() {
        this.strikeCount = 0;
        this.coordinates = new ArrayList<int[]>();
    }

    /**
     * Donne des coordonnées aux ship
     * @param x
     * @param y
     */
    public void addCoordinates(int x, int y) {
        int[] coordinate = {x, y};
        coordinates.add(coordinate);
    }



    /**
     * Clear les coordonnées d'un ship
     */
    public void clearCoordinates() {
        coordinates.clear();
    }

    public void setIsVertical(boolean isVertical) {
        this.isVertical = isVertical;
    }

    public ArrayList<int[]> getCoordinates() {
        return coordinates;
    }

    /**
     * Permet de savoir la direction d'un ship pour le déplacement
     * @return boolean
     */
    public boolean isVertical() {
        return isVertical;
    }

    public ShootingPower getPowershot() {
        return shootingPower;
    }

    public Length getLength() {
        return length;
    }


    /**
     * Met l'état à sunk si toutes les cases d'un ship est égale a la taille totale du ship
     * @return boolean
     */
    public boolean isSunk() {
        return strikeCount == length.value();
    }


    public int getStrikeCount() {
        return strikeCount;
    }

    public void strike() {
        this.strikeCount++;
    }

    public boolean isFusee() {
        return fusee;
    }

    public void setFusee() {
        this.fusee = false;
    }

}

