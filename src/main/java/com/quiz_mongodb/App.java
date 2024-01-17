package com.quiz_mongodb;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/signin.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.getIcons().add(new Image(String.valueOf(App.class.getResource("images/logo.png"))));
        stage.setScene(scene);
        stage.setTitle("QuizInfo");
        stage.show();

        stage.setOnCloseRequest(windowEvent -> {
            System.exit(0);
        });
    }
}
