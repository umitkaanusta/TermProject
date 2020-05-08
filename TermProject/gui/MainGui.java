package TermProject.gui;

import TermProject.tiles.Tile;

import TermProject.Main;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class MainGui {

    public MainGui(Tile[][] tiles) {
        Pane mainPane = new Pane();

        Scene mainScene = new Scene(mainPane,600,500);
        mainPane.setPrefSize(600,500);
        BorderPane borderPane = new BorderPane();
        Button playButton = new Button("PLAY");


        borderPane.setMinSize(600,500);
        borderPane.setCenter(playButton);
        playButton.setAlignment(Pos.CENTER);
        mainPane.getChildren().addAll(borderPane);
        playButton.setOnAction(e ->{
            IngameGui gui = new IngameGui(tiles);
            gui.showGameGui(Main.getStage());

        });
        Main.getStage().setScene(mainScene);
        Main.getStage().show();
    }
}