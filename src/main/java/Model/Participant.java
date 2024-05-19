/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;



public class Participant {
    private int ParticipantID;
     private String Name;
    private String Email;
    private int Eventid;
    
    
     public Participant() {
    }
    // Parameterized constructor
    public Participant(int participantID, String name, String email, int eventid) {
        this.ParticipantID=participantID;
        this.Email=email;
        this.Name=name;
        this.Eventid = eventid;
    }
    
    public Participant(String name, String email, int eventid) {
        this.Name=name;
        this.Email=email;
        this.Eventid = eventid;
    }
    
    
    // Getters and setters
    
      public int getParticipantID() {
        return ParticipantID;
    }

    public void setParticipantID(int participantID) {
        this.ParticipantID = participantID;
    }
    
    public int getEventid() {
        return Eventid;
    }

    public void setEventid(int eventid) {
        this.Eventid = eventid;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }
    
}
