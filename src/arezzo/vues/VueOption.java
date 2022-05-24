package arezzo.vues;

import arezzo.modeles.Arezzo;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.awt.event.ActionEvent;
import java.sql.SQLOutput;

public class VueOption {
    private Arezzo arezzo;
    @FXML
    ToggleGroup octave;
    @FXML
    ToggleGroup forme;

    public VueOption(Arezzo ar){
        arezzo = ar;
    }

    public void changer(){
        RadioButton formeSelect = (RadioButton) forme.getSelectedToggle();
        RadioButton octaveSelect = (RadioButton) octave.getSelectedToggle();
        String newForme = formeSelect.getId();
        String newOctave = octaveSelect.getId();
        System.out.println(formeSelect.getId());
        System.out.println(octaveSelect.getId());
        arezzo.changerForme(formeSelect.getText());
        arezzo.changerOctave(octaveSelect.getText());
    }
}
