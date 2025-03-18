package com.example.groupproject_2.Scenes;

import com.example.groupproject_2.Const;
import com.example.groupproject_2.Panes.OptionPane;
import javafx.scene.Scene;

public class OptionScene extends Scene {
    public OptionScene() {
        // = new Scene(root,width,height)
        super(new OptionPane(), Const.SCREEN_WIDTH,Const.SCREEN_HEIGHT);
    }
}