package com.example.groupproject_2.Panes;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class GamePane extends HBox {

    public GamePane() {

        // Left side (Upgrade Box)
        VBox upgradeBox = new VBox(10);
        upgradeBox.setBackground(Background.fill(Color.GREEN));
        upgradeBox.getChildren().add(new Label(""));
        HBox buttonArea = new HBox(10);
        // list
        ListView<String> upgradeList = new ListView<>();
        upgradeList.getItems().addAll("upgrade 1", "upgrade 2", "upgrade 3");
        upgradeList.setVisible(false);
        ListView<String> achievementList = new ListView<>();
        achievementList.getItems().addAll("achieve 1", "achieve 2");
        achievementList.setVisible(false);
        // buttons
        Button upgradeMenu = new Button("Upgrade Menu");
        Button achievements = new Button("Achievements");
        Button options = new Button("Options");
        //action event
        upgradeMenu.setOnAction(e -> {
            if (upgradeList.isVisible()) {
                upgradeList.setVisible(false);
            } else if (!upgradeList.isVisible()) {
                upgradeList.setVisible(true);
            }
        });
        achievements.setOnAction(e -> {
            if (achievementList.isVisible()) {
                achievementList.setVisible(false);
            } else if (!achievementList.isVisible()) {
                achievementList.setVisible(true);
            }
        });
        // add in children
        buttonArea.getChildren().addAll(upgradeMenu, achievements, options);
        buttonArea.setBackground(Background.fill(Color.RED));
        upgradeBox.getChildren().addAll(buttonArea, upgradeList, achievementList);

        // Middle (Player Box)
        VBox playerBox = new VBox(10);
        playerBox.getChildren().add(new Label("Player Section"));

        // Right (Enemy Box)
        VBox enemyBox = new VBox(10);
        enemyBox.getChildren().add(new Label("Enemy Section"));

        // Add all VBox containers to the HBox
        // Make each VBox grow to fill available space equally
        HBox.setHgrow(upgradeBox, Priority.ALWAYS);
        HBox.setHgrow(playerBox, Priority.ALWAYS);
        HBox.setHgrow(enemyBox, Priority.ALWAYS);
        this.getChildren().addAll(upgradeBox, playerBox, enemyBox);
    }
}