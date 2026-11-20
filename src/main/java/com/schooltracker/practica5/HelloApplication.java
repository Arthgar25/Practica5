package com.schooltracker.practica5;

import com.schooltracker.practica5.Model.CartaModel;
import com.schooltracker.practica5.View.MainScreen;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Automatically finds the file inside src/main/resources
        URL resource = getClass().getResource("/MP23_Dueling_Heroes_Cards.csv");

        if (resource == null) {
            throw new IOException("Could not find MP23_Dueling_Heroes_Cards.csv in resources.");
        }

        String filePath;
        try {
            filePath = Paths.get(resource.toURI()).toString();
        } catch (Exception e) {
            filePath = resource.getPath();
        }

        CartaModel model = new CartaModel(filePath);
        MainScreen root = new MainScreen();
        HelloController controller = new HelloController(model, root);
        Scene scene = new Scene(root, 720, 480);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}