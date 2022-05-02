package arezzo.vues;

import javafx.application.Platform;
import javafx.scene.control.MenuBar;

import java.awt.*;

public class VueMenu extends MenuBar {
    private javafx.scene.control.Menu menu;
    private javafx.scene.control.MenuItem quitter;

    public VueMenu(){

        //Menu : quitter
        menu = new javafx.scene.control.Menu("Menu");
        quitter = new javafx.scene.control.MenuItem("quit");
        quitter.setOnAction(actionEvent -> {
            Platform.exit();
        });
        menu.getItems().add(quitter);
        this.getMenus().add(menu);
    }
}
