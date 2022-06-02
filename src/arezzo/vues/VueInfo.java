package arezzo.vues;

import arezzo.modeles.Arezzo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class VueInfo implements Observateur{
    private Arezzo arezzo;
    private Stage stage;
    @FXML
    Label nomChanson;
    @FXML
    Button boutonFenetre;

    public VueInfo(Arezzo arezzo, Stage st){
        this.arezzo = arezzo;
        stage = st;
        arezzo.ajouterObs(this);
    }

    public void popFenetre(){
        FXMLLoader loader = new FXMLLoader();
        Stage st = new Stage();
        loader.setLocation(getClass().getResource("vuelistenotes.fxml"));

        VueListeNotes vueListeNotes = new VueListeNotes(arezzo,st);

        loader.setControllerFactory(ic ->{
            if (ic.equals(VueListeNotes.class)) return vueListeNotes;
            else{
                return null;
            }
        });

        Scene scene;
        try{
            scene = new Scene(loader.load(),400,500);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        st.setTitle("Liste des notes");
        st.initModality(Modality.WINDOW_MODAL);
        st.initOwner(stage);
        st.setScene(scene);
        st.show();
    }
    @Override
    public void reagir() {
        nomChanson.setText(arezzo.getNom());
    }
}
