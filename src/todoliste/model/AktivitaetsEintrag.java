package todoliste.model;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Attribute erstellungsDatum [Dateformat], aktivitaetsName [String], startDatum [DateFormat], endDatum [DateFormat], verbrauchteZeit [String], kategorie [String], prioritaet [String], status [String]
 */
public class AktivitaetsEintrag {


    private String erstellungsDatum, aktivitaetsName, startDatum, endDatum, verbrauchteZeit, kategorie, prioritaet, status;

    /**
     * Konstruktor, der alle Werte mit leeren Werten initialisiert
     */

    public AktivitaetsEintrag() {
        this.erstellungsDatum = "";
        this.aktivitaetsName = "";
        this.startDatum = "";
        this.endDatum = "";
        this.verbrauchteZeit = "";
        this.kategorie = "";
        this.prioritaet = "";
        this.status = "";
    }

    /**
     * Überladener Konstruktor
     * Initialisiert alle Werte mit leeren Werten und setzt dann die übergebenen Werte,
     * sofern sie ungleich null sind.
     *
     * @param aktivitaetsName zu setzender AktivitaetsName
     * @param startDatum      zu setzender StartDatum
     * @param endDatum        zu setzender EndDatum
     * @param verbrauchteZeit zu setzender VerbrauchteZeit
     * @param kategorie       zu setzender Kategorie
     * @param prioritaet      zu setzender Prioritaet
     * @param status          zu setzender Status
     */

    public AktivitaetsEintrag(String aktivitaetsName, String startDatum, String endDatum, String verbrauchteZeit, String kategorie, String prioritaet, String status) {
        setErstellungsDatum("");
        setAktivitaetsName(aktivitaetsName);
        setStartDatum(startDatum);
        setEndDatum(endDatum);
        setVerbrauchteZeit(verbrauchteZeit);
        setKategorie(kategorie);
        setPrioritaet(prioritaet);
        setStatus(status);
    }

    /**
     * Gibt den Wert des ErstellungsDatum zurück
     *
     * @return erstellungsDatum  String des aktualla ErstellungsDatum(zeit und datum)
     */
    public String getErstellungsDatum() {
        return erstellungsDatum;
    }

    /**
     * @param erstellungsDatum Die zu setzende ersttellung atum
     * @throws IllegalArgumentException wird geworfen, wenn die erstellungsDatum nicht numerischer Wert vorkommt
     */

    public void setErstellungsDatum(String erstellungsDatum) {
        // this.erstellungsDatum = erstellungsDatum;
        this.erstellungsDatum = date();

    }

    /**
     * Gibt den Wert des AktivitaetsName zurück
     *
     * @return aktivitaetsName  String des AktivitaetsName
     */
    public String getAktivitaetsName() {
        return aktivitaetsName;
    }

    /**
     * @param aktivitaetsName Die zu setzende aktivitaet
     * @throws IllegalArgumentException wird geworfen, wenn die Aktivtat nicht numerischer Wert vorkommt
     */
    public void setAktivitaetsName(String aktivitaetsName) {
        if (aktivitaetsName == null) {
            throw new IllegalArgumentException("Bitte Schreiben Sie die AktivitaetsName ");
        } else {
            this.aktivitaetsName = aktivitaetsName;
        }
    }

    /**
     * Gibt den Wert des StartDatum zurück
     *
     * @return startDatum  String des StartDatum
     */
    public String getStartDatum() {
        return startDatum;
    }

    /**
     * @param startDatum Die zu setzende startDatum
     */
    public void setStartDatum(String startDatum) {
       this.startDatum = startDatum;

    }

    /**
     * Gibt den Wert des EndDatum zurück
     *
     * @return endDatum  String des EndDatum
     */
    public String getEndDatum() {
        return endDatum;
    }

    /**
     * @param endDatum Die zu setzende endDatum
     * @throws IllegalArgumentException wird geworfen, wenn die EndDatum nicht numerischer Wert vorkommt
     */
    public void setEndDatum(String endDatum) {
        if (endDatum == null) {
            throw new IllegalArgumentException("Die EndDatum ist ungültig");
        } else {
            this.endDatum = endDatum;
        }
    }

    /**
     * Gibt den Wert des VerbrauchteZeit zurück
     *
     * @return verbrauchteZeit  String des VerbrauchteZeit
     */
    public String getVerbrauchteZeit() {
        return verbrauchteZeit;
    }

    /**
     * @param verbrauchteZeit muss ein positive wert sein
     *                        und nicht null auch nicht character oder
     *                        besonders type
     * @param verbrauchteZeit muss ein positive wert sein  und nicht null auch nicht character oder
     *                        besonders type
     * @throws IllegalArgumentException wird geworfen, wenn die VerbrauchteZeit nicht numerischer Wert vorkommt.
     */
    public void setVerbrauchteZeit(String verbrauchteZeit) {
        if (verbrauchteZeit == null || Integer.parseInt(verbrauchteZeit) >= 0) {
            throw new IllegalArgumentException("Bitte beachten Sie dass die verbraucher Zeit nicht null odeer negative wert ist");
        } else {
            this.verbrauchteZeit = verbrauchteZeit;
        }
    }

    /**
     * Gibt den Wert des Kategorie zurück
     *
     * @return kategorie String des Kategorie
     */
    public String getKategorie() {
        return kategorie;
    }

    /**
     * @param kategorie Die zu setzende kategorie
     * @throws IllegalArgumentException wird geworfen, wenn die kategorie nicht numerischer Wert vorkommt.
     */

    public void setKategorie(String kategorie) {
        if (kategorie == null) {
            throw new IllegalArgumentException("Sie haben keine Kategorie abgegeben , bitte geb mal die Kategorie ab");
        } else {
            this.kategorie = kategorie;
        }
    }

    /**
     * Gibt den Wert des Prioritaet zurück
     *
     * @return prioritaet String des Prioritaet
     */
    public String getPrioritaet() {
        return prioritaet;
    }

    /**
     * @param prioritaet Die zu setzende prioritaet
     * @throws IllegalArgumentException wird geworfen, wenn die priorität nicht numerischer Wert vorkommt.
     */


    public void setPrioritaet(String prioritaet) {
        if (prioritaet == null) {
            throw new IllegalArgumentException("Es gab kein Status abgegeben, bitte geb mal die Priorität ab");
        } else {
            this.prioritaet = prioritaet;
        }
    }

    /**
     * Gibt den Wert des Statuszurück
     *
     * @return status String des Status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status Die zu setzende Status
     * @throws IllegalArgumentException wird geworfen, wenn die Status n nicht numerischer Wert vorkommt.
     */
    public void setStatus(String status) {
        if (status == null) {
            throw new IllegalArgumentException("Du hast keine Status, es muss ein Statsu sein");
        } else {
            this.status = status;
        }
    }

    /**
     * Gibt den Wert des DateTimeFormatter
     *
     * @return LocalDateTime String des DateTimeFormatter
     */
    // method calculate die aktualle date und zeit
    public String date() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:[SSS]");
        return dtf.format(LocalDateTime.now());
    }


}
