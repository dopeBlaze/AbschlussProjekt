package todoliste;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import todoliste.datenbank.Datenbank;
import todoliste.model.AktivitaetsEintrag;


public class MainToDoListe extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/BearbeiteEintragToDoListe.fxml"));
        primaryStage.setTitle("ToDo Liste");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {

        ////////////////////////
        Datenbank.getInstance().connect(); //Test Erstellung der Datenbank
        ////////////////////////

      
        ///////////////////////
        AktivitaetsEintrag a = new AktivitaetsEintrag(); // Testobjekt
        System.out.println(a.getErstellungsDatum());
        ///////////////////////

        launch(args);

    }

    @Override
    public void stop() {
        // Trennen der Datenbankverbindung
        Datenbank.getInstance().disconnect();
    }
}
