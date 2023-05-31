package com.emsi.tache_manage.Controller;

import com.emsi.tache_manage.HelloApplication;
import com.emsi.tache_manage.entities.Employe;
import com.emsi.tache_manage.services.EmployeService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class LoginController {
 EmployeService employeService = new EmployeService();
    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Label messageLabel;


    @FXML
    private void initialize() {
        handleCancel();
    }

    @FXML
    private void handleLogin() throws Exception {
        String email = emailField.getText();
        String password = passwordField.getText();

        if (authenticate(email, password)) {
            messageLabel.setText("Authentification réussie!");
            messageLabel.setStyle("-fx-text-fill: green;");

            showMainInterface();

            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.close();

        } else {
            messageLabel.setText("Email ou mot de passe incorrect.");
            messageLabel.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    private void handleCancel() {
        emailField.setText("");
        passwordField.setText("");
        messageLabel.setText("");
    }

    private boolean authenticate(String email, String password) {
        List<Employe> employeList = employeService.findAll();
        for (Employe employe:employeList){
            if(email.equals(employe.getEmail()) && password.equals(employe.getPassword())){
                return true;
            }
        }
        return false;
    }

    private void showMainInterface() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/emsi/tache_manage/View/Main.fxml"));
            Parent root = loader.load();
            MainController mainController = loader.getController();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            // Passer la référence du stage à MainController
            mainController.setStage(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
