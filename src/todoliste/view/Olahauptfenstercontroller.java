package todoliste.view;

import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import todoliste.datenbank.beans.AktivitaetsEintragBean;
import todoliste.model.AktivitaetsEintrag;



public class Olahauptfenstercontroller {

    static int me=0;
    static int ss=0;
    static int mm=0;
    static int hh=0;
    static boolean tableThread = true;
    static boolean b=true;

    @FXML
    private Button btnachdate;

    @FXML
    private Button btvordate;

    @FXML
    private Button btNeuerEintrag;

    @FXML
    private Button btLöschen;

    @FXML
    private Button btAktivitäsnamebearbeiten;

    @FXML
    private Button btEintragbearbeiten;

    @FXML
    private Button btProgrammbeenden;

    @FXML
    private Button btStart;

    @FXML
    private Button btErledigt;

    @FXML
    private Button btPause;

    @FXML
    private Label labelhour;

    @FXML
    private Label labelminute;

    @FXML
    private Label labelsecond;

    @FXML
    private Label labelmillisecond;

    @FXML
    private TableView<AktivitaetsEintrag> tabelview;

    @FXML
    private TableColumn<AktivitaetsEintrag, String> tcAktivität;

    @FXML
    private TableColumn<AktivitaetsEintrag, String> tcStartdatum;

    @FXML
    private TableColumn<AktivitaetsEintrag, String> tcEnddatum;

    @FXML
    private TableColumn<AktivitaetsEintrag, Integer> tcVerbrauchtezeit;

    @FXML
    private TableColumn<AktivitaetsEintrag, String> tcPriorität;

    @FXML
    private TableColumn<AktivitaetsEintrag, String> tcStatus;

    @FXML
    private TableColumn<AktivitaetsEintrag, String> tcLabel;

    @FXML
    private DatePicker dpkalender;

    @FXML
    void butonPause() {
        b=false;

    }

    @FXML
    void buttonAktivitäsnamebearbeiten() throws IOException {
        Parent part = FXMLLoader.load(getClass().getResource("BearbeiteAktivitaetToDoListe.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(part);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Aktivitäten bearbeiten");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void buttonEintragbearbeiten() {

    }

    @FXML
    void buttonErledigt() {
        b=false;
        hh=0;
        mm=0;
        ss=0;
        me=0;

        labelhour.setText("00 : ");
        labelminute.setText("00 : ");
        labelsecond.setText("00 : ");
        labelmillisecond.setText("00 : ");

    }

    @FXML
    void buttonLöschen() {
        loeschen();
    }

    @FXML
    void buttonNeuerEintrag() throws IOException {
        Parent part = FXMLLoader.load(getClass().getResource("NeuerEintragToDoListe.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(part);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Neuen Eintrag hinzufügen");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void buttonProgrammbeenden() {
        b=false;
        Stage stage = (Stage) btProgrammbeenden.getScene().getWindow();
        stage.close();
    }



    @FXML
    void buttonStart() {
        b=true;
        Thread t=new Thread(() ->
        {
            for (;;)
            {
                if (b==true)
                {
                    try
                    {
                        Thread.sleep(1);
                        if (me>1000)
                        {
                            me=0;
                            ss++;
                        }
                        if (ss>60)
                        {
                            ss=0;
                            mm++;
                        }
                        if (mm>60)
                        {
                            mm=0;
                            hh++;
                        }

                        me++;


                        // UI-Thread soll die Oberfläche aktualisieren,
                        // deshalb wird Platform.runLater aufgerufen
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                labelmillisecond.setText(" : "+me);
                                labelsecond.setText(" : "+ss);
                                labelminute.setText(" : "+mm);
                                labelhour.setText("  "+hh);
                            }
                        });


                    }
                    catch (Exception ex)
                    {

                    }
                }
                else
                {
                    break;
                }
            }
        });

        t.start();
        start();

    }



    @FXML
    void buttonnachdate() {

    }

    @FXML
    void buttonvordate() {

    }

    @FXML
    void kalender() {

    }



    private ArrayList<AktivitaetsEintrag> aktivitaetsEintrags;
    private ObservableList<AktivitaetsEintrag> aktivitaetsEintrags2;


    @FXML
    void initialize() {
        assert btnachdate != null : "fx:id=\"btnachdate\" was not injected: check your FXML file 'Olahauptfenster.fxml'.";
        assert btvordate != null : "fx:id=\"btvordate\" was not injected: check your FXML file 'Olahauptfenster.fxml'.";
        assert btNeuerEintrag != null : "fx:id=\"btNeuerEintrag\" was not injected: check your FXML file 'Olahauptfenster.fxml'.";
        assert btLöschen != null : "fx:id=\"btLöschen\" was not injected: check your FXML file 'Olahauptfenster.fxml'.";
        assert btAktivitäsnamebearbeiten != null : "fx:id=\"btAktivitäsnamebearbeiten\" was not injected: check your FXML file 'Olahauptfenster.fxml'.";
        assert btEintragbearbeiten != null : "fx:id=\"btEintragbearbeiten\" was not injected: check your FXML file 'Olahauptfenster.fxml'.";
        assert btProgrammbeenden != null : "fx:id=\"btProgrammbeenden\" was not injected: check your FXML file 'Olahauptfenster.fxml'.";
        assert btStart != null : "fx:id=\"btStart\" was not injected: check your FXML file 'Olahauptfenster.fxml'.";
        assert btErledigt != null : "fx:id=\"btErledigt\" was not injected: check your FXML file 'Olahauptfenster.fxml'.";
        assert btPause != null : "fx:id=\"btPause\" was not injected: check your FXML file 'Olahauptfenster.fxml'.";
        assert labelhour != null : "fx:id=\"labelhour\" was not injected: check your FXML file 'Olahauptfenster.fxml'.";
        assert labelminute != null : "fx:id=\"labelminute\" was not injected: check your FXML file 'Olahauptfenster.fxml'.";
        assert labelsecond != null : "fx:id=\"labelsecond\" was not injected: check your FXML file 'Olahauptfenster.fxml'.";
        assert labelmillisecond != null : "fx:id=\"labelmillisecond\" was not injected: check your FXML file 'Olahauptfenster.fxml'.";
        assert tabelview != null : "fx:id=\"tabelview\" was not injected: check your FXML file 'Olahauptfenster.fxml'.";
        assert tcAktivität != null : "fx:id=\"tcAktivität\" was not injected: check your FXML file 'Olahauptfenster.fxml'.";
        assert tcStartdatum != null : "fx:id=\"tcStartdatum\" was not injected: check your FXML file 'Olahauptfenster.fxml'.";
        assert tcEnddatum != null : "fx:id=\"tcEnddatum\" was not injected: check your FXML file 'Olahauptfenster.fxml'.";
        assert tcVerbrauchtezeit != null : "fx:id=\"tcVerbrauchtezeit\" was not injected: check your FXML file 'Olahauptfenster.fxml'.";
        assert tcPriorität != null : "fx:id=\"tcPriorität\" was not injected: check your FXML file 'Olahauptfenster.fxml'.";
        assert tcStatus != null : "fx:id=\"tcStatus\" was not injected: check your FXML file 'Olahauptfenster.fxml'.";
        assert tcLabel != null : "fx:id=\"tcLabel\" was not injected: check your FXML file 'Olahauptfenster.fxml'.";
        assert dpkalender != null : "fx:id=\"dpkalender\" was not injected: check your FXML file 'Olahauptfenster.fxml'.";

        //aktivitaetsEintrags2 = AktivitaetsEintragBean.getArtikelliste();
        test();
        //aktivitaetsEintrags2 = FXCollections.observableArrayList(aktivitaetsEintrags);
        infoTable();
        refresh();

    }


    private void infoTable() {

        aktivitaetsEintrags2 = FXCollections.observableArrayList(AktivitaetsEintragBean.getAktivitaeten());

        tcAktivität.setCellValueFactory(new PropertyValueFactory<>("aktivitaetsName"));
        tcStartdatum.setCellValueFactory(new PropertyValueFactory<>("startDatum"));
        tcEnddatum.setCellValueFactory(new PropertyValueFactory<>("endDatum"));
        tcVerbrauchtezeit.setCellValueFactory(new PropertyValueFactory<>("verbrauchteZeit"));
        tcPriorität.setCellValueFactory(new PropertyValueFactory<>("prioritaet"));
        tcStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        tcLabel.setCellValueFactory(new PropertyValueFactory<>("kategorie"));

        tabelview.setItems(aktivitaetsEintrags2);

        tcAktivität.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setAktivitaetsName(t.getNewValue()));
        tcStartdatum.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setStartDatum(t.getNewValue()));
        tcEnddatum.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setEndDatum(t.getNewValue()));
        tcVerbrauchtezeit.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setVerbrauchteZeit(t.getNewValue()));
        tcPriorität.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setPrioritaet(t.getNewValue()));
        tcStatus.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setStatus(t.getNewValue()));
        tcLabel.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setKategorie(t.getNewValue()));

    }

   public void refresh()
    {
        //tabelview.refresh();
        tableThread = true;
        Thread t=new Thread(() ->
        {

                if (tableThread == true) {
                    try {
                        // UI-Thread soll die Oberfläche aktualisieren,
                        // deshalb wird Platform.runLater aufgerufen
                        Thread.sleep(1);
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                infoTable();
                            }
                        });

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

        });


        t.start();
    }


   private void loeschen() {

        AktivitaetsEintrag ausgewaehlterArtikel = tabelview.getSelectionModel().getSelectedItem();
        aktivitaetsEintrags2.remove(ausgewaehlterArtikel);

    }

    private void start() {

        AktivitaetsEintrag ausgewaehlterArtikel = tabelview.getSelectionModel().getSelectedItem();
        tcStatus.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setStatus(t.getNewValue()));



    }


}



