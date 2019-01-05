package com.se.ue04.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Fahrzeug {

    // Generator for automatic id creation
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;
    private String marke;
    private String bezeichnung;
    private int sitzplaetze;
    private String fahrer;
    private boolean verfuegbar;

    public Fahrzeug() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMarke() {
        return marke;
    }

    public void setMarke(String marke) {
        this.marke = marke;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public int getSitzplaetze() {
        return sitzplaetze;
    }

    public void setSitzplaetze(int sitzplaetze) {
        this.sitzplaetze = sitzplaetze;
    }

    public String getFahrer() {
        return fahrer;
    }

    public void setFahrer(String fahrer) {
        this.fahrer = fahrer;
    }

    public boolean isVerfuegbar() {
        return verfuegbar;
    }

    public void setVerfuegbar(boolean verfuegbar) {
        this.verfuegbar = verfuegbar;
    }

    @Override
    public String toString() {
        return "Fahrzeug{" +
                "id='" + id + '\'' +
                ", marke='" + marke + '\'' +
                ", bezeichnung='" + bezeichnung + '\'' +
                ", sitzplaetze=" + sitzplaetze +
                ", fahrer='" + fahrer + '\'' +
                ", verfuegbar=" + verfuegbar +
                '}';
    }
}
