package Model;
import java.sql.Date;
public class Event {
    private int Eventid;
    private String Titre;
    private String Description;
    private Date Date;
    private String Lieu;
    private String Type;
    private String Status;
    private float Prix;
    private int Capacite;
    
   // Add more fields as needed
    // Constructors
    public Event() {
    }
    public Event(int Eventid, String Titre, String Description,Date Date,String Lieu,String Type,String Status,float Prix,int Capacite) {
        this.Eventid = Eventid;
        this.Titre = Titre;
        this.Date = Date;
        this.Lieu = Lieu;
        this.Type=Type;
        this.Status = Status;
        this.Prix = Prix;
        this.Capacite = Capacite;
    }
    
    // Getters and setters
    public int getEventId() {
        return Eventid;
    }

    public void setEventId(int eventId) {
        this.Eventid = eventId;
    }

    public String getTitle() {
        return Titre;
    }

    public void setTitle(String title) {
        this.Titre = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

	public Date getDate() {
		return Date;
	}

	public void setDate(Date date) {
		Date = date;
	}

	public String getLieu() {
		return Lieu;
	}

	public void setLieu(String lieu) {
		Lieu = lieu;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public float getPrix() {
		return Prix;
	}

	public void setPrix(float prix) {
		Prix = prix;
	}

	public int getCapacite() {
		return Capacite;
	}

	public void setCapacite(int capacite) {
		Capacite = capacite;
	}

    // Add more getters and setters for additional fields as needed
}
