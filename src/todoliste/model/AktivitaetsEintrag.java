package todoliste.model;


/**
 * Klassen Model fuer die Aktivitaeten
 */
public class AktivitaetsEintrag {

    private String erstellungsDatum, aktivitaetsName, startDatum, endDatum, kategorie, prioritaet, status;
    private int verbrauchteZeit;

    /**
     * Konstruktor, der alle Attribute mit leeren Werten initialisiert
     * Wichtig: Dieser Konstruktor wird nie benutzt, da niemals leere Werte uebermittelt werden
     */
    public AktivitaetsEintrag() {
        this.erstellungsDatum = "";
        this.aktivitaetsName = "";
        this.startDatum = "";
        this.endDatum = "";
        this.verbrauchteZeit = 0;
        this.kategorie = "";
        this.prioritaet = "";
        this.status = "";
    }

    /**
     * Ueberladener Konstruktor
     * Initialisiert den Konstruktor mit den uebergebenen Parametern, sofern sie ungleich null sind.
     *
     * @param aktivitaetsName zu setzender AktivitaetsName
     */

    public AktivitaetsEintrag(String aktivitaetsName) {
        setAktivitaetsName(aktivitaetsName);
    }


    /**
     * Ueberladener Konstruktor
     * Initialisiert den Konstruktor mit den uebergebenen Parametern, sofern sie ungleich null sind.
     *
     * @param aktivitaetsName zu setzender AktivitaetsName
     * @param startDatum      zu setzender StartDatum
     * @param endDatum        zu setzender EndDatum
     * @param verbrauchteZeit zu setzender VerbrauchteZeit
     * @param kategorie       zu setzender Kategorie
     * @param prioritaet      zu setzender Prioritaet
     * @param status          zu setzender Status
     */

    public AktivitaetsEintrag(String aktivitaetsName, String startDatum, String endDatum, int verbrauchteZeit, String kategorie, String prioritaet, String status) {
        setAktivitaetsName(aktivitaetsName);
        setStartDatum(startDatum);
        setEndDatum(endDatum);
        setVerbrauchteZeit(verbrauchteZeit);
        setKategorie(kategorie);
        setPrioritaet(prioritaet);
        setStatus(status);
    }

    /**
     * Ueberladener Konstruktor
     * Initialisiert den Konstruktor mit den uebergebenen Parametern, sofern sie ungleich null sind.
     *
     * @param erstellungsDatum zu setzendes ErstellungsDatum
     * @param aktivitaetsName zu setzender AktivitaetsName
     * @param startDatum      zu setzender StartDatum
     * @param endDatum        zu setzender EndDatum
     * @param verbrauchteZeit zu setzender VerbrauchteZeit
     * @param kategorie       zu setzender Kategorie
     * @param prioritaet      zu setzender Prioritaet
     * @param status          zu setzender Status
     */

    public AktivitaetsEintrag(String erstellungsDatum, String aktivitaetsName, String startDatum, String endDatum, int verbrauchteZeit, String kategorie, String prioritaet, String status) {
        setErstellungsDatum(erstellungsDatum);
        setAktivitaetsName(aktivitaetsName);
        setStartDatum(startDatum);
        setEndDatum(endDatum);
        setVerbrauchteZeit(verbrauchteZeit);
        setKategorie(kategorie);
        setPrioritaet(prioritaet);
        setStatus(status);
    }

    /**
     * Gibt den Wert vom ErstellungsDatum zurueck
     *
     * @return erstellungsDatum String vom aktuellen ErstellungsDatum
     */
    public String getErstellungsDatum() {
        return erstellungsDatum;
    }

    /**
     * Setzt das ErstellungsDatum auf den uebergebenen Wert
     *
     * @param erstellungsDatum das zu setzende ErstellungsDatum
     * @throws IllegalArgumentException wird geworfen, wenn das ErstellungsDatum keinen Wert uebermittelt bekommt
     */
    public void setErstellungsDatum(String erstellungsDatum) {
        if (erstellungsDatum == null) {
            throw new IllegalArgumentException("ErstellungsDatum fehlt!");
        } else {
            this.erstellungsDatum = erstellungsDatum;
        }
    }

    /**
     * Gibt den Wert von AktivitaetsName zurueck
     *
     * @return aktivitaetsName String von AktivitaetsName
     */
    public String getAktivitaetsName() {
        return aktivitaetsName;
    }

    /**
     * Setzt den AktivitaetsNamen auf den uebergebenen Wert
     *
     * @param aktivitaetsName der zu setzende AktivitaetsName
     * @throws IllegalArgumentException wird geworfen, wenn der AktivitaetsName keinen Wert uebermittelt bekommt
     */
    public void setAktivitaetsName(String aktivitaetsName) {
        if (aktivitaetsName == null) {
            throw new IllegalArgumentException("AktivitaetsName fehlt!");
        } else {
            this.aktivitaetsName = aktivitaetsName;
        }
    }

    /**
     * Gibt den Wert vom StartDatum zurueck
     *
     * @return startDatum String vom StartDatum
     */
    public String getStartDatum() {
        return startDatum;
    }

    /**
     * Setzt das StartDatum auf den uebergebenen Wert
     *
     * @param startDatum das zu setzende StartDatum
     * @throws IllegalArgumentException wird geworfen, wenn das StartDatum keinen Wert uebermittelt bekommt
     */
    public void setStartDatum(String startDatum) {
        if (startDatum == null) {
            throw new IllegalArgumentException("StartDatum fehlt!");
        } else {
            this.startDatum = startDatum;
        }
    }

    /**
     * Gibt den Wert vom EndDatum zurueck
     *
     * @return endDatum String vom EndDatum
     */
    public String getEndDatum() {
        return endDatum;
    }

    /**
     * Setzt das EndDatum auf den uebergebenen Wert
     *
     * @param endDatum das zu setzende EndDatum
     * @throws IllegalArgumentException wird geworfen, wenn das EndDatum keinen Wert uebermittelt bekommt
     */
    public void setEndDatum(String endDatum) {
        if (endDatum == null) {
            throw new IllegalArgumentException("EndDatum fehlt!");
        } else {
            this.endDatum = endDatum;
        }
    }

    /**
     * Gibt den Wert von VerbrauchteZeit zurueck
     *
     * @return verbrauchteZeit int von VerbrauchteZeit
     */
    public int getVerbrauchteZeit() {
        return verbrauchteZeit;
    }

    /**
     * Setzt die VerbrauchteZeit auf den uebergebenen Wert
     *
     * @param verbrauchteZeit die zu setzende VerbrauchteZeit
     * @throws IllegalArgumentException wird geworfen, wenn die VerbrauchteZeit einen negativen Wert uebermittelt bekommt
     */
    public void setVerbrauchteZeit(int verbrauchteZeit) {
        if (verbrauchteZeit < 0) {
            throw new IllegalArgumentException("Die verbrauchte Zeit enthaelt einen negativen Wert!");
        } else {
            this.verbrauchteZeit = verbrauchteZeit;
        }
    }

    /**
     * Gibt den Wert von Kategorie zurueck
     *
     * @return kategorie String von Kategorie
     */
    public String getKategorie() {
        return kategorie;
    }

    /**
     * Setzt die Kategorie auf den uebergebenen Wert
     *
     * @param kategorie die zu setzende Kategorie
     * @throws IllegalArgumentException wird geworfen, wenn die Kategorie keinen Wert uebermittelt bekommt
     */
    public void setKategorie(String kategorie) {
        if (kategorie == null) {
            throw new IllegalArgumentException("Kategorie fehlt!");
        } else {
            this.kategorie = kategorie;
        }
    }

    /**
     * Gibt den Wert von Prioritaet zurueck
     *
     * @return prioritaet String von Prioritaet
     */
    public String getPrioritaet() {
        return prioritaet;
    }

    /**
     * Setzt die Prioritaet auf den uebergebenen Wert
     *
     * @param prioritaet die zu setzende Prioritaet
     * @throws IllegalArgumentException wird geworfen, wenn die Prioritaet keinen Wert uebermittelt bekommt
     */
    public void setPrioritaet(String prioritaet) {
        if (prioritaet == null) {
            throw new IllegalArgumentException("Prioritaet fehlt!");
        } else {
            this.prioritaet = prioritaet;
        }
    }

    /**
     * Gibt den Wert von Status zurueck
     *
     * @return status String von Status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Setzt den Status auf den uebergebenen Wert
     *
     * @param status der zu setzende Status
     * @throws IllegalArgumentException wird geworfen, wenn der Status keinen Wert uebermittelt bekommt
     */
    public void setStatus(String status) {
        if (status == null) {
            throw new IllegalArgumentException("Status fehlt!");
        } else {
            this.status = status;
        }
    }
}
