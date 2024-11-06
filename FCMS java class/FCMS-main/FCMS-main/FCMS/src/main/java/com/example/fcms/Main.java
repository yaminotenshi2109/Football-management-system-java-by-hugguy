package com.example.fcms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private static Stage stg; 
    @Override
    public void start(Stage stage) throws IOException {
        stg = stage;
        stage.setResizable(false); 

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1400, 850);
        stage.setTitle("Football Club Management System");
        stage.setScene(scene);
        stage.show();
    }

    public void changeScene(String fxml) throws IOException{        
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
    }

    public static void main(String[] args) {
        launch();
    }
}