package todoliste.view;

import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.DatePicker;
import javafx.scene.control.cell.PropertyValueFactory;
import todoliste.datenbank.beans.AktivitaetsEintragBean;
import todoliste.model.AktivitaetsEintrag;


public class BearbeiteEintragToDoListeController {

    @FXML
    private Button btnUbernehmen;

    @FXML
    private TextField tfEintragsname;

    @FXML
    private TableView<AktivitaetsEintrag> TVAktivitaetsname;

    @FXML
    private ObservableList<AktivitaetsEintrag> tableData;

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

    @FXML
    void setzteEnddatum() {
        endDatum.setShowWeekNumbers(false);
        if (endDatum.getValue().compareTo(startDatum.getValue()) < 0) {
            startDatum.setValue(endDatum.getValue());
        }
    }

    @FXML
    void setzteStartdatum() {
        startDatum.setShowWeekNumbers(false);

        if (startDatum.getValue().compareTo(endDatum.getValue()) > 0){
            endDatum.setValue(startDatum.getValue());
        }

        // Startdatum setzen nicht unter jetzigen Datum moeglich
        if (startDatum.getValue().compareTo(LocalDate.now()) < 0){
            startDatum.setValue(LocalDate.now());
        }
    }

    /**
     * by presing on the Übernehmen button we get the entered value of Activity
     *
     */
    @FXML
    void uebernehmeEintragsname() {

    }

    @FXML
    void initialize() {

        assert btnUbernehmen != null : "fx:id=\"btnUbernehmen\" was not injected: check your FXML file 'BearbeiteEintragToDoListe.fxml'.";
        assert tfEintragsname != null : "fx:id=\"tfEintragsname\" was not injected: check your FXML file 'BearbeiteEintragToDoListe.fxml'.";
        assert TVAktivitaetsname != null : "fx:id=\"TVAktivitaetsname\" was not injected: check your FXML file 'BearbeiteEintragToDoListe.fxml'.";
        assert cbKategorie != null : "fx:id=\"cbKategorie\" was not injected: check your FXML file 'NeuerEintragToDoListe.fxml'.";
        assert cbPrioritaet != null : "fx:id=\"cbPrioritaet\" was not injected: check your FXML file 'NeuerEintragToDoListe.fxml'.";
        assert startDatum != null : "fx:id=\"startDatum\" was not injected: check your FXML file 'BearbeiteEintragToDoListe.fxml'.";
        assert endDatum != null : "fx:id=\"endDatum\" was not injected: check your FXML file 'BearbeiteEintragToDoListe.fxml'.";

        startDatum.setValue(LocalDate.now());
        startDatum.getEditor().setDisable(true);
        endDatum.setValue(LocalDate.now());
        endDatum.getEditor().setDisable(true);

        initTableAktivitaet();
    }


    private void initTableAktivitaet() {
        tableData = FXCollections.observableArrayList(AktivitaetsEintragBean.getAktivitaeten());

        //Spalten erstellen
        TableColumn<AktivitaetsEintrag, String> tc1 = new TableColumn<>("Aktivität Name");
        TableColumn<AktivitaetsEintrag, String> tc2 = new TableColumn<>("Start Datum");
        TableColumn<AktivitaetsEintrag, String> tc3 = new TableColumn<>("End Datum");
        TableColumn<AktivitaetsEintrag, String> tc4 = new TableColumn<>("Priorität");
        TableColumn<AktivitaetsEintrag, String> tc5 = new TableColumn<>("Kategorie");

        // Zuordnung Werte <-> Model
        tc1.setCellValueFactory(new PropertyValueFactory<>("aktivitaetsName"));
        tc2.setCellValueFactory(new PropertyValueFactory<>("startDatum"));
        tc3.setCellValueFactory(new PropertyValueFactory<>("endDatum"));
        tc4.setCellValueFactory(new PropertyValueFactory<>("prioritaet"));
        tc5.setCellValueFactory(new PropertyValueFactory<>("kategorie"));

        // Spalten hinzufuegen
        TVAktivitaetsname.getColumns().add(tc1);
        TVAktivitaetsname.getColumns().add(tc2);
        TVAktivitaetsname.getColumns().add(tc3);
        TVAktivitaetsname.getColumns().add(tc4);
        TVAktivitaetsname.getColumns().add(tc5);

        // Daten zuweisen
        TVAktivitaetsname.setItems(tableData);

        // Für eine gefilterte und sortierte Ansicht
        tableFilteredData = new FilteredList<>(tableData, p -> true);
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

