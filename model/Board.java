package model;

import model.ship.*;


// board 15x15
// contain 10 ships
//Les bateaux peuvent bouger d'une case à la fois
public class Board {
    private Square[][] board;
    private Ship[] ships;

    Board() {
        final int BOARD_SIZE = 15;
        final int SHIP_NUMBER = 10;
        this.board = new Square[BOARD_SIZE][BOARD_SIZE];
        this.ships = new Ship[SHIP_NUMBER];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = new Square();
            }
        }


    }

    public void shipCreator(int battleShipNumber, int cruiserNumber, int destroyerNumber, int submarineNumber) {
        int shipNumber = 0;
        for (int i = 0; i < battleShipNumber; i++) {
            ships[shipNumber] = new BattleShip();
            shipNumber++;
        }
        for (int i = 0; i < cruiserNumber; i++) {
            ships[shipNumber] = new Cruiser();
            shipNumber++;
        }
        for (int i = 0; i < destroyerNumber; i++) {
            ships[shipNumber] = new Destroyer();
            shipNumber++;
        }
        for (int i = 0; i < submarineNumber; i++) {
            ships[shipNumber] = new Submarine();
            shipNumber++;
        }
    }

    public Square[][] getBoard() {
        return board;
    }


    public void hit(int x, int y) {
        board[x][y].hit();
    }

    public boolean isHit(int x, int y) {
        return board[x][y].isHit();
    }

    public boolean isContainShip(int x, int y) {
        return board[x][y].isContainShip();
    }

    // take care of orientation and position
    public boolean placeShip(Ship ship, int x, int y) {
        if (ship.isVertical()) {
            if (x + ship.getLength().value() <= 15) {
                for (int i = 0; i < ship.getLength().value(); i++) {
                    board[x + i][y].setContainShip(true);
                    board[x + i][y].setShip(ship);
                }
                return true;
            }
        } else {
            if (y + ship.getLength().value() <= 15) {
                for (int i = 0; i < ship.getLength().value(); i++) {
                    board[x][y + i].setContainShip(true);
                    board[x][y + i].setShip(ship);

                }
                return true;
            }
        }
        return false;
    }


    public void placeShipRandomly(Ship ship) {
        int x = (int) (Math.random() * 15);
        int y = (int) (Math.random() * 15);
        while (!placeShip(ship, x, y)) {
            x = (int) (Math.random() * 15);
            y = (int) (Math.random() * 15);
        }

    }
}
