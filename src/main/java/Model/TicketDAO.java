package Model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TicketDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/events";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Méthode pour obtenir tous les billets de la base de données
    public static List<Ticket> getAllTickets() throws SQLException, ClassNotFoundException {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT * FROM billet";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(rs.getInt("Id"));
                ticket.setEventId(rs.getInt("Eventid"));
                ticket.setInviteId(rs.getInt("Inviteid"));
                ticket.setPrice(rs.getDouble("Price"));
                ticket.setStatus(rs.getString("Status"));
                tickets.add(ticket);
            }
        }
        return tickets;
    }

    // Méthode pour ajouter un billet dans la base de données
    public static void addTicket(Ticket ticket) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO billet (Eventid, Inviteid, Price, Status) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, ticket.getEventId());
            stmt.setInt(2, ticket.getInviteId());
            stmt.setDouble(3, ticket.getPrice());
            stmt.setString(4, ticket.getStatus());
            stmt.executeUpdate();
        }
    }

    // Méthode pour mettre à jour un billet dans la base de données
    public static void updateTicket(Ticket ticket) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE billet SET Eventid=?, Inviteid=?, Price=?, Status=? WHERE Id=?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, ticket.getEventId());
            stmt.setInt(2, ticket.getInviteId());
            stmt.setDouble(3, ticket.getPrice());
            stmt.setString(4, ticket.getStatus());
            stmt.setInt(5, ticket.getId());
            stmt.executeUpdate();
        }
    }

    // Méthode pour supprimer un billet de la base de données
    public static void deleteTicket(int ticketId) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM billet WHERE Id=?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, ticketId);
            stmt.executeUpdate();
        }
    }

    // Méthode privée pour établir la connexion à la base de données
    private static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Récupérer les données du formulaire d'ajout de billet
        int eventId = Integer.parseInt(request.getParameter("eventId"));
        int inviteId = Integer.parseInt(request.getParameter("inviteId"));
        double price = Double.parseDouble(request.getParameter("price"));
        String status = request.getParameter("status");
        
        // Créer un nouvel objet Ticket avec les données récupérées
        Ticket newTicket = new Ticket();
        newTicket.setEventId(eventId);
        newTicket.setInviteId(inviteId);
        newTicket.setPrice(price);
        newTicket.setStatus(status);
        
        // Ajouter le nouveau billet à la base de données
        try {
            TicketDAO.addTicket(newTicket);
            // Rediriger vers la page JSP principale après l'ajout de billet
            response.sendRedirect("tickets.jsp");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace(); // Gérer l'erreur comme vous le souhaitez
        }
    }

}
