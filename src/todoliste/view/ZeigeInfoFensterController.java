package todoliste.view;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import todoliste.datenbank.beans.AktivitaetsEintragBean;
import todoliste.model.AktivitaetsEintrag;


public class ZeigeInfoFensterController {


    @FXML
    private Button btOk;

    @FXML
    private Button btAbbruch;

    @FXML
    private TableView<AktivitaetsEintrag> tableviewinfo;

    @FXML
    private TableColumn<AktivitaetsEintrag, String> tcAktivitaet;

    @FXML
    private TableColumn<AktivitaetsEintrag, String> tcStartdatum;

    @FXML
    private TableColumn<AktivitaetsEintrag, String> tcEnddatum;

    @FXML
    private TableColumn<AktivitaetsEintrag, Integer> Verbrauchtezeit;

    @FXML
    private TableColumn<AktivitaetsEintrag, String> tcPrioritaet;

    @FXML
    private TableColumn<AktivitaetsEintrag, String> tcStatus;

    @FXML
    private TableColumn<AktivitaetsEintrag, String> tcKategorie;

    private ObservableList<AktivitaetsEintrag> obsAktivitaetsEintrag;

    private ArrayList <AktivitaetsEintrag> arrayListGesamt = AktivitaetsEintragBean.getAktivitaeten();
    private ArrayList <AktivitaetsEintrag> arrayListSorted = new ArrayList<>();

    @FXML
    void erledigenButtonInfo() {

        for (AktivitaetsEintrag i : arrayListSorted) {
            i.setStatus("erledigt");
            AktivitaetsEintragBean.saveAktivitaet(i);
        }

        Stage stage = (Stage) btAbbruch.getScene().getWindow();
        stage.close();

    }

    @FXML
    void verschiebenButtonInfo() {
        String nextDay = LocalDate.now().plusDays(1).toString();
        for (AktivitaetsEintrag i : arrayListSorted) {
            i.setEndDatum(nextDay);
            AktivitaetsEintragBean.saveAktivitaet(i);
        }

        Stage stage = (Stage) btOk.getScene().getWindow();
        stage.close();
    }

    @FXML
    void initialize() {
        assert btOk != null : "fx:id=\"btOk\" was not injected: check your FXML file 'ZeigeInfoFenster.fxml'.";
        assert btAbbruch != null : "fx:id=\"btAbbruch\" was not injected: check your FXML file 'ZeigeInfoFenster.fxml'.";
        assert tableviewinfo != null : "fx:id=\"tableviewinfo\" was not injected: check your FXML file 'ZeigeInfoFenster.fxml'.";
        assert tcAktivitaet != null : "fx:id=\"tcAktivität\" was not injected: check your FXML file 'ZeigeInfoFenster.fxml'.";
        assert tcStartdatum != null : "fx:id=\"tcStartdatum\" was not injected: check your FXML file 'ZeigeInfoFenster.fxml'.";
        assert tcEnddatum != null : "fx:id=\"tcEnddatum\" was not injected: check your FXML file 'ZeigeInfoFenster.fxml'.";
        assert Verbrauchtezeit != null : "fx:id=\"Verbrauchtezeit\" was not injected: check your FXML file 'ZeigeInfoFenster.fxml'.";
        assert tcPrioritaet != null : "fx:id=\"tcPriorität\" was not injected: check your FXML file 'ZeigeInfoFenster.fxml'.";
        assert tcStatus != null : "fx:id=\"tcStatus\" was not injected: check your FXML file 'ZeigeInfoFenster.fxml'.";
        assert tcKategorie != null : "fx:id=\"tcKategorie\" was not injected: check your FXML file 'ZeigeInfoFenster.fxml'.";


        for (AktivitaetsEintrag i : arrayListGesamt) {
            if (i.getEndDatum().compareTo(LocalDate.now().toString()) == 0 && !i.getStatus().contains("erledigt")){
                arrayListSorted.add(i);
            }
        }

        obsAktivitaetsEintrag = FXCollections.observableArrayList(arrayListSorted);
        infoTable();

    }


    private void infoTable() {


        tcAktivitaet.setCellValueFactory(new PropertyValueFactory<>("aktivitaetsName"));
        tcStartdatum.setCellValueFactory(new PropertyValueFactory<>("startDatum"));
        tcEnddatum.setCellValueFactory(new PropertyValueFactory<>("endDatum"));
        Verbrauchtezeit.setCellValueFactory(new PropertyValueFactory<>("verbrauchteZeit"));
        tcPrioritaet.setCellValueFactory(new PropertyValueFactory<>("prioritaet"));
        tcStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        tcKategorie.setCellValueFactory(new PropertyValueFactory<>("kategorie"));


        tableviewinfo.setItems(obsAktivitaetsEintrag);

        tcAktivitaet.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setAktivitaetsName(t.getNewValue()));
        tcStartdatum.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setStartDatum(t.getNewValue()));
        tcEnddatum.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setEndDatum(t.getNewValue()));
        Verbrauchtezeit.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setVerbrauchteZeit(t.getNewValue()));
        tcPrioritaet.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setPrioritaet(t.getNewValue()));
        tcStatus.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setStatus(t.getNewValue()));
        tcKategorie.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setKategorie(t.getNewValue()));

    }

}
