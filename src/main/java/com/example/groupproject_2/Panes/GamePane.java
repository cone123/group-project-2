package com.example.groupproject_2.Panes;
import static com.example.groupproject_2.Const.*;
import static com.example.groupproject_2.Classes.Player.*;
import com.example.groupproject_2.Classes.Enemy;
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
    private ListView<Upgrade> upgradeList;
    private ListView<Achievement> achievementList;
    private VBox menuBox;
    private final VBox enemyBox;
    private final VBox playerBox;
    private Achievement achievement1;
    private Label moneyLabel;
    private Label clickPowerLabel;
    private final List<Image> enemyPics = new ArrayList<>(); // init ts pic b4 that
    private Enemy easyEnemy; // initialize an enemy not here
    private Label enemyHealth = new Label();
    private StackPane hitBox = new StackPane();

    public GamePane() {
        loadEnemyImages();
        easyEnemy = makeEnemy();
        menuBox = createMenu();
        playerBox = createPlayerBox();
        enemyBox = createEnemyBox();
        initializeMenu();
        autoClicking();
    }
    /*
    * this makes things faster
    * */
    private void loadEnemyImages(){
        enemyPics.add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/chicken.png"))));
        enemyPics.add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/enemy1.png"))));
    }
    /*
    * gets the upgrade name then switch statements the name
    * then plus one to the upgrade
    * */
    private void applyUpgradeEffect(Upgrade upgrade) {
        String name = upgrade.getName().toLowerCase();
        switch (name) {
            case "click power" -> player.setClickPower(player.getClickPower() + 1);
            case "auto click power" -> player.setAutoClickRate(player.getAutoClickRate() + 0.5);
        }
    }
    /*
    * method for the autoclicking upgrade
    * every second you get money
    * also hits enemy now
    * */
    private void autoClicking(){
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e->{
                    double autoClickRate = player.getAutoClickRate();
                    double newMoney = player.getMoney()+autoClickRate;
                    player.setMoney(newMoney);
                    moneyLabel.setText("$" + player.getMoney());
                    System.out.println("auto clicking money" + autoClickRate);
                    // temp
                    double health = easyEnemy.getHealth();
                    if (health > 0){
                        easyEnemy.setHealth(health-player.getAutoClickRate());
                        enemyHealth.setText(easyEnemy.healthToString());
                    } else if (health <= 1) {
                        enemyKilled();
                    }
                })
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    /*
    * if the health is more than 0 take damage else enemyKilled triggers
    * or if the damage done goes negative kill the enemy
    * */
    private void enemyHit(){
        double health = easyEnemy.getHealth();
        if (health > 0){
            if(easyEnemy.getHealth()-player.getClickPower() < 0){
                enemyKilled();
            }
            easyEnemy.setHealth(health-player.getClickPower());
        } else if (health <= 1) {
            enemyKilled();
        }
        enemyHealth.setText(easyEnemy.healthToString());
    }
    /*
    * basically this just makes a new enemy with class attributes
    * and then sets all the images and visual stuff
    * */
    private void enemyKilled() {
        easyEnemy = makeEnemy();
        hitBox.getChildren().clear();
        ImageView backgroundRight = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/two-cliffs-on-sky-background-vector_right.jpg"))));
        backgroundRight.setFitWidth((double) SCREEN_WIDTH /3);
        backgroundRight.prefHeight(SCREEN_HEIGHT);
        backgroundRight.setTranslateX(-1);
        enemyHealth.setText(easyEnemy.healthToString());
        Label enemyNameLabel = new Label(easyEnemy.getName());
        enemyNameLabel.setFont(Font.font(20));
        enemyNameLabel.setTranslateY(-HIT_BOX_SIZE-40);
        easyEnemy.getImageView().setFitWidth(HIT_BOX_SIZE*2);
        easyEnemy.getImageView().setFitHeight(HIT_BOX_SIZE*2);
        easyEnemy.getImageView().setPreserveRatio(true);
        hitBox.getChildren().addAll(backgroundRight, easyEnemy.getImageView(), enemyHealth,enemyNameLabel);
    }
    /*
    * enemy hit and clickedEnemy are intentionally separate
    * because this is more organized ?
    * */
    private void clickedEnemy(){
        enemyHit();
        double money = player.getMoney()+player.getClickPower();
        player.setMoney(money);
        player.setTotalClicks(player.getTotalClicks()+1);
        if(player.getTotalClicks() >= 100 && !achievement1.isUnlocked()){
            achievement1.setUnlocked(true);
            achievementList.getItems().add(achievement1);
        }
        moneyLabel.setText("$" + player.getMoney());
        clickPowerLabel.setText("Click Power: " + player.getClickPower());
        System.out.println(money);
        System.out.println("$" + player.getMoney());
        System.out.println("Total Clicks: " + player.getTotalClicks());
    }
    /*
    * makes the enemy box initially some of the stuff
    * gets updated when enemyKilled is triggered
    * */
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
        Label enemyNameLabel = new Label(easyEnemy.getName());
        enemyNameLabel.setFont(Font.font(20));
        enemyNameLabel.setTranslateY(-HIT_BOX_SIZE-40);

        easyEnemy.getImageView().setFitWidth(HIT_BOX_SIZE*2);
        easyEnemy.getImageView().setFitHeight(HIT_BOX_SIZE*2);
        easyEnemy.getImageView().setPreserveRatio(true);
        hitBox.getChildren().addAll(backgroundRight,easyEnemy.getImageView(),enemyHealth,enemyNameLabel);
        hitBox.setPrefSize(200, 200);
        hitBox.setOnMouseClicked(e -> { // Clicking enemy event
            clickedEnemy();
        });
        enemyBox.getChildren().addAll(hitBox);
        return enemyBox;
    }
    /*
    * this is for randomly generating enemies
    * */
    private Enemy makeEnemy(){
        ArrayList<String> names = new ArrayList<>(Arrays.asList("bob","joe","james","michael","evan","pepperoni","pizza","car","a chicken","evil chicken","not a chicken"));
        Enemy enemy1 = new Enemy();
        Random r = new Random();
        int rand = r.nextInt(names.size());
        enemy1.setName(names.get(rand));
        enemy1.setHealth(r.nextInt(4,8));

        int randomPic = r.nextInt(enemyPics.size());
        enemy1.setImageView(new ImageView(enemyPics.get(randomPic)));
        return enemy1;
    }
    /*
    * makes the player box
    * */
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
        clickPowerLabel.setTranslateY(-200);
        playerBox.getChildren().addAll(playerStack);
        playerBox.setMinWidth((double) SCREEN_WIDTH /3);
        return playerBox;
    }
    /*
    * the glue
    * */
    private void initializeMenu(){
        HBox.setHgrow(menuBox, Priority.ALWAYS);
        HBox.setHgrow(playerBox, Priority.ALWAYS);
        HBox.setHgrow(enemyBox, Priority.ALWAYS);
        this.getChildren().addAll(menuBox, playerBox, enemyBox);
    }
    /*
    * make menu box for upgrades etc
    * */
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
    /*
    * my holy grail switch statement
    * */
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
                System.out.println("set prev scene game");
                HelloApplication.mainStage.setScene(new OptionScene());
                break;
        }
    }
}