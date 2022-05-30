package arezzo.vues;

import arezzo.modeles.Arezzo;
import arezzo.modeles.ListeNotes;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class VueListeNotes {
    private Arezzo arezzo;
    private Stage stage;

    public VueListeNotes(Arezzo arezzo, Stage st){
        this.arezzo = arezzo;
        stage = st;
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
