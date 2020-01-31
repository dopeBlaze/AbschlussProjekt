package todoliste.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class NeuerEintragToDoListeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnEintragHinzufuegen;

    @FXML
    private TextField tfNeuerEintrag;

    @FXML
    private TableView<?> TVAktivitaetsname;

    @FXML
    private MenuButton btnLabel;

    @FXML
    private MenuButton btnPrioritaet;

    @FXML
    private DatePicker btnCalPickStart;

    @FXML
    private DatePicker btnCalPickEnd;

    @FXML
    void setzeEnddatum(ActionEvent event) {

    }

    @FXML
    void setzeLabel(ActionEvent event) {

    }

    @FXML
    void setzeNeuenEintrag(ActionEvent event) {

    }

    @FXML
    void setzePrioritaet(ActionEvent event) {

    }

    @FXML
    void setzeStartdatum(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert btnEintragHinzufuegen != null : "fx:id=\"btnEintragHinzufuegen\" was not injected: check your FXML file 'NeuerEintragToDoListe.fxml'.";
        assert tfNeuerEintrag != null : "fx:id=\"tfNeuerEintrag\" was not injected: check your FXML file 'NeuerEintragToDoListe.fxml'.";
        assert TVAktivitaetsname != null : "fx:id=\"TVAktivitaetsname\" was not injected: check your FXML file 'NeuerEintragToDoListe.fxml'.";
        assert btnLabel != null : "fx:id=\"btnLabel\" was not injected: check your FXML file 'NeuerEintragToDoListe.fxml'.";
        assert btnPrioritaet != null : "fx:id=\"btnPrioritaet\" was not injected: check your FXML file 'NeuerEintragToDoListe.fxml'.";
        assert btnCalPickStart != null : "fx:id=\"btnCalPickStart\" was not injected: check your FXML file 'NeuerEintragToDoListe.fxml'.";
        assert btnCalPickEnd != null : "fx:id=\"btnCalPickEnd\" was not injected: check your FXML file 'NeuerEintragToDoListe.fxml'.";

    }
}
