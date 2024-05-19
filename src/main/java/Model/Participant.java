package model;

import java.sql.Date;

public class Participant {
    private int ParticipantID;
     private String Name;
    private String Email;
    private int Eventid;
    
    // Parameterized constructor
    public Event(int ParticipantID, String Name, String Email, int Eventid) {
        this.ParticipantID=ParticipantID;
        this.Name=Name;
        this.eventid = eventid;
    }
    // Getters and setters
    public int getEventid() {
        return eventid;
    }

    public void setEventid(int eventid) {
        this.eventid = eventid;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    

    // Add more getters and setters for additional fields as needed
}
