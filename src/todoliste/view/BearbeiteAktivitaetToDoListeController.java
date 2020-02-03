package todoliste.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import todoliste.model.AktivitaetsEintrag;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class BearbeiteAktivitaetToDoListeController {

    @FXML
    private Button btnUebernehmen;

    @FXML
    private TextField tfAktivitaetsname;

    @FXML
    private TableView<?> TVAktivitaetsname;

    @FXML
    private Button btnLoeschen;

    @FXML
    void loescheAktivitaetsname(ActionEvent event) {

    }

    @FXML
    void uebernehmeAktivitaetsname(ActionEvent event) {

    }

    @FXML
    void initialize() {


        //ArrayList <AktivitaetsEintrag>
        AktivitaetsEintrag a = new AktivitaetsEintrag(LocalDateTime.now().toString(), "Laufen", "2020-02-03", "2020-02-03", 0, "Privat", "normal", "nicht gestartet");

        AktivitaetsEintrag b = new AktivitaetsEintrag(LocalDateTime.now().toString(), "Laufen", "2020-02-03", "2020-02-03", 0, "Privat", "normal", "nicht gestartet");
        AktivitaetsEintrag c = new AktivitaetsEintrag(LocalDateTime.now().toString(), "Laufen", "2020-02-03", "2020-02-03", 0, "Privat", "normal", "nicht gestartet");

        assert btnUebernehmen != null : "fx:id=\"btnUebernehmen\" was not injected: check your FXML file 'BearbeiteAktivitaetToDoListe.fxml'.";
        assert tfAktivitaetsname != null : "fx:id=\"tfAktivitaetsname\" was not injected: check your FXML file 'BearbeiteAktivitaetToDoListe.fxml'.";
        assert TVAktivitaetsname != null : "fx:id=\"TVAktivitaetsname\" was not injected: check your FXML file 'BearbeiteAktivitaetToDoListe.fxml'.";
        assert btnLoeschen != null : "fx:id=\"btnLoeschen\" was not injected: check your FXML file 'BearbeiteAktivitaetToDoListe.fxml'.";

    }
}