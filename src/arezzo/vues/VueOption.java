package arezzo.vues;

import arezzo.modeles.Arezzo;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

import java.awt.*;
import java.sql.SQLOutput;

public class VueOption implements Observateur{
    private Arezzo arezzo;
    @FXML
    ToggleGroup octave;
    @FXML
    ToggleGroup forme;
    @FXML
    Slider volume;
    @FXML
    Slider tempo;
    @FXML
    ToggleGroup instru;
    @FXML
    ImageView playButton;

    public VueOption(Arezzo ar){
        arezzo = ar;
        arezzo.ajouterObs(this);
    }

    public void changer(){
        RadioButton formeSelect = (RadioButton) forme.getSelectedToggle();
        RadioButton octaveSelect = (RadioButton) octave.getSelectedToggle();
        arezzo.changerForme(formeSelect.getText());
        arezzo.changerOctave(octaveSelect.getText());
    }

    public void animation(){
        RotateTransition rotate = new RotateTransition();
        rotate.setAxis(Rotate.Z_AXIS);  //Configuration des angles
        rotate.setByAngle(360);
        rotate.setCycleCount(1);        //On effectue l'animation qu'une seule fois
        rotate.setDuration(Duration.millis(1000));  //On réduit le temps de l'animation pour plus de fluidité
        rotate.setNode(playButton); //On ajoute l'animation au bouton play
        rotate.play();              //On joue le tout
    }

    public void changerVolume(){
        double sliderVol = volume.getValue();
        arezzo.modifVolume(sliderVol);
    }

    public void changerTempo() {
        int sliderTempo = (int) tempo.getValue();
        arezzo.modifTempo(sliderTempo);
        System.out.println("New tempo " + sliderTempo);
    }

    public void changerInstru(){
        RadioButton newInstruBout = (RadioButton) instru.getSelectedToggle();
        arezzo.modifInstrument(newInstruBout.getText());
        animation();
    }

    public void jouer() {
        if (arezzo.isMelodieVide()){
            return;
        }
        arezzo.jouerTout();
    }

    @Override
    public void reagir() {
        playButton.setImage(arezzo.getImagePlay());
    }
}
