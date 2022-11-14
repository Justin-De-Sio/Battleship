package ece.fr.model;
import ece.fr.model.ship.*;
import ece.fr.model.ship.Direction;

public class BOT {
    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    Board board;

    public  BOT(Board B_bot) {
        this.board = B_bot;
    }



        public void move () {
            try {
                int rand_boat =(int) (Math.random() * 10);//on choisi un bateau random parmis les 10
                int direction = (int) (Math.random() * 2);
                System.out.println(board.getShipsList()[rand_boat]);
                if (board.getShipsList()[rand_boat].isVertical()) {//on regarde si le bateau est horizontal ou vertical
                    if (direction == 0) {
                        board.moveShip(board.getShipsList()[rand_boat], Direction.NORTH);
                    } else {
                        board.moveShip(board.getShipsList()[rand_boat], Direction.SOUTH);
                    }
                } else {
                    if (direction == 0) {
                        board.moveShip(board.getShipsList()[rand_boat], Direction.EAST);
                    } else {
                        board.moveShip(board.getShipsList()[rand_boat], Direction.WEST);
                    }
                }
            } catch (IllegalArgumentException e) {
                move();
            }
        }

        public void  hitBot (Board Joeur){
            int rand_boat = (int) (Math.random() * 10);//on choisi un bateau random parmis les 10
            int randy = (int) (Math.random() * 15);
            int randx = (int) (Math.random() * 15);
            Ship bateau = board.getShipsList()[rand_boat];
            board.shoots(bateau, randx, randy, Joeur);
        }
        public boolean canMove( ){
            for (int i=0;i<board.getShipsList().length;i++){
                if (board.getShipsList()[i].getStrikeCount()==0){
                    return true;
                }
            }
            return false;
        }
        public boolean hit_or_move () {// si 1 on tir, si 0 on bouge
            int rand = (int) (Math.random() * 10);
            if (rand <=7) { //on à une 7/10 de faire un tir
              return true;
            } else {//on à 2/10 de bouger
                return !canMove();
            }
        }

    }

