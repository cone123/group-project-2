package com.example.groupproject_2.Panes;

import static com.example.groupproject_2.Classes.Player.*;
import com.example.groupproject_2.Classes.Upgrade;
import com.example.groupproject_2.Classes.Achievement;
import com.example.groupproject_2.HelloApplication;
import com.example.groupproject_2.Scenes.GameScene;
import com.example.groupproject_2.Scenes.IntroScene;
import com.example.groupproject_2.Scenes.OptionScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class GamePane extends HBox {
    public GamePane() {
        
        // money and click power text
        Label moneyLabel = new Label("Money: " + player.getMoney());
        Label clickPowerLabel = new Label("Click Power: " + player.getClickPower());
        // Left side (Upgrade Box)
        VBox upgradeBox = new VBox(10);
        upgradeBox.setBackground(Background.fill(Color.GREEN));
        upgradeBox.getChildren().add(new Label(""));
        HBox buttonArea = new HBox(10);
        // upgrade list
        Upgrade clickPower = new Upgrade("click power",10,1.15);
        Upgrade autoClickPower = new Upgrade("auto click power",10,1.2);
        Upgrade goldbonus = new Upgrade("gold bonus",10,1.3);
        ListView<Upgrade> upgradeList = new ListView<>();
        upgradeList.getItems().addAll(clickPower,autoClickPower,goldbonus);
        upgradeList.setVisible(false);
        upgradeList.setOnMouseClicked(event -> {
            Upgrade selectedUpgrade = upgradeList.getSelectionModel().getSelectedItem();
            if (selectedUpgrade != null && player.getMoney() >= selectedUpgrade.getCurrentCost()) {
                // Deduct cost
                player.setMoney((int) (player.getMoney() - selectedUpgrade.getCurrentCost()));
                // Purchase the upgrade
                selectedUpgrade.purchase();
                // Apply the upgrade effect
                applyUpgradeEffect(selectedUpgrade);
                // Refresh the list to show updated cost
                upgradeList.refresh();
                System.out.println(player.getClickPower());
                // need to update the labels for money
                moneyLabel.setText("Money: " + player.getMoney());
                clickPowerLabel.setText("Click Power: " + player.getClickPower());
            }
        });

        // achievements
        ListView<String>achievementList = new ListView<>();
        Achievement achievement1 = new Achievement();
        achievement1.setName("you clicked 100 times");
        achievementList.setVisible(false);

        // buttons
        Button upgradeMenu = new Button("Upgrade Menu");
        Button achievements = new Button("Achievements");
        Button options = new Button("Options");
        //action event
        upgradeMenu.setOnAction(e -> {
            if(upgradeList.isVisible()) {
                upgradeList.setVisible(false);
            } else if (!upgradeList.isVisible()) {
                upgradeList.setVisible(true);
            }
        });
        achievements.setOnAction(e -> {
            if(achievementList.isVisible()) {
                achievementList.setVisible(false);
            }else if(!achievementList.isVisible()) {
                achievementList.setVisible(true);
            }
        });
        options.setOnAction(e->{
            player.setPreviousScene(new GameScene());
            HelloApplication.mainStage.setScene(new OptionScene());
        });
        // addin children
        buttonArea.getChildren().addAll(upgradeMenu, achievements, options);
        buttonArea.setBackground(Background.fill(Color.RED));
        upgradeBox.getChildren().addAll(buttonArea,upgradeList,achievementList);

        // Middle (Player Box)
        VBox playerBox = new VBox(10);
        playerBox.getChildren().add(new Label("Player Section"));
        playerBox.setBorder(Border.stroke(Color.BLACK));

        playerBox.getChildren().addAll(moneyLabel, clickPowerLabel);

        // Right (Enemy Box)
        VBox enemyBox = new VBox(10);
        enemyBox.getChildren().add(new Label("Enemy Section"));
        enemyBox.setBorder(Border.stroke(Color.BLACK));

        Circle circle = new Circle(50, Color.RED); // using circle for temp enemy
        StackPane hitBox = new StackPane(circle);
        hitBox.setPrefSize(200, 200);
        circle.setOnMouseClicked(e -> { // Clicking enemy event
            double money = player.getMoney()+player.getClickPower();
            System.out.println(money);
            player.setMoney(money); // adding money each click
            System.out.println("Money: " + player.getMoney()); // print to console
            player.setTotalClicks(player.getTotalClicks()+1); // add total clicks
            System.out.println("Total Clicks: " + player.getTotalClicks());
            if(player.getTotalClicks() >= 100 && !achievement1.isUnlocked()){ // achievement
                achievement1.setUnlocked(true);
                achievementList.getItems().add(achievement1.getName());
            }
            // need to update the labels for money
            moneyLabel.setText("Money: " + player.getMoney());
            clickPowerLabel.setText("Click Power: " + player.getClickPower());
        });
        enemyBox.getChildren().addAll(hitBox);
        // Add all VBox containers to the HBox
        // Make each VBox grow to fill available space equally
        HBox.setHgrow(upgradeBox, Priority.ALWAYS);
        HBox.setHgrow(playerBox, Priority.ALWAYS);
        HBox.setHgrow(enemyBox, Priority.ALWAYS);
        this.getChildren().addAll(upgradeBox, playerBox, enemyBox);
    }
    private void applyUpgradeEffect(Upgrade upgrade) {
        String name = upgrade.getName().toLowerCase();
        switch (name) {
            case "click power" -> player.setClickPower(player.getClickPower() + 1);
            case "auto click power" -> player.setAutoClickRate(player.getAutoClickRate() + 0.5);
            case "gold bonus" -> player.setGoldMultiplier(player.getGoldMultiplier() + 0.2);
        }
    }
}