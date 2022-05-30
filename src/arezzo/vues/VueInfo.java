package arezzo.vues;

import arezzo.modeles.Arezzo;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class VueInfo implements Observateur{
    private Arezzo arezzo;
    @FXML
    Label nomChanson;

    public VueInfo(Arezzo arezzo){
        this.arezzo = arezzo;
        arezzo.ajouterObs(this);
    }
    @Override
    public void reagir() {
        nomChanson.setText(arezzo.getNom());
    }
}
