package TermProject.tiles;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.io.File;

public class PipeStatic extends Pipe {

    private boolean isStarter;
    private boolean isEnd;
    private Boolean isUp;
    private Boolean isRight;

    public PipeStatic(int id, int x, int y, boolean isStarter, boolean isEnd, Boolean isUp, Boolean isRight) {

        super(id, x, y, true, false);
        this.isUp = isUp;
        this.isEnd = isEnd;
        this.isRight = isRight;
        this.isStarter = isStarter;
        setFill();
    }

    @Override
    public void setFill() {
        // End
        if (isEnd && (isRight != null && isRight) && isUp == null)
            this.setFill(new ImagePattern(new Image(new File("img/EndRight.gif").toURI().toString())));
        else if (isEnd && (isRight != null && !isRight) && isUp == null)
            this.setFill(new ImagePattern(new Image(new File("img/EndLeft.gif").toURI().toString())));
        else if (isEnd && (isUp != null && isUp) && isRight == null)
            this.setFill(new ImagePattern(new Image(new File("img/EndUp.gif").toURI().toString())));
        else if (isEnd && (isUp != null && !isUp) && isRight == null)
            this.setFill(new ImagePattern(new Image(new File("img/EndDown.gif").toURI().toString())));
            // Starter
        else if (isStarter && (isRight != null && isRight) && isUp == null)
            this.setFill(new ImagePattern(new Image(new File("img/StarterRight.gif").toURI().toString())));
        else if (isStarter && (isRight != null && !isRight) && isUp == null)
            this.setFill(new ImagePattern(new Image(new File("img/StarterLeft.gif").toURI().toString())));
        else if (isStarter && (isUp != null && isUp) && isRight == null)
            this.setFill(new ImagePattern(new Image(new File("img/StarterUp.gif").toURI().toString())));
        else if (isStarter && (isUp != null && !isUp) && isRight == null)
            this.setFill(new ImagePattern(new Image(new File("img/StarterDown.gif").toURI().toString())));
            // Calling PipeStatic directly from PipeStatic, you can call it from Pipe class too
        else if (!isEnd && !isStarter && (isUp != null && isUp) && isRight == null)
            this.setFill(new ImagePattern(new Image(new File("img/PipeStaticVertical.gif").toURI().toString())));
        else if (!isEnd && !isStarter && (isUp != null && !isUp) && isRight == null)
            this.setFill(new ImagePattern(new Image(new File("img/PipeStaticHorizontal.gif").toURI().toString())));
        else if (!isEnd && !isStarter && (isRight != null && isRight) && (isUp != null && isUp))
            this.setFill(new ImagePattern(new Image(new File("img/PipeStatic01.gif").toURI().toString())));
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

    public Boolean isUp() {
        return isUp;
    }

    public void setUp(Boolean up) {
        isUp = up;
    }

    public Boolean isRight() {
        return isRight;
    }

    public void setRight(Boolean right) {
        isRight = right;
    }

    @Override
    public String toString() {
        return "ID: " + getTileId() +
                "\nAt : " + getxCord() + "," + getYCord();
    }
}