package todoliste.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
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
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private LineChart<?, ?> lcWochenbericht;

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
      XYChart.Series series=new XYChart.Series();
        series.getData().add(new XYChart.Data(1,23));
        series.getData().add(new XYChart.Data(2,13));
        series.getData().add(new XYChart.Data(3,43));
        series.getData().add(new XYChart.Data(4,54));
        series.getData().add(new XYChart.Data(5,55));
        lcWochenbericht.getData().addAll(series);
    }

}
