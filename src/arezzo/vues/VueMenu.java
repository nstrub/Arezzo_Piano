package arezzo.vues;

import arezzo.modeles.Arezzo;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Popup;

import java.awt.*;

public class VueMenu extends MenuBar implements Observateur{
    private javafx.scene.control.Menu menu;
    @FXML
    private javafx.scene.control.MenuItem fermer;
    @FXML
    private MenuItem nouveau;
    private Popup popup;
    private String nomTitre;
    private Arezzo arezzo;

    public VueMenu(Arezzo arezzo) {
        this.arezzo = arezzo;
        this.nomTitre = arezzo.getNom();
        arezzo.ajouterObs(this);
    }

    public void quitter(javafx.event.ActionEvent actionEvent) {
        Platform.exit();
        System.exit(0);
    }

    public void nouveau(){
        //popup.show(); ??
        nomTitre = "NouveauTitre";
        arezzo.changerNom(nomTitre);
    }

    public void clean(ActionEvent actionEvent) {
        arezzo.cleanNotes();
        nouveau();
    }

    @Override
    public void reagir() {

    }
}
