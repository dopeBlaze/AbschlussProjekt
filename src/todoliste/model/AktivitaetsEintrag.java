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
     * @param erstellungsDatum zu setzender ErstellungsDatum
     * @param aktivitaetsName  zu setzender AktivitaetsName
     * @param startDatum       zu setzender StartDatum
     * @param endDatum         zu setzender EndDatum
     * @param verbrauchteZeit  zu setzender VerbrauchteZeit
     * @param kategorie        zu setzender Kategorie
     * @param prioritaet       zu setzender Prioritaet
     * @param status           zu setzender Status
     */

    public AktivitaetsEintrag(String erstellungsDatum, String aktivitaetsName, String startDatum, String endDatum, String verbrauchteZeit, String kategorie, String prioritaet, String status) {
        setErstellungsDatum(erstellungsDatum);
        setAktivitaetsName(aktivitaetsName);
        setStartDatum(startDatum);
        setEndDatum(endDatum);
        setVerbrauchteZeit(verbrauchteZeit);
        setKategorie(kategorie);
        setPrioritaet(prioritaet);
        setStatus(status);
    }

    // getErstellungsDatum ruf die aktualle zeit und date
    public String getErstellungsDatum() {
        this.erstellungsDatum = date();
       return erstellungsDatum;
    }

    /**
     *
     * @param erstellungsDatum Die zu setzende ersttellung atum
     *@throws IllegalArgumentException wird geworfen, wenn die erstellungsDatum nicht numerischer Wert vorkommt
     */
    public void setErstellungsDatum(String erstellungsDatum) {
        this.erstellungsDatum = erstellungsDatum;
        if (erstellungsDatum == null)
            throw new IllegalArgumentException("Die angegeben Datum ist ungültig");
    }

    public String getAktivitaetsName() {
        return aktivitaetsName;
    }

    /**
     *
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

    public String getStartDatum() {
        return startDatum;
    }

    /**
     *
     * @param startDatum Die zu setzende startDatum
     */
    public void setStartDatum(String startDatum) {
        this.startDatum = startDatum;
    }

    public String getEndDatum() {
        return endDatum;
    }

    /**
     *
     * @param endDatum Die zu setzende endDatum
     * @throws IllegalArgumentException wird geworfen, wenn die EndDatum nicht numerischer Wert vorkommt
     */
    public void setEndDatum(String endDatum) {
        this.endDatum = endDatum;
        if (endDatum == null)
            throw new IllegalArgumentException("Die EndDatum ist ungültig");
    }

    public String getVerbrauchteZeit() {
        return verbrauchteZeit;
    }

    /**
     *
     * @param verbrauchteZeit muss ein positive wert sein  und nicht null auch nicht character oder
     *                        besonders type
     * @throws IllegalArgumentException wird geworfen, wenn die VerbrauchteZeit nicht numerischer Wert vorkommt.
     */
    public void setVerbrauchteZeit(String verbrauchteZeit) {
        this.verbrauchteZeit = verbrauchteZeit;
        if (verbrauchteZeit == null || Integer.parseInt(verbrauchteZeit) >= 0  )
            throw  new IllegalArgumentException("Bitte beachten Sie dass die verbraucher Zeit nicht null odeer negative wert ist");
    }

    public String getKategorie() {
        return kategorie;
    }

    /**
     *
     * @param kategorie Die zu setzende kategorie
     * @throws IllegalArgumentException wird geworfen, wenn die kategorie nicht numerischer Wert vorkommt.
     */
    public void setKategorie(String kategorie) {
        this.kategorie = kategorie;
        if (kategorie == null)
            throw new IllegalArgumentException("Sie haben keine Kategorie abgegeben , bitte geb mal die Kategorie ab");
    }

    public String getPrioritaet() {
        return prioritaet;
    }

    /**
     *
     * @param prioritaet  Die zu setzende prioritaet
     * @throws IllegalArgumentException wird geworfen, wenn die priorität nicht numerischer Wert vorkommt.
     */


    public void setPrioritaet(String prioritaet) {
        if (prioritaet == null)
            throw new IllegalArgumentException("Es gab kein Status abgegeben, bitte geb mal die Priorität ab");
        this.prioritaet = prioritaet;
    }

    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status  Die zu setzende Status
     *@throws IllegalArgumentException wird geworfen, wenn die Status n nicht numerischer Wert vorkommt.
     */
    public void setStatus(String status) {
        if (status == null)
            throw new IllegalArgumentException("Du hast keine Status, es muss ein Statsu sein");
        this.status = status;
    }

    // method calculate die aktualle date und zeit
    public String date(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        return dtf.format(LocalDateTime.now());
    }

}
