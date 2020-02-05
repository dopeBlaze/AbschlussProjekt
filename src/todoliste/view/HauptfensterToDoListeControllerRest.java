package todoliste.view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import todoliste.datenbank.beans.AktivitaetsEintragBean;
import todoliste.model.AktivitaetsEintrag;
import todoliste.util.EditingTextCell;

public class HauptfensterToDoListeControllerRest {

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


    //ToDo evtl. Button in Hauptfenster GUI aktivieren, falls noch Zeit zur Bearbeitung des Features bleibt!
    @FXML
    void anzeigenWochenbericht(ActionEvent event) {
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


    // Aktuellen Eintrag löschen
    //HauptfensterToDoListeController.remove(angezeigteAktivitaet);

    // Gibt an, dass ein Fehler aufgetreten ist als Hinweisfenster mit ok Button
//    private void alertAnzeigen(String title, String message) {
//        Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setTitle(title);
//        alert.setHeaderText(null);
//        alert.setContentText(message);
//        alert.showAndWait();
//    }


// Notwendig um Zellen in einer TableView zu bearbeiten

    // Tabelle editierbar machen
    // tableData.setEditable(true);

//        // Zellenerscheinung definieren (notwendig für Editierung)
//        Callback<TableColumn<AktivitaetsEintrag, String>, TableCell<AktivitaetsEintrag, String>> cellTextFactory = p -> new EditingTextCell<>();
//        tc1.setCellFactory(cellTextFactory);
//        tc2.setCellFactory(cellTextFactory);
//        tc3.setCellFactory(cellTextFactory);
//        tc4.setCellFactory(cellTextFactory);
//        tc5.setCellFactory(cellTextFactory);
//        tc6.setCellFactory(cellTextFactory);
//        tc7.setCellFactory(cellTextFactory);
//
//        // Was passiert, nachdem die Zellenänderung stattgefunden hat
//        tc1.setOnEditCommit(t -> t.getTableView().
//
//                getItems().
//
//                get(t.getTablePosition().
//
//                        getRow()).
//
//                setAktivitaetsName(t.getNewValue()));
//        tc2.setOnEditCommit(t -> t.getTableView().
//
//                getItems().
//
//                get(t.getTablePosition().
//
//                        getRow()).
//
//                setStartDatum(t.getNewValue()));
//        tc3.setOnEditCommit(t -> t.getTableView().
//
//                getItems().
//
//                get(t.getTablePosition().
//
//                        getRow()).
//
//                setEndDatum(t.getNewValue()));
//        tc4.setOnEditCommit(t -> t.getTableView().
//
//                getItems().
//
//                get(t.getTablePosition().
//
//                        getRow()).
//
//                setVerbrauchteZeit(t.getNewValue()));
//        tc5.setOnEditCommit(t -> t.getTableView().
//
//                getItems().
//
//                get(t.getTablePosition().
//
//                        getRow()).
//
//                setKategorie(t.getNewValue()));
//        tc6.setOnEditCommit(t -> t.getTableView().
//
//                getItems().
//
//                get(t.getTablePosition().
//
//                        getRow()).
//
//                setPrioritaet(t.getNewValue()));
//        tc7.setOnEditCommit(t -> t.getTableView().
//
//                getItems().
//
//                get(t.getTablePosition().
//
//                        getRow()).
//
//                setStatus(t.getNewValue()));
//    }
}



