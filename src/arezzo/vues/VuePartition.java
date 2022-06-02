package arezzo.vues;

import arezzo.modeles.Arezzo;
import arezzo.modeles.Observateur;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class VuePartition implements Observateur {
    private Arezzo arezzo;
    @FXML
    public ImageView parti;


    public VuePartition(Arezzo arezzo){
        this.arezzo = arezzo;
        arezzo.ajouterObs(this);
        if(!arezzo.isMelodieVide()){
            parti.setImage(arezzo.getImage());
        }
    }

    @Override
    public void reagir() {
        if(arezzo.isMelodieVide()){
            return;
        }
        this.parti.setImage(arezzo.getImage());
    }
}
