package arezzo.vues;

import arezzo.modeles.Arezzo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

import java.sql.SQLOutput;

public class VueOption {
    private Arezzo arezzo;
    @FXML
    ToggleGroup octave;
    @FXML
    ToggleGroup forme;
    @FXML
    Slider volume;
    @FXML
    Slider tempo;

    public VueOption(Arezzo ar){
        arezzo = ar;
    }

    public void changer(){
        RadioButton formeSelect = (RadioButton) forme.getSelectedToggle();
        RadioButton octaveSelect = (RadioButton) octave.getSelectedToggle();
        arezzo.changerForme(formeSelect.getText());
        arezzo.changerOctave(octaveSelect.getText());
    }

    public void changetVolume(){
        double sliderVol = volume.getValue();
        arezzo.modifVolume(sliderVol);
        System.out.println("New volume " + sliderVol);
    }

    public void changerTempo() {
        int sliderTempo = (int) tempo.getValue();
        arezzo.modifTempo(sliderTempo);
        System.out.println("New tempo " + sliderTempo);
    }

    public void jouer() {
        arezzo.jouerTout();
    }
}
