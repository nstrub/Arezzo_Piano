package arezzo.vues;

import arezzo.modeles.Arezzo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.awt.*;

public class VueClavier {
    private Arezzo arezzo;

    public VueClavier(Arezzo ar){
        this.arezzo = ar;
    }

    @FXML
    void jouer(ActionEvent actionEvent) {
        System.out.println("Event là");
        if(actionEvent.getSource().getClass().equals(Button.class)){
            Button bout = (Button) actionEvent.getSource();
            arezzo.setMelodie(bout.getId());
            System.out.println(bout.getId());
        }
    }
}