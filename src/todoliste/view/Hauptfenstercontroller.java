package todoliste.view;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import todoliste.datenbank.beans.AktivitaetsEintragBean;
import todoliste.model.AktivitaetsEintrag;

/**
 * Controller fuer das Hauptfenster
 */
public class Hauptfenstercontroller {

    static int me = 0;
    static int ss = 0;
    static int mm = 0;
    static int hh = 0;
    static int gesamtZeitStart = 0;
    static int gesamtZeitPause = 0;
    static int gesamtZeitErledigt = 0;
    static String idCheck;
    static boolean b = true;

    private ObservableList<AktivitaetsEintrag> obsAktivitaetsEintrag;

    @FXML
    private Button btnachdate;

    @FXML
    private Button btvordate;

    @FXML
    private Button btNeuerEintrag;

    @FXML
    private Button btLoeschen;

    @FXML
    private Button btAktivitaetsnameBearbeiten;

    @FXML
    private Button btEintragbearbeiten;

    @FXML
    private Button btProgrammbeenden;

    @FXML
    private Button btStart;

    @FXML
    private Button btErledigt;

    @FXML
    private Button btPause;

    @FXML
    private Label labelhour;

    @FXML
    private Label labelminute;

    @FXML
    private Label labelsecond;

    @FXML
    private Label labelmillisecond;

    @FXML
    private Label labelGestarteteAktivitaet;

    @FXML
    private TableView<AktivitaetsEintrag> tabelview;

    @FXML
    private TableColumn<AktivitaetsEintrag, String> tcAktivitaet;

    @FXML
    private TableColumn<AktivitaetsEintrag, String> tcStartdatum;

    @FXML
    private TableColumn<AktivitaetsEintrag, String> tcEnddatum;

    @FXML
    private TableColumn<AktivitaetsEintrag, Integer> tcVerbrauchtezeit;

    @FXML
    private TableColumn<AktivitaetsEintrag, String> tcPrioritaet;

    @FXML
    private TableColumn<AktivitaetsEintrag, String> tcStatus;

    @FXML
    private TableColumn<AktivitaetsEintrag, String> tcLabel;

    @FXML
    private DatePicker dpkalender;

    /**
     * Bei der Auswahl des DatePickers wird die Tabelle aktualisiert.
     */
    @FXML
    void kalenderAuswahl() {
        kalender();
        infoTable();
    }

    /**
     * Weiterschalten des Datums um einen Tag.
     */
    @FXML
    void buttonnachdate() {

        dpkalender.setValue(dpkalender.getValue().plusDays(1));
        // Aktualisierung der Tabelle
        infoTable();

    }

    /**
     * Zurueckschalten des Datums um einen Tag.
     */
    @FXML
    void buttonvordate() {

        dpkalender.setValue(dpkalender.getValue().minusDays(1));
        // Aktualisierung der Tabelle
        infoTable();
    }

    /**
     *Oeffnet Fenster BearbeiteAktivitaetToDoListe
     * @throws IOException wirft IOException
     */
    @FXML
    void buttonAktivitaesnamebearbeiten() throws IOException {
        Parent part = FXMLLoader.load(getClass().getResource("BearbeiteAktivitaetToDoListe.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(part);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Aktivitätsnamen anlegen/ bearbeiten");
        stage.setScene(scene);
        stage.show();
    }

    /**
     *Oeffnet Fenster BearbeiteEintragToDoListe
     * @throws IOException wirft IOException
     */
    @FXML
    void buttonEintragbearbeiten() throws IOException {

        try {
            AktivitaetsEintrag selectedAktivity = tabelview.getSelectionModel().getSelectedItem();
            BearbeiteEintragToDoListeController bearbeiteEintrag = new BearbeiteEintragToDoListeController();

            // Uebermittlung vom ErstellungsDatum der ausgewaehlten Aktivitaet
            bearbeiteEintrag.getDatetime(selectedAktivity.getErstellungsDatum());

            Parent part = FXMLLoader.load(getClass().getResource("BearbeiteEintragToDoListe.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(part);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Eintrag bearbeiten");
            stage.setScene(scene);
            stage.showAndWait();
            infoTable();
        } catch (NullPointerException e){
            // Rückmeldung wenn Fehler
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Bearbeiten nicht möglich!");
            alert.setContentText("Keine Aktivität ausgewählt!\nBitte eine Aktivität auswählen!");
            alert.showAndWait();
        }
    }

    /**
     * Durch Button Aktion wird die loeschen Methode ausgefuehrt.
     */
    @FXML
    void buttonLoeschen() {
        loeschen();
    }

    /**
     * Oeffnet Fenster NeuerEintragToDoListe
     * @throws IOException wirft IOException
     */
    @FXML
    void buttonNeuerEintrag() throws IOException {
        Parent part = FXMLLoader.load(getClass().getResource("NeuerEintragToDoListe.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(part);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Neuen Eintrag hinzufügen");
        stage.setScene(scene);
        stage.showAndWait();
        infoTable();
        tabelview.refresh();
    }

    /**
     * Durch Button Aktion wird das Programm beendet
     * @throws IOException wirft IOException
     */
    @FXML
    void buttonProgrammbeenden() throws IOException {

        ArrayList <AktivitaetsEintrag> arrayListGesamt = AktivitaetsEintragBean.getAktivitaeten();
        ArrayList <AktivitaetsEintrag> arrayListSorted = new ArrayList<>();
        for (AktivitaetsEintrag i : arrayListGesamt) {
            // Pruefung ob Aktivitaeten in der Deadline und unerledigt sind
            if (i.getEndDatum().compareTo(dpkalender.getValue().toString()) == 0 && !i.getStatus().contains("erledigt")){
                // Fuegt zutreffende Aktivitaeten einer Liste hinzu
                arrayListSorted.add(i);
            }
        }

        // Wenn die Liste nicht leer ist, wird ein Infofenster mit der Liste der unerledigten Aktivitaeten angezeigt
        if (!arrayListSorted.isEmpty()){

            Parent part = FXMLLoader.load(getClass().getResource("ZeigeInfoFenster.fxml"));
            Stage stage2 = new Stage();
            Scene scene = new Scene(part);
            stage2.initModality(Modality.APPLICATION_MODAL);
            stage2.setTitle("Warnung");
            stage2.setScene(scene);
            stage2.showAndWait();
        }

        b = false;
        Stage stage = (Stage) btProgrammbeenden.getScene().getWindow();
        stage.close();
    }


    /**
     * Startet die Zeiterfassung der ausgewaehlten Aktivitaet
     */
    @FXML
    void buttonStart() {

        try {

            gesamtZeitStart = 0;
            // Wenn die Aktivitaet schon eine Zeiterfassung hat, so wird diese Zeit im richtigen Format ausgegeben
            AktivitaetsEintrag aktivitaetsEintrag = tabelview.getSelectionModel().getSelectedItem();

            idCheck = aktivitaetsEintrag.getErstellungsDatum();
            labelGestarteteAktivitaet.setText(aktivitaetsEintrag.getAktivitaetsName());

            gesamtZeitStart = aktivitaetsEintrag.getVerbrauchteZeit();

            hh = (gesamtZeitStart - gesamtZeitStart % 3600) / 3600;
            mm = (gesamtZeitStart % 3600 - gesamtZeitStart % 3600 % 60) / 60;
            ss = gesamtZeitStart % 3600 % 60;

            b = true;
            Thread t = new Thread(() -> {
                while(b){
                    try {
                        Thread.sleep(1);
                        if (me == 1000) {
                            me = 0;
                            ss++;
                        }
                        if (ss == 60) {
                            ss = 0;
                            mm++;
                        }
                        if (mm == 60) {
                            mm = 0;
                            hh++;
                        }
                        me++;

                        // UI-Thread soll die Oberfläche aktualisieren,
                        // deshalb wird Platform.runLater aufgerufen
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                labelmillisecond.setText(me + "");
                                labelsecond.setText(ss + " :");
                                labelminute.setText(mm + " :");
                                labelhour.setText(hh + " :");
                            }
                        });
                    }
                    catch (Exception ex) {

                    }
                }
            });

            t.start();
            aktivitaetsEintrag.setStatus("in Bearbeitung");

            // Aktivierung / Deaktivierung verschiedener Oberflaechenelemente
            btStart.setDisable(true);
            btPause.setDisable(false);
            tabelview.setDisable(true);
            btAktivitaetsnameBearbeiten.setDisable(true);
            btNeuerEintrag.setDisable(true);
            btProgrammbeenden.setDisable(true);
            btEintragbearbeiten.setDisable(true);
            btLoeschen.setDisable(true);
            btvordate.setDisable(true);
            btnachdate.setDisable(true);
            dpkalender.setDisable(true);

            tabelview.refresh();

        } catch (NullPointerException e){
            // Rückmeldung wenn Fehler
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Starten nicht möglich!");
            alert.setContentText("Keine Aktivität ausgewählt!\nBitte eine Aktivität auswählen!");
            alert.showAndWait();
        }
    }

    /**
     * Stoppt die Zeiterfassung der ausgewaehlten Aktivitaet
     */
    @FXML
    void buttonPause() {
        b = false;
        gesamtZeitPause = 0;
        AktivitaetsEintrag aktivitaetsEintrag = tabelview.getSelectionModel().getSelectedItem();

        aktivitaetsEintrag.setStatus("pausiert");

        // Aktivierung / Deaktivierung verschiedener Oberflaechenelemente
        btStart.setDisable(false);
        btPause.setDisable(true);
        tabelview.setDisable(false);
        btAktivitaetsnameBearbeiten.setDisable(false);
        btNeuerEintrag.setDisable(false);
        btProgrammbeenden.setDisable(false);
        btEintragbearbeiten.setDisable(false);
        btLoeschen.setDisable(false);
        btvordate.setDisable(false);
        btnachdate.setDisable(false);

        // Gesamtzeit wird zusammengerechnet und der Aktivitaet gesetzt
        gesamtZeitPause = (hh * 3600) + (mm * 60) + (ss);

        aktivitaetsEintrag.setVerbrauchteZeit(gesamtZeitPause);
        tabelview.refresh();

        // Datenbank speichern
        // Ermittlung welche Aktivitaet ausgewaehlt wurde
        ArrayList <AktivitaetsEintrag> arrayData = AktivitaetsEintragBean.getAktivitaeten();
        for (AktivitaetsEintrag array : arrayData) {

            if (array.getErstellungsDatum().equals(aktivitaetsEintrag.getErstellungsDatum())){
                array.setVerbrauchteZeit(gesamtZeitPause);
                array.setStatus(aktivitaetsEintrag.getStatus());

                AktivitaetsEintragBean.saveAktivitaetSingle(array);
            }
        }
    }

    /**
     * Stoppt die Zeiterfassung und setzt Status 'erledigt' der ausgewaehlten Aktivitaet
     */
    @FXML
    void buttonErledigt() {
        try {
            b = false;
            gesamtZeitErledigt = 0;

            labelhour.setText("00 :");
            labelminute.setText("00 :");
            labelsecond.setText("00 :");
            labelmillisecond.setText("000");

            AktivitaetsEintrag aktivitaetsEintrag = tabelview.getSelectionModel().getSelectedItem();

            aktivitaetsEintrag.setStatus("erledigt");

            // Aktivierung / Deaktivierung verschiedener Oberflaechenelemente
            btStart.setDisable(false);
            btPause.setDisable(true);
            tabelview.setDisable(false);
            btAktivitaetsnameBearbeiten.setDisable(false);
            btNeuerEintrag.setDisable(false);
            btProgrammbeenden.setDisable(false);
            btEintragbearbeiten.setDisable(false);
            btLoeschen.setDisable(false);
            btvordate.setDisable(false);
            btnachdate.setDisable(false);

            // Gesamtzeit wird erfasst
            gesamtZeitErledigt = aktivitaetsEintrag.getVerbrauchteZeit();

            // Wenn man Aktivitaeten nur erledigt kann es zu Fehlern fuehren
            // Loesung bietet ein idCheck
            // idCheck notwendig beim Wechsel zwischen den Aktivitaeten
            if (idCheck.contains(aktivitaetsEintrag.getErstellungsDatum()) && hh >=0 && mm >= 0 && ss >= 0){

                gesamtZeitErledigt = (hh * 3600) + (mm * 60) + (ss);
            }

            aktivitaetsEintrag.setVerbrauchteZeit(gesamtZeitErledigt);

            // Datenbank speichern
            // Ermittlung welche Aktivitaet ausgewaehlt wurde
            ArrayList <AktivitaetsEintrag> arrayData = AktivitaetsEintragBean.getAktivitaeten();
            for (AktivitaetsEintrag array : arrayData) {

                if (array.getErstellungsDatum().equals(aktivitaetsEintrag.getErstellungsDatum())){
                    array.setVerbrauchteZeit(gesamtZeitErledigt);
                    array.setStatus(aktivitaetsEintrag.getStatus());

                    AktivitaetsEintragBean.saveAktivitaetSingle(array);
                }
            }

            tabelview.refresh();
        } catch (NullPointerException e){
            // Rückmeldung wenn Fehler
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erledigen nicht möglich!");
            alert.setContentText("Keine Aktivität ausgewählt!\nBitte eine Aktivität auswählen!");
            alert.showAndWait();
        }
    }

    /**
     * Initialisierung des Fensters
     */
    @FXML
    void initialize() {
        assert btnachdate != null : "fx:id=\"btnachdate\" was not injected: check your FXML file 'Hauptfenster.fxml'.";
        assert btvordate != null : "fx:id=\"btvordate\" was not injected: check your FXML file 'Hauptfenster.fxml'.";
        assert btNeuerEintrag != null : "fx:id=\"btNeuerEintrag\" was not injected: check your FXML file 'Hauptfenster.fxml'.";
        assert btLoeschen != null : "fx:id=\"btLoeschen\" was not injected: check your FXML file 'Hauptfenster.fxml'.";
        assert btAktivitaetsnameBearbeiten != null : "fx:id=\"btAktivitaetsnameBearbeiten\" was not injected: check your FXML file 'Hauptfenster.fxml'.";
        assert btEintragbearbeiten != null : "fx:id=\"btEintragbearbeiten\" was not injected: check your FXML file 'Hauptfenster.fxml'.";
        assert btProgrammbeenden != null : "fx:id=\"btProgrammbeenden\" was not injected: check your FXML file 'Hauptfenster.fxml'.";
        assert btStart != null : "fx:id=\"btStart\" was not injected: check your FXML file 'Hauptfenster.fxml'.";
        assert btErledigt != null : "fx:id=\"btErledigt\" was not injected: check your FXML file 'Hauptfenster.fxml'.";
        assert btPause != null : "fx:id=\"btPause\" was not injected: check your FXML file 'Hauptfenster.fxml'.";
        assert labelhour != null : "fx:id=\"labelhour\" was not injected: check your FXML file 'Hauptfenster.fxml'.";
        assert labelminute != null : "fx:id=\"labelminute\" was not injected: check your FXML file 'Hauptfenster.fxml'.";
        assert labelsecond != null : "fx:id=\"labelsecond\" was not injected: check your FXML file 'Hauptfenster.fxml'.";
        assert labelmillisecond != null : "fx:id=\"labelmillisecond\" was not injected: check your FXML file 'Hauptfenster.fxml'.";
        assert labelGestarteteAktivitaet != null : "fx:id=\"labelGestarteteAktivitaet\" was not injected: check your FXML file 'Hauptfenster.fxml'.";
        assert tabelview != null : "fx:id=\"tabelview\" was not injected: check your FXML file 'Hauptfenster.fxml'.";
        assert tcAktivitaet != null : "fx:id=\"tcAktivität\" was not injected: check your FXML file 'Hauptfenster.fxml'.";
        assert tcStartdatum != null : "fx:id=\"tcStartdatum\" was not injected: check your FXML file 'Hauptfenster.fxml'.";
        assert tcEnddatum != null : "fx:id=\"tcEnddatum\" was not injected: check your FXML file 'Hauptfenster.fxml'.";
        assert tcVerbrauchtezeit != null : "fx:id=\"tcVerbrauchtezeit\" was not injected: check your FXML file 'Hauptfenster.fxml'.";
        assert tcPrioritaet != null : "fx:id=\"tcPrioritaet\" was not injected: check your FXML file 'Hauptfenster.fxml'.";
        assert tcStatus != null : "fx:id=\"tcStatus\" was not injected: check your FXML file 'Hauptfenster.fxml'.";
        assert tcLabel != null : "fx:id=\"tcLabel\" was not injected: check your FXML file 'Hauptfenster.fxml'.";
        assert dpkalender != null : "fx:id=\"dpkalender\" was not injected: check your FXML file 'Hauptfenster.fxml'.";

        // Deaktivierung verschiedener Oberflaechenelemente
        btPause.setDisable(true);
        dpkalender.getEditor().setDisable(true);
        // Default Wert fuer den Kalender gesetzt
        dpkalender.setValue(LocalDate.now());

        infoTable();
    }

    /**
     * Methode fuer die Erstellung der TableView
     */
    private void infoTable() {

        // Nur Aktivitaeten, die dem Datum des Kalenders entsprechen werden angezeigt
        ArrayList <AktivitaetsEintrag> listGesamt = AktivitaetsEintragBean.getAktivitaeten();
        ArrayList <AktivitaetsEintrag> listSorted = new ArrayList<>();
        for (AktivitaetsEintrag i : listGesamt) {
            if (i.getStartDatum().compareTo(dpkalender.getValue().toString()) <= 0 && i.getEndDatum().compareTo(dpkalender.getValue().toString()) >= 0){
                listSorted.add(i);
            }
        }

        obsAktivitaetsEintrag = FXCollections.observableArrayList(listSorted);
        //Setting the coloumns header names  according to the controller and required table
        tcAktivitaet.setCellValueFactory(new PropertyValueFactory<>("aktivitaetsName"));
        tcStartdatum.setCellValueFactory(new PropertyValueFactory<>("startDatum"));
        tcEnddatum.setCellValueFactory(new PropertyValueFactory<>("endDatum"));
        tcVerbrauchtezeit.setCellValueFactory(new PropertyValueFactory<>("verbrauchteZeit"));
        tcPrioritaet.setCellValueFactory(new PropertyValueFactory<>("prioritaet"));
        tcStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        tcLabel.setCellValueFactory(new PropertyValueFactory<>("kategorie"));

        tabelview.setItems(obsAktivitaetsEintrag);
    }

    /**
     * Loescht die ausgewaehlte Aktivitaet
     */
    private void loeschen() {

        AktivitaetsEintrag ausgewaehlterArtikel = tabelview.getSelectionModel().getSelectedItem();

        // Abfrage ob eine Aktivitaet ausgewaehlt wurde
        if (ausgewaehlterArtikel == null){
            // Rueckmeldung wenn Fehler
            Alert alert3 = new Alert(Alert.AlertType.WARNING);
            alert3.setTitle("Löschen nicht möglich!");
            alert3.setContentText("Keine Aktivitaet ausgewaehlt!\nBitte eine Aktivitaet auswaehlen!");
            alert3.showAndWait();
        } else {
            // Loeschbestaetigung abfragen
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Löschen bestätigen");
            alert.setHeaderText(null);
            alert.setContentText("Möchten Sie die aktuelle Aktivität wirklich löschen?");
            Optional<ButtonType> op = alert.showAndWait();
            // Es soll nur gelöscht werden, wenn der Benutzer "Ok" angeklickt hat
            if (op.isPresent() && op.get() == ButtonType.OK) {
                // Pruefung ob der Aktivitaet schon eine Zeit zugwiesen wurde
                if (ausgewaehlterArtikel.getVerbrauchteZeit() == 0) {
                    obsAktivitaetsEintrag.remove(ausgewaehlterArtikel);
                    // Eintrag aus der Datenbank löschen
                    AktivitaetsEintragBean.deleteAktivitaet(ausgewaehlterArtikel);
                } else {
                    // Rückmeldung wenn nicht möglich
                    Alert alert2 = new Alert(Alert.AlertType.WARNING);
                    alert2.setTitle("Löschen nicht möglich!");
                    alert2.setContentText("Die Aktivitaet hat schon eine erfasste Zeit!");
                    alert2.showAndWait();
                }
            }
        }
    }

    /**
     * Methode, wodurch der StartButton und der ErledigtButton deaktiviert werden,
     * sobald man sich nicht in LocalDate.now() (Heute) befindet
     */
     private void kalender(){

         if (LocalDate.now().isEqual(dpkalender.getValue())){

             btStart.setDisable(false);
             btErledigt.setDisable(false);
         }
         else{
             btStart.setDisable(true);
             btErledigt.setDisable(true);

         }
     }
}



