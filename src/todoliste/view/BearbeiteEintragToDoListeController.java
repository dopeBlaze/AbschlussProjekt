package todoliste.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class BearbeiteEintragToDoListeController {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnUbernehmen;

    @FXML
    private TextField tfEintragsname;

    @FXML
    private Button btnCalPickStart;

    @FXML
    private Button btnCalPickEnd;

    @FXML
    private TableView<?> TVAktivitaetsname;

    @FXML
    private MenuButton btnLabel;

    @FXML
    private MenuButton btnPriritaet;

    @FXML
    void setzeLabel(ActionEvent event) {

    }

    @FXML
    void setzePrioritaet(ActionEvent event) {

    }

    @FXML
    void setzteEnddatum(ActionEvent event) {

    }

    @FXML
    void setzteStartdatum(ActionEvent event) {

    }

    @FXML
    void uebernehmeEintragsname(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert btnUbernehmen != null : "fx:id=\"btnUbernehmen\" was not injected: check your FXML file 'BearbeiteEintragToDoListe.fxml'.";
        assert tfEintragsname != null : "fx:id=\"tfEintragsname\" was not injected: check your FXML file 'BearbeiteEintragToDoListe.fxml'.";
        assert btnCalPickStart != null : "fx:id=\"btnCalPickStart\" was not injected: check your FXML file 'BearbeiteEintragToDoListe.fxml'.";
        assert btnCalPickEnd != null : "fx:id=\"btnCalPickEnd\" was not injected: check your FXML file 'BearbeiteEintragToDoListe.fxml'.";
        assert TVAktivitaetsname != null : "fx:id=\"TVAktivitaetsname\" was not injected: check your FXML file 'BearbeiteEintragToDoListe.fxml'.";
        assert btnLabel != null : "fx:id=\"btnLabel\" was not injected: check your FXML file 'BearbeiteEintragToDoListe.fxml'.";
        assert btnPriritaet != null : "fx:id=\"btnPriritaet\" was not injected: check your FXML file 'BearbeiteEintragToDoListe.fxml'.";

    }
}
