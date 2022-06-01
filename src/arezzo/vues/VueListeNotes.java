package arezzo.vues;

import arezzo.modeles.Arezzo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
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
    private Stage stage;
    private ObservableList<String> stringObservableList;
    private ArrayList<String> arrayNotes;
    private ObservableList<String> listeObservable;


    public VueListeNotes(Arezzo arezzo, Stage st) {
        this.arezzo = arezzo;
        stage = st;
        arrayNotes = arezzo.getListeNotes();
        listeObservable = arezzo.getListeNotesObservable();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ContextMenu menu = new ContextMenu();
        MenuItem suppr = new MenuItem("Supprimer notes");
        menu.getItems().add(suppr);
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
        listViewNotes.contextMenuProperty().setValue(menu);
        //fenetreNotes.setCellFactory(stringListeView -> new CompoCell(arezzo));
    }

/*

    public void popFenetre(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("vuelistenotes.fxml"));
        ListeNotes listeNotes = new ListeNotes(arezzo);

        loader.setControllerFactory(ic ->{
            if(ic.equals(ListeNotes.class)) return listeNotes;
            else return null;
        });
        Scene scene;
        try{
            scene = new Scene(loader.load(),400,500);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage st = new Stage();
        st.setTitle("Liste des notes");
        st.initModality(Modality.WINDOW_MODAL);
        st.initOwner(stage);
        st.setScene(scene);
        st.show();
    }

 */
}
