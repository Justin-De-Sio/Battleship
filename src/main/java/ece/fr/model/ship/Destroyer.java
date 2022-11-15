package ece.fr.model.ship;

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
