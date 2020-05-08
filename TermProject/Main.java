package TermProject;




import TermProject.gui.MainGui;
import TermProject.tiles.Tile;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.stage.Stage;


public class Main extends Application {
    public static IntegerProperty NumberOfMoves = new SimpleIntegerProperty(0);
    public static final int MAX_LEVEL = TermProject.util.LevelCreator.getMaxLevel(); // Read the number of files in levels folder
    public static int LEVEL = 1;
    private static Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Main.primaryStage = primaryStage;
        Tile[][] board = TermProject.util.LevelCreator.createLevel(LEVEL);
        new MainGui(board);
        primaryStage.setResizable(false);
        primaryStage.setTitle("TileGame");

    }

    public static Stage getStage() {
        return primaryStage;
    }
}