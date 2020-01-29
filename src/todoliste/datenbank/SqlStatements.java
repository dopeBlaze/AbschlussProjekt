package todoliste.datenbank;

public class SqlStatements {

    // Aktuelle Version
    public final static int VERSION = 1;

    // Befehle zum Setzen und Auslesen der Version
    public final static String GET_VERSION = "PRAGMA USER_VERSION;";
    public final static String SET_VERSION = "PRAGMA USER_VERSION = ";

    // +-------------------------------------------------------------------------------------------------------------------------+
    // |                                                  VERSION 1																 |
    // +-------------------------------------------------------------------------------------------------------------------------+

    // Erstellen der Tabellen
    public static final String CREATE_TABLE_AKTIVITAETSNAME = "CREATE TABLE \"table_aktivitaetsname\" (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, aktivitaetsname VARCHAR(255) NOT NULL UNIQUE);";
    public static final String CREATE_TABLE_KATEGORIE = "CREATE TABLE \"table_kategorie\" (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, kategoriename VARCHAR(255) NOT NULL UNIQUE);";
    public static final String CREATE_TABLE_PRIORITAET = "CREATE TABLE \"table_prioritaet\" (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, prioritaetname VARCHAR(255) NOT NULL UNIQUE);";
    public static final String CREATE_TABLE_STATUS = "CREATE TABLE \"table_status\" (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, statusname VARCHAR(255) NOT NULL UNIQUE);";
    public static final String CREATE_TABLE_TODOLISTE = "CREATE TABLE \"table_todoliste\" ( "
            + "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
            + "erstellungsdatum DATETIME NOT NULL UNIQUE, "
            + "aktivitaetsname_id INTEGER NOT NULL, "
            + "startdatum DATETIME NOT NULL, "
            + "enddatum DATETIME NOT NULL, "
            + "verbrauchtezeit VARCHAR(255), "
            + "kategorie_id INTEGER NOT NULL, "
            + "prioritaet_id INTEGER NOT NULL, "
            + "status_id INTEGER NOT NULL, "
            + "FOREIGN KEY (aktivitaetsname_id) REFERENCES aktivitaetsname(id), "
            + "FOREIGN KEY (kategorie_id) REFERENCES kategorie(id), "
            + "FOREIGN KEY (prioritaet_id) REFERENCES prioritaet(id), "
            + "FOREIGN KEY (status_id) REFERENCES status(id)"
            + ");";
    public static final String ACTIVATE_FOREIGN_KEYS = "PRAGMA foreign_keys = ON;";

    // Erstellen der Sicht
    static final String CREATE_VIEW_TODOLISTE = "CREATE VIEW todoliste AS "
            + "SELECT t.erstellungsdatum AS ErstellungsDatum, "
            + "a.aktivitaetsname AS Aktivitaetsname, "
            + "t.startdatum AS StartDatum, "
            + "t.enddatum AS EndDatum, "
            + "t.verbrauchtezeit AS VerbrauchteZeit, "
            + "k.kategoriename AS Kategorie, "
            + "p.prioritaetname AS Prioritaet, "
            + "s.statusname AS Status "
            + "FROM table_todoliste t "
            + "INNER JOIN table_aktivitaetsname a ON t.aktivitaetsname_id = a.id "
            + "INNER JOIN table_kategorie k ON t.kategorie_id = k.id "
            + "INNER JOIN table_prioritaet p ON t.prioritaet_id = p.id "
            + "INNER JOIN table_status s ON t.status_id = s.id;";


    /*
    // Erzeugung der Telefonbuch-Tabelle
    public final static String CREATE_TELEFONBUCH = "CREATE TABLE IF NOT EXISTS telefonbuch(" +
            "  vorname VARCHAR(50) NOT NULL," +
            "  nachname VARCHAR(50) NOT NULL," +
            "  strasse VARCHAR(50) NOT NULL," +
            "  hausnr VARCHAR(10) NOT NULL," +
            "  plz CHAR(5) NOT NULL," +
            "  ort VARCHAR(50) NOT NULL," +
            "  telefonnr VARCHAR(30) NOT NULL UNIQUE" +
            ");";
     */

    // Erstellen der Trigger
    public static final String CREATE_TRIGGER_TODOLISTE_INS = "CREATE TRIGGER ToDoListe_INS INSTEAD OF INSERT ON todoliste FOR EACH ROW\n"
            +   "BEGIN\n"
            +   "\n"
            +   "INSERT INTO table_aktivitaetsname (aktivitaetsname) SELECT NEW.Aktivitaetsname WHERE NOT EXISTS (SELECT 1 FROM table_aktivitaetsname WHERE aktivitaetsname = NEW.Aktivitaetsname);\n"
            +   "INSERT INTO table_kategorie (kategoriename) SELECT NEW.Kategorie WHERE NOT EXISTS (SELECT 1 FROM table_kategorie WHERE kategoriename = NEW.Kategorie);\n"
            +   "INSERT INTO table_prioritaet (prioritaetname) SELECT NEW.Prioritaet WHERE NOT EXISTS (SELECT 1 FROM table_prioritaet WHERE prioritaetname = NEW.Prioritaet);\n"
            +   "INSERT INTO table_status (statusname) SELECT NEW.Status WHERE NOT EXISTS (SELECT 1 FROM table_status WHERE statusname = NEW.Status);\n"
            +   "\n"
            +   "INSERT INTO table_todoliste (erstellungsdatum, aktivitaetsname_id, startdatum, enddatum, verbrauchtezeit, kategorie_id, prioritaet_id, status_id) "
            +       "SELECT "
            +       "NEW.ErstellungsDatum, "
            +       "(SELECT id FROM table_aktivitaetsname WHERE aktivitaetsname = NEW.Aktivitaetsname), "
            +       "NEW.StartDatum, "
            +       "NEW.EndDatum, "
            +       "NEW.VerbrauchteZeit, "
            +       "(SELECT id FROM table_kategorie WHERE kategoriename = NEW.Kategorie), "
            +       "(SELECT id FROM table_prioritaet WHERE prioritaetname = NEW.Prioritaet), "
            +       "(SELECT id FROM table_status WHERE statusname = NEW.Status);"
            +   "\n"
            +   "END;";

    public static final String CREATE_TRIGGER_TODOLISTE_DEL = "CREATE TRIGGER ToDoListe_DEL INSTEAD OF DELETE ON todoliste FOR EACH ROW\n"
            +   "BEGIN\n"
            +   "\n"
            +   "DELETE FROM table_todoliste WHERE erstellungsdatum = OLD.ErstellungsDatum;\n"
            +   "\n"
            +   "DELETE FROM table_aktivitaetsname WHERE id NOT IN (SELECT aktivitaetsname_id FROM table_todoliste);\n"
            +   "DELETE FROM table_kategorie WHERE id NOT IN (SELECT kategorie_id FROM table_todoliste);\n"
            +   "DELETE FROM table_prioritaet WHERE id NOT IN (SELECT prioritaet_id FROM table_todoliste);\n"
            +   "DELETE FROM table_status WHERE id NOT IN (SELECT status_id FROM table_todoliste);\n"
            +   "\n"
            +   "END;";

    public static final String CREATE_TRIGGER_TODOLISTE_UPD = "CREATE TRIGGER ToDoListe_UPD INSTEAD OF UPDATE ON todoliste FOR EACH ROW\n"
            +   "BEGIN\n"
            +   "\n"
            +   "INSERT INTO table_aktivitaetsname (aktivitaetsname) SELECT NEW.Aktivitaetsname WHERE NOT EXISTS (SELECT 1 FROM table_aktivitaetsname WHERE aktivitaetsname = NEW.Aktivitaetsname);\n"
            +   "INSERT INTO table_kategorie (kategoriename) SELECT NEW.Kategorie WHERE NOT EXISTS (SELECT 1 FROM table_kategorie WHERE kategoriename = NEW.Kategorie);\n"
            +   "INSERT INTO table_prioritaet (prioritaetname) SELECT NEW.Prioritaet WHERE NOT EXISTS (SELECT 1 FROM table_prioritaet WHERE prioritaetname = NEW.Prioritaet);\n"
            +   "INSERT INTO table_status (statusname) SELECT NEW.Status WHERE NOT EXISTS (SELECT 1 FROM table_status WHERE statusname = NEW.Status);\n"
            +   "\n"
            +   "UPDATE table_todoliste SET "
            +   "erstellungsdatum = NEW.ErstellungsDatum, "
            +   "aktivitaetsname_id = (SELECT id FROM table_aktivitaetsname WHERE aktivitaetsname = NEW.Aktivitaetsname), "
            +   "startdatum = NEW.StartDatum, "
            +   "enddatum = NEW.EndDatum, "
            +   "verbrauchtezeit = NEW.VerbrauchteZeit, "
            +   "kategorie_id = (SELECT id FROM table_kategorie WHERE kategoriename = NEW.Kategorie), "
            +   "prioritaet_id = (SELECT id FROM table_prioritaet WHERE prioritaetname = NEW.Prioritaet), "
            +   "status_id = (SELECT id FROM table_status WHERE statusname = NEW.Status)"
            +   "WHERE erstellungsdatum = OLD.ErstellungsDatum;"
            +   "\n"
            +   "\n"
            +   "DELETE FROM table_aktivitaetsname WHERE id NOT IN (SELECT aktivitaetsname_id FROM table_todoliste);\n"
            +   "DELETE FROM table_kategorie WHERE id NOT IN (SELECT kategorie_id FROM table_todoliste);\n"
            +   "DELETE FROM table_prioritaet WHERE id NOT IN (SELECT prioritaet_id FROM table_todoliste);\n"
            +   "DELETE FROM table_status WHERE id NOT IN (SELECT status_id FROM table_todoliste);\n"
            +   "\n"
            +   "END;";
}
