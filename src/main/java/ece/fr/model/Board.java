package ece.fr.model;


import ece.fr.model.ship.*;

import static java.lang.Math.sqrt;

// board 15x15
// contain 10 ships
//Les bateaux peuvent bouger d'une case à la fois
public class Board {
    private Ship[][] board;
    private Ship[] shipsList;
    private SecondBoard secondBoard;


    public Board() {
        final int BOARD_SIZE = 15;

        this.board = new Ship[BOARD_SIZE][BOARD_SIZE];
        this.secondBoard = new SecondBoard();

        final int totalBattleship = 1;
        final int totalCruiser = 2;
        final int totalDestroyer = 3;
        final int totalSubmarine = 4;
        this.shipsList = shipCreator(totalBattleship, totalCruiser, totalDestroyer, totalSubmarine);
        placeShipsRandomly(this.shipsList);
//        placeShipsForTest(this.shipsList);


    }

    public void setBoard(int x, int y, Ship ship) {
        this.board[x][y] = ship;
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
        placeShip(shipsList[4], 1, 7, false);
        placeShip(shipsList[5], 13, 10, false);
        placeShip(shipsList[6], 14, 5, false);
        placeShip(shipsList[7], 11, 12, false);
        placeShip(shipsList[8], 13, 14, false);
        placeShip(shipsList[9], 0, 14, false);
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
                    if ((board[x + i][y] != null) && (board[x + i][y] != ship)) {
                        return false;
                    }
                }
            } else {
                for (int i = 0; i < ship.getLength().value(); i++) {
                    if ((board[x][y + i] != null) && (board[x][y + i] != ship)) {
                        return false;
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
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
    public void moveShip(Ship ship, Direction direction) throws IllegalArgumentException {
        int[] coordinate = ship.getCoordinates().get(0);
        int x = coordinate[0];
        int y = coordinate[1];
        // take care of length
        if (ship.getStrikeCount() == 0) {
            switch (direction) {
                case NORTH:
                    if (isPlaceable(ship, x - 1, y, ship.isVertical())) {
                        removeShip(ship);
                        placeShip(ship, x - 1, y);
                    } else {
                        throw new IllegalArgumentException(String.valueOf(direction));
                    }
                    break;
                case SOUTH:
                    if (isPlaceable(ship, x + 1, y, ship.isVertical())) {
                        removeShip(ship);
                        placeShip(ship, x + 1, y);
                    } else {
                        throw new IllegalArgumentException(String.valueOf(direction));
                    }
                    break;
                case EAST:
                    if (isPlaceable(ship, x, y + 1, ship.isVertical())) {
                        removeShip(ship);
                        placeShip(ship, x, y + 1);
                    } else {
                        throw new IllegalArgumentException(String.valueOf(direction));
                    }

                    break;
                case WEST:
                    if (isPlaceable(ship, x, y - 1, ship.isVertical())) {
                        removeShip(ship);
                        placeShip(ship, x, y - 1);
                    } else {
                        throw new IllegalArgumentException(String.valueOf(direction));
                    }

                    break;
                default:
                    throw new IllegalArgumentException(String.valueOf(direction));
            }
        } else {
            throw new IllegalArgumentException(String.valueOf(direction));
        }
    }


    public Ship[] getShipsList() {
        return shipsList;
    }

    public void setShipsList(Ship[] shipList) {
        this.shipsList = shipList;
    }

    public Ship[][] getBoard() {
        return board;
    }

    public void setBoard(Ship[][] board) {
        this.board = board;
    }

    public void shoots(Ship shipAttacker, int xTarget, int yTarget, Board victim) {

        int powerShip = (int) sqrt(shipAttacker.getPowershot().value());
        // centrer le point de tir
        int xTargetCenter = (int) (xTarget - (powerShip) / 2);
        int yTargetCenter = (int) (yTarget - (powerShip) / 2);

        // tirer sur la cible
        for (int x = 0; x < powerShip; x++) {
            for (int y = 0; y < powerShip; y++) {


                if (isRealCoordonate(xTargetCenter + x, yTargetCenter + y)
                        && (victim.getBoard()[xTargetCenter + x][yTargetCenter + y] != null)
                        && !victim.getSecondBoard().isStrike(xTargetCenter + x, yTargetCenter + y)
                        && ((victim.getBoard()[xTargetCenter + x][yTargetCenter + y].getLength() != Length.SUBMARINE)
                        || (shipAttacker.getLength() == Length.SUBMARINE))) {
                    victim.getBoard()[xTargetCenter + x][yTargetCenter + y].strike();
                    victim.secondBoard.strike(xTargetCenter + x, yTargetCenter + y);
                }

            }
        }

    }

    private boolean isRealCoordonate(int i, int i1) {
        return (i >= 0 && i <= 14) && (i1 >= 0 && i1 <= 14);
    }

    public SecondBoard getSecondBoard() {
        return secondBoard;
    }

    public void setSecondBoard(SecondBoard s2) {
        this.secondBoard = s2;
    }

}
