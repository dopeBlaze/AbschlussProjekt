package todoliste.view;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
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
    private TableView<?> TVAktivitaetsname;

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
        LocalDate e = endDatum.getValue();
        datumVerify(e,startDatum.getValue());
        System.out.println(e);
    }

    @FXML
    void setzteStartdatum(ActionEvent event) {
        LocalDate s = startDatum.getValue();
        System.out.println(s);
    }

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
    @FXML
    void setTVAktivitaetsname(ActionEvent event) {
        ObservableList<AktivitaetsEintrag> x = (ObservableList<AktivitaetsEintrag>) TVAktivitaetsname.getItems();
        System.out.println(x);
    }

    @FXML
    void initialize() {
        assert btnUbernehmen != null : "fx:id=\"btnUbernehmen\" was not injected: check your FXML file 'BearbeiteEintragToDoListe.fxml'.";
        assert tfEintragsname != null : "fx:id=\"tfEintragsname\" was not injected: check your FXML file 'BearbeiteEintragToDoListe.fxml'.";
        assert TVAktivitaetsname != null : "fx:id=\"TVAktivitaetsname\" was not injected: check your FXML file 'BearbeiteEintragToDoListe.fxml'.";
        assert kategory != null : "fx:id=\"kategory\" was not injected: check your FXML file 'BearbeiteEintragToDoListe.fxml'.";
        assert Prioritaet != null : "fx:id=\"Prioritaet\" was not injected: check your FXML file 'BearbeiteEintragToDoListe.fxml'.";
        assert startDatum != null : "fx:id=\"startDatum\" was not injected: check your FXML file 'BearbeiteEintragToDoListe.fxml'.";
        assert endDatum != null : "fx:id=\"endDatum\" was not injected: check your FXML file 'BearbeiteEintragToDoListe.fxml'.";
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
