package todoliste.view;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

public class WochenberichtToDoListe implements Initializable {

    @FXML
    private LineChart<String, Number> lcWochenbericht;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private Button btnOK;

    @FXML
    private TableColumn<?, ?> tcAktivität;

    @FXML
    private TableColumn<?, ?> tcGesamtzeit;

    @FXML
    private Label lDatum;

    @FXML
    void bestätigeOK() {
        Stage stage = (Stage) btnOK.getScene().getWindow();
        stage.close();

    }

    @FXML
    void initialize() {
        assert lcWochenbericht != null : "fx:id=\"lcWochenbericht\" was not injected: check your FXML file 'WochenberichtToDoListe.fxml'.";
        assert xAxis != null : "fx:id=\"xAxis\" was not injected: check your FXML file 'WochenberichtToDoListe.fxml'.";
        assert yAxis != null : "fx:id=\"yAxis\" was not injected: check your FXML file 'WochenberichtToDoListe.fxml'.";
        assert btnOK != null : "fx:id=\"btnOK\" was not injected: check your FXML file 'WochenberichtToDoListe.fxml'.";
        assert tcAktivität != null : "fx:id=\"tcAktivität\" was not injected: check your FXML file 'WochenberichtToDoListe.fxml'.";
        assert tcGesamtzeit != null : "fx:id=\"tcGesamtzeit\" was not injected: check your FXML file 'WochenberichtToDoListe.fxml'.";
        assert lDatum != null : "fx:id=\"lDatum\" was not injected: check your FXML file 'WochenberichtToDoListe.fxml'.";
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        int geamtZeit1 = 2, geamtZeit2 = 2, geamtZeit3 = 5, geamtZeit4 = 1, geamtZeit5 = 7, geamtZeit6 = 2, geamtZeit7 = 0;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        ObservableList<XYChart.Data<String, Number>> data = FXCollections.observableArrayList();

        data.add(new XYChart.Data(dtf.format(LocalDate.now().plusDays(-4)), geamtZeit1));
        data.add(new XYChart.Data(dtf.format(LocalDate.now().plusDays(-3)), geamtZeit2));
        data.add(new XYChart.Data(dtf.format(LocalDate.now().plusDays(-2)), geamtZeit3));
        data.add(new XYChart.Data(dtf.format(LocalDate.now().plusDays(-1)), geamtZeit4));
        data.add(new XYChart.Data(dtf.format(LocalDate.now()) , geamtZeit5));
        data.add(new XYChart.Data(dtf.format(LocalDate.now().plusDays(1)), geamtZeit6));
        data.add(new XYChart.Data(dtf.format(LocalDate.now().plusDays(2)), geamtZeit7));

        SortedList<XYChart.Data<String, Number>> sortedData = new SortedList<>(data);

        lcWochenbericht.getData().addAll(new XYChart.Series<>(sortedData));

    }

}
