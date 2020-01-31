package todoliste;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.ParallelCamera;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import todoliste.datenbank.Datenbank;
import todoliste.datenbank.beans.AktivitaetsEintragBean;
import todoliste.model.AktivitaetsEintrag;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MainToDoListe extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/ToDoListe.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {

        ////////////////////////
        Datenbank.getInstance().connect(); //Test Erstellung der Datenbank
        ////////////////////////

      
        ///////////////////////
        /*AktivitaetsEintrag a = new AktivitaetsEintrag(LocalDateTime.now().toString(), "Einkaufen", "2020-01-31", "2020-01-31", 0, "Privat", "normal", "nicht gestartet"); // Testobjekt
        AktivitaetsEintragBean.saveAktivitaet(a);
        AktivitaetsEintrag b = new AktivitaetsEintrag(LocalDateTime.now().toString(), "Arbeiten", "2020-01-31", "2020-01-31", 0, "Arbeit", "niedrig", "nicht gestartet"); // Testobjekt
        AktivitaetsEintragBean.saveAktivitaet(b);
        AktivitaetsEintrag c = new AktivitaetsEintrag(LocalDateTime.now().toString(), "Arbeiten", "2020-01-31", "2020-01-31", 0, "Arbeit", "hoch", "nicht gestartet"); // Testobjekt
        AktivitaetsEintragBean.saveAktivitaet(c);

        AktivitaetsEintragBean.deleteAktivitaet(c);

        b.setAktivitaetsName("Schlafen");
        AktivitaetsEintragBean.saveAktivitaet(b);
        */

        ArrayList<AktivitaetsEintrag> ar = AktivitaetsEintragBean.getAktivitaeten();

        for (AktivitaetsEintrag array: ar) {
            //array.setAktivitaetsName("Warten");
            //AktivitaetsEintragBean.saveAktivitaet(array);
            if (array.getErstellungsDatum().equals("2020-01-31T14:10:09.841996800")){
                AktivitaetsEintragBean.deleteAktivitaet(array);
            }
            System.out.println(array.getErstellungsDatum() + " " + array.getAktivitaetsName());
        }

        ArrayList<String> arName = AktivitaetsEintragBean.getAktivitaetsNamen();
        for (String array: arName) {
            //array.setAktivitaetsName("Warten");
            //AktivitaetsEintragBean.saveAktivitaet(array);
            System.out.println(array);
        }
        ///////////////////////

        launch(args);

    }

    @Override
    public void stop() {
        // Trennen der Datenbankverbindung
        Datenbank.getInstance().disconnect();
    }
}
