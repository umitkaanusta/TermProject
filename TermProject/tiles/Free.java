package TermProject.tiles;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.io.File;

public class Free extends Empty {

    public Free(int id, int x, int y) {
        super(id, x, y, true);
        setFill();
    }

    @Override
    public void setFill() {
        this.setFill(new ImagePattern(new Image(new File("img/EmptyFree.gif").toURI().toString())));

    }

    @Override
    public String toString() {
        return "ID: " + getTileId() +
                "\nEmpty and free Tile" +
                "\nAt : " + getxCord() + "," + getYCord();
    }
}
