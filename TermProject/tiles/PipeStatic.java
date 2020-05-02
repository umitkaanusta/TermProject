import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.io.File;

public class PipeStatic extends Pipe {
    private boolean isStarter;
    private boolean isEnd
    private boolean isUp;
    private boolean isRight;

    public PipeStatic(int id, int x, int y, boolean isStarter, boolean isEnd,
    		boolean isUp, boolean isRight) {
        super(id, x, y, true, !isRight);
        this.isStarter = isStarter;
        this.isEnd = isEnd;
        this.isUp = isUp;
        this.isRight = isRight; // Setting this to false makes the default PipeStatic non-vertical
        setFill();
    }

    @Override
    public void setFill() {
    	// End
        if (isEnd && isRight)
            this.setFill(new ImagePattern(new Image(new File("img/EndRight.gif").toURI().toString())));
        else if (isEnd && !isRight)
            this.setFill(new ImagePattern(new Image(new File("img/EndLeft.gif").toURI().toString())));
        else if (isEnd && isUp)
            this.setFill(new ImagePattern(new Image(new File("img/EndUp.gif").toURI().toString())));
        else if (isEnd && !isUp)
            this.setFill(new ImagePattern(new Image(new File("img/EndDown.gif").toURI().toString())));
        // Starter
        else if (isStarter && isRight)
            this.setFill(new ImagePattern(new Image(new File("img/StarterRight.gif").toURI().toString())));
        else if (isStarter && !isRight)
            this.setFill(new ImagePattern(new Image(new File("img/StarterLeft.gif").toURI().toString())));
        else if (isStarter && isUp)
            this.setFill(new ImagePattern(new Image(new File("img/StarterUp.gif").toURI().toString())));
        else if (isStarter && !isUp)
            this.setFill(new ImagePattern(new Image(new File("img/StarterDown.gif").toURI().toString())));
        // Calling PipeStatic directly from PipeStatic, you can call it from Pipe class too
        else if (!isEnd && !isStarter && isRight)
            this.setFill(new ImagePattern(new Image(new File("img/PipeStaticVertical.gif").toURI().toString())));
        else if (!isEnd && !isStarter && !isRight)
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