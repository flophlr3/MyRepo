package org.hbrs.se1.ws24.exercises.uebung4;

import java.io.Serializable;

public class UserStories implements Serializable {
    private static int counter = 1;
    private int id;
    private String beschreibung;
    private String akzeptanzkriterium;
    private int mehrwert;
    private int strafe;
    private int aufwand;
    private int risiko;
    private String projektZuordnung;
    private double prioritaetslevel;


    public UserStories(String beschreibung, String akzeptanzkriterium, int mehrwert, int strafe, int aufwand, int risiko, String projektZuordnung) {
        id = counter;
        counter++;
        this.beschreibung = beschreibung;
        this.akzeptanzkriterium = akzeptanzkriterium;
        this.mehrwert = mehrwert;
        this.strafe = strafe;
        this.aufwand = aufwand;
        this.risiko = risiko;
        this.projektZuordnung = projektZuordnung;
        prioritaetslevel = (this.mehrwert + this.strafe) / (this.risiko + this.aufwand);
    }

    //UserStory getter
    public double getPrioritätslevel() {
        return prioritaetslevel;
    }

    public Integer getID() {
        return id;
    }

    public String getProjektZuordnung() {
        return projektZuordnung;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public String getAkzeptanzkriterium() {
        return akzeptanzkriterium;
    }

    public int getMehrwert() {
        return mehrwert;
    }

    public int getStrafe() {
        return strafe;
    }

    public int getAufwand() {
        return aufwand;
    }

    public int getRisiko() {
        return risiko;
    }

    public double getPrioritaetslevel() {
        return prioritaetslevel;
    }

    //Userstory setter
    public void setPrioritätslevel(double prioritaetslevel) {
        this.prioritaetslevel = prioritaetslevel;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public void setAkzeptanzkriterium(String akzeptanzkriterium) {
        this.akzeptanzkriterium = akzeptanzkriterium;
    }

    public void setAufwand(int aufwand) {
        this.aufwand = aufwand;
    }

    public void setMehrwert(int mehrwert) {
        this.mehrwert = mehrwert;
    }

    public void setStrafe(int strafe) {
        this.strafe = strafe;
    }

    public void setRisiko(int risiko) {
        this.risiko = risiko;
    }

    public void setPrioritaetslevel(double prioritaetslevel) {
        this.prioritaetslevel = prioritaetslevel;
    }

    public void setProjektZuordnung(String projektZuordnung) {
        this.projektZuordnung = projektZuordnung;
    }


    public int compareTo(UserStories other) {
        // Vergleich der Prioritätslevel
        return Double.compare(this.prioritaetslevel, other.prioritaetslevel);
    }
}