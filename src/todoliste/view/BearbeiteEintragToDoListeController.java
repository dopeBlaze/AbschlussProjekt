package todoliste.view;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.DatePicker;
import javafx.scene.control.cell.PropertyValueFactory;
import todoliste.datenbank.beans.AktivitaetsEintragBean;
import todoliste.model.AktivitaetsEintrag;

import javax.annotation.processing.Completion;

public class BearbeiteEintragToDoListeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnUbernehmen;

    @FXML
    private TextField tfEintragsname;

    @FXML
    private TableView<AktivitaetsEintrag> TVAktivitaetsname;

    @FXML
    private ComboBox<String> kategory;

    @FXML
    private ComboBox<String> Prioritaet;

    @FXML
    private DatePicker startDatum;

    @FXML
    private DatePicker endDatum;

    @FXML
    void setzteEnddatum(ActionEvent event) {
        endDatum.setShowWeekNumbers(false);

        LocalDate e = endDatum.getValue();
        datumVerify(e,startDatum.getValue());
        if (endDatum.getValue().compareTo(startDatum.getValue()) < 0) {
            startDatum.setValue(endDatum.getValue());
        }
    }

    @FXML
    void setzteStartdatum(ActionEvent event) {
        startDatum.setShowWeekNumbers(false);
        // Startdatum setzen nicht unter jetzigen Datum moeglich
        if (startDatum.getValue().compareTo(LocalDate.now()) < 0){
            startDatum.setValue(LocalDate.now());
        }
    }
    /**
     * by presing on the Übernehmen button we get the entered value of Activity
     * @param event
     */
    @FXML
    void uebernehmeEintragsname(ActionEvent event) {
    setKategory(event);
    setPrioritaet(event);
    }
    @FXML
    void setKategory(ActionEvent event) {
        String f = kategory.getValue();
        System.out.println(f);
    }

    @FXML
    void setPrioritaet(ActionEvent event) {
        String p = Prioritaet.getValue();
        System.out.println(p);
    }

    private ObservableList<AktivitaetsEintrag> datatable;
    private FilteredList<AktivitaetsEintrag> tableFilteredData;


    @FXML
    void initialize() {




        assert btnUbernehmen != null : "fx:id=\"btnUbernehmen\" was not injected: check your FXML file 'BearbeiteEintragToDoListe.fxml'.";
        assert tfEintragsname != null : "fx:id=\"tfEintragsname\" was not injected: check your FXML file 'BearbeiteEintragToDoListe.fxml'.";
        assert TVAktivitaetsname != null : "fx:id=\"TVAktivitaetsname\" was not injected: check your FXML file 'BearbeiteEintragToDoListe.fxml'.";
        assert kategory != null : "fx:id=\"kategory\" was not injected: check your FXML file 'BearbeiteEintragToDoListe.fxml'.";
        assert Prioritaet != null : "fx:id=\"Prioritaet\" was not injected: check your FXML file 'BearbeiteEintragToDoListe.fxml'.";
        assert startDatum != null : "fx:id=\"startDatum\" was not injected: check your FXML file 'BearbeiteEintragToDoListe.fxml'.";
        assert endDatum != null : "fx:id=\"endDatum\" was not injected: check your FXML file 'BearbeiteEintragToDoListe.fxml'.";

        //Intializing the date  that we want  to start with todays's date so we have it already in our window
        startDatum.setValue(LocalDate.now());
        startDatum.getEditor().setDisable(true);
        //Intialization of the date ehat we want the end date is already assigned with today' s daate
        endDatum.setValue(LocalDate.now());
        endDatum.getEditor().setDisable(true);

        //Spalten erstellen
        TableColumn<AktivitaetsEintrag, String> tc1 = new TableColumn<>("Erstellung Datum");
        TableColumn<AktivitaetsEintrag, String> tc2 = new TableColumn<>("Start Datum");
        TableColumn<AktivitaetsEintrag, String> tc3 = new TableColumn<>("End Datum");
        TableColumn<AktivitaetsEintrag, String> tc4 = new TableColumn<>("Aktivität Name");
        TableColumn<AktivitaetsEintrag, String> tc5 = new TableColumn<>("Kategory");
        TableColumn<AktivitaetsEintrag, String> tc6 = new TableColumn<>("Priority");

        // Zuordnung Werte <-> Model
        tc1.setCellValueFactory(new PropertyValueFactory<>("Erstellung Datum"));
        tc2.setCellValueFactory(new PropertyValueFactory<>("Start Datum"));
        tc3.setCellValueFactory(new PropertyValueFactory<>("End Datum"));
        tc4.setCellValueFactory(new PropertyValueFactory<>("Aktivität Name"));
        tc5.setCellValueFactory(new PropertyValueFactory<>("Aktivität Name"));
        tc6.setCellValueFactory(new PropertyValueFactory<>("Priorität Name"));

        // Spalten hinzuf�gen
        TVAktivitaetsname.getColumns().add(tc1);
        TVAktivitaetsname.getColumns().add(tc2);
        TVAktivitaetsname.getColumns().add(tc3);
        TVAktivitaetsname.getColumns().add(tc4);
        TVAktivitaetsname.getColumns().add(tc5);
        TVAktivitaetsname.getColumns().add(tc6);

        // Daten zuweisen
        datatable = FXCollections.observableArrayList(AktivitaetsEintragBean.getAktivitaeten());
        TVAktivitaetsname.setItems(datatable);


        tc1.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setErstellungsDatum(t.getNewValue()));
        tc2.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setStartDatum(t.getNewValue()));
        tc3.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setEndDatum(t.getNewValue()));
        tc3.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setEndDatum(t.getNewValue()));
        tc4.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setAktivitaetsName(t.getNewValue()));
        tc5.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setKategorie(t.getNewValue()));

        kategory.setPromptText("Kategorie");
        kategory.setItems(FXCollections.observableArrayList());
        kategory.getItems().add("Arbeit");
        kategory.getItems().add("Privat");
        Prioritaet.setPromptText("Prioritaet");
        Prioritaet.setItems(FXCollections.observableArrayList());
        Prioritaet.getItems().add("Hoch");
        Prioritaet.getItems().add("Normal");
        Prioritaet.getItems().add("Niedrig");

    }






    public  boolean datumVerify(LocalDate startDatum,LocalDate endDatum) {
        try {
            if (startDatum.getMonthValue() > endDatum.getMonthValue() || startDatum.getDayOfMonth() > endDatum.getDayOfMonth() || startDatum.getYear() > endDatum.getYear())
                System.out.println("Please set the date  in a correct way");

        } catch (IllegalArgumentException e) {
            System.out.println(" Try to select the end date  to be after the start date" +e);
        }

        return false;
    }
}

