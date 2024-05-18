package Controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import model.InviteDAO;
import model.Invite;

@WebServlet(name = "InviteServlet", urlPatterns = {"/InviteServlet"})
public class InviteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private InviteDAO inviteDAO;

    @Override
    public void init() {
        inviteDAO = new InviteDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/add":
                    insertInvite(request, response);
                    break;
                case "/delete":
                    deleteInvite(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateInvite(request, response);
                    break;
                default:
                    listInvite(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private void listInvite(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Invite> listInvite = inviteDAO.selectAllInvite();
        request.setAttribute("listInvite", listInvite);
        RequestDispatcher dispatcher = request.getRequestDispatcher("events.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("AddInvite.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int inviteId = Integer.parseInt(request.getParameter("Inviteid"));
        Invite existingInvite = inviteDAO.selectInvite(inviteId);
        RequestDispatcher dispatcher = request.getRequestDispatcher("AddInvite.jsp");
        request.setAttribute("invite", existingInvite);
        dispatcher.forward(request, response);
    }

    private void insertInvite(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String name = request.getParameter("Name");
        String email = request.getParameter("Email");
        int eventId = Integer.parseInt(request.getParameter("Evenid"));

        Invite invite = new Invite(name, email, eventId);
        try {
            inviteDAO.insertInvite(invite);
            response.sendRedirect("InviteServlet");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void updateInvite(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int inviteId = Integer.parseInt(request.getParameter("Inviteid"));
        String name = request.getParameter("Name");
        String email = request.getParameter("Email");
        int eventId = Integer.parseInt(request.getParameter("Eventid"));
       
        Invite invite = new Invite(inviteId, name, email, eventId);
        inviteDAO.updateInvite(invite);
        response.sendRedirect("InviteServlet");
    }

    private void deleteInvite(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int inviteId = Integer.parseInt(request.getParameter("Inviteid"));
        inviteDAO.deleteInvite(inviteId);
        response.sendRedirect("InviteServlet");
    }
}
