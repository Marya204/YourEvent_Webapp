package Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.TicketDAO;
import model.Ticket;

@WebServlet("/TicketServlet")
public class TicketServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    TicketDAO dao;

    public TicketServlet() throws ClassNotFoundException, SQLException {
        super();
        dao = new TicketDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "list"; // Par défaut, afficher la liste des billets
        }

        try {
            switch (action) {
                case "list":
                    listTickets(request, response);
                    break;
                case "add":
                    showAddForm(request, response);
                    break;
                case "insert":
                    addTicket(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updateTicket(request, response);
                    break;
                case "delete":
                    deleteTicket(request, response);
                    break;
                default:
                    listTickets(request, response);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Ticket ticket = new Ticket();
        ticket.setId(Integer.parseInt(request.getParameter("id")));
        ticket.setEventid(Integer.parseInt(request.getParameter("eventid")));
        ticket.setInviteid(Integer.parseInt(request.getParameter("inviteid")));
        ticket.setPrice(Double.parseDouble(request.getParameter("price")));
        ticket.setStatus(request.getParameter("status"));
        response.sendRedirect("Ticket.jsp");

        try {
            dao.insertTicket(ticket);
            List<Ticket> tickets = dao.getAllTickets();
            request.setAttribute("listbillet", tickets);
            request.getRequestDispatcher("Ticket.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'erreur en affichant un message d'erreur ou en redirigeant vers une page d'erreur
            response.sendRedirect("error.jsp");
        }
    }

    private void listTickets(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Ticket> tickets = dao.getAllTickets();
        request.setAttribute("listTickets", tickets);
        request.getRequestDispatcher("list_tickets.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("add_ticket.jsp").forward(request, response);
    }

    private void addTicket(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        Ticket ticket = new Ticket();
        ticket.setEventid(Integer.parseInt(request.getParameter("eventid")));
        ticket.setInviteid(Integer.parseInt(request.getParameter("inviteid")));
        ticket.setPrice(Double.parseDouble(request.getParameter("price")));
        ticket.setStatus(request.getParameter("status"));

        dao.insertTicket(ticket);
        response.sendRedirect("TicketServlet");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int ticketId = Integer.parseInt(request.getParameter("id"));
        Ticket existingTicket = dao.getTicketById(ticketId);
        request.setAttribute("ticket", existingTicket);
        request.getRequestDispatcher("edit_ticket.jsp").forward(request, response);
    }

    private void updateTicket(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        Ticket ticket = new Ticket();
        ticket.setId(Integer.parseInt(request.getParameter("id")));
        ticket.setEventid(Integer.parseInt(request.getParameter("eventid")));
        ticket.setInviteid(Integer.parseInt(request.getParameter("inviteid")));
        ticket.setPrice(Double.parseDouble(request.getParameter("price")));
        ticket.setStatus(request.getParameter("status"));

        dao.updateTicket(ticket);
        response.sendRedirect("TicketServlet");
    }

    private void deleteTicket(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int ticketId = Integer.parseInt(request.getParameter("id"));
        dao.deleteTicket(ticketId);
        response.sendRedirect("TicketServlet");
    }
}
