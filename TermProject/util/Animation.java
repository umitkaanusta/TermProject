package TermProject.util;

import TermProject.tiles.PipeStatic;

import javafx.animation.PathTransition;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathElement;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.ArrayList;

public class Animation {
    private static PathTransition pt;
    private static ArrayList<PathElement> paths;

    public static void playAnimation(Pane pane, ArrayList<PathElement> paths) {
    	// Create the "animating" object
        Animation.paths = paths;
        Circle ball = new Circle(45, 45, 13);
        ball.setFill(Color.YELLOW);

        // Find the starter tile "from pane" to start the animation
        Node starter = getStarterFromPane(pane);
        double xStarter = starter.getLayoutX();
        double yStarter = starter.getLayoutY();

        // Create the animation
        ball.setStroke(Color.BLACK);
        paths.add(0, new MoveTo(xStarter + AnimationUtils.LOCATION, yStarter + AnimationUtils.LOCATION));
        pane.getChildren().add(ball);
        pt = new PathTransition();
        pt.setDuration(Duration.seconds(2));
        pt.setDuration(Duration.seconds(3));
        pt.setCycleCount(1);
        pt.setNode(ball);
        pt.setPath(new Path(paths));
        pt.play();
    }

    private static Node getStarterFromPane(Pane pane) {
        for(Node node: pane.getChildren()) {
            if(node instanceof PipeStatic && ((PipeStatic) node).isStarter()) {
                return node;
            }
        }
        return null;
    }

    public static PathTransition getPathTransition() {
        return pt;
    }

    public static ArrayList<PathElement> getPaths() {
        return paths;
    }

}