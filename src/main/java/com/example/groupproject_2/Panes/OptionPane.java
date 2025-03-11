package com.example.groupproject_2.Panes;

import com.example.groupproject_2.HelloApplication;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;


import static com.example.groupproject_2.Classes.Player.*;

public class OptionPane extends BorderPane {
    public OptionPane() {
        GridPane root = new GridPane();
        Label stats = new Label("Stats");
        Label currentCoins = new Label("Current Coins");
        player.setMoney(5);
        Text coins = new Text(""+player.getMoney());
        Label playTime = new Label("Total Play Time");
        double timePlayed = System.currentTimeMillis()- player.getStartTime().getTime();
        Label time = new Label(""+timePlayed);
        Button soundToggle = new Button("Toggle Sound");
        Button musicToggle = new Button("Toggle Music");
        Button back = new Button("Back");
        soundToggle.setOnAction(e->{
            //stop/play sounds
        });
        musicToggle.setOnAction(e->{
            //stop/play music
        });
        back.setOnAction(e->{
            HelloApplication.mainStage.setScene(player.getPreviousScene());
            //go back to previous screen
        });
        this.setCenter(root);
        root.add(stats,         0,0,1,1);
        root.add(currentCoins,  0,1,1,1);
        root.add(coins,         1,1,1,1);
        root.add(playTime,      0,2,1,1);
        root.add(time,          1,2,1,1);
        root.add(soundToggle,   0,3,1,1);
        root.add(musicToggle,   0,4,1,1);
        root.add(back,          0,5,1,1);
    }
}
