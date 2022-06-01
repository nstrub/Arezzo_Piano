package arezzo.modeles;
/*
import arezzo.modeles.Arezzo;
import arezzo.vues.CompoCell;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ListeNotes implements Initializable {
    private Arezzo arezzo;
    private ObservableList<String> listeObservable;
    @FXML
    ListView fenetreNotes;

    public ListeNotes(Arezzo arezzo){
        this.arezzo = arezzo;
        listeObservable = arezzo.getListeNotesObservable();
        arrayNotes = arezzo.getListeNotes();
    }

    Migration vers Vueliste note de tout ca
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ContextMenu menu = new ContextMenu();
        for (String notes: arrayNotes
        ) {
            fenetreNotes.getItems().add(notes);
        }
        fenetreNotes = new ListView<>(listeObservable);
        fenetreNotes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        //fenetreNotes.setCellFactory(stringListeView -> new CompoCell(arezzo));
    }
}




/*
    public void initialize(){
        ContextMenu menu = new ContextMenu();
        for (String notes: arrayNotes
             ) {
            fenetreNotes.getItems().add(notes);
        }
        fenetreNotes = new ListView<>(listeObservable);
        fenetreNotes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        //fenetreNotes.setCellFactory(stringListeView -> new CompoCell(arezzo));
    }
 */