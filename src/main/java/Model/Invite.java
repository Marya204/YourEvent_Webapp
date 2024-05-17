package model;

public class Invite {
    protected int Inviteid;
    protected String Name;
    protected String Email;
    protected int Eventid;

    public Invite(String Name, String Email, int Eventid) {
    	 this.Name = Name;
         this.Email = Email;
         this.Eventid = Eventid;
    }

    public Invite(int Inviteid, String Name, String Email, int Eventid) {
        this.Inviteid = Inviteid;
        this.Name = Name;
        this.Email = Email;
        this.Eventid = Eventid;
    }

    // Getters and Setters
    public int getInviteid() {
        return Inviteid;
    }

    public void setInviteid(int Inviteid) {
        this.Inviteid = Inviteid;
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
    
    public int getEventid() {
        return Eventid;
    }
    public void setEventid(int Eventid) {
        this.Eventid = Eventid;
    }
}
