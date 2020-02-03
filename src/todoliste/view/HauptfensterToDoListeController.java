package todoliste.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import todoliste.datenbank.beans.AktivitaetsEintragBean;
import todoliste.model.AktivitaetsEintrag;

public class HauptfensterToDoListeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<AktivitaetsEintrag> tvHauptfenster;

    private ObservableList<AktivitaetsEintrag> tableData;


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

    private ArrayList<AktivitaetsEintrag> aktivitaeten;
    private int angezeigteAktivitaet;

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

            // Aktuellen Eintrag herausfinden
            AktivitaetsEintrag zuLoeschen = aktivitaeten.get(angezeigteAktivitaet);

            // Eintrag aus der Datenbank löschen
            //TODO Methode delete in Beans hinterlegen
            // if (!AktivitaetsEintragBean.delete(zuLoeschen)) {
            // Fehlermeldung ausgeben
            //  alertAnzeigen("Fehler beim Löschen", "Die aktuelle Aktivität konnte aus der Datenbank nicht gelöscht werden, da sie bereits verwendet wurde.");

            // Löschen abbrechen
            return;
        }
        // Eintrag entfernen
        // aktivitaeten.remove(zuLoeschen);

        // Wenn der letzte Eintrag in der Aktivitaetenliste gelöscht wird, muss ein neuer leerer Eintrag generiert werden
        if (aktivitaeten.size() == 0) {
            aktivitaeten.add(new AktivitaetsEintrag());
        }

    }


    // Aktuellen Eintrag löschen
    //HauptfensterToDoListeController.remove(angzeigteAktivitaet);


//    private void alertAnzeigen(String title, String message) {
//        Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setTitle(title);
//        alert.setHeaderText(null);
//        alert.setContentText(message);
//        alert.showAndWait();
//    }


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

        // Daten für die Tabelle laden
        //TODO Beans muss noch eingebunden werden
        // tableData = AktivitaetsEintragBean.getAktivitaet();

        // Initialisierung der Tabelleneingenschaften
        initTable();
        initContextMenu();
    }

    /**
     * Diese Funktion initialisiert die TableView
     */
    private void initTable() {
        // Spalten erstellen
        TableColumn<AktivitaetsEintrag, String> tc1 = new TableColumn<>("Aktivität");
        TableColumn<AktivitaetsEintrag, String> tc2 = new TableColumn<>("Startdatum");
        TableColumn<AktivitaetsEintrag, String> tc3 = new TableColumn<>("Enddatum");
        TableColumn<AktivitaetsEintrag, String> tc4 = new TableColumn<>("Verbrauchte Zeit");
        TableColumn<AktivitaetsEintrag, String> tc5 = new TableColumn<>("Kategorie");
        TableColumn<AktivitaetsEintrag, String> tc6 = new TableColumn<>("Priorität");
        TableColumn<AktivitaetsEintrag, String> tc7 = new TableColumn<>("Status");


        // Zuordnung Werte <-> Model
        tc1.setCellValueFactory(new PropertyValueFactory<>("aktivitaetsName"));
        tc2.setCellValueFactory(new PropertyValueFactory<>("startDatum"));
        tc3.setCellValueFactory(new PropertyValueFactory<>("endDatum"));
        tc4.setCellValueFactory(new PropertyValueFactory<>("verbrauchteZeit"));
        tc5.setCellValueFactory(new PropertyValueFactory<>("kategorie"));
        tc6.setCellValueFactory(new PropertyValueFactory<>("prioritaet"));
        tc7.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Spalten hinzufügen
        tvHauptfenster.getColumns().add(tc1);
        tvHauptfenster.getColumns().add(tc2);
        tvHauptfenster.getColumns().add(tc3);
        tvHauptfenster.getColumns().add(tc4);
        tvHauptfenster.getColumns().add(tc5);
        tvHauptfenster.getColumns().add(tc6);
        tvHauptfenster.getColumns().add(tc7);
    }

    /**
     * Diese Funktion initialisiert ein ContextMenu (Ein Menü das an der Stelle des
     * Mauszeigers eingeblendet wird und durch einen Klick mit der rechten Maustaste
     * aktiviert wird).
     */
    private void initContextMenu() {
        ContextMenu cm = new ContextMenu();

        MenuItem mi1 = new MenuItem("Neuen Eintrag hinzufügen");
        MenuItem mi2 = new MenuItem("Bearbeiten");
        MenuItem mi3 = new MenuItem("Löschen");
        MenuItem mi4 = new MenuItem("Start");
        MenuItem mi5 = new MenuItem("Pause");
        MenuItem mi6 = new MenuItem("Erledigt");

        /*mi1.setOnAction(e -> hinzufuegenNeuerEintrag());
        mi2.setOnAction(e -> bearbeiteEintrag());
        mi3.setOnAction(e -> loescheAktivitaet());
        mi4.setOnAction(e -> starteZeiterfassung());
        mi5.setOnAction(e -> pausiereZeiterfassung());
        mi6.setOnAction(e -> erledigtZeiterfassung());*/

        cm.getItems().addAll(mi1, mi2, mi3, mi4, mi5, mi6);

        tvHauptfenster.setContextMenu(cm);
    }


}



