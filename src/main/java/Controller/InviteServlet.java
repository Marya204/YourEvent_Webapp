package Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import dao.InviteDAO;
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
                    addInvite(request, response);
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

    private void listEvent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Invite> listevent = inviteDAO.getAllInvite();
        request.setAttribute("listevent", listinvite);
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
        int Inviteid = Integer.parseInt(request.getParameter("Invited"));
        Invite existingEvent = inviteDAO.getInvite(Inviteid);
        RequestDispatcher dispatcher = request.getRequestDispatcher("AddInvite.jsp");
        request.setAttribute("invite", existingInvite);
        dispatcher.forward(request, response);
    }

    private void addInvite(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String Name = request.getParameter("Name");
        String Email = request.getParameter("Email");
        int Eventid = Integer.parseInt(request.getParameter("Evenid"));

        Invite invite = new Invite(Name, Email, Eventid);
        try {
            inviteDAO.addInvite(invite);
            response.sendRedirect("InviteServlet");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
    private void updateInvite(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int Inviteid = Integer.parseInt(request.getParameter("Inviteid"));
        String Name = request.getParameter("Name");
        String Email = request.getParameter("Email");
        int Eventid = Integer.parseInt(request.getParameter("Eventid"));
       
        Invite invite = new Invite(Inviteid, Name, Email, Eventid);
        inviteDAO.updateInvite(invite);
        response.sendRedirect("InviteServlet");
    }

    private void deleteInvite(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int Inviteid = Integer.parseInt(request.getParameter("Inviteid"));
        inviteDAO.deleteEvent(Inviteid);
        response.sendRedirect("EventServlet");
    }
}
