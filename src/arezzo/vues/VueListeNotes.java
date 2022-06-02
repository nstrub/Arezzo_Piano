package arezzo.vues;

import arezzo.modeles.Arezzo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VueListeNotes implements Initializable {
    private Arezzo arezzo;
    @FXML
    ListView<String> listViewNotes;
    @FXML
    ContextMenu menuListe;
    private Stage stage;
    private ObservableList<String> stringObservableList;
    private ArrayList<String> arrayNotes;
    private ObservableList<String> listeObservable;


    public VueListeNotes(Arezzo arezzo, Stage st) {
        this.arezzo = arezzo;
        stage = st;
        arrayNotes = arezzo.getListeNotes();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MenuItem suppr = new MenuItem("Supprimer notes");
        menuListe.getItems().add(suppr);
        suppr.setOnAction(actionEvent -> {
            System.out.println("Suppression item select");
            listViewNotes.getSelectionModel().getSelectedItems().forEach(arezzo::supprimerNotes);
            listViewNotes.getItems().clear();
            initialize(url, resourceBundle);
        });
        for (String notes: arrayNotes
        ) {
            listViewNotes.getItems().add(notes);
        }

        listViewNotes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listViewNotes.contextMenuProperty().setValue(menuListe);
        listViewNotes.setCellFactory(stringListeView -> new CompoCell(arezzo));
    }
}
