package model;
// board 15x15
// contain 10 ships
//Les bateaux peuvent bouger d'une case à la fois
public class Board {
    private Square[][] board;
    private Ship[] ships;
    Board(){
        board = new Square[15][15];
        ships = new Ship[10];
        for(int i=0;i<15;i++){
            for(int j=0;j<15;j++){
                board[i][j] = new Square();
            }
        }
    }

    public Square[][] getBoard() {
        return board;
    }

    public void placeShip(Ship ship, int x, int y){
        if(ship.isVertical() == 0){
            for(int i=0;i<ship.getLength();i++){
                board[x][y+i].setShip(ship);
                board[x][y+i].containShip();
            }
        }
        else{
            for(int i=0;i<ship.getLength();i++){
                board[x+i][y].setShip(ship);
                board[x+i][y].containShip();
            }
        }
    }

    public void hit(int x, int y){
        board[x][y].hit();
    }

    public boolean isHit(int x, int y){
        return board[x][y].isHit();
    }

    public boolean isContainShip(int x, int y){
        return board[x][y].isContainShip();
    }











}
