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

        this.board = new Ship[BOARD_SIZE][BOARD_SIZE];

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = null;

            }
        }
        int totalBattleship = 2;
        int totalCruiser = 2;
        int totalDestroyer = 2;
        int totalSubmarine = 2;


        this.ships = shipCreator(totalBattleship, totalCruiser, totalDestroyer, totalSubmarine);
        for (Ship ship : ships) {
            placeShipRandomly(ship);
        }

    }


    public Ship[] shipCreator(int battleShipNumber, int cruiserNumber, int destroyerNumber, int submarineNumber) {
        int shipNumber = 0;
        Ship[] ships = new Ship[battleShipNumber + cruiserNumber + destroyerNumber + submarineNumber];
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
        return ships;
    }


    public boolean isSunk(int x, int y) {
        return board[x][y].isSunk();
    }


    public boolean placeShip(Ship ship, int x, int y) {
        return placeShip(ship, x, y, ship.isVertical());
    }

    // take care of orientation and position
    public boolean placeShip(Ship ship, int x, int y, boolean isVertical) {
        ship.setIsVertical(isVertical);
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

    public int[] removeShip(Ship ship) {
        int[] coordinates = new int[2];
        for (int i = 0; i < ship.getLength().value(); i++) {
            coordinates = ship.getCoordinates().get(i);
            board[coordinates[0]][coordinates[1]] = null;
        }
        return coordinates;
    }


    public void placeShipRandomly(Ship ship) {
        int x = (int) (Math.random() * 15);
        int y = (int) (Math.random() * 15);
        boolean isVertical = Math.random() < 0.5;
        while (!placeShip(ship, x, y, isVertical)) {
            x = (int) (Math.random() * 15);
            y = (int) (Math.random() * 15);

        }
    }


    //le bateau peut bouger d'une case à la fois
    // le bateau sera remove de l'ancienne position et placé à la nouvelle
    public void moveShip(Ship ship, Direction direction) {

    }


    public Ship[] getShips() {
        return ships;
    }

    public Ship[][] getBoard() {
        return board;
    }

    public void shoots(Ship shipAttacker, int xTarget, int yTarget, Board victim) {


        switch (shipAttacker.getPowershot().value()) {
            case 1: {
                shipAttacker.shoot(victim.getBoard()[xTarget][yTarget]);

            }
            case 4: {
                for (int y = 0; y < 2; y++) {
                    for (int x = 0; x < 2; x++) {
                        shipAttacker.shoot(victim.getBoard()[xTarget + x][yTarget + y]);
                    }
                }

            }
            case 9: {
                xTarget--;//je me positione en haut à droite
                yTarget--;
                for (int y = 0; y < 3; y++) {
                    for (int x = 0; x < 3; x++) {
                        shipAttacker.shoot(victim.getBoard()[xTarget + x][yTarget + y]);
                    }
                }
            }


        }
    }
}
