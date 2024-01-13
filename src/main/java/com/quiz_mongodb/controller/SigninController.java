package com.quiz_mongodb.controller;

import com.mongodb.client.*;
import com.quiz_mongodb.database.Database;
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

public class SigninController implements Initializable {
    @FXML
    TextField tf_username;
    @FXML
    PasswordField tf_password;
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
    public void signin() {
        String username = tf_username.getText();
        String password = tf_password.getText();

        if (username.isEmpty() || password.isEmpty()) {
            // Traitement
            System.out.println("Remplissez tous les champs du formulaire");
        } else {
            Document userDoc = new Document();
            userDoc.append("username", username);
            userDoc.append("password", password);
            Document result = users.find(userDoc).first();

            if (result != null) {
                showToast("Connecté avec succès");
                //System.out.println(result.toJson());
                // Chargement de la scene principale une fois la connexion effecutée
                //Database.close();
            } else {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Erreur d'authentification");
                error.setContentText("Erreur, veuillez réessayer");
                error.show();
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
    public void loadSignup() {
        loadNewContent("signup.fxml");
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
