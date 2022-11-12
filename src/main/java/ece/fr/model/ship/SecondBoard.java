package ece.fr.model.ship;

/**
 * @author Justin
 * savegarde les tires touchés
 * 15x15
 */
public class SecondBoard {
    boolean[][] board;

    public SecondBoard() {
        board = new boolean[15][15];
    }

    public void strike(int x, int y) {
        board[x][y] = true;
    }

    public boolean isStrike(int x, int y) {
        return board[x][y];
    }

    public void clear() {
        board = new boolean[15][15];
    }

    public boolean[][] getBoard() {
        return board;
    }




}
