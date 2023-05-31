package com.emsi.tache_manage.Controller;

import com.emsi.tache_manage.entities.Tache;
import com.emsi.tache_manage.services.TacheService;
import com.emsi.tache_manage.services.files.TacheFile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


import java.util.List;

public class ControllerExcel {
    TacheFile tacheFile = new TacheFile();

    @FXML
    private Label labelimp;

    @FXML
    private Label labelexp;

    @FXML
    private TableView<Tache> tableTaches;

    @FXML
    private TableColumn<Tache, Integer> columnId;

    @FXML
    private TableColumn<Tache, String> columnNom;

    @FXML
    private TableColumn<Tache, String> columnDescription;

    @FXML
    private TableColumn<Tache, String> columnStatut;

    @FXML
    private TableColumn<Tache, String> columnPriorite;

    private ObservableList<Tache> tacheList;

    TacheService tacheService = new TacheService();

    public void ExporterExcel(){
        tacheFile.ExportTacheExcel();
        Boolean res = (tacheFile.ExportTacheExcel());
        if (res.equals(true)){
            labelexp.setText("Success");
            labelexp.setStyle("-fx-text-fill: green;");
            labelimp.setText("");
            tableTaches.getItems().clear();
        } else{
            labelexp.setText("Erreur");
            labelexp.setStyle("-fx-text-fill: red;");
        }
    }


    public void ImporterExcel(){
        tacheFile.viderandreinitialiser();
        Boolean res = (tacheFile.ImportTacheExcel());
        if(res.equals(true)){
            labelimp.setText("Success");
            labelimp.setStyle("-fx-text-fill: green;");
            labelexp.setText("");
            ResultImportExcel();
        } else{
            labelimp.setText("Error");
            labelimp.setStyle("-fx-text-fill: red;");
            tableTaches.getItems().clear();
        }
    }

    public void ResultImportExcel(){
        tacheList = FXCollections.observableArrayList();

        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        columnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        columnStatut.setCellValueFactory(new PropertyValueFactory<>("statut"));
        columnPriorite.setCellValueFactory(new PropertyValueFactory<>("priority"));

        List<Tache> tacheList1 = tacheService.findAll();
        for (Tache tache : tacheList1) {
            tacheList.add(new Tache(tache.getId(), tache.getNom(), tache.getDescription(), tache.getStatut(), tache.getPriority()));
        }

        tableTaches.setItems(tacheList);
    }



}
