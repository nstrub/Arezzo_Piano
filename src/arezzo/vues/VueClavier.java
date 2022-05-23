package arezzo.vues;

import arezzo.modeles.Arezzo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.awt.*;

public class VueClavier {
    private Arezzo arezzo;
    private String noteAjoue;

    public VueClavier(Arezzo ar){
        this.arezzo = ar;
    }

    @FXML
    void jouer(ActionEvent actionEvent) {
        if(actionEvent.getSource().getClass().equals(Button.class)){
            Button bout = (Button) actionEvent.getSource();
            noteAjoue = bout.getId();
            if(noteAjoue.equals("CC")) {
                noteAjoue = "^C";
            } else if (noteAjoue.equals("FF")) {
                noteAjoue = "^F";
            } else if (noteAjoue.equals("DD")) {
                noteAjoue = "^D";
            } else if (noteAjoue.equals("GG")) {
                noteAjoue = "^G";
            } else if (noteAjoue.equals("AA")) {
                noteAjoue = "^A";
            }


            arezzo.setMelodie(noteAjoue);
            System.out.println(noteAjoue);
        }
    }
}