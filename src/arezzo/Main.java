package arezzo;

import arezzo.modeles.Arezzo;
import arezzo.vues.VueClavier;
import arezzo.vues.VueMenu;
import arezzo.vues.VuePremier;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import partition.*;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane pane = new BorderPane();
        FXMLLoader loader = new FXMLLoader();
        Arezzo arezzo = new Arezzo();

        loader.setLocation(getClass().getResource("vues/vuepremier.fxml"));
        loader.setControllerFactory(iC -> new VuePremier(arezzo));
        pane.setCenter(loader.load());

        loader = new FXMLLoader();
        loader.setLocation((getClass().getResource("vues/vueclavier.fxml")));
        loader.setControllerFactory(ic -> new VueClavier(arezzo));
        pane.setBottom(loader.load());

        pane.setTop(new VueMenu());

        Scene scene = new Scene(pane, 500, 500);
        stage.setTitle("Arezzo");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
