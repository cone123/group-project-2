package com.example.groupproject_2.Panes;
import static com.example.groupproject_2.Const.*;
import com.example.groupproject_2.HelloApplication;
import com.example.groupproject_2.Scenes.OptionScene;
import javafx.animation.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;

public class CreditsPane extends Pane {
    public CreditsPane(){

        this.setBackground(Background.fill(Color.BLACK));

        Text title = new Text("Ultimate Clickerama");
        title.setFill(Color.BLUE);
        title.setTranslateX((double) SCREEN_WIDTH /2-50);
        title.setTranslateY((double) SCREEN_HEIGHT /2);
        this.getChildren().add(title);

        FadeTransition fadeTitle= new FadeTransition(Duration.millis(5000),title);
        fadeTitle.setFromValue(1);
        fadeTitle.setToValue(0);

        ScaleTransition scaleTitle = new ScaleTransition(Duration.millis(5000),title);
        scaleTitle.setByX(10f);
        scaleTitle.setByY(10f);

        ParallelTransition titleAnimation = new ParallelTransition(fadeTitle,scaleTitle);

        ArrayList<Text> textList = new ArrayList<>();
        textList.add(new Text("Developers:"));
        textList.add(new Text("Evan Benko"));
        textList.add(new Text("Cohen Poisson"));
        textList.add(new Text("Olivier Tshikani"));
        textList.add(new Text("Resources Used:"));
        textList.add(new Text("sword.png"));
        textList.add(new Text("enemy1.png"));
        textList.add(new Text("vecteezy_cute-devil-monster-character-for-your-creative-design_27294904.png"));
        textList.add(new Text("vecteezy_delightful-character-design-featuring-a-lovable-monster_27294885.png"));
        textList.add(new Text("ginbey.png"));
        textList.add(new Text("vecteezy_endearing-video-game-character-for-tshirt-artwork_27294906.png"));
        textList.add(new Text("vecteezy_enhance-your-designs-with-a-transparent-cartoon-cow_23546332.png"));
        textList.add(new Text("vecteezy_imaginative-and-lovable-game-character-for-tshirt-graphic_27294895.png"));
        textList.add(new Text("goat.png"));
        textList.add(new Text("monkey.png"));
        textList.add(new Text("vecteezy_playful-game-character-motif-for-your-creative-project_25175164.png"));
        textList.add(new Text("chicken.png"));
        textList.add(new Text("grass.png"));
        textList.add(new Text("Image Credits To:"));
        textList.add(new Text("https://www.vecteezy.com"));
        textList.add(new Text("https://www.pngaaa.com"));
        textList.add(new Text("https://in.pinterest.com/pin/stone-and-grass-platform--13018286414851365"));

        ParallelTransition scroll = new ParallelTransition();
        int offset = 0;
        for (Text text : textList) {
            text.setFill(Color.WHITE);

            text.setTranslateX((double) (SCREEN_WIDTH /2) - 300);
            text.setTranslateY(SCREEN_HEIGHT + 200 + offset);

            text.setFont(Font.font("Impact", FontWeight.THIN, 20));

            this.getChildren().add(text);

            TranslateTransition transition = new TranslateTransition(Duration.millis(21000), text);
            transition.setByY(-SCREEN_HEIGHT*4);
            transition.setInterpolator(Interpolator.LINEAR);
            scroll.getChildren().add(transition);

            offset += 100;
        }

        SequentialTransition animate = new SequentialTransition(titleAnimation, scroll);
        animate.setOnFinished(e->{
            HelloApplication.mainStage.setScene(new OptionScene());
        });
        animate.play();
    }
}
