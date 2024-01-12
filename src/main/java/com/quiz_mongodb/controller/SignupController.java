package com.quiz_mongodb.controller;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import com.quiz_mongodb.database.Database;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.bson.Document;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class SignupController implements Initializable {
    @FXML
    TextField tf_username;
    @FXML
    PasswordField tf_password, tf_password_confirmation;

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
                    Alert success = new Alert(Alert.AlertType.INFORMATION);
                    success.setContentText("Inscription effectuée");
                    success.show();
                    loadNewContent("signin.fxml");
                }
            }
        }
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
