package com.example.groupproject_2.Scenes;
import com.example.groupproject_2.Const;
import com.example.groupproject_2.Panes.GamePane;
import javafx.scene.Scene;


public class GameScene extends Scene {

    public GameScene() {
        // = new Scene(root,width,height)
        super(new GamePane(), Const.SCREEN_WIDTH,Const.SCREEN_HEIGHT);
    }
}