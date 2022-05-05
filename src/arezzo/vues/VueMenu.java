package arezzo.vues;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;

import java.awt.*;
import java.awt.event.ActionEvent;

public class VueMenu extends MenuBar {
    private javafx.scene.control.Menu menu;
    @FXML
    private javafx.scene.control.MenuItem fermer;

    public VueMenu() {

    }

    public void quitter(javafx.event.ActionEvent actionEvent) {
        Platform.exit();
        System.exit(0);
    }
}
