package com.schooltracker.practica5;

import com.schooltracker.practica5.View.MainScreen;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        MainScreen root = new MainScreen();
        Scene scene = new Scene(root, 720, 480);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}
