package Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.Ticket;
import Model.TicketDAO;

public class TicketServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if (action != null) {
            switch (action) {
                case "add":
                    addTicket(request, response);
                    break;
                case "update":
                    updateTicket(request, response);
                    break;
                case "delete":
                    deleteTicket(request, response);
                    break;
                default:
                    // Action non valide
                    response.sendRedirect("tickets.jsp");
                    break;
            }
        } else {
            // Action non spécifiée
            response.sendRedirect("tickets.jsp");
        }
    }
    
    // Méthode pour ajouter un billet
    private void addTicket(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Récupérer les données du formulaire
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
            response.sendRedirect("tickets.jsp");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace(); // Gérer l'erreur comme vous le souhaitez
        }
    }
    
    // Méthode pour mettre à jour un billet
    private void updateTicket(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Récupérer les données du formulaire
        int ticketId = Integer.parseInt(request.getParameter("ticketId"));
        int eventId = Integer.parseInt(request.getParameter("eventId"));
        int inviteId = Integer.parseInt(request.getParameter("inviteId"));
        double price = Double.parseDouble(request.getParameter("price"));
        String status = request.getParameter("status");
        
        // Créer un objet Ticket avec les données récupérées
        Ticket updatedTicket = new Ticket();
        updatedTicket.setId(ticketId);
        updatedTicket.setEventId(eventId);
        updatedTicket.setInviteId(inviteId);
        updatedTicket.setPrice(price);
        updatedTicket.setStatus(status);
        
        // Mettre à jour le billet dans la base de données
        try {
            TicketDAO.updateTicket(updatedTicket);
            response.sendRedirect("tickets.jsp");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace(); // Gérer l'erreur comme vous le souhaitez
        }
    }
    
    // Méthode pour supprimer un billet
    private void deleteTicket(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Récupérer l'ID du billet à supprimer depuis la requête
        int ticketId = Integer.parseInt(request.getParameter("ticketId"));
        
        // Supprimer le billet de la base de données
        try {
            TicketDAO.deleteTicket(ticketId);
            response.sendRedirect("tickets.jsp");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace(); // Gérer l'erreur comme vous le souhaitez
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Rediriger vers la page JSP pour l'ajout de billet
        request.getRequestDispatcher("addTicket.jsp").forward(request, response);
    }
    
}
