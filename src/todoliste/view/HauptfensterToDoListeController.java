package todoliste.view;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HauptfensterToDoListeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<?> tvHauptfenster;

    @FXML
    private Button btnStart;

    @FXML
    private Button btnPause;

    @FXML
    private Button btnErledigt;

    @FXML
    private Button btnWochenberichtAnzeigen;

    @FXML
    private Button btnAktivitaetsnameBearbeiten;

    @FXML
    private Button btnEintragBearbeiten;

    @FXML
    private Button btnLoeschen;

    @FXML
    private Button btnNeuerEintrag;

    @FXML
    private Button btnVorherigerKalendertag;

    @FXML
    private Button btnNaechsterKalendertag;

    @FXML
    private DatePicker btnCalPick;

    @FXML
    private Button btnProgrammBeenden;

    @FXML
    void anzeigenWochenbericht(ActionEvent event) {

    }

    @FXML
    void bearbeiteAktivitaetsname(ActionEvent event) {

    }

    @FXML
    void bearbeiteEintrag(ActionEvent event) {

    }

    @FXML
    void beendeProgramm(ActionEvent event) {

    }

    @FXML
    void erledigtZeiterfassung(ActionEvent event) {

    }

    @FXML
    void hinzufuegenNeuerEintrag(ActionEvent event) {

    }

    @FXML
    void loescheAktivitaet(ActionEvent event) {
    // Löschbestätigung abfragen
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Löschen bestätigen");
        alert.setHeaderText(null);
        alert.setContentText("Möchten Sie die aktuelle Aktivität wirklich löschen?");
        Optional<ButtonType> op = alert.showAndWait();

        // Es soll nur gelöscht werden, wenn der Benutzer "Ok" angeklickt hat
        if (op.isPresent() && op.get() == ButtonType.OK) {

            // Aktuellen Eintrag löschen
            HauptfensterToDoListeController.remove(angzeigteAktivitaet);


            // Aktuellen Aktivitaet anzeigen
            anzeigen();
        }
    }

    @FXML
    void pausiereZeiterfassung(ActionEvent event) {

    }

    @FXML
    void starteZeiterfassung(ActionEvent event) {

    }

    @FXML
    void waehleNaechstenKalendertag(ActionEvent event) {

    }

    @FXML
    void waehleVorherigenKalendertag(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert tvHauptfenster != null : "fx:id=\"tvHauptfenster\" was not injected: check your FXML file 'HauptfensterToDoListe.fxml'.";
        assert btnStart != null : "fx:id=\"btnStart\" was not injected: check your FXML file 'HauptfensterToDoListe.fxml'.";
        assert btnPause != null : "fx:id=\"btnPause\" was not injected: check your FXML file 'HauptfensterToDoListe.fxml'.";
        assert btnErledigt != null : "fx:id=\"btnErledigt\" was not injected: check your FXML file 'HauptfensterToDoListe.fxml'.";
        assert btnWochenberichtAnzeigen != null : "fx:id=\"btnWochenberichtAnzeigen\" was not injected: check your FXML file 'HauptfensterToDoListe.fxml'.";
        assert btnAktivitaetsnameBearbeiten != null : "fx:id=\"btnAktivitaetsnameBearbeiten\" was not injected: check your FXML file 'HauptfensterToDoListe.fxml'.";
        assert btnEintragBearbeiten != null : "fx:id=\"btnEintragBearbeiten\" was not injected: check your FXML file 'HauptfensterToDoListe.fxml'.";
        assert btnLoeschen != null : "fx:id=\"btnLoeschen\" was not injected: check your FXML file 'HauptfensterToDoListe.fxml'.";
        assert btnNeuerEintrag != null : "fx:id=\"btnNeuerEintrag\" was not injected: check your FXML file 'HauptfensterToDoListe.fxml'.";
        assert btnVorherigerKalendertag != null : "fx:id=\"btnVorherigerKalendertag\" was not injected: check your FXML file 'HauptfensterToDoListe.fxml'.";
        assert btnNaechsterKalendertag != null : "fx:id=\"btnNaechsterKalendertag\" was not injected: check your FXML file 'HauptfensterToDoListe.fxml'.";
        assert btnCalPick != null : "fx:id=\"btnCalPick\" was not injected: check your FXML file 'HauptfensterToDoListe.fxml'.";
        assert btnProgrammBeenden != null : "fx:id=\"btnProgrammBeenden\" was not injected: check your FXML file 'HauptfensterToDoListe.fxml'.";

    }
}
