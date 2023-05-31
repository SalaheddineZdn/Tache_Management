package com.emsi.tache_manage.Controller;

import com.emsi.tache_manage.services.files.TacheFile;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class ControllerTxt {
    TacheFile tacheFile = new TacheFile();

    @FXML
    private TextArea AreaResult;

    @FXML
    private Label labelimp;

    @FXML
    private Label labelexp;


    public void ExporterTxt(){
        tacheFile.ExportTacheTxt();
        Boolean res = (tacheFile.ExportTacheJson());
        if (res.equals(true)){
            labelexp.setText("Success");
            labelexp.setStyle("-fx-text-fill: green;");
            labelimp.setText("");
            AreaResult.setText("");
        } else{
            labelexp.setText("Erreur");
            labelexp.setStyle("-fx-text-fill: red;");
        }
    }



    public void ImporterTxt() {
        tacheFile.viderandreinitialiser();
        tacheFile.ImportTacheTxt();
        String contenu = tacheFile.ResultImportTxt();
        if(!contenu.equals("")){
            labelimp.setText("Success");
            labelimp.setStyle("-fx-text-fill: green;");
            labelexp.setText("");
            AreaResult.setText(contenu);
        } else{
            labelimp.setText("Error");
            labelimp.setStyle("-fx-text-fill: red;");
        }
    }



}
