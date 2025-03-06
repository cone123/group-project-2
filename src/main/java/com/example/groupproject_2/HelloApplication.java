package com.example.groupproject_2;
import com.example.groupproject_2.Scenes.IntroScene;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    public static Stage mainStage;

    @Override
    public void start(Stage stage) throws IOException {


        //makes the stage globally accessible, so we can use it in other files
        mainStage = stage;
        mainStage.setTitle("Hello!");
        mainStage.setScene(new IntroScene());
        mainStage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
