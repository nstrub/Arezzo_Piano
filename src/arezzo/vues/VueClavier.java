package arezzo.vues;

import arezzo.modeles.Arezzo;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.awt.*;
import java.awt.event.ActionEvent;

public class VueClavier {
    private Arezzo arezzo;

    public VueClavier(Arezzo ar){
        this.arezzo = ar;
    }

    @FXML
    void jouer(javafx.event.ActionEvent actionEvent) {
        System.out.println("Event là");
        if(actionEvent.getSource().getClass().equals(Button.class)){
            javafx.scene.control.Button bout = (javafx.scene.control.Button) actionEvent.getSource();
            arezzo.setMelodie(bout.getId());
            System.out.println(bout.getId());
        }
    }
}