package TermProject.util;

import TermProject.tiles.Pipe;
import TermProject.tiles.PipeStatic;
import TermProject.tiles.Curved;
import TermProject.tiles.Tile;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.PathElement;

import java.util.ArrayList;

public class AnimationUtils {
    public static final int BOARD_SIZE = BoardUtils.BOARD_SIZE;
    private static ArrayList<PathElement> paths = new ArrayList<>();
    static final int LOCATION = 40;

    public static Tile getStarterTile(Tile[][] tiles) {
        Pipe returnTile = null;
        for(int i = 0; i < tiles[0].length; i++) {
            for(int j = 0; j < tiles.length; j++) {
                if(tiles[i][j] instanceof PipeStatic) {
                    PipeStatic tile = (PipeStatic) tiles[i][j];
                    if (tile.isStarter()) {
                        returnTile = (Pipe) tile;
                    }
                }
            }
        }
        return returnTile;
    }

    public static Tile getEndTile(Tile[][] tiles) {
        Pipe returnTile = null;
        for(int i = 0; i < tiles[0].length; i++) {
            for(int j = 0; j < tiles.length; j++) {
                if(tiles[i][j] instanceof PipeStatic) {
                    PipeStatic tile = (PipeStatic) tiles[i][j];
                    if (tile.isEnd()) {
                        returnTile = (Pipe) tile;
                    }
                }
            }
        }
        return returnTile;
    }

    public static boolean checkPath(Tile[][] tiles) {
        LastMove lastMove = null;
        Pipe starterTile = (Pipe) getStarterTile(tiles);
        int xStarter = starterTile.getxCord();
        int yStarter = starterTile.getYCord();

        // Iterate until currentTile == endTile
        while(!(tiles[xStarter][yStarter] == getEndTile(tiles))) {
            Tile currentTile = tiles[xStarter][yStarter];
            // Check whether moving is possible at each if block
            
            // Check going up
            if(checkUp(currentTile) && lastMove != LastMove.DOWN) {
            	if(BoardUtils.checkInside(xStarter, yStarter - 1)
            			&& checkDown(tiles[xStarter][yStarter-1])) {
                    yStarter--;
                    lastMove = LastMove.UP;
                    paths.add(new LineTo(xStarter * 100 + LOCATION, yStarter * 100 + LOCATION));
                    continue;
                }
                else {
                    paths.clear();
                    return false;
                }
            }
            
            // Check going down
            if(checkDown(currentTile) && lastMove != LastMove.UP) {
            	if(BoardUtils.checkInside(xStarter, yStarter + 1)
            			&& checkUp(tiles[xStarter][yStarter+1])) {
                    yStarter++;
                    lastMove = LastMove.DOWN;
                    paths.add(new LineTo(xStarter * 100 + LOCATION, yStarter * 100 + LOCATION));
                    continue;
                }
                else {
                    paths.clear();
                    return false;
                }
            }
            
            // Check going right
            if(checkRight(currentTile) && lastMove != LastMove.LEFT) {
            	if(BoardUtils.checkInside(xStarter + 1, yStarter)
            			&& checkLeft(tiles[xStarter+1][yStarter])) {
                    xStarter++;
                    lastMove = LastMove.RIGHT;
                    paths.add(new LineTo(xStarter * 100 + LOCATION, yStarter * 100 + LOCATION));
                    continue;
                }
                else {
                    paths.clear();
                    return false;
                }
            }
            
            // Check going left
            if(checkLeft(currentTile) && lastMove != LastMove.RIGHT) {
            	if(BoardUtils.checkInside(xStarter - 1, yStarter)
            			&& checkRight(tiles[xStarter-1][yStarter])) {
                    xStarter--;
                    lastMove = LastMove.LEFT;
                    paths.add(new LineTo(xStarter * 100 + LOCATION, yStarter * 100 + LOCATION));
                    continue;
                }
                else {
                    paths.clear();
                    return false;
                }
            }

        }
        // If everything is complete
        return true;
    }

    public static ArrayList<PathElement> getPaths() {
        return paths;
    }

    public static void setPaths(ArrayList<PathElement> paths) {
        AnimationUtils.paths = paths;
    }
    
    public static boolean checkUp(Tile tile) {
    	boolean checkUpPipe = tile instanceof Pipe && !(tile instanceof PipeStatic) && ((Pipe) tile).isVertical();
        boolean checkUpStatic = tile instanceof PipeStatic && ((PipeStatic) tile).isUp();
        boolean checkUpCurved = tile instanceof Curved && ((Curved) tile).getPosition1() == 0;
        boolean checkUp = (checkUpPipe || checkUpStatic || checkUpCurved);
        return checkUp;
    }
    
    public static boolean checkDown(Tile tile) {
    	boolean checkDownPipe = tile instanceof Pipe && !(tile instanceof PipeStatic) && ((Pipe) tile).isVertical();
        boolean checkDownStatic = tile instanceof PipeStatic && !((PipeStatic) tile).isUp();
        boolean checkDownCurved = tile instanceof Curved && ((Curved) tile).getPosition1() == 1;
        boolean checkDown = (checkDownPipe || checkDownStatic || checkDownCurved);
        return checkDown;
    }
    
    public static boolean checkRight(Tile tile) {
    	boolean checkRightPipe = tile instanceof Pipe && !(tile instanceof PipeStatic) && !((Pipe) tile).isVertical();
        boolean checkRightStatic = tile instanceof PipeStatic && ((PipeStatic) tile).isRight();
        boolean checkRightCurved = tile instanceof Curved && ((Curved) tile).getPosition2() == 1;
        boolean checkRight = (checkRightPipe || checkRightStatic || checkRightCurved);
        return checkRight;
    }
    
    public static boolean checkLeft(Tile tile) {
    	boolean checkLeftPipe = tile instanceof Pipe && !(tile instanceof PipeStatic) && !((Pipe) tile).isVertical();
        boolean checkLeftStatic = tile instanceof PipeStatic && !((PipeStatic) tile).isRight();
        boolean checkLeftCurved = tile instanceof Curved && ((Curved) tile).getPosition2() == 0;
        boolean checkLeft = (checkLeftPipe || checkLeftStatic || checkLeftCurved);
        return checkLeft;
    }
}
