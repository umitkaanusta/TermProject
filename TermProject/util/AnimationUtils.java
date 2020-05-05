package TermProject.util;

import TermProject.tiles.Tile;
import TermProject.tiles.Curved;
import TermProject.tiles.Empty;
import TermProject.tiles.Free;
import TermProject.tiles.Pipe;
import TermProject.tiles.PipeStatic;

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
		while(!(tiles[xStarter][yStarter] == starterTile)) {
			Tile currentTile = tiles[xStarter][yStarter];
			// Check whether moving is possible at each if block			
			
			// Check up (no pun intended)
			if(currentTile.getYCord() % 2 == 0 && lastMove != LastMove.UP) {
				if(BoardUtils.checkInside(xStarter, yStarter + 1) 
						&& tiles[xStarter][yStarter+1].getYCord() % 2 != 0) {
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
			
			// Check down
			if(currentTile.getYCord() % 2 != 0 && lastMove != LastMove.DOWN) {
				if(BoardUtils.checkInside(xStarter, yStarter - 1) 
						&& tiles[xStarter][yStarter-1].getYCord() % 2 == 0) {
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
			
			// Check right
			if(currentTile.getxCord() % 2 == 0 && lastMove != LastMove.LEFT) {
				if(BoardUtils.checkInside(xStarter + 1, yStarter) 
						&& tiles[xStarter+1][yStarter].getxCord() % 2 != 0) {
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
			
			// Check left
			if(currentTile.getxCord() % 2 != 0 && lastMove != LastMove.RIGHT) {
				if(BoardUtils.checkInside(xStarter - 1, yStarter) 
					&& tiles[xStarter-1][yStarter].getxCord() % 2 == 0) {
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
	
}
