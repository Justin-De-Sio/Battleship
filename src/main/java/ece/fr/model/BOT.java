package ece.fr.model;
import ece.fr.model.Board;
import ece.fr.model.ship.*;
import ece.fr.model.ship.Direction;

import java.awt.*;
public class BOT {
    Board BoardBot = new Board();

    public  BOT(Board B_bot) {
        this.BoardBot = B_bot;
    }

       public Board get_BoardBot () {
            return this.BoardBot;
        }
         public void set_BoardBot (Board bot){
            this.BoardBot = bot;
        }
        public Board move (Board board2) {
            try {
                int rand_boat =(int) (Math.random() * 10);//on choisi un bateau random parmis les 10
                int direction = (int) (Math.random() * 2);
                System.out.println(board2.getShipsList()[rand_boat]);
                if (board2.getShipsList()[rand_boat].isVertical()) {//on regarde si le bateau est horizontal ou vertical
                    if (direction == 0) {
                        board2.moveShip(board2.getShipsList()[rand_boat], Direction.NORTH);
                    } else {
                        board2.moveShip(board2.getShipsList()[rand_boat], Direction.SOUTH);
                    }
                } else {
                    if (direction == 0) {
                        board2.moveShip(board2.getShipsList()[rand_boat], Direction.EAST);
                    } else {
                        board2.moveShip(board2.getShipsList()[rand_boat], Direction.WEST);
                    }
                }
            } catch (IllegalArgumentException e) {
                move(board2);
            }
            return board2;
        }

        public void  hitBot (Board Joeur,Board board2){
            int rand_boat = (int) (Math.random() * 10);//on choisi un bateau random parmis les 10
            int randy = (int) (Math.random() * 15);
            int randx = (int) (Math.random() * 15);
            Ship bateau = board2.getShipsList()[rand_boat];
            board2.shoots(bateau, randx, randy, Joeur);
        }
        public boolean verifShip(Board board2){
            for (int i=0;i<3;i++){
                if (board2.getShipsList()[i].getStrikeCount()==0){
                    return true;
                }
            }
            return false;
        }
        public boolean hit_or_move (Board board2) {// si 1 on tir, si 0 on bouge
            int rand = (int) (Math.random() * 10);
            if (rand ==7) { //on à une 7/10 de faire un tir
              return true;
            } else {//on à 2/10 de bouger
                if(verifShip(board2)){
                    return false;
                }
                else{
                    return true;
                }
            }
        }

    }

