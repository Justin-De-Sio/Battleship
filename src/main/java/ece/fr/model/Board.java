package ece.fr.model;


import ece.fr.model.ship.*;

// board 15x15
// contain 10 ships
//Les bateaux peuvent bouger d'une case à la fois
public class Board {
    private final Ship[][] board;
    private final Ship[] shipsList;


    public Board() {
        final int BOARD_SIZE = 15;

        this.board = new Ship[BOARD_SIZE][BOARD_SIZE];

        final int totalBattleship = 2;
        final int totalCruiser = 2;
        final int totalDestroyer = 2;
        final int totalSubmarine = 2;
        this.shipsList = shipCreator(totalBattleship, totalCruiser, totalDestroyer, totalSubmarine);
//        placeShipsRandomly(this.shipsList);
        placeShipsForTest(this.shipsList);


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

    void placeShipsForTest(Ship[] shipsList) {
        placeShip(shipsList[0], 0, 0, true);
        placeShip(shipsList[1], 0, 3, true);
        placeShip(shipsList[2], 0, 6, true);
        placeShip(shipsList[3], 3, 7, true);
        placeShip(shipsList[4], 1, 8, false);
        placeShip(shipsList[5], 13, 10, false);
        placeShip(shipsList[6], 14, 5, false);
        placeShip(shipsList[7], 11, 12, false);
    }

    public boolean placeShip(Ship ship, int x, int y) {
        return placeShip(ship, x, y, ship.isVertical());
    }

    // take care of orientation and position
    public boolean placeShip(Ship ship, int x, int y, boolean isVertical) {
        //use isPlaceable

        if (isPlaceable(ship, x, y, isVertical)) {
            ship.setIsVertical(isVertical);
            ship.clearCoordinates();
            ship.addCoordinates(x, y);
            board[x][y] = ship;
            if (isVertical) {
                for (int i = 1; i < ship.getLength().value(); i++) {
                    ship.addCoordinates(x + i, y);
                    board[x + i][y] = ship;
                }
            } else {
                for (int i = 1; i < ship.getLength().value(); i++) {
                    ship.addCoordinates(x, y + i);
                    board[x][y + i] = ship;
                }
            }
            return true;
        }
        return false;
    }

    public boolean isPlaceable(Ship ship, int x, int y, boolean isVertical) {
        // check if ship is placeable
        // check if ship is not out of bound
        // check if ship is not overlapping
// check if ship is not touching another ship

        try {
            if (isVertical) {
                for (int i = 0; i < ship.getLength().value(); i++) {
                    if (board[x + i][y] != null) {
                        return false;
                    }
                }
            } else {
                for (int i = 0; i < ship.getLength().value(); i++) {
                    if (board[x][y + i] != null) {
                        return false;
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }

    public int[] removeShip(Ship ship) {
        int[] coordinates = new int[2];
        for (int i = 0; i < ship.getLength().value(); i++) {
            coordinates = ship.getCoordinates().get(i);
            board[coordinates[0]][coordinates[1]] = null;
        }
        return coordinates;
    }

    public void placeShipsRandomly(Ship[] shipsList) {
        for (Ship ship : shipsList) {
            placeShipRandomly(ship);
        }
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
    public void moveShip( Ship ship, Direction direction) {
        int[] coordinate = ship.getCoordinates().get(0);
        int x = coordinate[0];
        int y = coordinate[1];
        switch (direction) {
            case NORTH:
                if (x > 0) {
                    removeShip(ship);
                    placeShip(ship, x - 1, y);
                }
                break;
            case SOUTH:
                if (x < 14) {
                    removeShip(ship);
                    placeShip(ship, x + 1, y);
                }
                break;
            case EAST:
                if (y < 14) {
                    removeShip(ship);
                    placeShip(ship, x, y + 1);
                }
                break;
            case WEST:
                if (y > 0) {
                    removeShip(ship);
                    placeShip(ship, x, y - 1);
                }
                break;
        }
    }



    public Ship[] getShipsList() {
        return shipsList;
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
