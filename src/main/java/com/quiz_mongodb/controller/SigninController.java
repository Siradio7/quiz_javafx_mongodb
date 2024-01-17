package com.quiz_mongodb.controller;

import com.mongodb.client.*;
import com.quiz_mongodb.database.Database;
import com.quiz_mongodb.enums.ToastType;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.bson.Document;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.prefs.Preferences;

public class SigninController implements Initializable {
    @FXML
    TextField tf_username;
    @FXML
    PasswordField tf_password;
    @FXML
    Label toast;

    MongoDatabase database;
    MongoCollection<Document> users;
    Preferences preferences = Preferences.userNodeForPackage(SigninController.class);
    private String USER_IS_CONNECTED_KEY = "";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        database = Database.getDatabase();
        users = database.getCollection("users");
    }

    @FXML
    public void signin() {
        String username = tf_username.getText();
        String password = tf_password.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showToast("Remplissez tous les champs du formulaire", ToastType.INFO);
        } else {
            Document userDoc = new Document();
            userDoc.append("username", username);
            userDoc.append("password", password);
            Document result = users.find(userDoc).first();

            if (result != null) {
                showToast("Connecté avec succès", ToastType.SUCCESS);
                preferences.put(USER_IS_CONNECTED_KEY, "true");
                Database.close();
            } else {
                showToast("Erreur d'authentification", ToastType.ERROR);
            }
        }
    }

    private void showToast(String message, ToastType toastType) {
        toast.setText(message);

        switch (toastType) {
            case INFO:
                toast.setStyle("-fx-text-fill: white; -fx-background-color: #022B3A; -fx-background-radius: 10");
                //toast.setGraphic();
                break;
            case WARNING:
                toast.setStyle("-fx-text-fill: yellow; -fx-background-color: #022B3A; -fx-background-radius: 10");
                //toast.setGraphic(); On charge l'icône d'avertissement
                break;
            case ERROR:
                toast.setStyle("-fx-text-fill: red; -fx-background-color: #022B3A; -fx-background-radius: 10");
                //toast.setGraphic(); On charge l'icône d'erreur
                break;
            case SUCCESS:
                toast.setStyle("-fx-text-fill: green; -fx-background-color: #022B3A; -fx-background-radius: 10");
                //toast.setGraphic(); On charge l'icône de succès
        }

        toast.setVisible(true);
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setNode(toast);
        translateTransition.setDuration(Duration.millis(1000));
        translateTransition.setFromX(400);
        translateTransition.setByX(-400);
        translateTransition.play();

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                TranslateTransition translateTransition = new TranslateTransition();
                translateTransition.setNode(toast);
                translateTransition.setToX(400);
                translateTransition.setByX(400);
                translateTransition.setDuration(Duration.millis(1000));
                translateTransition.play();
                translateTransition.setOnFinished(event -> {
                    toast.setVisible(false);

                    if (toastType == ToastType.SUCCESS) {
                        loadNewContent("home.fxml");
                    }
                });
            }
        };

        timer.schedule(task, 2000);
    }

    @FXML
    public void loadSignup() {
        loadNewContent("signup.fxml");
    }

    private void loadNewContent(String fichier) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/quiz_mongodb/views/" + fichier));
            Stage stage = (Stage) tf_username.getScene().getWindow();
            Scene scene = new Scene(loader.load());

            stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
