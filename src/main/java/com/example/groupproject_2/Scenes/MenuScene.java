package com.example.groupproject_2.Scenes;
import com.example.groupproject_2.Const;
import com.example.groupproject_2.Panes.MenuPane;
import javafx.scene.Scene;

public class MenuScene extends Scene {
    public MenuScene(){
        super(new MenuPane(), Const.SCREEN_WIDTH,Const.SCREEN_HEIGHT);
    }
}
