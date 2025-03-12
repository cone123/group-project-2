package com.example.groupproject_2.Scenes;

import com.example.groupproject_2.Const;
import com.example.groupproject_2.Panes.CreditsPane;
import javafx.scene.Scene;

public class CreditsScene extends Scene {
    // = new Scene(root,width,height)
    public CreditsScene(){
        super(new CreditsPane(), Const.SCREEN_WIDTH,Const.SCREEN_HEIGHT);
    }
}
