package com.example.groupproject_2.Panes;

import com.example.groupproject_2.Const;
import com.example.groupproject_2.HelloApplication;
import com.example.groupproject_2.Scenes.GameScene;
import javafx.animation.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Objects;


public class IntroPane extends BorderPane {
    public IntroPane() {
        StackPane centerPane = new StackPane();

        //Buttons
        Button newGame = new Button("New Game");
        Button loadGame = new Button("Load Game");
        Button options = new Button("Options");
        // removing highlight doesnt look pretty
        newGame.setFocusTraversable(false);
        loadGame.setFocusTraversable(false);
        options.setFocusTraversable(false);
        HBox menuBox = new HBox();
        menuBox.getChildren().addAll(newGame,loadGame,options);
        menuBox.setAlignment(Pos.CENTER);
        menuBox.setPadding(new Insets(0, 0, 150,0));
        menuBox.setScaleX(2);
        menuBox.setScaleY(2);
        menuBox.setSpacing(50);

        this.setCenter(centerPane);
        this.setBottom(menuBox);



        // Set up the title text
        Text title = new Text("Ultimate Clickerama");
        title.setFont(Const.ImpactBoldRegular);
        title.setFill(Color.DARKBLUE);
        title.setStroke(Color.PALEGOLDENROD);
        title.setEffect(new DropShadow(10, Color.BLACK));
        centerPane.getChildren().add(title);

        // Add title to the StackPane (centered by default)

        // Set up the sword ImageView
        ArrayList<ImageView> swordsList = new ArrayList<>();
        for (int i = 0;i<40;i++){
            swordsList.add(new ImageView(new Image((Objects.requireNonNull(getClass().getResourceAsStream("/Images/sword.png"))))));
        }
        for (ImageView item: swordsList){
            centerPane.getChildren().add(item);
        }
        System.out.println(swordsList.size());
        int count = 0;
        for (Node item: centerPane.getChildren()){
            if (item instanceof ImageView) {
                count++;
                item.setTranslateX(-2000);
                ImageView imgView = (ImageView) item;
                imgView.setFitWidth(40);
                imgView.setFitHeight(40);
                imgView.setPreserveRatio(true);
            }
        }

        // Title animations
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), title);
        fadeTransition.setFromValue(0.1);
        fadeTransition.setToValue(1);

        TranslateTransition titleTranslate = new TranslateTransition(Duration.millis(1000), title);
        titleTranslate.setFromX(-250);
        titleTranslate.setToX(0);
        titleTranslate.setInterpolator(Interpolator.LINEAR);

        // Buttons transition
        FadeTransition buttonFade = new FadeTransition(Duration.millis(1000), menuBox);
        buttonFade.setFromValue(0.1);
        buttonFade.setToValue(1);

        TranslateTransition buttonTranslate = new TranslateTransition(Duration.millis(1000), menuBox);
        buttonTranslate.setFromY(1000);
        buttonTranslate.setToY(0);
        buttonTranslate.setInterpolator(Interpolator.LINEAR);

        ParallelTransition titleTransition = new ParallelTransition(fadeTransition,titleTranslate,buttonTranslate,buttonFade);
        ArrayList<TranslateTransition> swordBouncesIn = new ArrayList<>();
        ArrayList<TranslateTransition> swordBouncesOut = new ArrayList<>();

        for (Node item: centerPane.getChildren()){
            if (item instanceof ImageView){
                swordBouncesIn.add(new TranslateTransition(Duration.millis(200), item));
                swordBouncesOut.add(new TranslateTransition(Duration.millis(200), item));
            }
        }
        for (int i = 0; i<16; i++) {
            swordBouncesIn.get(i).setFromX(-200+i*27);
            swordBouncesIn.get(i).setFromY(-50);
            swordBouncesIn.get(i).setToY(-70);
            swordBouncesIn.get(i).getNode().setRotate(140);

            swordBouncesOut.get(i).setFromY(-70);
            swordBouncesOut.get(i).setToY(-50);
        }
        for (int i = 0; i<4; i++){
            swordBouncesIn.get(i+16).setFromX(240);
            swordBouncesIn.get(i+16).setToX(260);
            swordBouncesIn.get(i+16).setFromY(-70 + i * 46);
            swordBouncesIn.get(i+16).getNode().setRotate(-129);

            swordBouncesOut.get(i+16).setFromX(260);
            swordBouncesOut.get(i+16).setToX(240);
        }
        for (int i = 0; i<16; i++) {
            swordBouncesIn.get(i+20).setFromX(200-i*27);
            swordBouncesIn.get(i+20).setFromY(50);
            swordBouncesIn.get(i+20).setToY(70);
            swordBouncesIn.get(i+20).getNode().setRotate(-37);

            swordBouncesOut.get(i+20).setFromY(70);
            swordBouncesOut.get(i+20).setToY(50);
        }
        for (int i = 0; i<4; i++){
            swordBouncesIn.get(i+36).setFromX(-240);
            swordBouncesIn.get(i+36).setToX(-260);
            swordBouncesIn.get(i+36).setFromY(70 - i * 46);
            swordBouncesIn.get(i+36).getNode().setRotate(51);

            swordBouncesOut.get(i+36).setFromX(-260);
            swordBouncesOut.get(i+36).setToX(-240);
        }
        SequentialTransition swordsSequence = new SequentialTransition();


        for (int i = 0; i<swordBouncesIn.size(); i++){
            try{
                swordBouncesIn.get(i).setDuration(Duration.millis(50));
                swordsSequence.getChildren().add(swordBouncesIn.get(i));
                swordBouncesOut.get(i+2).setDuration(Duration.millis(50));
                swordsSequence.getChildren().add(swordBouncesOut.get(i+2));
                swordBouncesIn.get(i+20).setDuration(Duration.millis(50));
                swordsSequence.getChildren().add(swordBouncesIn.get(i+20));
                swordBouncesOut.get(i+22).setDuration(Duration.millis(50));
                swordsSequence.getChildren().add(swordBouncesOut.get(i+22));
            }catch (Exception e){
                e.getStackTrace();
            }
        }
        swordsSequence.setCycleCount(Animation.INDEFINITE);
        swordsSequence.play();

        SequentialTransition start = new SequentialTransition(titleTransition);
        start.play();

        // Switch to GameScene on click
        newGame.setOnMouseClicked(e -> {
            HelloApplication.mainStage.setScene(new GameScene());
        });
    }
}