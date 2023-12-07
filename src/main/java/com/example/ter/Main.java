package com.example.ter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Group root = new Group();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("TresEnRaya.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("Tres En Raya");
        stage.setResizable(false);

        stage.setScene(scene);
        stage.show();
    }
}