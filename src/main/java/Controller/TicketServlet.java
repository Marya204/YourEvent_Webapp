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
            // Action non sp�cifi�e
            response.sendRedirect("tickets.jsp");
        }
    }
    
    // M�thode pour ajouter un billet
    private void addTicket(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // R�cup�rer les donn�es du formulaire
        int eventId = Integer.parseInt(request.getParameter("eventId"));
        int inviteId = Integer.parseInt(request.getParameter("inviteId"));
        double price = Double.parseDouble(request.getParameter("price"));
        String status = request.getParameter("status");
        
        // Cr�er un nouvel objet Ticket avec les donn�es r�cup�r�es
        Ticket newTicket = new Ticket();
        newTicket.setEventId(eventId);
        newTicket.setInviteId(inviteId);
        newTicket.setPrice(price);
        newTicket.setStatus(status);
        
        // Ajouter le nouveau billet � la base de donn�es
        try {
            TicketDAO.addTicket(newTicket);
            response.sendRedirect("tickets.jsp");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace(); // G�rer l'erreur comme vous le souhaitez
        }
    }
    
    // M�thode pour mettre � jour un billet
    private void updateTicket(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // R�cup�rer les donn�es du formulaire
        int ticketId = Integer.parseInt(request.getParameter("ticketId"));
        int eventId = Integer.parseInt(request.getParameter("eventId"));
        int inviteId = Integer.parseInt(request.getParameter("inviteId"));
        double price = Double.parseDouble(request.getParameter("price"));
        String status = request.getParameter("status");
        
        // Cr�er un objet Ticket avec les donn�es r�cup�r�es
        Ticket updatedTicket = new Ticket();
        updatedTicket.setId(ticketId);
        updatedTicket.setEventId(eventId);
        updatedTicket.setInviteId(inviteId);
        updatedTicket.setPrice(price);
        updatedTicket.setStatus(status);
        
        // Mettre � jour le billet dans la base de donn�es
        try {
            TicketDAO.updateTicket(updatedTicket);
            response.sendRedirect("tickets.jsp");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace(); // G�rer l'erreur comme vous le souhaitez
        }
    }
    
    // M�thode pour supprimer un billet
    private void deleteTicket(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // R�cup�rer l'ID du billet � supprimer depuis la requ�te
        int ticketId = Integer.parseInt(request.getParameter("ticketId"));
        
        // Supprimer le billet de la base de donn�es
        try {
            TicketDAO.deleteTicket(ticketId);
            response.sendRedirect("tickets.jsp");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace(); // G�rer l'erreur comme vous le souhaitez
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Rediriger vers la page JSP pour l'ajout de billet
        request.getRequestDispatcher("addTicket.jsp").forward(request, response);
    }
    
}