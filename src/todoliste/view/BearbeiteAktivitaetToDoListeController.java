package todoliste.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import todoliste.model.AktivitaetsEintrag;

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


        /////////////////////// Testeinträge
        ArrayList <AktivitaetsEintrag> arrayList = new ArrayList<>();
        AktivitaetsEintrag a = new AktivitaetsEintrag(LocalDateTime.now().toString(), "Laufen", "2020-02-03", "2020-02-03", 0, "Privat", "normal", "nicht gestartet");
        arrayList.add(a);
        AktivitaetsEintrag b = new AktivitaetsEintrag(LocalDateTime.now().toString(), "Laufen", "2020-02-03", "2020-02-03", 0, "Privat", "normal", "nicht gestartet");
        arrayList.add(b);
        AktivitaetsEintrag c = new AktivitaetsEintrag(LocalDateTime.now().toString(), "Laufen", "2020-02-03", "2020-02-03", 0, "Privat", "normal", "nicht gestartet");
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
        TableColumn<AktivitaetsEintrag, String> tc1 = new TableColumn<>("Aktivitaetsname");

        // Zuordnung Werte <-> Model
        tc1.setCellValueFactory(new PropertyValueFactory<>("aktivitaetsName"));

        // Spalten hinzufügen
        TVAktivitaetsname.getColumns().add(tc1);

        // Daten zuweisen
        TVAktivitaetsname.setItems(tableData);

        /* Für eine gefilterte und sortierte Ansicht
        tableFilteredData = new FilteredList<>(tableData, p -> true);
        SortedList<Artikel> tableSortedData = new SortedList<>(tableFilteredData);
        tableSortedData.comparatorProperty().bind(tvExample.comparatorProperty());
        tvExample.setItems(tableSortedData);

        // Filterung hinzufügen
        tableFilteredData.setPredicate(artikel -> {

            if (artikel.getArtikelNr().equals("12345-4")) return true;
            if (artikel.getName().contains("3")) return true;

            return false;
        });*/

        // TableView Menü-Button anzeigen
        TVAktivitaetsname.setTableMenuButtonVisible(true);


        // Notwendig um Zellen in einer TableView zu bearbeiten

        // Tabelle editierbar machen
        TVAktivitaetsname.setEditable(true);

        // Zellenerscheinung definieren (notwendig für Editierung)
        /*Callback<TableColumn<Artikel, String>, TableCell<Artikel, String>> cellTextFactory = p -> new EditingTextCell<>();
        tc1.setCellFactory(cellTextFactory);*/

        // Was passiert, nachdem die Zellenänderung stattgefunden hat
//        tc1.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setName(t.getNewValue()));
    }
}