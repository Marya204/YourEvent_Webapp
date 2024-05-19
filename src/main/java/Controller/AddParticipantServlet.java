/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Participant;
import model.ParticipantDAO;



/**
 *
 * @author hp
 */
@WebServlet(name = "AddParticipantServlet", urlPatterns = {"/AddParticipantServlet"})

public class AddParticipantServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form data
        String Name = request.getParameter("Name");
        String Email = request.getParameter("Email");
        int Eventid = request.getParameter("Eventid");

     
        Participant participant = new Participant(Name,Email,Eventid);

        // Insert the Participant into the database
        ParticipantDAO participantDAO = new ParticipantDAO();
        try {
            participantDAO.addParticipant(participant);
        } catch (SQLException e) {
            // Handle database errors when inserting the event
            e.printStackTrace();
            // You can redirect to an appropriate error page here
        }

        // Redirect to the events page after insertion
        response.sendRedirect("Partcipant.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward POST requests to doGet to handle form submission
        doGet(request, response);
    }
}
