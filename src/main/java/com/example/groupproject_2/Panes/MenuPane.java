package com.example.groupproject_2.Panes;

import com.example.groupproject_2.HelloApplication;
import com.example.groupproject_2.Scenes.IntroScene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class MenuPane extends BorderPane {
    public MenuPane(){
        this.setTop(new Text("Menu Screen"));
        Button start = new Button("Start");
        this.setCenter(start);
        start.setOnMouseClicked(e->{
            HelloApplication.mainStage.setScene(new IntroScene());
        });

    }
}
