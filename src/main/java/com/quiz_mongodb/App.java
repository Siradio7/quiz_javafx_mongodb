package com.quiz_mongodb;

import com.quiz_mongodb.controller.SigninController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.prefs.Preferences;

public class App extends Application {
    Preferences preferences = Preferences.userNodeForPackage(SigninController.class);
    private String USER_IS_CONNECTED_KEY = "";
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader;
        String userIsConnected = preferences.get(USER_IS_CONNECTED_KEY, "");

        if (!userIsConnected.isEmpty() && userIsConnected.equals("true")) {
            fxmlLoader = new FXMLLoader(App.class.getResource("views/home.fxml"));
        } else {
            fxmlLoader = new FXMLLoader(App.class.getResource("views/signin.fxml"));
        }

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
