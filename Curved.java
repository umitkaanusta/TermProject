import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.io.File;

public class Curved extends Tile {

    private int position1;
    private int position2;

    public Curved(int id, int x, int y, boolean isStatic, int position1, int position2) {

        super(id, x, y, isStatic);
        this.position1 = position1;
        this.position2 = position2;
        setFill();
    }

    @Override
    public void setFill() {
        if (position1 == 0 && position2 == 0 && !isStatic())
            this.setFill(new ImagePattern(new Image(new File("img/Curved00.gif").toURI().toString())));
        else if (position1 == 0 && position2 == 1 && !isStatic())
            this.setFill(new ImagePattern(new Image(new File("img/Curved01.gif").toURI().toString())));
        else if (position1 == 1 && position2 == 0 && !isStatic())
            this.setFill(new ImagePattern(new Image(new File("img/Curved10.gif").toURI().toString())));
        else if (position1 == 1 && position2 == 1 && !isStatic())
            this.setFill(new ImagePattern(new Image(new File("img/Curved11.gif").toURI().toString())));

    }

    public void setPosition(int position1, int position2) {
        setPosition1(position1);
        setPosition2(position2);


    }

    public int getPosition1() {
        return position1;
    }

    public void setPosition1(int position1) {
        this.position1 = position1;
    }

    public int getPosition2() {
        return position2;
    }

    public void setPosition2(int position2) {
        this.position2 = position2;
    }

    @Override
    public String toString() {
        return "ID: " + getTileId() +
                "\nCurved Pipe" +
                "\nAt : " + getxCord() + "," + getYCord();

    }
}
