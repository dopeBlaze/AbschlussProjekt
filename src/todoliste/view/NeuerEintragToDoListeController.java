package todoliste.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import todoliste.datenbank.beans.AktivitaetsEintragBean;
import todoliste.model.AktivitaetsEintrag;
import java.time.LocalDate;

/**
 * Controller von NeuerEintragToDoListe
 */
public class NeuerEintragToDoListeController {

    @FXML
    private Button btnEintragHinzufuegen;

    @FXML
    private TextField tfNeuerEintrag;

    @FXML
    private TableView<AktivitaetsEintrag> TVAktivitaetsname;

    @FXML
    private ObservableList<AktivitaetsEintrag> tableData;

    @FXML
    private FilteredList<AktivitaetsEintrag> tableFilteredData;

    @FXML
    private DatePicker btnCalPickStart;

    @FXML
    private DatePicker btnCalPickEnd;

    @FXML
    private ChoiceBox<String> cbKategorie;

    @FXML
    private ChoiceBox<String> cbPrioritaet;


    /**
     * Neuer Eintrag wird mit den gesetzten Werten hinzugefuegt
     */
    @FXML
    void addNeuenEintrag() {
        try{
            AktivitaetsEintrag selected = TVAktivitaetsname.getSelectionModel().getSelectedItem();
            AktivitaetsEintrag neuerEintrag = new AktivitaetsEintrag(selected.getAktivitaetsName(), btnCalPickStart.getValue().toString(), btnCalPickEnd.getValue().toString(), 0, cbKategorie.getValue(), cbPrioritaet.getValue(), "nicht gestartet");
            AktivitaetsEintragBean.saveAktivitaet(neuerEintrag);
            // Rückmeldung wenn erfolgreich
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erfolgreich!");
            alert.setContentText("Aktivität wurde hinzugefügt.");
            alert.showAndWait();

        } catch (NullPointerException e){
            // Rückmeldung wenn Fehler
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Hinzufügen nicht möglich!");
            alert.setContentText("Aktivitätsname fehlt!\nBitte einen Namen auswählen!");
            alert.showAndWait();
        }
    }


    /**
     * Datumsabgleich von DatePicker btnCalPickStart
     * Wenn Startdatum spaeter als Enddatum, dann wird Enddatum gleich Startdatum gesetzt
     */
    @FXML
    void setzeStartdatum() {
        btnCalPickStart.setShowWeekNumbers(false);

        // Ist das Startdatum groesser als das EndDatum, so wird das EndDatum gleich dem StartDatum gesetzt
        if (btnCalPickStart.getValue().compareTo(btnCalPickEnd.getValue()) > 0){
            btnCalPickEnd.setValue(btnCalPickStart.getValue());
        }

        // Startdatum setzen nicht unter jetzigen Datum moeglich
        if (btnCalPickStart.getValue().compareTo(LocalDate.now()) < 0){
            btnCalPickStart.setValue(LocalDate.now());
        }
    }

    /**
     * Datumsabgleich von DatePicker btnCalPickEnd
     * Wenn Enddatum frueher als Startdatum, dann wird Startdatum gleich Enddatum gesetzt
     */
    @FXML
    void setzeEnddatum() {
        btnCalPickEnd.setShowWeekNumbers(false);

        // Ist das EndDatum kleiner als das StartDatum, so wird das StartDatum gleich dem EndDatum gesetzt
        if (btnCalPickEnd.getValue().compareTo(btnCalPickStart.getValue()) < 0) {
            btnCalPickStart.setValue(btnCalPickEnd.getValue());
        }
    }

    /**
     * Initialisierung des Fensters
     */
    @FXML
    void initialize() {
        assert btnEintragHinzufuegen != null : "fx:id=\"btnEintragHinzufuegen\" was not injected: check your FXML file 'NeuerEintragToDoListe.fxml'.";
        assert tfNeuerEintrag != null : "fx:id=\"tfNeuerEintrag\" was not injected: check your FXML file 'NeuerEintragToDoListe.fxml'.";
        assert TVAktivitaetsname != null : "fx:id=\"TVAktivitaetsname\" was not injected: check your FXML file 'NeuerEintragToDoListe.fxml'.";
        assert btnCalPickStart != null : "fx:id=\"btnCalPickStart\" was not injected: check your FXML file 'NeuerEintragToDoListe.fxml'.";
        assert btnCalPickEnd != null : "fx:id=\"btnCalPickEnd\" was not injected: check your FXML file 'NeuerEintragToDoListe.fxml'.";
        assert cbKategorie != null : "fx:id=\"cbKategorie\" was not injected: check your FXML file 'NeuerEintragToDoListe.fxml'.";
        assert cbPrioritaet != null : "fx:id=\"cbPrioritaet\" was not injected: check your FXML file 'NeuerEintragToDoListe.fxml'.";

        // Defaultwerte fuer die DatePicker setzen und die Textfelder deaktivieren
        btnCalPickStart.setValue(LocalDate.now());
        btnCalPickStart.getEditor().setDisable(true);
        btnCalPickEnd.setValue(LocalDate.now());
        btnCalPickEnd.getEditor().setDisable(true);

        initTable();
    }

    /**
     * Initialisierung der TableView
     */
    private void initTable() {
        tableData = FXCollections.observableArrayList(AktivitaetsEintragBean.getAktivitaetsNamen());

        // Spalten erstellen
        TableColumn<AktivitaetsEintrag, String> tcAktivitaetsName = new TableColumn<>("Aktivitaetsname");
        tcAktivitaetsName.setPrefWidth(320.0);

        // Zuordnung Werte <-> Model
        tcAktivitaetsName.setCellValueFactory(new PropertyValueFactory<>("aktivitaetsName"));

        // Spalten hinzufügen
        TVAktivitaetsname.getColumns().add(tcAktivitaetsName);

        // Daten zuweisen
        TVAktivitaetsname.setItems(tableData);

        // Für eine gefilterte und sortierte Ansicht
        tableFilteredData = new FilteredList<>(tableData, p -> true);
        SortedList<AktivitaetsEintrag> tableSortedData = new SortedList<>(tableFilteredData);
        tableSortedData.comparatorProperty().bind(TVAktivitaetsname.comparatorProperty());
        TVAktivitaetsname.setItems(tableSortedData);

        // Filter Predicate setzen
        tfNeuerEintrag.textProperty().addListener((observable, oldValue, newValue) -> {
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