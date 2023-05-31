package com.emsi.tache_manage.Controller;

import com.emsi.tache_manage.entities.Tache;
import com.emsi.tache_manage.enums.Priority;
import com.emsi.tache_manage.enums.Statut;
import com.emsi.tache_manage.services.TacheService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DetailsController implements Initializable {
    TacheService tacheService = new TacheService();


    @FXML
    private TextField idTextField;
    @FXML
    private TextField nomTextField;
    @FXML
    private TextArea descriptionTextArea;
    @FXML
    private ToggleGroup prioriteToggleGroup;
    @FXML
    private ToggleGroup statutToggleGroup;

    @FXML
    private RadioButton debut;

    @FXML
    private RadioButton encours;

    @FXML
    private RadioButton termine;

    @FXML
    private RadioButton basse;

    @FXML
    private RadioButton moyenne;

    @FXML
    private RadioButton haute;

    @FXML
    private Button btnedit;

    @FXML
    private Button btndelete;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tacheList = FXCollections.observableArrayList();

        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        columnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        columnStatut.setCellValueFactory(new PropertyValueFactory<>("statut"));
        columnPriorite.setCellValueFactory(new PropertyValueFactory<>("priority"));

        List<Tache> tacheList1 = tacheService.findAll();
        for (Tache tache : tacheList1){
            tacheList.add(new Tache(tache.getId(),tache.getNom(),tache.getDescription(),tache.getStatut(),tache.getPriority()));
        }

        tableTaches.setItems(tacheList);

        btndelete.setOnAction(event -> {
            Tache selectedTache = tableTaches.getSelectionModel().getSelectedItem();
            if (selectedTache != null) {
                tableTaches.getItems().remove(selectedTache);
                tacheService.remove(selectedTache);
            } else {
                // Aucune ligne sélectionnée, afficher un message d'erreur ou prendre une autre action
            }
        });

        tableTaches.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                idTextField.setText(String.valueOf(newValue.getId()));
                nomTextField.setText(newValue.getNom());
                descriptionTextArea.setText(newValue.getDescription());
                if (newValue.getStatut().name().equals("debut")) {
                    statutToggleGroup.selectToggle(debut);
                } else if (newValue.getStatut().name().equals("encours")) {
                    statutToggleGroup.selectToggle(encours);
                } else if (newValue.getStatut().name().equals("termine")) {
                    statutToggleGroup.selectToggle(termine);
                }

                if (newValue.getPriority().name().equals("basse")) {
                    prioriteToggleGroup.selectToggle(basse);
                } else if (newValue.getPriority().name().equals("moyenne")) {
                    prioriteToggleGroup.selectToggle(moyenne);
                } else if (newValue.getPriority().name().equals("haute")) {
                    prioriteToggleGroup.selectToggle(haute);
                }
            }
        });

    }

    @FXML
    private void modifyTache() throws IOException {
        Tache tache = new Tache();
        Tache selectedTache = tableTaches.getSelectionModel().getSelectedItem();
        if (selectedTache != null) {
            selectedTache.setNom(nomTextField.getText());
            tache.setNom(nomTextField.getText());
            selectedTache.setDescription(descriptionTextArea.getText());
            tache.setDescription(descriptionTextArea.getText());
            RadioButton selectedStatutRadioButton = (RadioButton) statutToggleGroup.getSelectedToggle();
            if (selectedStatutRadioButton != null) {
                String statut = selectedStatutRadioButton.getText().toLowerCase().replaceAll("\\s", "");
                selectedTache.setStatut(Statut.valueOf(statut));
                tache.setStatut(Statut.valueOf(statut));
            }
            RadioButton selectedPriorityRadioButton = (RadioButton) prioriteToggleGroup.getSelectedToggle();
            if (selectedPriorityRadioButton != null) {
                String priority = selectedPriorityRadioButton.getText().toLowerCase().replaceAll("\\s", "");
                selectedTache.setPriority(Priority.valueOf(priority));
                tache.setPriority(Priority.valueOf(priority));
            }
            tache.setId(Integer.valueOf(idTextField.getText()));
            tacheService.update(tache);
            tableTaches.refresh();

        }
    }
}
