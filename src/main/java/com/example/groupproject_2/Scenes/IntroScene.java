package com.example.groupproject_2.Scenes;

import com.example.groupproject_2.Const;
import com.example.groupproject_2.Panes.IntroPane;
import javafx.scene.Scene;


public class IntroScene extends Scene {

    public IntroScene() {
        // = new Scene(root,width,height)
        super(new IntroPane(), Const.SCREEN_WIDTH,Const.SCREEN_HEIGHT);
    }
}
