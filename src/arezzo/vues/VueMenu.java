package arezzo.vues;

import arezzo.modeles.Arezzo;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Popup;

import java.awt.*;
import java.util.Optional;

public class VueMenu extends MenuBar{
    private javafx.scene.control.Menu menu;
    @FXML
    private javafx.scene.control.MenuItem fermer;
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


    public void nouveau(){
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



}
