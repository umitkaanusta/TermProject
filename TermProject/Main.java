package TermProject;


import java.io.FileNotFoundException;

import TermProject.gui.MainGui;
import TermProject.tiles.Tile;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.stage.Stage;


public class Main extends Application {
    public static int NumberOfMoves = 0;
    public static final int MAX_LEVEL = TermProject.util.LevelCreator.getMaxLevel(); // Read the number of files in levels folder
    public static int LEVEL = 1;
    private static Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        Main.primaryStage = primaryStage;
        Tile[][] grid = TermProject.util.LevelCreator.createLevel(LEVEL);
        new MainGui(grid);
        primaryStage.setResizable(false);

    }

    public static Stage getStage() {
        return primaryStage;
    }
}