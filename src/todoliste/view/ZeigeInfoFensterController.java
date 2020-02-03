package todoliste.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import todoliste.datenbank.beans.AktivitaetsEintragBean;
import todoliste.model.AktivitaetsEintrag;


public class ZeigeInfoFensterController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btOk;

    @FXML
    private Button btAbbruch;


    @FXML
    private TableView<AktivitaetsEintrag> tableviewinfo;

    @FXML
    private TableColumn<AktivitaetsEintrag, String> tcAktivität;

    @FXML
    private TableColumn<AktivitaetsEintrag, String> tcStartdatum;

    @FXML
    private TableColumn<AktivitaetsEintrag, String> tcEnddatum;

    @FXML
    private TableColumn<AktivitaetsEintrag, Integer> Verbrauchtezeit;

    @FXML
    private TableColumn<AktivitaetsEintrag, String> tcPriorität;

    @FXML
    private TableColumn<AktivitaetsEintrag, String> tcStatus;

    @FXML
    private TableColumn<AktivitaetsEintrag, String> tcLable;

    @FXML
    void abbruchButtonInfo() {

        Stage stage = (Stage) btAbbruch.getScene().getWindow();
        stage.close();

    }

    @FXML
    void obkButtonInfo() {
        Stage stage = (Stage) btOk.getScene().getWindow();
        stage.close();

    }


    private ArrayList<AktivitaetsEintrag> aktivitaetsEintrags;
    private ObservableList<AktivitaetsEintrag> tableData;
    private FilteredList<AktivitaetsEintrag> tableFilteredData;
    private int angezeigterEintrag;


    @FXML
    void initialize() {
        assert btOk != null : "fx:id=\"btOk\" was not injected: check your FXML file 'ZeigeInfoFenster.fxml'.";
        assert btAbbruch != null : "fx:id=\"btAbbruch\" was not injected: check your FXML file 'ZeigeInfoFenster.fxml'.";
        assert tableviewinfo != null : "fx:id=\"tableviewinfo\" was not injected: check your FXML file 'ZeigeInfoFenster.fxml'.";
        assert tcAktivität != null : "fx:id=\"tcAktivität\" was not injected: check your FXML file 'ZeigeInfoFenster.fxml'.";
        assert tcStartdatum != null : "fx:id=\"tcStartdatum\" was not injected: check your FXML file 'ZeigeInfoFenster.fxml'.";
        assert tcEnddatum != null : "fx:id=\"tcEnddatum\" was not injected: check your FXML file 'ZeigeInfoFenster.fxml'.";
        assert Verbrauchtezeit != null : "fx:id=\"Verbrauchtezeit\" was not injected: check your FXML file 'ZeigeInfoFenster.fxml'.";
        assert tcPriorität != null : "fx:id=\"tcPriorität\" was not injected: check your FXML file 'ZeigeInfoFenster.fxml'.";
        assert tcStatus != null : "fx:id=\"tcStatus\" was not injected: check your FXML file 'ZeigeInfoFenster.fxml'.";
        assert tcLable != null : "fx:id=\"tcLable\" was not injected: check your FXML file 'ZeigeInfoFenster.fxml'.";

        // tableData = AktivitaetsEintragBean.getArtikelliste();
        infoTable();


    }


    private void infoTable() {

        tcAktivität.setCellValueFactory(new PropertyValueFactory<>("aktivitaetsName"));
        tcStartdatum.setCellValueFactory(new PropertyValueFactory<>("startDatum"));
        tcEnddatum.setCellValueFactory(new PropertyValueFactory<>("endDatum"));
        Verbrauchtezeit.setCellValueFactory(new PropertyValueFactory<>("verbrauchteZeit"));
        tcPriorität.setCellValueFactory(new PropertyValueFactory<>("prioritaet"));
        tcStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        tcLable.setCellValueFactory(new PropertyValueFactory<>("kategorie"));

        tableviewinfo.getColumns().add(tcAktivität);
        tableviewinfo.getColumns().add(tcStartdatum);
        tableviewinfo.getColumns().add(tcEnddatum);
        tableviewinfo.getColumns().add(Verbrauchtezeit);
        tableviewinfo.getColumns().add(tcPriorität);
        tableviewinfo.getColumns().add(tcStatus);
        tableviewinfo.getColumns().add(tcLable);

        tcAktivität.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setAktivitaetsName(t.getNewValue()));
        tcStartdatum.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setStartDatum(t.getNewValue()));
        tcEnddatum.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setEndDatum(t.getNewValue()));
        Verbrauchtezeit.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setVerbrauchteZeit(Integer.parseInt(String.valueOf(t.getNewValue()))));
        tcPriorität.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setPrioritaet(t.getNewValue()));
        tcStatus.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setStatus(t.getNewValue()));
        tcLable.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setKategorie(t.getNewValue()));

    }




}
