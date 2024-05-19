package Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import model.ParticipantDAO;
import model.Participant;

@WebServlet(name = "ParticipantServlet", urlPatterns = {"/ParticipantServlet"})
public class ParticipantServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ParticipantDAO participantDAO;

    @Override
    public void init() {
        participantDAO = new ParticipantDAO();
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
                    addParticipant(request, response);
                    break;
                case "/delete":
                    deleteParticipant(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateParticipant(request, response);
                    break;
                default:
                    listParticipant(request, response);
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

    private void listParticipant(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Participant> listparticipant = participantDAO.getAllParticipants();
        request.setAttribute("listparticipant", listparticipant);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Participant.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("AddParticipant.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int participantID = Integer.parseInt(request.getParameter("ParticipantID"));
        Participant existingParticipant = participantDAO.getParticipant(participantID);
        RequestDispatcher dispatcher = request.getRequestDispatcher("AddParticipant.jsp");
        request.setAttribute("participant", existingParticipant);
        dispatcher.forward(request, response);
    }

    private void addParticipant(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String name = request.getParameter("Name");
        String email = request.getParameter("Email");
        int eventId = Integer.parseInt(request.getParameter("Eventid"));

        Participant participant = new Participant(name,email,eventId);
        try {
            participantDAO.addParticipant(participant);
            response.sendRedirect("ParticipantServlet");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
    

    private void updateParticipant(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int ParticipantId = Integer.parseInt(request.getParameter("ParticipantID"));
        String name = request.getParameter("Name");
        String email = request.getParameter("Email");
        int eventId = Integer.parseInt(request.getParameter("Eventid"));
       
        Participant participant = new Participant(ParticipantId,name,email,eventId);
        participantDAO.updateParticipant(participant);
        response.sendRedirect("ParticipantServlet");
    }

    private void deleteParticipant(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int Participantid = Integer.parseInt(request.getParameter("ParticipantID"));
        participantDAO.deleteParticipant(Participantid);
        response.sendRedirect("ParticipantServlet");
    }
}
