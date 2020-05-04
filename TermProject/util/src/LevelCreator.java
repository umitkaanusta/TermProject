import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class LevelCreator {

   public static Tile[][] createLevel(int level) throws FileNotFoundException {

       Tile[][] grid = new Tile[4][4];
        ArrayList<Tile> tiles = readInput(level);

        int pos = 0;
        for (int row = 0; row < 4; row++){
            for (int col = 0; col < 4; col++){

                grid[col][row] = tiles.get(pos);
                pos++;

            }
        }

        return grid;
    }

    private static ArrayList<Tile> readInput(int level) throws FileNotFoundException {

       ArrayList<Tile> tiles = new ArrayList<>();

       File file = new File("levels/level" + level + ".txt");
       Scanner sc = new Scanner(file);

               while(sc.hasNext()){

                   String line = sc.nextLine();
                   String[] lineinput = line.split(",");
                   int id = Integer.parseInt(lineinput[0]);
                   int x = getxCord(id);
                   int y = getyCord(id);
                   String type = lineinput[1];
                   String property = lineinput[2];

                   switch (type){

                       case "Starter":
                           if(property.equalsIgnoreCase("Vertical"))
                               tiles.add(new PipeStatic(id,x,y,true,false,true,false));

                           else
                               tiles.add(new PipeStatic(id,x,y,true,false,false,true));

                           break;

                       case "Empty":

                            if(property.equalsIgnoreCase("none"))
                                tiles.add(new Empty(id,x,y,false));

                            else
                                tiles.add(new Free(id,x,y));

                       break;

                       case "Pipe":
                           if (property.equalsIgnoreCase("Vertical"))
                               tiles.add(new Pipe(id,x,y,false,true));

                           else if(property.equalsIgnoreCase("Horizontal"))
                               tiles.add(new Pipe(id,x,y,false,false));

                           else if(property.equalsIgnoreCase("00"))
                               tiles.add(new Curved(id,x,y,false,0,0));

                           else if(property.equalsIgnoreCase("01"))
                               tiles.add(new Curved(id,x,y,false,0,1));

                           else if(property.equalsIgnoreCase("10"))
                               tiles.add(new Curved(id,x,y,false,1,0));

                           else if(property.equalsIgnoreCase("11"))
                               tiles.add(new Curved(id,x,y,false,1,1));

                           break;

                       case "End":
                           if (property.equalsIgnoreCase("Horizontal"))
                               tiles.add(new PipeStatic(id,x,y,false,true,false,true));

                           else
                               tiles.add(new PipeStatic(id,x,y,false,true,true,false));

                           break;

                       case "PipeStatic":
                           if (property.equalsIgnoreCase("Horizontal"))
                               tiles.add(new PipeStatic(id,x,y,false,false,false,true));

                           else if (property.equalsIgnoreCase("Vercital"))
                               tiles.add(new PipeStatic(id,x,y,false,true,true,false));

                           else if(property.equalsIgnoreCase("01"))
                               tiles.add((new PipeStatic(id,x,y,false,false,true,true)));

                           break;

                   }
               }
    return tiles;
   }

    public int getMaxLevel(){
       File file = new File("levels/");
       return Objects.requireNonNull(file.listFiles()).length;

    }

    private static int getxCord(int position){
       int x = 0;
       x = (position - 1) % 4;

       return x;
    }

    private static int getyCord(int position){
       int y = 0;
       y = (position - 1) / 4;

       return y;
    }


}
