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
        public Board move () {
            try {
                int rand_boat =0;// (int) (Math.random() * 10);//on choisi un bateau random parmis les 10
                int direction = (int) (Math.random() * 2);

                if (BoardBot.getShipsList()[rand_boat].isVertical()) {//on regarde si le bateau est horizontal ou vertical
                    if (direction == 0) {
                        BoardBot.moveShip(BoardBot.getShipsList()[rand_boat], Direction.NORTH);
                    } else {
                        BoardBot.moveShip(BoardBot.getShipsList()[rand_boat], Direction.SOUTH);
                    }
                } else {
                    if (direction == 0) {
                        BoardBot.moveShip(BoardBot.getShipsList()[rand_boat], Direction.EAST);
                    } else {
                        BoardBot.moveShip(BoardBot.getShipsList()[rand_boat], Direction.WEST);
                    }
                }
            } catch (IllegalArgumentException e) {
                move();
            }
            return BoardBot;
        }

        public void hitBot (Board Joeur){
            int rand_boat = (int) (Math.random() * 10);//on choisi un bateau random parmis les 10
            int randy = (int) (Math.random() * 15);
            int randx = (int) (Math.random() * 15);
            Ship bateau = BoardBot.getShipsList()[rand_boat];
            BoardBot.shoots(bateau, randx, randy, Joeur);
        }
        public boolean hit_or_move () {// si 1 on tir, si 0 on bouge
            int rand = (int) (Math.random() * 10);
            if (rand ==7) { //on à une 7/10 de faire un tir
                return true;
            } else {//on à 2/10 de bouger
                return false;
            }
        }

    }

