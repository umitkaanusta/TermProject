package TermProject.util;

import TermProject.tiles.Free;
import TermProject.tiles.Tile;

public class BoardUtils {

    public static final int BOARD_SIZE = 400;

    public static Tile getTileFromCursor(Tile[][] board, double xLoc, double yLoc) {
        int xCord = (int) (xLoc - 1) / (BOARD_SIZE / 4);
        int yCord = (int) (yLoc - 1) / (BOARD_SIZE / 4);
        if(!checkInside(xCord, yCord)) {
            return null;
        }
        return board[xCord][yCord];
    }

    static boolean checkInside(int x, int y) {
        boolean falseCase = x < 0 || x > 3 || y < 0 || y > 3;
        return falseCase ? false : true;
    }

    public static boolean checkSwap(Tile tile1, Tile tile2) {
        boolean temp = false;
        if(!(tile1 instanceof Free || tile2 instanceof Free))
            temp = false;

        if (!tile1.isStatic() && tile2 instanceof Free)
            temp = true;

       if(Math.abs(tile1.getxCord() - tile2.getxCord()) + Math.abs(tile1.getYCord() - tile2.getYCord()) !=1)
           temp = false;

        return temp;
    }

}
