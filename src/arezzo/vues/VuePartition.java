package arezzo.vues;

import arezzo.modeles.Arezzo;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import partition.Partition;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;

public class VuePartition implements Observateur{
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
        this.parti.setImage(arezzo.getImage());
    }
}
