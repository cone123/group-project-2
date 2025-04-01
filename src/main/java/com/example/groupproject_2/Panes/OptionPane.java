package com.example.groupproject_2.Panes;

import com.example.groupproject_2.Classes.MusicManager;
import com.example.groupproject_2.Classes.SoundManager;
import com.example.groupproject_2.HelloApplication;
import com.example.groupproject_2.Scenes.CreditsScene;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.*;

import static com.example.groupproject_2.Classes.Player.*;
import static com.example.groupproject_2.Const.*;

public class OptionPane extends StackPane {
    public OptionPane() {
        // background to match intro
        ImageView background = new ImageView(new Image(getClass().getResourceAsStream("/Images/blue-and-red-heroes-comic-cartoon-background-abstract-pop-art-comic-sunburst-effect-with-halftones-vector-filtered.jpg")));
        background.setFitWidth(SCREEN_WIDTH);
        background.setFitHeight(SCREEN_HEIGHT);
        this.getChildren().add(background);

        // layout
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        Label stats = new Label("Stats");
        Label currentCoins = new Label("Current Coins");
        Text coins = new Text("" + (int) player.getMoney());
        Label playTime = new Label("Total Play Time");
        double timePlayed = (System.currentTimeMillis()/1000) - (player.getStartTime().getTime()/1000);
        Label time = new Label(timePlayed + " Seconds");

        Button soundToggle = new Button("Toggle Sound");
        Button musicToggle = new Button(MusicManager.isMuted() ? "Unmute Music" : "Mute Music");
        Button back = new Button("Back");
        Button credits = new Button("Credits");
        Button save = new Button("Save");

        soundToggle.setOnAction(e -> {
            // stop/play sounds
            SoundManager.toggleMute();
            soundToggle.setText(SoundManager.isMuted() ? "Unmute Sound" : "Mute Sound");
        });

        musicToggle.setOnAction(e -> {
            MusicManager.toggleMute();
            musicToggle.setText(MusicManager.isMuted() ? "Unmute Music" : "Mute Music");
        });

        back.setOnAction(e -> {
            HelloApplication.mainStage.setScene(player.getPreviousScene());
        });
        save.setOnAction(e->{
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(player.getSaveFile()))) {
                writer.write(player.getMoney() + "\n");
                writer.write(player.getClickPower() + "\n");
                writer.write(player.getAutoClickRate() + "\n");
                writer.write(player.getTotalClicks() + "\n");
                writer.write(player.isAchievement1()+ "\n");
                System.out.println("Progress saved!");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        credits.setOnAction(e -> HelloApplication.mainStage.setScene(new CreditsScene()));

        root.add(stats, 0, 0, 1, 1);
        root.add(currentCoins, 0, 1, 1, 1);
        root.add(coins, 1, 1, 1, 1);
        root.add(playTime, 0, 2, 1, 1);
        root.add(time, 1, 2, 1, 1);
        root.add(soundToggle, 0, 3, 1, 1);
        root.add(musicToggle, 0, 4, 1, 1);
        root.add(back, 0, 5, 1, 1);
        root.add(credits, 0, 6, 1, 1);
        root.add(save,0,7,1,1);

        // VBox to hold everything
        VBox wrapper = new VBox(root);
        wrapper.setAlignment(Pos.CENTER);
        wrapper.setPadding(new Insets(40));
        this.getChildren().add(wrapper);

        //  adding some style to everything
        root.getChildren().forEach(node -> {
            if (node instanceof Label label) {
                label.setFont(Font.font("Arial", FontWeight.BOLD, 18));
                label.setTextFill(Color.WHITE);
            } else if (node instanceof Button btn) {
                btn.setFont(Font.font("Arial", FontWeight.BOLD, 16));
                btn.setStyle("-fx-background-color: lightcoral; -fx-text-fill: white;");
                btn.setPrefWidth(220);
                btn.setFocusTraversable(false);
            }
        });
    }
}
