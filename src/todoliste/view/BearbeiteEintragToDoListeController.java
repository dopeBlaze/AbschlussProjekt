package todoliste.view;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.DatePicker;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import todoliste.datenbank.beans.AktivitaetsEintragBean;
import todoliste.model.AktivitaetsEintrag;


public class BearbeiteEintragToDoListeController {

    private static String erstellDatum;

    @FXML
    private Button btnUbernehmen;

    @FXML
    private TextField tfEintragsname;

    @FXML
    private AktivitaetsEintrag selectedAktivity;

    @FXML
    private TableView<AktivitaetsEintrag> TVAktivitaetsname;

    @FXML
    private TableView<AktivitaetsEintrag> TVAktivitaet;

    @FXML
    private ArrayList<AktivitaetsEintrag> arrayData;

    @FXML
    private ObservableList<AktivitaetsEintrag> tableDataAktivitaet;

    @FXML
    private ObservableList<AktivitaetsEintrag> tableDataAktivitaetName;

    @FXML
    private FilteredList<AktivitaetsEintrag> tableFilteredData;

    @FXML
    private ChoiceBox<String> cbKategorie;

    @FXML
    private ChoiceBox<String> cbPrioritaet;

    @FXML
    private DatePicker startDatum;

    @FXML
    private DatePicker endDatum;

    /**
     * Datumsabgleich von DatePicker endDatum
     */
    @FXML
    void setzteEnddatum() {
        endDatum.setShowWeekNumbers(false);

        // Ist das EndDatum kleiner als das StartDatum, so wird das StartDatum gleich dem EndDatum gesetzt
        if (endDatum.getValue().compareTo(startDatum.getValue()) < 0) {
            startDatum.setValue(endDatum.getValue());
        }

        // Die Werte des DatePickers werden gesetzt
        selectedAktivity.setStartDatum(startDatum.getValue().toString());
        selectedAktivity.setEndDatum(endDatum.getValue().toString());
    }

    /**
     * Datumsabgleich von DatePicker startDatum
     */
    @FXML
    void setzteStartdatum() {
        startDatum.setShowWeekNumbers(false);

        // Ist das Startdatum groesser als das EndDatum, so wird das EndDatum gleich dem StartDatum gesetzt
        if (startDatum.getValue().compareTo(endDatum.getValue()) > 0){
            endDatum.setValue(startDatum.getValue());
        }

        // Startdatum setzen nicht unter jetzigen Datum moeglich
        if (startDatum.getValue().compareTo(LocalDate.now()) < 0){
            startDatum.setValue(LocalDate.now());
        }

        // Die Werte des DatePickers werden gesetzt
        selectedAktivity.setStartDatum(startDatum.getValue().toString());
        selectedAktivity.setEndDatum(endDatum.getValue().toString());
    }

    /**
     * Zu nutzendes ErstellungsDatum
     * @param objektDate das uebergebene ErstellungsDatum
     */
    void getDatetime(String objektDate){
        erstellDatum = objektDate;
    }


    /**
     * Speichert die Aenderungen in die Datenbank und schließt das Fenster
     *
     */
    @FXML
    void uebernehmeEintragsname() {

        // Liest den ausgewaehlten Aktivitaetsnamen aus und setzt ihn
        AktivitaetsEintrag name = TVAktivitaetsname.getSelectionModel().getSelectedItem();
        selectedAktivity.setAktivitaetsName(name.getAktivitaetsName());

        // die Werte der ChoiceBoxen werden gesetzt
        selectedAktivity.setKategorie(cbKategorie.getValue());
        selectedAktivity.setPrioritaet(cbPrioritaet.getValue());

        // Der einzelne Eintrag wird abgespeichert
        AktivitaetsEintragBean.saveAktivitaetSingle(selectedAktivity);

        // Fenster wird geschlossen
        Stage stage = (Stage) btnUbernehmen.getScene().getWindow();
        stage.close();
    }

    @FXML
    void initialize() {

        assert btnUbernehmen != null : "fx:id=\"btnUbernehmen\" was not injected: check your FXML file 'BearbeiteEintragToDoListe.fxml'.";
        assert tfEintragsname != null : "fx:id=\"tfEintragsname\" was not injected: check your FXML file 'BearbeiteEintragToDoListe.fxml'.";
        assert TVAktivitaet != null : "fx:id=\"TVAktivitaet\" was not injected: check your FXML file 'BearbeiteEintragToDoListe.fxml'.";
        assert TVAktivitaetsname != null : "fx:id=\"TVAktivitaetsname\" was not injected: check your FXML file 'BearbeiteEintragToDoListe.fxml'.";
        assert cbKategorie != null : "fx:id=\"cbKategorie\" was not injected: check your FXML file 'NeuerEintragToDoListe.fxml'.";
        assert cbPrioritaet != null : "fx:id=\"cbPrioritaet\" was not injected: check your FXML file 'NeuerEintragToDoListe.fxml'.";
        assert startDatum != null : "fx:id=\"startDatum\" was not injected: check your FXML file 'BearbeiteEintragToDoListe.fxml'.";
        assert endDatum != null : "fx:id=\"endDatum\" was not injected: check your FXML file 'BearbeiteEintragToDoListe.fxml'.";



        // Ermittlung welche Aktivitaet ausgewaehlt wurde
        arrayData = AktivitaetsEintragBean.getAktivitaeten();
        for (AktivitaetsEintrag array : arrayData) {
            // erstellDatum VergleichsString
            // muss vom Hauptfenster mitgeliefert werden
            if (array.getErstellungsDatum().equals(erstellDatum)){
                selectedAktivity = array;
                tableDataAktivitaet = FXCollections.observableArrayList(AktivitaetsEintragBean.getAktivitaetSingle(selectedAktivity));
                initTableAktivitaet();
            }
        }


        // Startwerte fuer die DatePicker von Aktivitaet
        startDatum.setValue(LocalDate.parse(selectedAktivity.getStartDatum()));
        startDatum.getEditor().setDisable(true);
        endDatum.setValue(LocalDate.parse(selectedAktivity.getEndDatum()));
        endDatum.getEditor().setDisable(true);

        // Startwerte fuer die ChoiceBoxen von Aktivitaet
        cbKategorie.setValue(selectedAktivity.getKategorie());
        cbPrioritaet.setValue(selectedAktivity.getPrioritaet());

        initTableAktivitaetsNamen();

        // Default Auswahl von AktivitaetsName der Aktivitaet
        TVAktivitaetsname.getSelectionModel().select(selectedAktivity);
    }

    /**
     * Initialisierung von TableView der Aktivitaet
     */
    private void initTableAktivitaet() {

        //Spalten erstellen
        TableColumn<AktivitaetsEintrag, String> tc1 = new TableColumn<>("Aktivitaet Name");
        TableColumn<AktivitaetsEintrag, String> tc2 = new TableColumn<>("Start Datum");
        TableColumn<AktivitaetsEintrag, String> tc3 = new TableColumn<>("End Datum");
        TableColumn<AktivitaetsEintrag, String> tc4 = new TableColumn<>("Prioritaet");
        TableColumn<AktivitaetsEintrag, String> tc5 = new TableColumn<>("Kategorie");

        // Zuordnung Werte <-> Model
        tc1.setCellValueFactory(new PropertyValueFactory<>("aktivitaetsName"));
        tc2.setCellValueFactory(new PropertyValueFactory<>("startDatum"));
        tc3.setCellValueFactory(new PropertyValueFactory<>("endDatum"));
        tc4.setCellValueFactory(new PropertyValueFactory<>("prioritaet"));
        tc5.setCellValueFactory(new PropertyValueFactory<>("kategorie"));

        // Spalten hinzufuegen
        TVAktivitaet.getColumns().add(tc1);
        TVAktivitaet.getColumns().add(tc2);
        TVAktivitaet.getColumns().add(tc3);
        TVAktivitaet.getColumns().add(tc4);
        TVAktivitaet.getColumns().add(tc5);

        // Daten zuweisen
        TVAktivitaet.setItems(tableDataAktivitaet);
    }

    /**
     * Initialisierung von TableView der AktivitaetsNamen
     */
    private void initTableAktivitaetsNamen() {
        tableDataAktivitaetName = FXCollections.observableArrayList(AktivitaetsEintragBean.getAktivitaetsNamen());

        //Spalten erstellen
        TableColumn<AktivitaetsEintrag, String> tc1 = new TableColumn<>("Aktivitaet Name");
        tc1.setPrefWidth(320.0);

        // Zuordnung Werte <-> Model
        tc1.setCellValueFactory(new PropertyValueFactory<>("aktivitaetsName"));

        // Spalten hinzufuegen
        TVAktivitaetsname.getColumns().add(tc1);

        // Daten zuweisen
        TVAktivitaetsname.setItems(tableDataAktivitaetName);

        // Fuer eine gefilterte und sortierte Ansicht
        tableFilteredData = new FilteredList<>(tableDataAktivitaetName, p -> true);
        SortedList<AktivitaetsEintrag> tableSortedData = new SortedList<>(tableFilteredData);
        tableSortedData.comparatorProperty().bind(TVAktivitaetsname.comparatorProperty());
        TVAktivitaetsname.setItems(tableSortedData);

        // Filter Predicate setzen
        tfEintragsname.textProperty().addListener((observable, oldValue, newValue) -> {
            tableFilteredData.setPredicate(aktivitaetsName -> {
                // wenn Filter leer dann zeige alle Aktivitaetsnamen
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // vergleiche Aktivitaetsnamen mit Filtertext
                String lowerCaseFilter = newValue.toLowerCase();

                if (aktivitaetsName.getAktivitaetsName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter hat Treffer
                }
                return false; // Vergleich findet keine Uebereinstimmung
            });
        });
    }
}

