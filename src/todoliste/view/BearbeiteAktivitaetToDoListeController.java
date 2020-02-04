package todoliste.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import todoliste.model.AktivitaetsEintrag;
import todoliste.util.EditingTextCell;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class BearbeiteAktivitaetToDoListeController {

    @FXML
    private Button btnUebernehmen;

    @FXML
    private TextField tfAktivitaetsname;

    @FXML
    private TableView<AktivitaetsEintrag> TVAktivitaetsname;

    @FXML
    private ObservableList<AktivitaetsEintrag> tableData;

    private FilteredList<AktivitaetsEintrag> tableFilteredData;

    @FXML
    private Button btnLoeschen;

    @FXML
    void loescheAktivitaetsname() {

        AktivitaetsEintrag ausgewaehlterArtikel = TVAktivitaetsname.getSelectionModel().getSelectedItem();
        tableData.remove(ausgewaehlterArtikel);
        // Platform.runLater(() -> ArtikelBean.delete(ausgewaehlterArtikel)); // Datensatz aus Datenbank speichern
        TVAktivitaetsname.refresh(); // manuelle Aktualisierung der angezeigten Daten
    }

    @FXML
    void uebernehmeAktivitaetsname() {

        Stage stage = (Stage) btnUebernehmen.getScene().getWindow();
        stage.close();
    }

    @FXML
    void initialize() {


        /////////////////////// Testeinträge
        ArrayList <AktivitaetsEintrag> arrayList = new ArrayList<>();
        AktivitaetsEintrag a = new AktivitaetsEintrag(LocalDateTime.now().toString(), "Laufen", "2020-02-03", "2020-02-03", 0, "Privat", "normal", "nicht gestartet");
        arrayList.add(a);
        AktivitaetsEintrag b = new AktivitaetsEintrag(LocalDateTime.now().toString(), "Putzen", "2020-02-03", "2020-02-03", 0, "Privat", "normal", "nicht gestartet");
        arrayList.add(b);
        AktivitaetsEintrag c = new AktivitaetsEintrag(LocalDateTime.now().toString(), "Kochen", "2020-02-03", "2020-02-03", 0, "Privat", "normal", "nicht gestartet");
        arrayList.add(c);
        ////////////////////////

        tableData = FXCollections.observableArrayList(arrayList);


        assert btnUebernehmen != null : "fx:id=\"btnUebernehmen\" was not injected: check your FXML file 'BearbeiteAktivitaetToDoListe.fxml'.";
        assert tfAktivitaetsname != null : "fx:id=\"tfAktivitaetsname\" was not injected: check your FXML file 'BearbeiteAktivitaetToDoListe.fxml'.";
        assert TVAktivitaetsname != null : "fx:id=\"TVAktivitaetsname\" was not injected: check your FXML file 'BearbeiteAktivitaetToDoListe.fxml'.";
        assert btnLoeschen != null : "fx:id=\"btnLoeschen\" was not injected: check your FXML file 'BearbeiteAktivitaetToDoListe.fxml'.";

        initTable();
    }


    /**
     * Diese Funktion initialisiert die TableView
     */
    private void initTable() {
        // Spalten erstellen
        TableColumn<AktivitaetsEintrag, String> tcAktivitaetsName = new TableColumn<>("Aktivitaetsname");
        tcAktivitaetsName.setPrefWidth(334.0);

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
        tfAktivitaetsname.textProperty().addListener((observable, oldValue, newValue) -> {
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


        // Notwendig um Zellen in einer TableView zu bearbeiten

        // Tabelle editierbar machen
        TVAktivitaetsname.setEditable(true);

        // Zellenerscheinung definieren (notwendig für Editierung)
        Callback<TableColumn<AktivitaetsEintrag, String>, TableCell<AktivitaetsEintrag, String>> cellTextFactory = p -> new EditingTextCell<>();
        tcAktivitaetsName.setCellFactory(cellTextFactory);

        // Aenderungen uebernehmen
        tcAktivitaetsName.setOnEditCommit(t -> {
            String oldAktivitaetsName = t.getOldValue();
            t.getTableView().getItems().get(t.getTablePosition().getRow()).setAktivitaetsName(t.getNewValue());
            String newAktivitaetsName = t.getNewValue();

            // TODO in Datenbank übernehmen
            // if Anweisung Pruefung ob Name schon vorhanden, ansonsten speichern
            try{
                // Bean einfuegen
                // AktivitaetsEintragBean.saveAktivitaetsName(newAktivitaetsName);
            } catch(IllegalArgumentException e){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Name schon vergeben");
                alert.setHeaderText("Look, an Information Dialog");
                alert.setContentText("Der Name existiert schon!\nBitte wähle einen neuen Namen aus!");

                alert.showAndWait();


                t.getTableView().getItems().get(t.getTablePosition().getRow()).setAktivitaetsName(oldAktivitaetsName);
                TVAktivitaetsname.refresh();
            }
        });
    }
}