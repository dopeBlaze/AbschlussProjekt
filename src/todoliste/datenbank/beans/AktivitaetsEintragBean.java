package todoliste.datenbank.beans;

import todoliste.datenbank.Datenbank;
import todoliste.model.AktivitaetsEintrag;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Beans zur Kommunikation zwischen Controller und Datenbank
 */
public class AktivitaetsEintragBean {

    // Cast ArrayList in ObservableList
    // ObservableList<AktivitaetsEintrag> oListAktivitaeten = FXCollections.observableArrayList(arrayListAktivitaeten);

    private static PreparedStatement pstmtSelectAktivitaet;
    private static PreparedStatement pstmtInsertAktivitaet;
    private static PreparedStatement pstmtUpdateAktivitaet;
    private static PreparedStatement pstmtDeleteAktivitaet;
    private static PreparedStatement pstmtSelectAktivitaetsNamen;
    private static PreparedStatement pstmtInsertAktivitaetsName;
    private static PreparedStatement pstmtUpdateAktivitaetsName;
    private static PreparedStatement pstmtDeleteAktivitaetsName;
    private static PreparedStatement pstmtSelectAktivitaetSingle;

    private static HashMap<AktivitaetsEintrag, String> idListe;
    private static HashMap<AktivitaetsEintrag, String> idListeName;

    /**
     * Initialisierungsblock
     * Wird ausgefuehrt wenn die Klasse erzeugt wird
     */
    static {
        System.out.println("static-Block ausgeführt");

        // Statements vorbereiten
        pstmtSelectAktivitaet = Datenbank.getInstance().prepareStatement("SELECT ErstellungsDatum, AktivitaetsName, StartDatum, EndDatum, VerbrauchteZeit, Kategorie, Prioritaet, Status FROM todoliste;");
        pstmtInsertAktivitaet = Datenbank.getInstance().prepareStatement("INSERT INTO todoliste (ErstellungsDatum, AktivitaetsName, StartDatum, EndDatum, VerbrauchteZeit, Kategorie, Prioritaet, Status) VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
        pstmtUpdateAktivitaet = Datenbank.getInstance().prepareStatement("UPDATE todoliste SET ErstellungsDatum = ?, AktivitaetsName = ?, StartDatum = ?, EndDatum = ?, VerbrauchteZeit = ?, Kategorie = ?, Prioritaet = ?, Status = ? WHERE ErstellungsDatum = ?;");
        pstmtDeleteAktivitaet = Datenbank.getInstance().prepareStatement("DELETE FROM todoliste WHERE ErstellungsDatum = ?;");

        pstmtSelectAktivitaetsNamen = Datenbank.getInstance().prepareStatement("SELECT AktivitaetsName FROM aktivitaetsname;");
        pstmtInsertAktivitaetsName = Datenbank.getInstance().prepareStatement("INSERT INTO aktivitaetsname (AktivitaetsName) VALUES (?);");
        // Einfuegen von doppelten UNIQUE Eintraegen wird abgefangen
        // pstmtInsertAktivitaetsName = Datenbank.getInstance().prepareStatement("INSERT INTO aktivitaetsname (AktivitaetsName) SELECT AktivitaetsName FROM aktivitaetsname WHERE NOT EXISTS (SELECT 1 FROM table_aktivitaetsname WHERE aktivitaetsname = ?);");
        pstmtUpdateAktivitaetsName = Datenbank.getInstance().prepareStatement("UPDATE aktivitaetsname SET AktivitaetsName = ? WHERE AktivitaetsName = ?;");
        //pstmtDeleteAktivitaetsName = Datenbank.getInstance().prepareStatement("DELETE FROM aktivitaetsname WHERE AktivitaetsName = ?;");
        // Loeschen nicht moeglich wenn id von Aktivitaetsname in Benutzung
        pstmtDeleteAktivitaetsName = Datenbank.getInstance().prepareStatement("DELETE FROM table_aktivitaetsname WHERE AktivitaetsName = ? AND id NOT IN (SELECT aktivitaetsname_id FROM table_todoliste);");

        pstmtSelectAktivitaetSingle = Datenbank.getInstance().prepareStatement("SELECT ErstellungsDatum, AktivitaetsName, StartDatum, EndDatum, VerbrauchteZeit, Kategorie, Prioritaet, Status FROM todoliste WHERE ErstellungsDatum = ?;");

        idListe = new HashMap<>();
        idListeName = new HashMap<>();
    }

    /**
     * Laedt die gesammte ToDoListe aus der Datenbank und gibt sie als Liste von AktivitaetsEintraegen zurueck
     *
     * @return Liste mit allen AktivitaetsEintraegen
     * @throws IllegalArgumentException wird geworfen, wenn intern eine SQL- oder ClassNotFoundException aufgetreten ist.
     */
    public static ArrayList<AktivitaetsEintrag> getAktivitaeten() {
        ArrayList<AktivitaetsEintrag> result = null;

        try {
            // Datenbankabfrage ausfuehren
            ResultSet rs = pstmtSelectAktivitaet.executeQuery();

            // Result initialisieren
            result = new ArrayList<>();

            // Zuruecksetzen der idListe
            idListe.clear();

            // Alle Datensaetze abfragen und passend dazu neue Eintraege generieren
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

                // Objekt der idListe hinzufuegen
                idListe.put(eintrag, eintrag.getErstellungsDatum());
            }

        } catch (SQLException ignored) {}

        return result;
    }

    /**
     * Speichert einen uebergebenen AktivitaetsEintrag in der Datenbank. Ob der Eintrag
     * in der ToDoListe schon vorhanden ist oder nicht, also ob ein Update oder ein
     * insert-Befehl fuer die Datenbank ausgefuehrt werden muss, ist fuer den Aufruf von der GUI
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
                zuSpeichern.setErstellungsDatum(LocalDateTime.now().toString());
                pstmt = pstmtInsertAktivitaet;
            } else {
                // UPDATE
                pstmt = pstmtUpdateAktivitaet;
                pstmt.setString(9, id);
            }

            // Das PreparedStatement mit Informationen fuettern
            pstmt.setString(1, zuSpeichern.getErstellungsDatum());
            pstmt.setString(2, zuSpeichern.getAktivitaetsName());
            pstmt.setString(3, zuSpeichern.getStartDatum());
            pstmt.setString(4, zuSpeichern.getEndDatum());
            pstmt.setInt(5, zuSpeichern.getVerbrauchteZeit());
            pstmt.setString(6, zuSpeichern.getKategorie());
            pstmt.setString(7, zuSpeichern.getPrioritaet());
            pstmt.setString(8, zuSpeichern.getStatus());

            // Ausfuehren von Insert oder Update
            pstmt.executeUpdate();
            result = true;

            // Neuen oder geaenderten Datensatz in der idListe aktualisieren
            idListe.put(zuSpeichern, zuSpeichern.getErstellungsDatum());

            Datenbank.getInstance().commit();

        } catch (SQLException e) {
            try {
                Datenbank.getInstance().rollback();
            } catch (SQLException ignored) {}
            e.printStackTrace();
            throw new IllegalArgumentException("Fehler beim Speichern der Aktivität in die Datenbank");
        }

        return result;
    }

    /**
     * Loescht einen uebergebenen AktivitaetsEintrag aus der Datenbank
     *
     * @param zuLoeschen AktivitaetsEintrag, der geloescht werden soll
     * @return true, wenn das Loeschen erfolgreich war, false andernfalls
     */
    public static boolean deleteAktivitaet(AktivitaetsEintrag zuLoeschen) {
        // Ist der Eintrag ueberhaupt in der Datenbank?
        if (idListe.get(zuLoeschen) == null) {
            // Der Eintrag ist nicht in der Datenbank enthalten und daher war das Loeschen erfolgreich
            return true;
        }

        boolean result = false;

        try {
            pstmtDeleteAktivitaet.setString(1, zuLoeschen.getErstellungsDatum());
            pstmtDeleteAktivitaet.executeUpdate();
            result = true;

            Datenbank.getInstance().commit();

        } catch (SQLException e) {
            System.err.println("Fehler beim Löschen der Aktivität aus der Datenbank: " + e.getLocalizedMessage());
        }

        return result;
    }


    /**
     * Laedt die gesammte AktivitaetsNamenTabelle aus der Datenbank und gibt sie alls Liste von AktivitaetsNamen zurueck
     *
     * @return Liste mit allen AktivitaetsNamen
     * @throws IllegalArgumentException wird geworfen, wenn intern eine SQL- oder ClassNotFoundException aufgetreten ist.
     */
    public static ArrayList<AktivitaetsEintrag> getAktivitaetsNamen() {
        ArrayList<AktivitaetsEintrag> result = null;

        try {
            // Datenbankabfrage ausfuehren
            ResultSet rs = pstmtSelectAktivitaetsNamen.executeQuery();

            // Result initialisieren
            result = new ArrayList<>();

            // Zuruecksetzen der idListe
            idListeName.clear();

            // Alle Datensaetze abfragen und passend dazu neue Eintraege generieren
            while (rs.next()) {
                AktivitaetsEintrag eintrag = new AktivitaetsEintrag(
                    rs.getString("AktivitaetsName")
                );
                result.add(eintrag);

                //Objekt der idListeName hinzufuegen
                idListeName.put(eintrag, eintrag.getAktivitaetsName());
            }

        } catch (SQLException ignored) {}

        return result;
    }

    /**
     * Speichert einen uebergebenen AktivitaetsNamen in der Datenbank. Ob der Eintrag
     * in der AktivitaetsNamenTabelle schon vorhanden ist oder nicht, also ob ein Update oder ein
     * insert-Befehl fuer die Datenbank ausgefuehrt werden muss, ist fuer den Aufruf von der GUI
     * irrelevant. Dies findet diese Methode heraus.
     *
     * @param zuSpeichern AktivitaetsName, der gespeichert werden soll
     * @return true, wenn die Speicherung erfolgreich war, false andernfalls
     */
    public static boolean saveAktivitaetsName(AktivitaetsEintrag zuSpeichern) {
        boolean result = false;

        try {
            PreparedStatement pstmt;

            // Entscheiden, ob INSERT oder UPDATE
            String id = idListeName.get(zuSpeichern);

            if (id == null) {
                // INSERT
                pstmt = pstmtInsertAktivitaetsName;
            } else {
                // UPDATE
                // Nutzerhinweis ob der neue Name schon vergeben ist uebernimmt der Controller
                pstmt = pstmtUpdateAktivitaetsName;
                pstmt.setString(2, id);
            }

            // Das PreparedStatement mit Informationen fuettern
            pstmt.setString(1, zuSpeichern.getAktivitaetsName());

            // Ausfuehren von Insert oder Update
            pstmt.executeUpdate();
            result = true;

            // Neuen oder geaenderten Datensatz in der idListe aktualisieren
            idListeName.put(zuSpeichern, zuSpeichern.getAktivitaetsName());

            Datenbank.getInstance().commit();

        } catch (SQLException e) {
            System.err.println("Fehler beim Löschen vom Aktivitätsnamen aus der Datenbank: " + e.getLocalizedMessage());
            try {
                Datenbank.getInstance().rollback();
            } catch (SQLException ignored) {}
            //e.printStackTrace();
            throw new IllegalArgumentException("Fehler beim Speichern der Aktivität in die Datenbank");
        }

        return result;
    }

    /**
     * Loescht einen uebergebenen AktivitaetsNamen aus der Datenbank
     *
     * @param zuLoeschen AktivitaetsName, der geloescht werden soll
     * @return true, wenn das Loeschen erfolgreich war, false andernfalls
     */
    public static boolean deleteAktivitaetsName(AktivitaetsEintrag zuLoeschen) {
        // Ist der Eintrag ueberhaupt in der Datenbank?
        if (idListeName.get(zuLoeschen) == null) {
            // Der Eintrag ist nicht in der Datenbank enthalten und daher war das Loeschen erfolgreich
            return true;
        }

        boolean result = false;

        try {
            pstmtDeleteAktivitaetsName.setString(1, zuLoeschen.getAktivitaetsName());
            pstmtDeleteAktivitaetsName.executeUpdate();
            result = true;

            Datenbank.getInstance().commit();

        } catch (SQLException e) {
            System.err.println("Fehler beim Löschen vom Aktivitätsnamen aus der Datenbank: " + e.getLocalizedMessage());
        }

        return result;
    }

    /**
     * Laedt die gesammte ToDoListe aus der Datenbank und gibt sie alls Liste von AktivitaetsEintraegen zurueck
     *
     * @return Liste mit allen AktivitaetsEintraegen
     * @throws IllegalArgumentException wird geworfen, wenn intern eine SQL- oder ClassNotFoundException aufgetreten ist.
     */
    public static ArrayList<AktivitaetsEintrag> getAktivitaetSingle(AktivitaetsEintrag zuBearbeiten) {
        ArrayList<AktivitaetsEintrag> result = null;

        try {
            // Datenbankabfrage ausfuehren
            pstmtSelectAktivitaetSingle.setString(1, zuBearbeiten.getErstellungsDatum());
            ResultSet rs = pstmtSelectAktivitaetSingle.executeQuery();

            // Result initialisieren
            result = new ArrayList<>();

            // Zuruecksetzen der idListe
            idListe.clear();

            // Alle Datensaetze abfragen und passend dazu neue Eintraege generieren
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

                // Objekt der idListe hinzufuegen
                idListe.put(eintrag, eintrag.getErstellungsDatum());
            }

        } catch (SQLException ignored) {}

        return result;
    }

    /**
     * Speichert einen uebergebenen AktivitaetsEintrag in der Datenbank.
     *
     * @param zuSpeichern AktivitaetsEintrag, der gespeichert werden soll
     * @return true, wenn die Speicherung erfolgreich war, false andernfalls
     */
    public static boolean saveAktivitaetSingle(AktivitaetsEintrag zuSpeichern) {
        boolean result = false;

        try {
            PreparedStatement pstmt;

            // UPDATE
            pstmt = pstmtUpdateAktivitaet;

            // Das PreparedStatement mit Informationen fuettern
            pstmt.setString(1, zuSpeichern.getErstellungsDatum());
            pstmt.setString(2, zuSpeichern.getAktivitaetsName());
            pstmt.setString(3, zuSpeichern.getStartDatum());
            pstmt.setString(4, zuSpeichern.getEndDatum());
            pstmt.setInt(5, zuSpeichern.getVerbrauchteZeit());
            pstmt.setString(6, zuSpeichern.getKategorie());
            pstmt.setString(7, zuSpeichern.getPrioritaet());
            pstmt.setString(8, zuSpeichern.getStatus());
            pstmt.setString(9, zuSpeichern.getErstellungsDatum());

            // Ausfuehren von Update
            pstmt.executeUpdate();
            result = true;

            Datenbank.getInstance().commit();

        } catch (SQLException e) {
            try {
                Datenbank.getInstance().rollback();
            } catch (SQLException ignored) {}
            e.printStackTrace();
            throw new IllegalArgumentException("Fehler beim Ändern der Aktivitaet in die Datenbank");
        }

        return result;
    }
}
