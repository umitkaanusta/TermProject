package TermProject.gui;

import TermProject.util.LevelCreator;
import TermProject.Main;
import TermProject.util.Animation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class EndGui {

    EndGui(){

        Pane endPane = new Pane();


        Scene endScene = new Scene(endPane,600,500);
        endPane.setPrefSize(600,500);

        Text congrats = new Text("CONGRATULATIONS!");
        congrats.setX(10);
        congrats.setY(150);
        congrats.setFont(Font.font("OCR A Extended",60));

        Text move = new Text("Total Moves: " + IngameGui.TOTAL_MOVES);
        move.setX(230);
        move.setY(225);
        move.setFont(Font.font("OCR A Extended",15));





        Button exitBt = new Button("EXIT THE GAME");
        exitBt.setPrefSize(150,25);
        Button playBt = new Button("PLAY AGAIN ");
        playBt.setPrefSize(150,25);



        HBox hBox = new HBox(exitBt,playBt);
        hBox.setSpacing(5);
        hBox.setAlignment(Pos.CENTER);
        hBox.setLayoutX(150);
        hBox.setLayoutY(250);

        playBt.setOnMousePressed(e -> {
            Main.LEVEL = 1;
            Main.NumberOfMoves.set(0);
            Animation.getPaths().clear();
            new MainGui(LevelCreator.createLevel(1));

        });

        exitBt.setOnMousePressed(e -> Main.getStage().close());

        endPane.getChildren().addAll(hBox,move,congrats);
        Main.getStage().setScene(endScene);
        Main.getStage().show();

    }
}