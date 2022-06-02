package arezzo.vues;

import arezzo.modeles.Arezzo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;
import java.io.IOException;

public class CompoCell extends ListCell<String> {
    @FXML
    private Label note;
    @FXML
    private ImageView image;
    @FXML
    private AnchorPane pane;
    private Arezzo arezzo;
    public CompoCell(Arezzo arezzo) {
        this.arezzo = arezzo;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("compocell.fxml"));
        loader.setControllerFactory(ic->this);
        try{
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void updateItem(String s, boolean b) {
        if(s != null){
            super.updateItem(s, b);
            if(s.toUpperCase().contains("C")){
                note.setText("Do");
            }
        }
    }
}
