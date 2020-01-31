package todoliste.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
    private TableView<?> TVAktivitaetsname;

    @FXML
    private ComboBox<String> kategory;

    @FXML
    private ComboBox<String> Prioritaet;

    @FXML
    private DatePicker startDatum;

    @FXML
    private DatePicker endDatum;

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
        assert TVAktivitaetsname != null : "fx:id=\"TVAktivitaetsname\" was not injected: check your FXML file 'BearbeiteEintragToDoListe.fxml'.";
        assert kategory != null : "fx:id=\"kategory\" was not injected: check your FXML file 'BearbeiteEintragToDoListe.fxml'.";
        assert Prioritaet != null : "fx:id=\"Prioritaet\" was not injected: check your FXML file 'BearbeiteEintragToDoListe.fxml'.";
        assert startDatum != null : "fx:id=\"startDatum\" was not injected: check your FXML file 'BearbeiteEintragToDoListe.fxml'.";
        assert endDatum != null : "fx:id=\"endDatum\" was not injected: check your FXML file 'BearbeiteEintragToDoListe.fxml'.";
        kategory.setPromptText("Kategorie");
        kategory.setItems(FXCollections.observableArrayList());
        kategory.getItems().add("Arbeit");
        kategory.getItems().add("Privat");
        Prioritaet.setPromptText("Prioritaet");
        Prioritaet.setItems(FXCollections.observableArrayList());
        Prioritaet.getItems().add("Hoch");
        Prioritaet.getItems().add("Normal");
        Prioritaet.getItems().add("Niedrig");

    }
}
