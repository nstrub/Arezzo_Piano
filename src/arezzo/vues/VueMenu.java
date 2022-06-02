package arezzo.vues;

import arezzo.modeles.Arezzo;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class VueMenu extends MenuBar {
    @FXML
    private MenuItem nouveau;
    private String nomTitre;
    private Arezzo arezzo;

    public VueMenu(Arezzo arezzo) {
        this.arezzo = arezzo;
        this.nomTitre = arezzo.getNom();
    }

    public void quitter(javafx.event.ActionEvent actionEvent) {
        Platform.exit();
        System.exit(0);
    }

    public void nouveau() {
        TextInputDialog popUp = new TextInputDialog("Nouveau morceau");
        popUp.setWidth(400);
        popUp.setHeaderText("Entrer le titre de votre nouveau morceau");
        popUp.setTitle("Nouveau titre");
        Optional<String> input = popUp.showAndWait();
        nomTitre = popUp.getEditor().getText();
        arezzo.changerNom(nomTitre);
        arezzo.cleanNotes();
        arezzo.notifierObservateur();
    }

    public void ouvrirFichier() throws JSONException {
        JFileChooser fileChooser = new JFileChooser();
        int res = fileChooser.showOpenDialog(null);
        if (res == JFileChooser.APPROVE_OPTION) {
            arezzo.chargerDonnees(fileChooser.getSelectedFile());
        }
        arezzo.notifierObservateur();
    }

    public void sauvegarderFichier() throws JSONException, IOException {
        JFileChooser fileChooser = new JFileChooser();
        arezzo.sauvegarder();
        int res = fileChooser.showSaveDialog(null);
        if (res == JFileChooser.APPROVE_OPTION) {
            FileWriter creationFichier = new FileWriter(fileChooser.getSelectedFile());
            JSONObject jsonASave = arezzo.getJson();
            creationFichier.write(String.valueOf(jsonASave));
            creationFichier.flush();
        }
    }
}