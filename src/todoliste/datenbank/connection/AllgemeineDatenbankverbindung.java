package todoliste.datenbank.connection;

import java.sql.*;
import java.util.ArrayList;

public abstract class AllgemeineDatenbankverbindung {

    /**
     * Connection-Objekt, welches die Datenbankverbindung enthaelt
     */
    protected Connection con;

    /**
     * Liste aller erzeugten PreparedStatements
     */
    private ArrayList<PreparedStatement> pstmtList;

    /**
     * Konstruktor
     * Initialisiert die interne Liste der PreparedStatements
     */
    public AllgemeineDatenbankverbindung() {
        pstmtList = new ArrayList<>();
    }

    /**
     * Diese Methode dient der Erstellung der Verbindung zur Datenbank. Diese MUSS das
     * Connection-Objekt 'con' korrekt setzen.
     *
     * Empfehlung: AutoCommit auf false setzen
     *
     * @return true bei erfolgreicher Verbindung zur Datenbank, false andernfalls.
     */
    public abstract boolean connect();

    /**
     * Trennt die Verbindung zur Datenbank.
     */
    public void disconnect() {
        if (con != null) {

            // Schliessen aller PreparedStatements
            for (PreparedStatement pstmt : pstmtList) {
                try {
                    pstmt.close();

                    /* Sollte das Statement schon geschlossen sein, wird eine
                     * Fehlermeldung geworfen. Diese wird ignoriert und mit dem
                     * naechsten weitergemacht
                     */
                } catch (SQLException ignored) {}
            }

            // Loeschen aller Referenzen zu den PreparedStatements
            pstmtList.clear();

            // Datenbankverbindung trennen
            try {
                con.close();
            } catch (SQLException ignored) {
            } finally {
                con = null;
            }
        }
    }

    /**
     * Fuehrt ein Commit aus. Alle noch offenen Transaktionen werden in die Datenbank geschrieben.
     *
     * @throws SQLException wird geworfen, wenn ein Fehler beim Commit auftritt oder keine korrekte Verbindung zur Datenbank besteht
     */
    public void commit() throws SQLException {
        if (con != null) {
            con.commit();
        }
    }

    /**
     * Führt ein Rollback aus. Alle noch offenen Transaktionen werden rückgängig gemacht.
     *
     * @throws SQLException wird geworfen, wenn ein Fehler beim Rollback auftritt oder keine korrekte Verbindung zur Datenbank besteht
     */
    public void rollback() throws SQLException {
        if (con != null) {
            con.rollback();
        }
    }

    /**
     * Fuehrt ein beliebiges SQL-Statement aus, und gibt etwaiige Fehler auf der Fehlerausgabe aus
     * @param sql SQL-Statement welches ausgefuehrt werden soll
     * @return gibt true zurueck, wenn der Befehl ohne Fehler ausgefuehrt wurde, und false im Fehlerfall
     */
    public boolean execute(String sql) {
        if (con == null) {
            System.err.println("Datenbankverbindung geschlossen!");
            return false;
        } else {
            try {
                Statement stmt = con.createStatement();
                stmt.execute(sql);
                stmt.close();
            } catch (SQLException e) {
                System.err.println("Fehler beim Ausführen des folgenden Statements: " + sql + "\nFehlermeldung: " + e.getMessage());
                return false;
            }
        }
        return true;
    }

    /**
     * Fuehrt ein beliebiges SELECT-SQL-Statement aus und gibt das ResultSet zurueck
     * @param sql SQL-Statement welches ausgefuehrt werden soll
     * @return ResultSet des Ergebnisses des SELECT-Statements
     * @throws IllegalArgumentException wird geworfen, wenn das Statement nicht korrekt ausgeführt werden konnte
     */
    public ResultSet executeQuery(String sql) {
        if (con == null) {
            throw new IllegalArgumentException("Datenbankverbindung geschlossen!");
        } else {
            try {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                return rs;
            } catch (SQLException e) {
                throw new IllegalArgumentException("Fehler beim Ausführen des folgenden Statements: " + sql + "\nFehlermeldung: " + e.getMessage());
            }
        }
    }

    /**
     * Diese Methode bereitet den uebergebenen SQL-Code als PreparedStatement vor und gibt das erzeugte Objekt zurueck
     * @param sql SQL-Statement
     * @return PreparedStatementzu dem uebergebenen SQL-Code
     * @throws IllegalArgumentException wird geworfen, wenn das Statement nicht korrekt ausgefuehrt werden konnte
     */
    public PreparedStatement prepareStatement(String sql) {
        if (con == null) {
            throw new IllegalArgumentException("Datenbankverbindung geschlossen!");
        } else {
            try {
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmtList.add(pstmt);
                return pstmt;
            } catch (SQLException e) {
                throw new IllegalArgumentException("Fehler beim Ausführen des folgenden Statements: " + sql + "\nFehlermeldung: " + e.getMessage());
            }
        }
    }
}
