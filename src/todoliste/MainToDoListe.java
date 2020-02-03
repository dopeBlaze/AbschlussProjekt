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
        Parent root = FXMLLoader.load(getClass().getResource("view/ToDoListe.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {

        ////////////////////////
        Datenbank.getInstance().connect(); //Test Erstellung der Datenbank
        ////////////////////////

        // Testeinträge
        ///////////////////////
        /*AktivitaetsEintrag a = new AktivitaetsEintrag(LocalDateTime.now().toString(), "Laufen", "2020-01-31", "2020-01-31", 0, "Privat", "normal", "nicht gestartet"); // Testobjekt
        AktivitaetsEintragBean.saveAktivitaet(a);
        AktivitaetsEintrag b = new AktivitaetsEintrag(LocalDateTime.now().toString(), "Arbeiten", "2020-01-31", "2020-01-31", 0, "Arbeit", "niedrig", "nicht gestartet"); // Testobjekt
        AktivitaetsEintragBean.saveAktivitaet(b);*/
        AktivitaetsEintrag c = new AktivitaetsEintrag("Arbeiten", "2020-02-03", "2020-02-03", 0, "Arbeit", "hoch", "nicht gestartet"); // Testobjekt
        AktivitaetsEintragBean.saveAktivitaet(c);

        //AktivitaetsEintragBean.deleteAktivitaet(c);

        /*b.setAktivitaetsName("Schlafen");
        AktivitaetsEintragBean.saveAktivitaet(b);*/


        ArrayList<AktivitaetsEintrag> ar = AktivitaetsEintragBean.getAktivitaeten();

        for (AktivitaetsEintrag array: ar) {
            //array.setAktivitaetsName("Warten");
            //AktivitaetsEintragBean.saveAktivitaet(array);
            if (array.getErstellungsDatum().equals("2020-01-31T14:10:09.841996800")){
                AktivitaetsEintragBean.deleteAktivitaet(array);
            }
            System.out.println(array.getErstellungsDatum() + " " + array.getAktivitaetsName());
        }


        AktivitaetsEintrag name = new AktivitaetsEintrag("Gehen2");
        try {
            // Fehler wenn Name doppelt gespeichert werden soll, da Unique
            AktivitaetsEintragBean.saveAktivitaetsName(name);
        } catch (IllegalArgumentException e){
            System.err.println("Fehler beim Speichern des AktivitaetsNamen in der Datenbank: " + e.getLocalizedMessage());
        }

        ArrayList<AktivitaetsEintrag> arName = AktivitaetsEintragBean.getAktivitaetsNamen();
        for (AktivitaetsEintrag array: arName) {
            //array.setAktivitaetsName("Warten");
            //AktivitaetsEintragBean.saveAktivitaet(array);
            if (array.getAktivitaetsName().equals("Gehen2")){
                array.setAktivitaetsName("Gehen");
                AktivitaetsEintragBean.saveAktivitaetsName(array);
            }
            System.out.println(array.getAktivitaetsName());
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
