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

        grid.setGridLinesVisible(true);
        grid.setMaxWidth(1000);
        grid.setMaxHeight(700);
        grid.setMinWidth(1000);
        grid.setMinHeight(700);
        grid.setPadding(new Insets(0, 30, 30, 30));


        loader = new FXMLLoader();
        loader.setLocation((getClass().getResource("vues/vueclavier.fxml")));
        loader.setControllerFactory(ic -> new VueClavier(arezzo));
        grid.add(loader.load(), 0,1);

        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("vues/vuepartition.fxml"));
        loader.setControllerFactory(ic -> new VuePartition(arezzo));
        grid.add(loader.load(),1,0);

        grid.add(new VueMenu(), 0,0);

        Scene scene = new Scene(grid,1000, 700);
        stage.setTitle("Arezzo");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
