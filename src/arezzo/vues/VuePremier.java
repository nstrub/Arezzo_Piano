package arezzo.vues;

import arezzo.modeles.Arezzo;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import partition.*;

public class VuePremier {
    @FXML
    protected Label titre;
    protected Arezzo arezzo;

    public VuePremier(Arezzo ar){
        arezzo = ar;
    }
    @FXML
    void ajouter(){
        arezzo.getPartition().setMelodie("C");
    }
}
