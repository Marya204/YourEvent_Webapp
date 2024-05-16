package Model;

public class Ticket {
    private int id;
    private int eventId;
    private int inviteId;
    private double price;
    private String status;
    
    // Constructeur par défaut
    public Ticket() {
    }

    // Constructeur avec tous les paramètres
    public Ticket(int id, int eventId, int inviteId, double price, String status) {
        this.id = id;
        this.eventId = eventId;
        this.inviteId = inviteId;
        this.price = price;
        this.status = status;
    }

    // Getters et Setters pour tous les champs
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getInviteId() {
        return inviteId;
    }

    public void setInviteId(int inviteId) {
        this.inviteId = inviteId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
