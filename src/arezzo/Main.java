package arezzo;

import arezzo.modeles.Arezzo;
import arezzo.vues.VueClavier;
import arezzo.vues.VueMenu;
import arezzo.vues.VuePartition;
import arezzo.vues.VuePremier;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import partition.*;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        GridPane grid = new GridPane();
        FXMLLoader loader = new FXMLLoader();
        Arezzo arezzo = new Arezzo();

        VueMenu vueMenu = new VueMenu();
        VuePartition vuePartition = new VuePartition(arezzo);
        VueClavier vueClavier = new VueClavier(arezzo);

        loader.setLocation(getClass().getResource("root.fxml"));

        loader.setControllerFactory(ic -> {
            if(ic.equals(VueMenu.class)) return vueMenu;
            else if (ic.equals(VuePartition.class)) return  vuePartition;
            else if(ic.equals(VueClavier.class)) return vueClavier;
            else return null;
        });

        Scene scene;
        try {
            scene = new Scene(loader.load(), 1000,700);
        }catch (IOException e){
            throw  new RuntimeException(e);
        }
        stage.setTitle("Arezzo");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
