package arezzo.modeles;

import arezzo.modeles.Arezzo;
import arezzo.vues.CompoCell;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;

import java.util.ArrayList;

public class ListeNotes {
    private Arezzo arezzo;
    private ObservableList<String> listeObservable;
    @FXML
    ListView fenetreNotes;

    public ListeNotes(Arezzo arezzo){
        this.arezzo = arezzo;
        listeObservable = arezzo.getListeNotesObservable();
    }

    public void initialize(){
        fenetreNotes = new ListView<>(listeObservable);
        fenetreNotes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        //fenetreNotes.setCellFactory(stringListeView -> new CompoCell(arezzo));
    }

}
