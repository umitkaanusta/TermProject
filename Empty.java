import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.io.File;

public class Empty extends Tile {

    Empty(int id, int x, int y, boolean isStatic) {
        super(id, x, y, isStatic);
        setFill();
    }


    @Override
    public void setFill() {
        this.setFill(new ImagePattern(new Image(new File("img/Empty.gif").toURI().toString())));
    }

    @Override
    public String toString() {
        return "ID: " + getTileId() +
                "\n Empty Tile" +
                "\nAt : " + getxCord() + "," + getYCord();


    }
}
