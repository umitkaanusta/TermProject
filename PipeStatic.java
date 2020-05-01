import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.io.File;

public class PipeStatic extends Pipe {

    private boolean isStarter;
    private boolean isEnd;
    private boolean isUp;
    private boolean isRight;

    public PipeStatic(int id, int x, int y, boolean isUp, boolean isStarter, boolean isEnd, boolean isRight) {

        super(id, x, y, true, !isRight);
        this.isUp = isUp;
        this.isEnd = isEnd;
        this.isRight = isRight;
        this.isStarter = isStarter;
        setFill();
    }

    @Override
    public void setFill() {
        if (isEnd && isRight)
            this.setFill(new ImagePattern(new Image(new File("img/EndHorizontal.gif").toURI().toString())));
        else if (isEnd && isUp)
            this.setFill(new ImagePattern(new Image(new File("img/EndVertical.gif").toURI().toString())));
        else if (isStarter && isRight)
            this.setFill(new ImagePattern(new Image(new File("img/StarterHorizontal.gif").toURI().toString())));
        else if (isStarter && isUp)
            this.setFill(new ImagePattern(new Image(new File("img/StarterVertical.gif").toURI().toString())));
        else if (!isEnd && !isStarter && isUp)
            this.setFill(new ImagePattern(new Image(new File("img/PipeStaticVertical.gif").toURI().toString())));
        else if (!isEnd && !isStarter && isRight)
            this.setFill(new ImagePattern(new Image(new File("img/PipeStaticHorizontal.gif").toURI().toString())));
    }

    public boolean isStarter() {
        return isStarter;
    }

    public void setStarter(boolean starter) {
        isStarter = starter;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }

    public boolean isUp() {
        return isUp;
    }

    public void setUp(boolean up) {
        isUp = up;
    }

    public boolean isRight() {
        return isRight;
    }

    public void setRight(boolean right) {
        isRight = right;
    }

    @Override
    public String toString() {
        return "ID: " + getTileId() +
                "\nAt : " + getxCord() + "," + getYCord();
    }
}