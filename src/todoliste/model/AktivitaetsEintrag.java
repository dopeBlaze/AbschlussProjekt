package todoliste.model;


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


    public String getErstellungsDatum() {
       return erstellungsDatum;
    }

    public void setErstellungsDatum(String erstellungsDatum) {
        this.erstellungsDatum = erstellungsDatum;
    }

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

    public String getStartDatum() {
        return startDatum;
    }

    public void setStartDatum(String startDatum) {
        this.startDatum = startDatum;
    }

    public String getEndDatum() {
        return endDatum;
    }

    public void setEndDatum(String endDatum) {
        this.endDatum = endDatum;
    }

    public String getVerbrauchteZeit() {
        return verbrauchteZeit;
    }

    public void setVerbrauchteZeit(String verbrauchteZeit) {
        this.verbrauchteZeit = verbrauchteZeit;
    }

    public String getKategorie() {
        return kategorie;
    }

    public void setKategorie(String kategorie) {
        this.kategorie = kategorie;
    }

    public String getPrioritaet() {
        return prioritaet;
    }

    public void setPrioritaet(String prioritaet) {
        this.prioritaet = prioritaet;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }




}
