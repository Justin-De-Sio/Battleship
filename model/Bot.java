package model;

import model.ship.Direction;
import model.ship.Ship;

public class Bot {


    private Board B_board = new Board();

    public void Bot( Board bot){
        this.B_board=bot;
    }
    public Board get_Bboard(){return this.B_board;}
    public void set_Bboard(Board bot){this.B_board=bot;}

    public void move (){
        int rand_boat=(int) (Math.random()*10);//on choisi un bateau random parmis les 10
        int direction=(int)(Math.random()*2);//on choisis parmis les deux direction possible
        if (B_board.getShips()[rand_boat].isVertical()){//on regarde si le bateau est horizontal ou vertical
            if (direction==0){
                B_board.move(Direction.NORTH,B_board.getShips()[rand_boat]);
            }
            else {
                B_board.move(Direction.SOUTH,B_board.getShips()[rand_boat]);
            }
        }
        else{
            if (direction==0){
                B_board.move(Direction.EAST,B_board.getShips()[rand_boat]);
            }
            else {
                B_board.move(Direction.WEST,B_board.getShips()[rand_boat]);
            }
        }
    }


    public void hit(Board Joeur){
        int rand_boat=(int) (Math.random()*10);//on choisi un bateau random parmis les 10
        int randy=(int)(Math.random()*15);
        int randx=(int)(Math.random()*15);
        Ship bateau=B_board.getShips()[rand_boat];
        B_board.hits(bateau,randx,randy,Joeur);
    }
    public boolean hit_or_move(){// si 1 on tir, si 0 on bouge
        int rand=(int)(Math.random()*10);
        if (rand<7){ //on à une 7/10 de faire un tir
            return true;
        }
        else{//on à 2/10 de bouger
            return false;
        }

    }

}
