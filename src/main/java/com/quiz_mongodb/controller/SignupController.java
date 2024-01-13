package com.quiz_mongodb.controller;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import com.quiz_mongodb.database.Database;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class SignupController implements Initializable {
    @FXML
    TextField tf_username;
    @FXML
    PasswordField tf_password, tf_password_confirmation;
    @FXML
    Label toast;

    MongoDatabase database;
    MongoCollection<Document> users;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        database = Database.getDatabase();
        users = database.getCollection("users");
    }

    @FXML
    public void signup() {
        String username = tf_username.getText();
        String password = tf_password.getText();
        String password_confirmation = tf_password_confirmation.getText();

        if (username.isEmpty() || password.isEmpty() || password_confirmation.isEmpty()) {
            // TODO
            System.out.println("R");
        } else {
            if (!password.equals(password_confirmation)) {
                System.out.println("Password");
            } else {
                Document user = new Document();

                user.append("username", username);
                user.append("password", password);
                InsertOneResult result = users.insertOne(user);

                if (result.getInsertedId() != null) {
                    showToast("Inscription effectuée");
                    loadNewContent("signin.fxml");
                }
            }
        }
    }

    private void showToast(String message) {
        toast.setText(message);
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
                    // TODO chargment de la scène principal de l'application
                });
            }
        };

        timer.schedule(task, 2000);
    }

    @FXML
    public void loadSignin() {
        loadNewContent("signin.fxml");
    }

    private void loadNewContent(String fichier) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("views/" + fichier));
            Stage stage = (Stage) tf_username.getScene().getWindow();
            Scene scene = new Scene(loader.load(), stage.getWidth(), stage.getHeight());

            stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
