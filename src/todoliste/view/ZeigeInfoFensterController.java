package todoliste.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;


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
    private TableColumn<?, ?> tcAktivität;

    @FXML
    private TableColumn<?, ?> tcStartdatum;

    @FXML
    private TableColumn<?, ?> tcEnddatum;

    @FXML
    private TableColumn<?, ?> tcPriorität;

    @FXML
    private TableColumn<?, ?> tcStatus;

    @FXML
    private TableColumn<?, ?> tcLable;

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


    @FXML
    void initialize() {
        assert btOk != null : "fx:id=\"btOk\" was not injected: check your FXML file 'ZeigeInfoFenster.fxml'.";
        assert btAbbruch != null : "fx:id=\"btAbbruch\" was not injected: check your FXML file 'ZeigeInfoFenster.fxml'.";
        assert tcAktivität != null : "fx:id=\"tcAktivität\" was not injected: check your FXML file 'ZeigeInfoFenster.fxml'.";
        assert tcStartdatum != null : "fx:id=\"tcStartdatum\" was not injected: check your FXML file 'ZeigeInfoFenster.fxml'.";
        assert tcEnddatum != null : "fx:id=\"tcEnddatum\" was not injected: check your FXML file 'ZeigeInfoFenster.fxml'.";
        assert tcPriorität != null : "fx:id=\"tcPriorität\" was not injected: check your FXML file 'ZeigeInfoFenster.fxml'.";
        assert tcStatus != null : "fx:id=\"tcStatus\" was not injected: check your FXML file 'ZeigeInfoFenster.fxml'.";
        assert tcLable != null : "fx:id=\"tcLable\" was not injected: check your FXML file 'ZeigeInfoFenster.fxml'.";

    }
}
