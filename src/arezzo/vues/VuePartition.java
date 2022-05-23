package arezzo.vues;

import arezzo.modeles.Arezzo;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import partition.Partition;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;

public class VuePartition {
    private Arezzo arezzo;
    @FXML
    public ImageView img;


    public VuePartition(Arezzo arezzo){
        this.arezzo = arezzo;
        if(!arezzo.isMelodieVide()){
            img.setImage(arezzo.getImage());
        }
    }

    public void reagir(){
        img.setImage(arezzo.getImage());
    }
}
