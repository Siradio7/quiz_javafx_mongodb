package com.quiz_mongodb.controller;

import com.quiz_mongodb.dao.UserDAO;
import com.quiz_mongodb.enums.ToastType;
import com.quiz_mongodb.modele.User;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void signup() {
        String username = tf_username.getText();
        String password = tf_password.getText();
        String password_confirmation = tf_password_confirmation.getText();

        if (username.isEmpty() || password.isEmpty() || password_confirmation.isEmpty()) {
            showToast("Veuillez remplir tous les champs", ToastType.INFO);
        } else {
            if (!password.equals(password_confirmation)) {
                showToast("Les mots de passe ne sont pas identiques", ToastType.WARNING);
            } else {
                User user = new User(username, password);
                UserDAO data = new UserDAO();

                if (data.find(user)) {
                    showToast("Ce nom d'utilisateur est déjà utilisé", ToastType.ERROR);
                } else {
                    data.add(user);
                    showToast("Inscription effectuée", ToastType.SUCCESS);
                }
            }
        }
    }

    @FXML
    public void loadSignin() {
        loadNewContent("signin.fxml");
    }

    // Méthode pour afficher le toast
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
                        loadNewContent("signin.fxml");
                    }
                });
            }
        };

        timer.schedule(task, 2000);
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
