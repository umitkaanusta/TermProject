package TermProject.tiles;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.io.File;

public class Pipe extends Tile {
    private boolean isVertical;

    public Pipe(int id, int x, int y, boolean isStatic, boolean isVertical) {
        super(id, x, y, isStatic);
        this.isVertical = isVertical;
        setFill();
    }

    @Override
    public void setFill() {
        if(isVertical() && !isStatic())
            this.setFill(new ImagePattern(new Image(new File("img/PipeVertical.gif").toURI().toString())));
        else if(isVertical && isStatic())
            this.setFill(new ImagePattern(new Image(new File("img/PipeStaticVertical.gif").toURI().toString())));
        else if(!isVertical && !isStatic())
            this.setFill(new ImagePattern(new Image(new File("img/PipeHorizontal.gif").toURI().toString())));
        else if(!isVertical && isStatic())
            this.setFill(new ImagePattern(new Image(new File("img/PipeStaticHorizontal.gif").toURI().toString())));
    }

    public boolean isVertical() {
        return isVertical;
    }

    public void setVertical(boolean vertical) {
        isVertical = vertical;
    }

    @Override
    public String toString() {
        return "ID: " + getTileId() +
                "\nAt : " + getxCord() + "," + getYCord();
    }

}
