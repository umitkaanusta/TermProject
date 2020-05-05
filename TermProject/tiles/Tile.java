package TermProject.tiles;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {
    private int id;
    private int xCord;
    private int yCord;
    private boolean isStatic;
    private Image texture;

    public Tile(int id,int x, int y, boolean isStatic) {
    	super(100, 100);
        this.id = id;
        this.xCord = x;
        this.yCord = y;
        this.isStatic = isStatic;
        setFill();
        setStroke(Color.WHITE);
    }

    public void setFill() {
        this.setFill(new ImagePattern(this.texture));
    }

    public void setTileId(int id) {this.id = id;}

    public void setxCord(int x) {this.xCord = x;}

    public int getTileId() {
        return id;
    }

    public int getxCord() {
        return xCord;
    }

    public int getYCord() {
        return yCord;
    }

    public void setyCord(int y) {this.yCord = y; }

    public boolean isStatic() {return isStatic; }

    public void setStatic(boolean aStatic) {isStatic = aStatic; }

    public void setTexture(Image texture) {
        this.texture = texture;
    }

    @Override
    public String toString(){
        return "ID: " + id +
                "\nAt : " + xCord + "," + yCord;
    }

}
