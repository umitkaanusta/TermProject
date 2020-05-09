package TermProject.gui;

import TermProject.tiles.Tile;

import TermProject.Main;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MainGui {

    public MainGui(Tile[][] tiles) {
        Pane mainPane = new Pane();

        Scene mainScene = new Scene(mainPane,600,500);
        mainPane.setPrefSize(600,500);
        BorderPane borderPane = new BorderPane();
        Button playButton = new Button("PLAY");

        Text welcome = new Text("WELCOME");
        welcome.setX(150);
        welcome.setY(150);
        welcome.setFont(Font.font("OCR A Extended",70));

        Text devby = new Text("Developed by Umit Kaan Usta & Suleyman Emirhan Uslu");
        devby.setX(20);
        devby.setY(480);
        devby.setFont(Font.font("OCR A Extended",18));


        borderPane.setMinSize(600,500);
        borderPane.setCenter(playButton);
        playButton.setAlignment(Pos.CENTER);
        mainPane.getChildren().addAll(borderPane,welcome,devby);
        playButton.setOnAction(e ->{
            IngameGui gui = new IngameGui(tiles);
            gui.showGameGui(Main.getStage());

        });
        Main.getStage().setScene(mainScene);
        Main.getStage().show();
    }
}