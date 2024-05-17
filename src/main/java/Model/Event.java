package model;

import java.sql.Date;

public class Event {
    private int eventid;
    private String titre;
    private String description;
    private java.util.Date date;
    private String lieu;
    private String type;
    private String status;
    private float prix;
    private int capacite;
    
    // Parameterized constructor
    public Event(int eventid, String titre, String description, java.util.Date date, String lieu, String type, String status, float prix, int capacite) {
        this.eventid = eventid;
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.lieu = lieu;
        this.type = type;
        this.status = status;
        this.prix = prix;
        this.capacite = capacite;
    }
    
     public Event(String titre, String description, java.util.Date date, String lieu, String type, String status, float prix, int capacite) {
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.lieu = lieu;
        this.type = type;
        this.status = status;
        this.prix = prix;
        this.capacite = capacite;
    }

    // Getters and setters
    public int getEventid() {
        return eventid;
    }

    public void setEventid(int eventid) {
        this.eventid = eventid;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public java.util.Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    // Add more getters and setters for additional fields as needed
}
