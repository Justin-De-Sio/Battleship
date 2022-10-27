package model;

import model.ship.*;


// board 15x15
// contain 10 ships
//Les bateaux peuvent bouger d'une case à la fois
public class Board {
    private final Square[][] board;
    private final Ship[] ships;

    // Retourne la case de la board en x et y, de type Square
    public Square getSquare(int x, int y) {
        return board[x][y];
    }


    public Board() {
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


    public boolean hits(Ship shipAttacker, int xTarget, int yTarget) {
        //je vérifie que la case n'a j'amais été hit
        if (!board[xTarget][yTarget].isHit()) {
            int p_d_tir = shipAttacker.getPowershot().value();
            switch (p_d_tir) {
                case 1: {
                    board[xTarget][yTarget].hit();
                    return true;
                }
                case 4: {
                    for (int yi = 0; yi < 2; yi++) {
                        for (int xi = 0; xi < 2; xi++) {
                            board[xTarget + xi][yTarget + yi].hit();
                        }
                    }
                    return true;
                }
                case 9:{
                    xTarget = xTarget - 1;//je me positione en haut à droite
                    yTarget = yTarget - 1;
                    for (int yi = 0; yi < 3; yi++) {
                        for (int xi = 0; xi < 3; xi++) {
                            board[xTarget + xi][yTarget + yi].hit();
                        }
                    }
                    return true;
                }
                default:{
                    System.out.println("erreur!!!");
                    return false;
                }

            }
        }
         else {// si la casse est déja hit je renvois uen erreur
            System.out.println("erreur!!! vous avez déjas tirer sur cette case");
            return false;
        }
    }


    public boolean isHit(int x, int y) {
        return board[x][y].isHit();
    }


    public boolean isSunk(int x, int y) {
        Square square = board[x][y];
        Ship ship = square.getShip();
        return ship.isSunk();
    }

    // take care of orientation and position
    public boolean placeShip(Ship ship, int x, int y) {
        if (ship.isVertical()) {
            if (x + ship.getLength().value() <= 15) {
                for (int i = 0; i < ship.getLength().value(); i++) {
                    ship.addCoordinates(x + i, y);
                    board[x + i][y].setIsContainShip(true);
                    board[x + i][y].setShip(ship);
                }
                return true;
            }
        } else {
            if (y + ship.getLength().value() <= 15) {
                for (int i = 0; i < ship.getLength().value(); i++) {
                    ship.addCoordinates(x, y + i);
                    board[x][y + i].setIsContainShip(true);
                    board[x][y + i].setShip(ship);
                }
                return true;
            }
        }
        return false;
    }

    public void removeShip(Ship ship) {
        for (int i = 0; i < ship.getLength().value(); i++) {
            int[] coordinate = ship.getCoordinates().get(i);
            board[coordinate[0]][coordinate[1]].setIsContainShip(false);
            board[coordinate[0]][coordinate[1]].setShip(null);
        }
    }


    public void placeShipRandomly(Ship ship) {
        int x = (int) (Math.random() * 15);
        int y = (int) (Math.random() * 15);
        while (!placeShip(ship, x, y)) {
            x = (int) (Math.random() * 15);
            y = (int) (Math.random() * 15);

        }

    }


    //le bateau peut bouger d'une case à la fois
    // le bateau sera remove de l'ancienne position et placé à la nouvelle
    public void move(Direction direction, Ship ship) {
        int[] coordinate = ship.getCoordinates().get(0);
        int x = coordinate[0];
        int y = coordinate[1];
        switch (direction) {
            case NORTH:
                if (x - 1 >= 0) {
                    removeShip(ship);
                    placeShip(ship, x - 1, y);
                }
                break;
            case SOUTH:
                if (x + 1 <= 14) {
                    removeShip(ship);
                    placeShip(ship, x + 1, y);
                }
                break;
            case EAST:
                if (y - 1 >= 0) {
                    removeShip(ship);
                    placeShip(ship, x, y - 1);
                }
                break;
            case WEST:
                if (y + 1 <= 14) {
                    removeShip(ship);
                    placeShip(ship, x, y + 1);
                }
                break;
        }
    }

    public boolean isGameOver() {
        for (Ship ship : ships) {
            if (!ship.isSunk()) {
                return false;
            }
        }
        return true;
    }
}
