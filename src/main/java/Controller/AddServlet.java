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
import model.Event;
import model.EventDAO;



/**
 *
 * @author hp
 */
@WebServlet(name = "AddServlet", urlPatterns = {"/AddServlet"})

public class AddServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form data
        String titre = request.getParameter("titre");
        String description = request.getParameter("description");
        String dateString = request.getParameter("date");
        java.sql.Date date = null;

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = dateFormat.parse(dateString);
            date = new java.sql.Date(parsedDate.getTime());
        } catch (ParseException e) {
             e.printStackTrace();
    // Handle parsing error
        }
        String lieu = request.getParameter("lieu");
        String type = request.getParameter("type");
        String status = request.getParameter("status");
        float prix = Float.parseFloat(request.getParameter("prix"));
        int capacite = Integer.parseInt(request.getParameter("capacite"));

        // Create a new Event object
        Event event = new Event(titre, description, (java.sql.Date) date, lieu, type, status, prix, capacite);

        // Insert the event into the database
        EventDAO eventDAO = new EventDAO();
        try {
            eventDAO.addEvent(event);
        } catch (SQLException e) {
            // Handle database errors when inserting the event
            e.printStackTrace();
            // You can redirect to an appropriate error page here
        }

        // Redirect to the events page after insertion
        response.sendRedirect("events.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward POST requests to doGet to handle form submission
        doGet(request, response);
    }
}
