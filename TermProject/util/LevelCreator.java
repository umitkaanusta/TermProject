package TermProject.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import TermProject.tiles.Curved;
import TermProject.tiles.Empty;
import TermProject.tiles.Free;
import TermProject.tiles.Pipe;
import TermProject.tiles.PipeStatic;
import TermProject.tiles.Tile;

public class LevelCreator {

   public static Tile[][] createLevel(int level) throws FileNotFoundException {
	   Tile[][] board = new Tile[4][4];
       ArrayList<Tile> tiles = readInput(level);
       int pos = 0;
       for (int i = 0; i < 4; i++) {
           for (int j = 0; j < 4; j++) {
               board[i][j] = tiles.get(pos);
               pos++;
           }
       }
       return board;
    }

    private static ArrayList<Tile> readInput(int level) throws FileNotFoundException {
    	// Reads text files and fills the ArrayList tiles
		ArrayList<Tile> tiles = new ArrayList<>();
    	try {
        	File file = new File("levels/" + level + ".txt");
        	Scanner sc = new Scanner(file);
        	while(sc.hasNext()) {
        		String line = sc.nextLine();
        		if(line.trim().equalsIgnoreCase("")) {
        			// If line is empty, just continue
        			continue;
        		}
        		createTileFromInput(line, tiles);
        	}
    	} catch(IOException e) {
    		System.out.println("There is a problem with the input file. Try again.");
    	}
    	return tiles;
    }
    
    private static void createTileFromInput(String line, ArrayList<Tile> baseList) {
		String[] input = line.split(","); // Putting id, type, property in an array
		int id = Integer.parseInt(input[0]);
		String type = input[1];
		String property = input[2];
		int x = getxCord(id);
		int y = getyCord(id);
		
		switch(type) {
		case "Starter":
			if (property.equalsIgnoreCase("Vertical")) {
				baseList.add(new PipeStatic(id, x, y, true, false, false, null));
			}
			else {
				baseList.add(new PipeStatic(id, x, y, true, false, null, false));
			}
			break;
		case "End":
			if (property.equalsIgnoreCase("Vertical")) {
				baseList.add(new PipeStatic(id, x, y, false, true, false, null));
			}
			else {
				baseList.add(new PipeStatic(id, x, y, false, true, null, false));
			}
			break;
		case "PipeStatic":
			if (property.equalsIgnoreCase("Vertical")) {
				baseList.add(new Pipe(id, x, y, true, true));
			}
			else if (property.equalsIgnoreCase("Horizontal")) {
				baseList.add(new Pipe(id, x, y, true, false));
			}
			else {
				baseList.add(new PipeStatic(id, x, y, false, false, true, true));
			}
			break;
		case "Pipe":
			if (property.equalsIgnoreCase("Vertical")) {
				baseList.add(new Pipe(id, x, y, false, true));
			}
			else if (property.equalsIgnoreCase("Horizontal")) {
				baseList.add(new Pipe(id, x, y, false, false));
			}
			else {
				int position1 = Integer.parseInt(property.charAt(0) + "");
				int position2 = Integer.parseInt(property.charAt(1) + "");
				baseList.add(new Curved(id, x, y, position1, position2));
			}
			break;
		case "Empty":
			if (property.equalsIgnoreCase("Free")) {
				baseList.add(new Empty(id, x, y, true));
			}
			else {
				baseList.add(new Free(id, x, y));
			}
			break;
		}
    }

    public static int getMaxLevel() {
    	File file = new File("levels/");
    	return Objects.requireNonNull(file.listFiles()).length;
    }

    private static int getxCord(int position) {
    	return (position - 1) % 4;
    }

    private static int getyCord(int position) {
    	return (position - 1) / 4;
    }
    
}
