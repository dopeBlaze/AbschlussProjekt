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

    /**
     * Gibt den Wert des ErstellungsDatum zurück
     * @return erstellungsDatum  String des aktualla ErstellungsDatum(zeit und datum)
     */
    public  String getErstellungsDatum() {
        this.erstellungsDatum = date();
       return erstellungsDatum;
    }


    public void setErstellungsDatum(String erstellungsDatum) {
        this.erstellungsDatum = erstellungsDatum;
    }

    /**
     * Gibt den Wert des AktivitaetsName zurück
     * @return aktivitaetsName  String des AktivitaetsName
     */
    public String getAktivitaetsName() {
        return aktivitaetsName;
    }

    public void setAktivitaetsName(String aktivitaetsName) {
        if (aktivitaetsName == null) {
            throw new IllegalArgumentException("Bitte Schreiben Sie die AktivitaetsName ");
        } else {
            this.aktivitaetsName = aktivitaetsName;
        }
    }

    /**
     * Gibt den Wert des StartDatum zurück
     * @return startDatum  String des StartDatum
     */
    public String getStartDatum() {
        return startDatum;
    }

    public void setStartDatum(String startDatum) {
        this.startDatum = startDatum;
    }

    /**
     * Gibt den Wert des EndDatum zurück
     * @return endDatum  String des EndDatum
     */
    public String getEndDatum() {
        return endDatum;
    }

    public void setEndDatum(String endDatum) {
        this.endDatum = endDatum;
    }

    /**
     * Gibt den Wert des VerbrauchteZeit zurück
     * @return verbrauchteZeit  String des VerbrauchteZeit
     */
    public String getVerbrauchteZeit() {
        return verbrauchteZeit;
    }

    /**
     *
     * @param verbrauchteZeit muss ein positive wert sein
     *  und nicht null auch nicht character oder
     *  besonders type
     */
    public void setVerbrauchteZeit(String verbrauchteZeit) {
        this.verbrauchteZeit = verbrauchteZeit;
        if (verbrauchteZeit == null || Integer.parseInt(verbrauchteZeit) >= 0  )
            throw  new IllegalArgumentException("Bitte beachten Sie dass die verbraucher Zeit nicht null odeer negative wert ist");
    }

    /**
     * Gibt den Wert des Kategorie zurück
     * @return kategorie String des Kategorie
     */
    public String getKategorie() {
        return kategorie;
    }

    /**
     *
     * @param kategorie muss nicht null sein sonst bekommt man exception
     */

    public void setKategorie(String kategorie) {
        this.kategorie = kategorie;
        if (kategorie == null)
            throw new IllegalArgumentException("Sie haben keine Kategorie abgegeben , bitte geb mal die Kategorie ab");
    }

    /**
     * Gibt den Wert des Prioritaet zurück
     * @return prioritaet String des Prioritaet
     */
    public String getPrioritaet() {
        return prioritaet;
    }

    /**
     *
     * @param prioritaet  muss kein null sein ,es muss ein werte
     */


    public void setPrioritaet(String prioritaet) {
        if (prioritaet == null)
            throw new IllegalArgumentException("Es gab kein Status abgegeben, bitte geb mal die Priorität ab");
        this.prioritaet = prioritaet;
    }

    /**
     * Gibt den Wert des Statuszurück
     * @return status String des Status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status  mustt nicht eine Null sein
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
