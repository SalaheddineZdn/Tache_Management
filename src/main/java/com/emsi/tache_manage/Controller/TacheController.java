package com.emsi.tache_manage.Controller;

import com.emsi.tache_manage.entities.Tache;
import com.emsi.tache_manage.services.TacheService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class TacheController {


    private TacheService tacheService; // Service pour gérer les opérations CRUD sur les tâches

    // Constructeur
    public TacheController() {
        // Initialiser le service
        tacheService = new TacheService(); // Supposons que vous avez une classe TacheService pour gérer les opérations sur les tâches
    }

    // Méthode pour créer une nouvelle tâche
    public void createTache(Tache tache) {
        tacheService.save(tache);
    }

    // Méthode pour mettre à jour une tâche existante
    public void updateTache(Tache tache) {
        tacheService.update(tache);
    }

    // Méthode pour supprimer une tâche
    public void deleteTache(Tache tache) {
        tacheService.remove(tache);
    }

    // Méthode pour récupérer toutes les tâches
    public List<Tache> getAllTaches() {
        return tacheService.findAll();
    }

    @FXML
    public static void openlist(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(TacheController.class.getResource("com/emsi/tache_manage/View/detailstache.fxml"));
            Parent root = fxmlLoader.load();
            //Scene scene = node.getScene();
            //scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public static void openajout(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(TacheController.class.getResource("com/emsi/tache_manage/View/ajoutache.fxml"));
            Parent root = fxmlLoader.load();

            DetailsController newController = fxmlLoader.getController();

            // Afficher la nouvelle interface dans la même fenêtre
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public static void openedit(ActionEvent actionEvent) {
    }

}
