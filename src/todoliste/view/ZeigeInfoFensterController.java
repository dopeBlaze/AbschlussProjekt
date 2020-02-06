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

/**
 * Controller fuer das Infofenster beim Beenden des Programms
 */
public class ZeigeInfoFensterController {

    @FXML
    private Button btVerschieben;

    @FXML
    private Button btErledigen;

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

    /**
     * Button Aktion um alle Aktivitaeten als erledigt zu setzten
     */
    @FXML
    void erledigenButtonInfo() {

        // Setzt den Status jeder Aktivitaet als 'erledigt'
        for (AktivitaetsEintrag i : arrayListSorted) {
            i.setStatus("erledigt");
            // Speichern in Datenbank
            AktivitaetsEintragBean.saveAktivitaet(i);
        }

        Stage stage = (Stage) btErledigen.getScene().getWindow();
        stage.close();
    }

    /**
     * Button Aktion um bei allen Aktivitaeten die Deadline um einen Tag zu verschieben
     */
    @FXML
    void verschiebenButtonInfo() {
        // Setzt das Enddatum um einen Tag nach hinten
        String nextDay = LocalDate.now().plusDays(1).toString();
        for (AktivitaetsEintrag i : arrayListSorted) {
            i.setEndDatum(nextDay);
            // Speichern in Datenbank
            AktivitaetsEintragBean.saveAktivitaet(i);
        }

        Stage stage = (Stage) btVerschieben.getScene().getWindow();
        stage.close();
    }

    /**
     * Initialisierung des Fensters
     */
    @FXML
    void initialize() {
        assert btVerschieben != null : "fx:id=\"btOk\" was not injected: check your FXML file 'ZeigeInfoFenster.fxml'.";
        assert btErledigen != null : "fx:id=\"btAbbruch\" was not injected: check your FXML file 'ZeigeInfoFenster.fxml'.";
        assert tableviewinfo != null : "fx:id=\"tableviewinfo\" was not injected: check your FXML file 'ZeigeInfoFenster.fxml'.";
        assert tcAktivitaet != null : "fx:id=\"tcAktivität\" was not injected: check your FXML file 'ZeigeInfoFenster.fxml'.";
        assert tcStartdatum != null : "fx:id=\"tcStartdatum\" was not injected: check your FXML file 'ZeigeInfoFenster.fxml'.";
        assert tcEnddatum != null : "fx:id=\"tcEnddatum\" was not injected: check your FXML file 'ZeigeInfoFenster.fxml'.";
        assert Verbrauchtezeit != null : "fx:id=\"Verbrauchtezeit\" was not injected: check your FXML file 'ZeigeInfoFenster.fxml'.";
        assert tcPrioritaet != null : "fx:id=\"tcPriorität\" was not injected: check your FXML file 'ZeigeInfoFenster.fxml'.";
        assert tcStatus != null : "fx:id=\"tcStatus\" was not injected: check your FXML file 'ZeigeInfoFenster.fxml'.";
        assert tcKategorie != null : "fx:id=\"tcKategorie\" was not injected: check your FXML file 'ZeigeInfoFenster.fxml'.";


        // Liste mit allen nicht erledigten Aktivitaeten, die sich in der Deadline befinden
        for (AktivitaetsEintrag i : arrayListGesamt) {
            if (i.getEndDatum().compareTo(LocalDate.now().toString()) == 0 && !i.getStatus().contains("erledigt")){
                arrayListSorted.add(i);
            }
        }

        obsAktivitaetsEintrag = FXCollections.observableArrayList(arrayListSorted);
        infoTable();
    }


    /**
     * Methode zur Erstellung der TableView
     */
    private void infoTable() {

        tcAktivitaet.setCellValueFactory(new PropertyValueFactory<>("aktivitaetsName"));
        tcStartdatum.setCellValueFactory(new PropertyValueFactory<>("startDatum"));
        tcEnddatum.setCellValueFactory(new PropertyValueFactory<>("endDatum"));
        Verbrauchtezeit.setCellValueFactory(new PropertyValueFactory<>("verbrauchteZeit"));
        tcPrioritaet.setCellValueFactory(new PropertyValueFactory<>("prioritaet"));
        tcStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        tcKategorie.setCellValueFactory(new PropertyValueFactory<>("kategorie"));

        tableviewinfo.setItems(obsAktivitaetsEintrag);
    }
}
