package com.example.groupproject_2.Panes;

import static com.example.groupproject_2.Classes.Player.*;
import static com.example.groupproject_2.Const.*;
import com.example.groupproject_2.Classes.Upgrade;
import com.example.groupproject_2.Classes.Achievement;
import com.example.groupproject_2.HelloApplication;
import com.example.groupproject_2.Scenes.GameScene;
import com.example.groupproject_2.Scenes.OptionScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.util.Objects;


public class GamePane extends HBox {
    public GamePane() {
        this.setSpacing(0);
        // money and click power text
        Label moneyLabel = new Label(""+player.getMoney());
        moneyLabel.setFont(Font.font("",FontWeight.BOLD, FontPosture.REGULAR,40));
        moneyLabel.setTextFill(Color.GOLD);
        Label clickPowerLabel = new Label("Click Power: " + player.getClickPower());
        // Left side (Upgrade Box)
        VBox upgradeBox = new VBox(10);
        upgradeBox.setBackground(Background.fill(Color.GREEN));
        upgradeBox.setMinWidth((double) SCREEN_WIDTH /3);
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
                moneyLabel.setText("" + player.getMoney());
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
        ImageView backgroundLeft = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/two-cliffs-on-sky-background-vector_left.jpg"))));
        backgroundLeft.setFitWidth((double) SCREEN_WIDTH /3);
        backgroundLeft.setFitHeight(SCREEN_HEIGHT);

        moneyLabel.setTranslateY((double) -SCREEN_HEIGHT /2+30);
        StackPane playerStack = new StackPane(backgroundLeft,moneyLabel,clickPowerLabel);

        playerBox.getChildren().addAll(playerStack);
        playerBox.setMinWidth((double) SCREEN_WIDTH /3);










        // Right (Enemy Box)
        VBox enemyBox = new VBox(10);
        enemyBox.setMinWidth((double) SCREEN_WIDTH /3);

        Circle circle = new Circle(HIT_BOX_SIZE, Color.rgb(0,0,0,0)); // using circle for temp enemy
        ImageView enemy = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/vecteezy_transparent-background-with-a-chick_23959280.png"))));
        ImageView backgroundRight = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/two-cliffs-on-sky-background-vector_right.jpg")))); //https://static.vecteezy.com/system/resources/previews/033/106/191/non_2x/two-cliffs-on-sky-background-vector.jpg
        backgroundRight.setFitWidth((double) SCREEN_WIDTH /3);
        backgroundRight.setFitHeight(SCREEN_HEIGHT);
        backgroundRight.setTranslateX(-1);

        enemy.setFitWidth(HIT_BOX_SIZE*2);
        enemy.setFitHeight(HIT_BOX_SIZE*2);
        enemy.setPreserveRatio(true);

        StackPane hitBox = new StackPane(backgroundRight,enemy,circle);
        hitBox.setPrefSize(200, 200);
        circle.setOnMouseClicked(e -> { // Clicking enemy event
            double money = player.getMoney()+player.getClickPower();
            System.out.println(money);
            player.setMoney(money); // adding money each click
            System.out.println("" + player.getMoney()); // print to console
            player.setTotalClicks(player.getTotalClicks()+1); // add total clicks
            System.out.println("Total Clicks: " + player.getTotalClicks());
            if(player.getTotalClicks() >= 100 && !achievement1.isUnlocked()){ // achievement
                achievement1.setUnlocked(true);
                achievementList.getItems().add(achievement1.getName());
            }
            // need to update the labels for money
            moneyLabel.setText("" + player.getMoney());
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