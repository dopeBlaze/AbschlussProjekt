package todoliste.datenbank.beans;

import todoliste.datenbank.Datenbank;
import todoliste.model.AktivitaetsEintrag;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class AktivitaetsEintragBean {

    private static PreparedStatement pstmtSelectAktivitaet;
    private static PreparedStatement pstmtInsertAktivitaet;
    private static PreparedStatement pstmtUpdateAktivitaet;
    private static PreparedStatement pstmtDeleteAktivitaet;
    private static PreparedStatement pstmtSelectAktivitaetsNamen;
    private static PreparedStatement pstmtInsertAktivitaetsNamen;
    private static PreparedStatement pstmtUpdateAktivitaetsNamen;
    private static PreparedStatement pstmtDeleteAktivitaetsNamen;

    private static HashMap<AktivitaetsEintrag, String> idListe;
    private static HashMap<Integer, String> idListeName;

    /**
     * Initialisierungsblock
     * Wird ausgeführt wenn die Klasse erzeugt wird
     */
    static {
        System.out.println("static-Block ausgeführt");

        // Statements vorbereiten
        pstmtSelectAktivitaet = Datenbank.getInstance().prepareStatement("SELECT ErstellungsDatum, AktivitaetsName, StartDatum, EndDatum, VerbrauchteZeit, Kategorie, Prioritaet, Status FROM todoliste;");
        pstmtInsertAktivitaet = Datenbank.getInstance().prepareStatement("INSERT INTO todoliste (ErstellungsDatum, AktivitaetsName, StartDatum, EndDatum, VerbrauchteZeit, Kategorie, Prioritaet, Status) VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
        pstmtUpdateAktivitaet = Datenbank.getInstance().prepareStatement("UPDATE todoliste SET ErstellungsDatum = ?, AktivitaetsName = ?, StartDatum = ?, EndDatum = ?, VerbrauchteZeit = ?, Kategorie = ?, Prioritaet = ?, Status = ? WHERE ErstellungsDatum = ?;");
        pstmtDeleteAktivitaet = Datenbank.getInstance().prepareStatement("DELETE FROM todoliste WHERE ErstellungsDatum = ?;");

        pstmtSelectAktivitaetsNamen = Datenbank.getInstance().prepareStatement("SELECT AktivitaetsName FROM aktivitaetsname;");
        pstmtInsertAktivitaetsNamen = Datenbank.getInstance().prepareStatement("INSERT INTO aktivitaetsname (AktivitaetsName) VALUES (?);");
        pstmtUpdateAktivitaetsNamen = Datenbank.getInstance().prepareStatement("UPDATE aktivitaetsname SET AktivitaetsName = ? WHERE AktivitaetsName = ?;");
        pstmtDeleteAktivitaetsNamen = Datenbank.getInstance().prepareStatement("DELETE FROM aktivitaetsname WHERE AktivitaetsName = ?;");

        idListe = new HashMap<>();
        idListeName = new HashMap<>();
    }

    /**
     * Laedt die gesammte ToDoListe aus der Datenbank und gibt sie alls Liste von AktivitaetsEintraegen zurueck
     *
     * @return Liste mit allen AktivitaetsEintraegen
     * @throws IllegalArgumentException wird geworfen, wenn intern eine SQL- oder ClassNotFoundException aufgetreten ist.
     */
    public static ArrayList<AktivitaetsEintrag> getAktivitaeten() {
        ArrayList<AktivitaetsEintrag> result = null;

        try {
            // Datenbankabfrage ausführen
            ResultSet rs = pstmtSelectAktivitaet.executeQuery();

            // Result initialisieren
            result = new ArrayList<>();

            // Zurücksetzen der idListe
            idListe.clear();

            // Alle Datensätze abfragen und passend dazu neue Einträge generieren
            while (rs.next()) {
                AktivitaetsEintrag eintrag = new AktivitaetsEintrag(
                        rs.getString("ErstellungsDatum"),
                        rs.getString("AktivitaetsName"),
                        rs.getString("StartDatum"),
                        rs.getString("EndDatum"),
                        rs.getInt("VerbrauchteZeit"),
                        rs.getString("Kategorie"),
                        rs.getString("Prioritaet"),
                        rs.getString("Status")
                );
                result.add(eintrag);

                // Objekt der idListe hinzufügen
                idListe.put(eintrag, eintrag.getErstellungsDatum());
            }

        } catch (SQLException ignored) {}

        return result;
    }

    /**
     * Speichert einen übergebenen AktivitaetsEintrag in der Datenbank. Ob der Eintrag
     * in der ToDoListe schon vorhanden ist oder nicht, also ob ein update oder ein
     * insert-Befehl für die Datenbank ausgeführt werden muss, ist für den Aufruf von der GUI
     * irrelevant. Dies findet diese Methode heraus.
     *
     * @param zuSpeichern AktivitaetsEintrag, der gespeichert werden soll
     * @return true, wenn die Speicherung erfolgreich war, false andernfalls
     */
    public static boolean saveAktivitaet(AktivitaetsEintrag zuSpeichern) {
        boolean result = false;

        try {
            PreparedStatement pstmt;

            // Entscheiden, ob INSERT oder UPDATE
            String id = idListe.get(zuSpeichern);

            if (id == null) {
                // INSERT
                pstmt = pstmtInsertAktivitaet;
            } else {
                // UPDATE
                pstmt = pstmtUpdateAktivitaet;
                pstmt.setString(9, id);
            }

            // Das PreparedStatement mit Informationen füttern
            pstmt.setString(1, zuSpeichern.getErstellungsDatum());
            pstmt.setString(2, zuSpeichern.getAktivitaetsName());
            pstmt.setString(3, zuSpeichern.getStartDatum());
            pstmt.setString(4, zuSpeichern.getEndDatum());
            pstmt.setInt(5, zuSpeichern.getVerbrauchteZeit());
            pstmt.setString(6, zuSpeichern.getKategorie());
            pstmt.setString(7, zuSpeichern.getPrioritaet());
            pstmt.setString(8, zuSpeichern.getStatus());

            // Ausführen von Insert oder Update
            pstmt.executeUpdate();
            result = true;

            // Neuen oder geänderten Datensatz in der idListe aktualisieren
            idListe.put(zuSpeichern, zuSpeichern.getErstellungsDatum());

            Datenbank.getInstance().commit();

        } catch (SQLException e) {
            try {
                Datenbank.getInstance().rollback();
            } catch (SQLException ignored) {}
            e.printStackTrace();
            throw new IllegalArgumentException("Fehler beim Speicher der Aktivitaet in die Datenbank");
        }

        return result;
    }

    /**
     * Löscht einen übergebenen TelefonbuchEintrag aus der Datenbank
     *
     * @param zuLoeschen TelefonbuchEintrag, der gelöscht werden soll
     * @return true, wenn das Löschen erfolgreich war, false andernfalls
     */
    public static boolean deleteAktivitaet(AktivitaetsEintrag zuLoeschen) {
        // Ist der Eintrag überhaupt in der Datenbank?
        if (idListe.get(zuLoeschen) == null) {
            // Der Eintrag ist nicht in der Datenbank enthalten und daher war das Löschen erfolgreich
            return true;
        }

        boolean result = false;

        try {
            pstmtDeleteAktivitaet.setString(1, zuLoeschen.getErstellungsDatum());
            pstmtDeleteAktivitaet.executeUpdate();
            result = true;

            Datenbank.getInstance().commit();

        } catch (SQLException e) {
            System.err.println("Fehler beim Löschen der Aktivitaet aus der Datenbank: " + e.getLocalizedMessage());
        }

        return result;
    }


    /**
     * Laedt die gesammte AktivitaetsNamenTabelle aus der Datenbank und gibt sie alls Liste von AktivitaetsNamen zurueck
     *
     * @return Liste mit allen AktivitaetsNamen
     * @throws IllegalArgumentException wird geworfen, wenn intern eine SQL- oder ClassNotFoundException aufgetreten ist.
     */
    public static ArrayList<String> getAktivitaetsNamen() {
        ArrayList<String> result = null;

        try {
            // Datenbankabfrage ausführen
            ResultSet rs = pstmtSelectAktivitaetsNamen.executeQuery();
            String eintrag;
            int i = 0;

            // Result initialisieren
            result = new ArrayList<>();

            // Zurücksetzen der idListe
            idListeName.clear();

            // Alle Datensätze abfragen und passend dazu neue Einträge generieren
            while (rs.next()) {

                eintrag = rs.getString("AktivitaetsName");
                result.add(eintrag);

                //Objekt der idListeName hinzufügen
                idListeName.put(i, eintrag);
                i++;
            }

        } catch (SQLException ignored) {}

        return result;
    }

    /**
     * Speichert einen übergebenen AktivitaetsNamen in der Datenbank. Ob der Eintrag
     * in der AktivitaetsNamenTabelle schon vorhanden ist oder nicht, also ob ein update oder ein
     * insert-Befehl für die Datenbank ausgeführt werden muss, ist für den Aufruf von der GUI
     * irrelevant. Dies findet diese Methode heraus.
     *
     * @param zuSpeichern AktivitaetsNamen, der gespeichert werden soll
     * @return true, wenn die Speicherung erfolgreich war, false andernfalls
     */
    public static boolean saveAktivitaetsNamen(String zuSpeichern) {
        boolean result = false;

        try {
            PreparedStatement pstmt;

            // Entscheiden, ob INSERT oder UPDATE
            String id = idListeName.get(zuSpeichern);

            if (id == null) {
                // INSERT
                pstmt = pstmtInsertAktivitaetsNamen;
            } else {
                // UPDATE
                pstmt = pstmtUpdateAktivitaetsNamen;
                pstmt.setString(2, id);
            }

            // Das PreparedStatement mit Informationen füttern
            pstmt.setString(1, zuSpeichern);

            // Ausführen von Insert oder Update
            pstmt.executeUpdate();
            result = true;

            // Neuen oder geänderten Datensatz in der idListe aktualisieren
            //idListeName.put(zuSpeichern, zuSpeichern);

            Datenbank.getInstance().commit();

        } catch (SQLException e) {
            try {
                Datenbank.getInstance().rollback();
            } catch (SQLException ignored) {}
            e.printStackTrace();
            throw new IllegalArgumentException("Fehler beim Speicher der Aktivitaet in die Datenbank");
        }

        return result;
    }

    /**
     * Löscht einen übergebenen TelefonbuchEintrag aus der Datenbank
     *
     * @param zuLoeschen TelefonbuchEintrag, der gelöscht werden soll
     * @return true, wenn das Löschen erfolgreich war, false andernfalls
     */
    /*public static boolean deleteAktivitaetsNamen(AktivitaetsEintrag zuLoeschen) {
        // Ist der Eintrag überhaupt in der Datenbank?
        if (idListe.get(zuLoeschen) == null) {
            // Der Eintrag ist nicht in der Datenbank enthalten und daher war das Löschen erfolgreich
            return true;
        }

        boolean result = false;

        try {
            pstmtDeleteAktivitaet.setString(1, zuLoeschen.getErstellungsDatum());
            pstmtDeleteAktivitaet.executeUpdate();
            result = true;

            Datenbank.getInstance().commit();

        } catch (SQLException e) {
            System.err.println("Fehler beim Löschen der Aktivitaet aus der Datenbank: " + e.getLocalizedMessage());
        }

        return result;
    }*/
}
