package TermProject.gui;

import TermProject.tiles.*;
import TermProject.util.*;
import TermProject.Main;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.ArrayList;

public class IngameGui {

    public static int TOTAL_MOVES = 0;
    private Tile[][] tiles;
    private Scene gameScene;
    private Pane gamePane;
    private Pane statsPane;

    private ArrayList<Tile> swapArray;

    private double oldX = 0;
    private double oldY = 0;


    IngameGui(Tile[][] tiles) {
        this.tiles = tiles;
        Pane root = new Pane();

        gameScene = new Scene(root,600,500);
        gamePane = new Pane();
        int BOARD_SIZE = BoardUtils.BOARD_SIZE;
        root.setPrefSize(BOARD_SIZE,BOARD_SIZE);
        gamePane.setLayoutX(10);
        gamePane.setLayoutY(20);

        statsPane = new Pane();
        statsPane.setPrefSize((BOARD_SIZE / 2),BOARD_SIZE);
        statsPane.setLayoutX(BOARD_SIZE + 20);
        statsPane.setLayoutY(20);

        root.getChildren().addAll(gamePane,statsPane);


        for (int i = 0; i < tiles.length; i++){
            for (int j = 0; j < tiles[i].length; j++){

                Tile tile = tiles[i][j];
                tile.setLayoutX(i * 100);
                tile.setLayoutY(j * 100);

                gamePane.getChildren().addAll(tile);

            }
        }
        swapArray = new ArrayList<>();
    }

    public void showGameGui(Stage stage) {
        showGame();
        showStatsPane();
        stage.setScene(gameScene);
        stage.show();
    }

    private void showStatsPane() {
        Text level = new Text(statsPane.getPrefWidth() / 2 - 50, statsPane.getPrefHeight() / 4 - 50, "LEVEL :" + TermProject.Main.LEVEL);
        level.setTextAlignment(TextAlignment.CENTER);
        level.setFill(Color.RED);
        Text numberOfMoves = new Text(statsPane.getPrefWidth() / 2 - 50, statsPane.getPrefHeight() / 4 - 50,"MOVES : " + TOTAL_MOVES);
        numberOfMoves.setTextAlignment(TextAlignment.CENTER);
        numberOfMoves.setFill(Color.RED);

        VBox vbox = new VBox();
        vbox.setPrefSize(160,400);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(level,numberOfMoves);

        statsPane.getChildren().add(vbox);
    }

    private void showGame() {

        for(int row = 0; row < tiles.length; row++){
            for (int col = 0; col < tiles[row].length; col++){

                Tile tile = tiles[col][row];

                tile.setOnMousePressed(e ->{


                    oldX = tile.getLayoutX();
                    oldY = tile.getLayoutY();

                    if(!(tile instanceof Free) && !tile.isStatic()){

                        gamePane.getChildren().remove(tile);
                        gamePane.getChildren().add(tile);

                        swapArray.clear();
                        swapArray.add(tile);

                    }


                });

                tile.setOnMouseDragged(e ->{


                    if(!(tile instanceof Free) && !(tile.isStatic()))
                       tile.relocate(e.getSceneX() - tile.getWidth() / 2 - 10, e.getSceneY() - tile.getHeight() / 2 - 10);
                   // tile.setLayoutX(e.getSceneX() - tile.getWidth() / 2 - 10);
                    //tile.setLayoutY(e.getSceneY() - tile.getHeight() / 2 - 10);



                });

                tile.setOnMouseReleased(e -> {

                    if (e.getSceneX() > 10 && e.getSceneX() < 400 && e.getSceneY() > 10 && e.getSceneY() < 400){
                        if (!(tile.isStatic()) && !swapArray.isEmpty()) {
                            Tile tile2 = BoardUtils.getTileFromCursor(tiles, e.getSceneX(), e.getSceneY());



                            //Aborting move if tiles cant be swapped.
                            if (((tile2 == null) || !BoardUtils.checkSwap(swapArray.get(0), tile2))) {
                                swapArray.clear();
                                //tile.relocate(oldX, oldY);
                                tile.setLayoutX(oldX);
                                tile.setLayoutY(oldY);
                                return;

                            }

                            swapArray.add(tile2);
                            swapTiles(swapArray.get(0), swapArray.get(1));




                            if (AnimationUtils.checkPath(tiles)) {
                                TOTAL_MOVES += Main.NumberOfMoves;
                                Animation.playAnimation(gamePane, AnimationUtils.getPaths());

                                if (Main.LEVEL >= Main.MAX_LEVEL) {
                                    Animation.getPathTransition().setOnFinished(e2 -> {
                                        new EndGui();
                                    });

                                }
                                else {
                                    Tile[][] nextLevel = LevelCreator.createLevel(++Main.LEVEL);
                                    Animation.getPathTransition().setOnFinished(e2 -> {
                                        IngameGui nextLevelGui = new IngameGui(nextLevel);
                                        nextLevelGui.showGameGui(Main.getStage());
                                        Main.NumberOfMoves = 0;

                                    });
                                }
                            }
                        }
                }
                    else {
                        //tile.relocate(oldX, oldY);
                        tile.setLayoutX(oldX);
                        tile.setLayoutY(oldY);
                        swapArray.clear();
                    }
                });

                }
        }
    }

    private void swapTiles(Tile tile1, Tile tile2){
        int xCord1 = tile1.getxCord();
        int yCord1 = tile1.getYCord();
        int xCord2 = tile2.getxCord();
        int yCord2 = tile2.getYCord();

        if(!(xCord1 == xCord2 && yCord1 == yCord2)){
            Main.NumberOfMoves++;
            gamePane.getChildren().removeAll(tile1,tile2);

            tile1.setLayoutX(tile2.getLayoutX());
            tile1.setLayoutY(tile2.getLayoutY());
            tile2.setLayoutX(oldX);
            tile2.setLayoutY(oldY);

            gamePane.getChildren().addAll(tile1,tile2);
            tile1.setxCord(xCord2);
            tile1.setyCord(yCord2);
            tile2.setxCord(xCord1);
            tile2.setyCord(yCord1);

            tiles[xCord1][yCord1] = tile2;
            tiles[xCord2][yCord2] = tile1;
            swapArray.clear();
        }
        else
            //tile2.relocate(oldX,oldY);
        tile2.setLayoutX(oldX);
        tile2.setLayoutY(oldY);
    }
}







