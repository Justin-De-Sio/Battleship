package model;

import model.ship.*;


// board 15x15
// contain 10 ships
//Les bateaux peuvent bouger d'une case à la fois
public class Board {
    private final Ship[][] board;
    private final Ship[] ships;


    public Board() {
        final int BOARD_SIZE = 15;
        final int SHIP_NUMBER = 10;
        this.board = new Ship[BOARD_SIZE][BOARD_SIZE];
        this.ships = new Ship[SHIP_NUMBER];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = new Ship() {
                };
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
                    board[x + i][y] = ship;
                }
                return true;
            }
        } else {
            if (y + ship.getLength().value() <= 15) {
                for (int i = 0; i < ship.getLength().value(); i++) {
                    ship.addCoordinates(x, y + i);
                    board[x][y + i] = ship;
                }
                return true;
            }
        }
        return false;
    }

    public void removeShip(Ship ship) {
     // TODO
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



    public Ship[] getShips() {
        return ships;
    }

    public Ship getShip(int xAttacker, int yAttacker) {
        return board[xAttacker][yAttacker];
    }

    public Ship[][] getBoard() {
        return board;
    }

    public void hits(int xAttacker, int yAttacker, int xTarget, int yTarget, Board victim) {
        Ship shipAttacker = this.getBoard()[xAttacker][yAttacker];

        switch (shipAttacker.getPowershot().value()) {
            case 1: {
                shipAttacker.hit(victim.getBoard()[xTarget][yTarget]);

            }
            case 4: {
                for (int y = 0; y < 2; y++) {
                    for (int x = 0; x < 2; x++) {
                        shipAttacker.hit(victim.getBoard()[xTarget + x][yTarget + y]);
                    }
                }

            }
            case 9: {
                xTarget--;//je me positione en haut à droite
                yTarget--;
                for (int y = 0; y < 3; y++) {
                    for (int x = 0; x < 3; x++) {
                        shipAttacker.hit(victim.getBoard()[xTarget + x][yTarget + y]);
                    }
                }
            }


        }
    }
}
