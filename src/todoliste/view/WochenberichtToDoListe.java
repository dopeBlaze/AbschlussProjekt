package todoliste.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;

public class WochenberichtToDoListe {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private LineChart<?, ?> lcWochenbericht;

    @FXML
    private Button btnOK;

    @FXML
    private TableColumn<?, ?> tcAktivität;

    @FXML
    private TableColumn<?, ?> tcGesamtzeit;

    @FXML
    private Label lDatum;

    @FXML
    void bestätigeOK(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert lcWochenbericht != null : "fx:id=\"lcWochenbericht\" was not injected: check your FXML file 'WochenberichtToDoListe.fxml'.";
        assert btnOK != null : "fx:id=\"btnOK\" was not injected: check your FXML file 'WochenberichtToDoListe.fxml'.";
        assert tcAktivität != null : "fx:id=\"tcAktivität\" was not injected: check your FXML file 'WochenberichtToDoListe.fxml'.";
        assert tcGesamtzeit != null : "fx:id=\"tcGesamtzeit\" was not injected: check your FXML file 'WochenberichtToDoListe.fxml'.";
        assert lDatum != null : "fx:id=\"lDatum\" was not injected: check your FXML file 'WochenberichtToDoListe.fxml'.";

    }
}
