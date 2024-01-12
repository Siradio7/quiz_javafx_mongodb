package com.quiz_mongodb.controller;

import com.mongodb.client.*;
import com.quiz_mongodb.database.Database;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.bson.Document;

import java.net.URL;
import java.util.ResourceBundle;

public class SigninController implements Initializable {
    @FXML
    TextField tf_username;
    @FXML
    PasswordField tf_password;

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
                Alert success = new Alert(Alert.AlertType.INFORMATION);
                success.setTitle("Information");
                success.setContentText("Vous êtes connecté avec succès");
                success.show();
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
}
