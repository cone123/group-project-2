package com.example.groupproject_2.Panes;
import static com.example.groupproject_2.Const.*;

import com.example.groupproject_2.Classes.Enemy;
import com.example.groupproject_2.Classes.Player;
import com.example.groupproject_2.Classes.Upgrade;
import com.example.groupproject_2.Classes.Achievement;
import com.example.groupproject_2.HelloApplication;
import com.example.groupproject_2.Scenes.GameScene;
import com.example.groupproject_2.Scenes.OptionScene;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

import java.util.*;

public class GamePane extends HBox {
    private final Player player;
    private ListView<Upgrade> upgradeList;
    private ListView<Achievement> achievementList;
    private VBox menuBox;
    private final VBox enemyBox;
    private final VBox playerBox;
    private Achievement achievement1;
    private Label moneyLabel;
    private Label clickPowerLabel;
    private Enemy easyEnemy = this.makeEnemy(); // initialize an enemy
    private Label enemyHealth = new Label();
    public GamePane() {
        this.player = new Player();

        // Left side menu
        menuBox = createMenu();
        // Middle player
        playerBox = createPlayerBox();
        // Right enemy
        enemyBox = createEnemyBox();
        // Add all VBox to the HBox
        // Make each VBox grow to fill available space equally
        initializeMenu();
        autoClicking();
    }
    private void applyUpgradeEffect(Upgrade upgrade) {
        String name = upgrade.getName().toLowerCase();
        switch (name) {
            case "click power" -> player.setClickPower(player.getClickPower() + 1);
            case "auto click power" -> player.setAutoClickRate(player.getAutoClickRate() + 0.5);
        }
    }
    private void autoClicking(){
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e->{
                    double autoClickRate = player.getAutoClickRate();
                    double newMoney = player.getMoney()+autoClickRate;
                    player.setMoney(newMoney);
                    moneyLabel.setText("$" + player.getMoney());
                    System.out.println("auto clicking money" + autoClickRate);
                })
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    private void enemyHit(){
        double health = easyEnemy.getHealth();
        if (health > 0){
            easyEnemy.setHealth(health-1);
        } else if (health <= 0) {
            enemyKilled();
        }
        enemyHealth.setText(easyEnemy.healthToString());
    }
    private void enemyKilled(){
        easyEnemy = makeEnemy();
        enemyHealth.setText(easyEnemy.healthToString());
        easyEnemy.getImageView().setFitWidth(HIT_BOX_SIZE * 2);
        easyEnemy.getImageView().setFitHeight(HIT_BOX_SIZE * 2);

        System.out.println("killed enemy");
    }
    private void clickedEnemy(){
        enemyHit();
        double money = player.getMoney()+player.getClickPower();
        player.setMoney(money);
        player.setTotalClicks(player.getTotalClicks()+1);
        if(player.getTotalClicks() >= 100 && !achievement1.isUnlocked()){
            achievement1.setUnlocked(true);
            achievementList.getItems().add(achievement1);
        }
        moneyLabel.setText("" + player.getMoney());
        clickPowerLabel.setText("Click Power: " + player.getClickPower());
        System.out.println(money);
        System.out.println("" + player.getMoney());
        System.out.println("Total Clicks: " + player.getTotalClicks());
    }
    private VBox createEnemyBox(){
        // background and box
        VBox enemyBox = new VBox(10);
        enemyBox.setMinWidth((double) SCREEN_WIDTH /3);
        ImageView backgroundRight = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/two-cliffs-on-sky-background-vector_right.jpg")))); //https://static.vecteezy.com/system/resources/previews/033/106/191/non_2x/two-cliffs-on-sky-background-vector.jpg
        backgroundRight.setFitWidth((double) SCREEN_WIDTH /3);
        backgroundRight.prefHeight(SCREEN_HEIGHT);
        backgroundRight.setTranslateX(-1);
        // health
        enemyHealth.setFont(Font.font("Arial",FontWeight.BOLD,20));
        enemyHealth.setTextFill(Color.RED);
        enemyHealth.setText(easyEnemy.healthToString());
        enemyHealth.setTranslateY(-HIT_BOX_SIZE -10);
        // name

        easyEnemy.getImageView().setFitWidth(HIT_BOX_SIZE*2);
        easyEnemy.getImageView().setFitHeight(HIT_BOX_SIZE*2);
        easyEnemy.getImageView().setPreserveRatio(true);
        StackPane hitBox = new StackPane(backgroundRight,easyEnemy.getImageView(),enemyHealth);
        hitBox.setPrefSize(200, 200);
        hitBox.setOnMouseClicked(e -> { // Clicking enemy event
            clickedEnemy();
        });
        enemyBox.getChildren().addAll(hitBox);
        return enemyBox;
    }
    private Enemy makeEnemy(){
        ArrayList<String> names = new ArrayList<>(Arrays.asList("bob","joe","james"));
        Enemy enemy1 = new Enemy();
        Random r = new Random();
        int rand = r.nextInt(names.size());
        enemy1.setName(names.get(rand));
        enemy1.setHealth(r.nextInt(4,8));
        ArrayList<Image> enemyPics = new ArrayList<>();
        enemyPics.add((new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/chicken.png")))));
        enemyPics.add((new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/enemy1.png")))));
        int randomPic = r.nextInt(enemyPics.size());
        ImageView enemyImage = new ImageView(enemyPics.get(randomPic));
        enemy1.setImageView(enemyImage);
        return enemy1;
    }

    private VBox createPlayerBox(){
        VBox playerBox = new VBox(10);
        ImageView backgroundLeft = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/two-cliffs-on-sky-background-vector_left.jpg"))));
        backgroundLeft.setFitWidth((double) SCREEN_WIDTH /3);
        backgroundLeft.prefHeight(SCREEN_HEIGHT);
        moneyLabel = new Label("$"+ player.getMoney());
        moneyLabel.setFont(Font.font("",FontWeight.BOLD, FontPosture.REGULAR,40));
        moneyLabel.setTextFill(Color.GOLD);
        moneyLabel.setTranslateY((double) -SCREEN_HEIGHT /2+30);
        clickPowerLabel = new Label("Click Power: " + player.getClickPower());
        StackPane playerStack = new StackPane(backgroundLeft,moneyLabel,clickPowerLabel);
        playerBox.getChildren().addAll(playerStack);
        playerBox.setMinWidth((double) SCREEN_WIDTH /3);
        return playerBox;
    }
    private void initializeMenu(){
        HBox.setHgrow(menuBox, Priority.ALWAYS);
        HBox.setHgrow(playerBox, Priority.ALWAYS);
        HBox.setHgrow(enemyBox, Priority.ALWAYS);
        this.getChildren().addAll(menuBox, playerBox, enemyBox);
    }
    private VBox createMenu(){
        menuBox = new VBox(10);
        menuBox.setBackground(Background.fill(Color.YELLOW));
        menuBox.setMinWidth((double) SCREEN_WIDTH /3);
        HBox buttonArea = new HBox(10);
        buttonArea.setBackground(Background.fill(Color.BLACK));
        // buttons
        Button upgradeMenu = new Button("Upgrade Menu");
        Button achievements = new Button("Achievements");
        Button options = new Button("Options");
        upgradeMenu.setFocusTraversable(false);
        achievements.setFocusTraversable(false);
        options.setFocusTraversable(false);
        upgradeMenu.setPrefWidth(113);
        achievements.setPrefWidth(113);
        options.setPrefWidth(113);
        upgradeMenu.setBackground(Background.fill(Color.LIGHTCORAL));
        achievements.setBackground(Background.fill(Color.LIGHTCORAL));
        options.setBackground(Background.fill(Color.LIGHTCORAL));
        buttonArea.getChildren().addAll(upgradeMenu, achievements, options);
        // list views
        upgradeList = new ListView<>();
        upgradeList.setStyle("-fx-control-inner-background: #FFD700;");
        upgradeList.setPrefHeight(700);
        Upgrade clickPower = new Upgrade("click power",10,1.15);
        Upgrade autoClickPower = new Upgrade("auto click power",10,1.2);
        upgradeList.getItems().addAll(clickPower,autoClickPower);
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
                // need to update the labels for money
                moneyLabel.setText("" + player.getMoney());
                clickPowerLabel.setText("Click Power: " + player.getClickPower());
            }
        });
        // achievements
        achievementList = new ListView<>();
        achievementList.setPrefHeight(700);
        achievement1 = new Achievement();
        achievement1.setName("you clicked 100 times");

        upgradeMenu.setOnAction(e -> {
            switchMenu(upgradeMenu);
        });
        achievements.setOnAction(e -> {
            switchMenu(achievements);
        });
        options.setOnAction(e->{
            switchMenu(options);
        });
        buttonArea.setBackground(Background.fill(Color.RED));
        menuBox.getChildren().addAll(buttonArea,upgradeList);
        return menuBox;
    }
    private void switchMenu(Button button){
        switch (button.getText()){
            case "Upgrade Menu":
                menuBox.getChildren().remove(achievementList);
                if (!menuBox.getChildren().contains(upgradeList)) {
                    menuBox.getChildren().add(upgradeList);
                }
                break;
            case "Achievements":
                menuBox.getChildren().remove(upgradeList);
                if (!menuBox.getChildren().contains(achievementList)) {
                    menuBox.getChildren().add(achievementList);
                }
                break;
            case "Options":
                player.setPreviousScene(new GameScene());
                HelloApplication.mainStage.setScene(new OptionScene());
                break;
        }
    }
}