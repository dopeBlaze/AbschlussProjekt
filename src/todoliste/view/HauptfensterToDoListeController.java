package todoliste.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import todoliste.model.AktivitaetsEintrag;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

public class HauptfensterToDoListeController {

    @FXML
    private TableView<AktivitaetsEintrag> tvHauptfenster;

    @FXML
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

    //TODO evtl. Button in Hauptfenster GUI aktivieren, falls noch Zeit zur Bearbeitung des Features bleibt!
    @FXML
    void anzeigenWochenbericht() {

    }

    //TODO Werte mit übergeben (alle bisherigen Aktivitäten, die bereits in Datenbank erfasst sind in Liste unter Suchfeld anzeigen)
    @FXML
    void bearbeiteAktivitaetsname() throws IOException {

        Parent part = FXMLLoader.load(getClass().getResource("BearbeiteAktivitaetToDoListe.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(part);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Aktivitäten bearbeiten");
        stage.setScene(scene);
        stage.show();
    }


    //TODO Werte mit übergeben (alle bisherigen Aktivitäten, die bereits in Datenbank erfasst sind in Liste unter Suchfeld anzeigen)
    @FXML
    void bearbeiteEintrag() throws IOException {

        Parent part = FXMLLoader.load(getClass().getResource("BearbeiteEintragToDoListe.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(part);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Eintrag bearbeiten");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void beendeProgramm() {

    }

    //TODO Kalenderfeld soll für Text blockiert werden
    @FXML
    void dpKalender() {
    

    }

    //TODO Werte mit übergeben (alle bisherigen Aktivitäten, die bereits in Datenbank erfasst sind in Liste unter Suchfeld anzeigen)
    @FXML
    void hinzufuegenNeuerEintrag() throws IOException{

        Parent part = FXMLLoader.load(getClass().getResource("NeuerEintragToDoListe.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(part);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Neuen Eintrag hinzufügen");
        stage.setScene(scene);
        stage.show();
    }

    //TODO Prüfung fehlt, ob Zeit für Aktivität bereits gestoppt wurde-> falls ja, dann Löschen nicht möglich!
    @FXML
    void loescheAktivitaet() {

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
//        if (aktivitaeten.size() == 0) {
//            aktivitaeten.add(new AktivitaetsEintrag());
//        }

    }



    @FXML
    void starteZeiterfassung() {

    }

    @FXML
    void pausiereZeiterfassung() {

    }

    @FXML
    void erledigtZeiterfassung() {

    }

    @FXML
    void waehleNaechstenKalendertag() {

    }

    @FXML
    void waehleVorherigenKalendertag() {

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

        //Initialisierung des Kalenderfeldes mit aktuellem Datum beim Start der HauptfensterGUI
        btnCalPick.setValue(LocalDate.now());

        /////////////////////// Testeinträge
        ArrayList <AktivitaetsEintrag> arrayList = new ArrayList<>();
        AktivitaetsEintrag a = new AktivitaetsEintrag(LocalDateTime.now().toString(), "Laufen", "2020-02-03", "2020-02-03", 0, "Privat", "normal", "nicht gestartet");
        arrayList.add(a);
        AktivitaetsEintrag b = new AktivitaetsEintrag(LocalDateTime.now().toString(), "Putzen", "2020-02-03", "2020-02-03", 0, "Privat", "normal", "nicht gestartet");
        arrayList.add(b);
        AktivitaetsEintrag c = new AktivitaetsEintrag(LocalDateTime.now().toString(), "Kochen", "2020-02-03", "2020-02-03", 0, "Privat", "normal", "nicht gestartet");
        arrayList.add(c);
        ////////////////////////

        //Cast der ArrayList zu einer ObservableList
        tableData = FXCollections.observableArrayList(arrayList);

        // Daten für die Tabelle laden
        //TODO Beans muss noch eingebunden werden
        // tableData = AktivitaetsEintragBean.getAktivitaet();

        // Initialisierung der Tabelleneingenschaften
        initTable();
        //initContextMenu();
    }

    /**
     * Initialisiert und läd die Aktivitätenliste
     */
    private ArrayList<AktivitaetsEintrag> aktivitaeten;

    private int angezeigteAktivitaet;

    private void ladeAktivitaetenliste() {
        // Aktivitätenliste initialisieren und laden
        ladeAktivitaetenliste();
        angezeigteAktivitaet = 0;
        // Sollte wider erwarten die Tableview null sein, so wird sie hier neu initialisiert
        if (aktivitaeten == null) {
            aktivitaeten = new ArrayList<>();
        }

        // Den aktuellen Eintrag anzeigen
        anzeigen();
    }

    private void anzeigen() {
        // Anzuzeigender Eintrag heraussuchen
        AktivitaetsEintrag eintragZumAnzeigen = aktivitaeten.get(angezeigteAktivitaet);

//        // Eintrag in der Oberfläche anzeigen
//        aktivitaetsName.setText(eintragZumAnzeigen.getAktivitaetsName());
//        startDatum.setText(eintragZumAnzeigen.getStartDatum());
//        endDatum.setText(eintragZumAnzeigen.getEndDatum());
//        verbrauchteZeit.setText(eintragZumAnzeigen.getVerbrauchteZeit());
//        kategorie.setText(eintragZumAnzeigen.getKategorie());
//        prioritaet.setText(eintragZumAnzeigen.getPrioritaet());
//        status.setText(eintragZumAnzeigen.getStatus());
    }

    /**
     * Diese Funktion initialisiert die TableView
     */
    private void initTable() {

        // Spalten erstellen
        TableColumn<AktivitaetsEintrag, String> tc1 = new TableColumn<>("Aktivität");
        TableColumn<AktivitaetsEintrag, String> tc2 = new TableColumn<>("Startdatum");
        TableColumn<AktivitaetsEintrag, String> tc3 = new TableColumn<>("Enddatum");
        TableColumn<AktivitaetsEintrag, Integer> tc4 = new TableColumn<>("Verbrauchte Zeit");
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
        //Daten zuweisen
        tvHauptfenster.setItems(tableData);
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

