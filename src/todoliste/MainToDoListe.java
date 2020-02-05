package todoliste;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import todoliste.datenbank.Datenbank;
import todoliste.datenbank.beans.AktivitaetsEintragBean;
import todoliste.model.AktivitaetsEintrag;
import java.util.ArrayList;


public class MainToDoListe extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("view/Olahauptfenster.fxml"));

        primaryStage.setTitle("ToDo Liste");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {

        // Test Erstellung der Datenbank
        Datenbank.getInstance().connect();
        ////////////////////////
      
        launch(args);

    }

    @Override
    public void stop() {
       // Trennen der Datenbankverbindung
       Datenbank.getInstance().disconnect();
    }
}