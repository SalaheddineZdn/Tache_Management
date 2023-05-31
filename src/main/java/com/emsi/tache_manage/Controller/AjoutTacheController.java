package com.emsi.tache_manage.Controller;

import com.emsi.tache_manage.entities.Tache;
import com.emsi.tache_manage.enums.Priority;
import com.emsi.tache_manage.enums.Statut;
import com.emsi.tache_manage.services.TacheService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AjoutTacheController {
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

    TacheService tacheService = new TacheService();



    @FXML
    public void ajouterTache() {
        String nom = nomTextField.getText();
        String description = descriptionTextArea.getText();

        RadioButton selectedRadioButtonS = (RadioButton) statutToggleGroup.getSelectedToggle();
        String selectedOptionStatut = selectedRadioButtonS.getText().toLowerCase().replaceAll("\\s", "");
        RadioButton selectedRadioButtonP = (RadioButton) prioriteToggleGroup.getSelectedToggle();
        String selectedOptionPriority = selectedRadioButtonP.getText().toLowerCase().replaceAll("\\s", "");

        Statut statut = Statut.valueOf(selectedOptionStatut);
        Priority priority = Priority.valueOf(selectedOptionPriority);

        Tache nouvelleTache = new Tache(nom, description,statut,priority);
        tacheService.save(nouvelleTache);

        nomTextField.clear();
        descriptionTextArea.clear();
    }

}

