package TermProject.gui;

import TermProject.util.LevelCreator;
import TermProject.Main;
import TermProject.util.Animation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;




public class EndGui {

    EndGui(){

        Pane endPane = new Pane();
        endPane.setStyle("-fx-background-color:#f84cff");

        Scene endScene = new Scene(endPane,600,500);
        endPane.setPrefSize(600,500);

        Button restartButton = new Button("PLAY AGAIN");
        Button exitButton = new Button("EXIT THE GAME");

        restartButton.setPrefSize(160,25);
        exitButton.setPrefSize(160,25);


        HBox hBox = new HBox(restartButton,exitButton);
        hBox.setSpacing(5);
        hBox.setLayoutX(endPane.getMaxWidth() / 2 - 180);
        hBox.setLayoutY(endPane.getMaxHeight() - 40);

        restartButton.setOnMousePressed(e -> {
            Main.LEVEL = 1;
            Main.NumberOfMoves.set(0);
            Animation.getPaths().clear();
            new MainGui(LevelCreator.createLevel(1));

        });

        exitButton.setOnMousePressed(e -> Main.getStage().close());

        endPane.getChildren().addAll(hBox);
        Main.getStage().setScene(endScene);
        Main.getStage().show();

    }
}