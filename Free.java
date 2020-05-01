import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.io.File;

public class Free extends Empty {

    Free(int id, int x, int y){

        super(id,x,y,false);
        setFill();

    }

    @Override
    public void setFill(){
        this.setFill(new ImagePattern(new Image(new File("img/EmptyFree.gif").toURI().toString())));

    }

    @Override
    public String toString() {
        return "ID: " + getTileId() +
                "\nEmpty and free Tile" +
                "\nAt : " + getxCord() + "," + getYCord();

    }


}
