package com.emsi.tache_manage.Controller;

import com.emsi.tache_manage.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {


    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private BorderPane borderPane;

    @FXML
    private VBox vboxaff;


    @FXML
    private void handleAjouter() {
        loadContent("/com/emsi/tache_manage/View/ajoutache.fxml");
    }

    @FXML
    private void handleDetails() {
        loadContent("/com/emsi/tache_manage/View/detailstache.fxml");
    }

    @FXML
    private void handleExcel() {
        loadContent("/com/emsi/tache_manage/View/TacheExcel.fxml");
    }

    @FXML
    private void handleJson() {
        loadContent("/com/emsi/tache_manage/View/TacheJson.fxml");
    }

    @FXML
    private void handleTxt() {
        loadContent("/com/emsi/tache_manage/View/TacheTxt.fxml");
    }



    private void loadContent(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Pane content = loader.load();
            stage.setTitle("TacheApp");

            borderPane.setCenter(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handlelogout() throws IOException {
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 650);
        stage.setTitle("Authentification");
        stage.setScene(scene);
        stage.show();
    }

}
