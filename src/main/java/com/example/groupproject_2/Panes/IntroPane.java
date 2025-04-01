package com.example.groupproject_2.Panes;

import com.example.groupproject_2.Classes.MusicManager;
import com.example.groupproject_2.HelloApplication;
import com.example.groupproject_2.Scenes.OptionScene;
import javafx.animation.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import static com.example.groupproject_2.Classes.Player.*;
import static com.example.groupproject_2.Const.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.scene.control.Slider;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;


public class IntroPane extends StackPane {


    public IntroPane() {
        BorderPane borderPane = new BorderPane();
        ImageView background = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/blue-and-red-heroes-comic-cartoon-background-abstract-pop-art-comic-sunburst-effect-with-halftones-vector-filtered.jpg"))));
        ImageView hero = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/vecteezy_endearing-video-game-character-for-tshirt-artwork_27294906.png"))));
        ImageView enemy = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/vecteezy_delightful-character-design-featuring-a-lovable-monster_27294885.png"))));
        hero.setFitWidth(200);
        hero.setPreserveRatio(true);
        hero.setTranslateX(-400);
        enemy.setTranslateX(400);
        enemy.setFitWidth(200);
        enemy.setPreserveRatio(true);
        background.setFitWidth(SCREEN_WIDTH);
        background.setFitHeight(SCREEN_HEIGHT);
        StackPane centerPane = new StackPane();

        //Buttons
        Button newGame = new Button("New Game");
        Button loadGame = new Button("Load Game");
        Button options = new Button("Options");
        VBox newGameBox = new VBox();
        TextField newGameName = new TextField("");
        newGameName.setMaxWidth(75);
        newGameName.setPromptText("your name");
        VBox loadGameBox = new VBox();
        TextField loadGameName = new TextField("");
        loadGameName.setMaxWidth(77);
        loadGameName.setPromptText("your name");
        newGameBox.getChildren().addAll(newGame,newGameName);
        loadGameBox.getChildren().addAll(loadGame,loadGameName);
        newGame.setFocusTraversable(false);
        loadGame.setFocusTraversable(false);
        options.setFocusTraversable(false);
        HBox menuBox = new HBox();
        menuBox.getChildren().addAll(newGameBox,loadGameBox,options);
        menuBox.setAlignment(Pos.CENTER);
        menuBox.setPadding(new Insets(0, 0, 150,0));
        menuBox.setScaleX(2);
        menuBox.setScaleY(2);
        menuBox.setSpacing(50);

        borderPane.setCenter(centerPane);
        borderPane.setBottom(menuBox);



        // Set up the title text
        Text title = new Text("ULTIMATE CLICKERAMA");
        title.setFont(Font.font("Century", FontWeight.BOLD, FontPosture.REGULAR,40));
        title.setFill(Color.DARKBLUE);
        title.setStroke(Color.PALEGOLDENROD);
        title.setEffect(new DropShadow(10, Color.WHITE));
        title.setScaleX(0.8f);
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
        this.getChildren().addAll(background,hero,enemy,borderPane);

        // Title animations
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(2000), title);
        fadeTransition.setFromValue(0.1);
        fadeTransition.setToValue(1);

        TranslateTransition titleTranslate = new TranslateTransition(Duration.millis(2000), title);
        titleTranslate.setFromX(-250);
        titleTranslate.setToX(0);
        titleTranslate.setInterpolator(Interpolator.LINEAR);

        // Buttons transition
        FadeTransition buttonFade = new FadeTransition(Duration.millis(2000), menuBox);
        buttonFade.setFromValue(0.1);
        buttonFade.setToValue(1);

        TranslateTransition buttonTranslate = new TranslateTransition(Duration.millis(2000), menuBox);
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
            swordBouncesIn.get(i+16).setFromY(-50 + i * 33);
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
            swordBouncesIn.get(i+36).setFromY(50 - i * 33);
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

        SequentialTransition start = new SequentialTransition(titleTransition, swordsSequence);
        start.setDelay(Duration.millis(1000));
        start.play();

        // Switch to GameScene on click
        newGame.setOnMouseClicked(e -> {
            MusicManager.stopMusic();
            player.setPreviousScene(introScene);
            player.setSaveFile(newGameName.getText()+".txt");
            HelloApplication.mainStage.setScene(gameScene);
        });

        loadGame.setOnMouseClicked(e -> {
            MusicManager.stopMusic();
            player.setPreviousScene(introScene);
            player.setSaveFile(loadGameName.getText()+".txt");
            File saveFile = new File(loadGameName.getText()+".txt");
            if (!saveFile.exists()) {
                System.out.println("No save file found. Starting fresh.");
                return;
            }
            try (BufferedReader reader = new BufferedReader(new FileReader(saveFile))) {
                player.setMoney(Double.parseDouble(reader.readLine()));
                player.setClickPower(Double.parseDouble(reader.readLine()));
                player.setAutoClickRate(Double.parseDouble(reader.readLine()));
                player.setTotalClicks(Integer.parseInt(reader.readLine()));
                player.setAchievement1(Boolean.parseBoolean(reader.readLine()));


                System.out.println("Progress loaded!");
            } catch (IOException | NumberFormatException ex) {
                ex.printStackTrace();
            }

            HelloApplication.mainStage.setScene(gameScene);
        });

        options.setOnMouseClicked(e -> {
            MusicManager.stopMusic();
            player.setPreviousScene(introScene);
            HelloApplication.mainStage.setScene(new OptionScene());
        });


        // Music playback
        MusicManager.playMusic("/Audio/introMusic.mp3", 0.6, true);

        // Mute/Unmute Button
        Button muteButton = new Button("Mute");
        muteButton.setOnAction(e -> {
            MusicManager.toggleMute();
            muteButton.setText(MusicManager.isMuted() ? "🔇" : "🔊");
        });

        // Adding a volume Slider
        Slider volumeSlider = new Slider(0, 1, 0.6);
        volumeSlider.setPrefWidth(20);
        volumeSlider.setShowTickLabels(false);
        volumeSlider.setShowTickMarks(false);
        volumeSlider.setBlockIncrement(0.1);
        volumeSlider.setVisible(false);
        volumeSlider.valueProperty().addListener((obs, oldVal, newVal) -> MusicManager.setVolume(newVal.doubleValue()));


        // Volume Toggle Button
        Button volumeButton = new Button("🔊");
        volumeButton.setOnAction(e -> volumeSlider.setVisible(!volumeSlider.isVisible()));


        // Bind slider to mediaPlayer volume
        volumeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            MusicManager.setVolume(newVal.doubleValue());
        });

        // Layout for controls
        HBox volumeControls = new HBox(5, muteButton, volumeButton, volumeSlider);
        volumeControls.setAlignment(Pos.TOP_RIGHT);
        volumeControls.setPickOnBounds(false);

        this.getChildren().add(volumeControls);
        StackPane.setAlignment(volumeControls, Pos.TOP_RIGHT);
        volumeControls.setTranslateX(-10);
        volumeControls.setTranslateY(10);


    }
}