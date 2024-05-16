package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Event;
import Model.EventDAO;

@WebServlet("/events")
public class EventServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Retrieve list of events from EventDAO
            List<Event> events = EventDAO.getAllEvents();
            
            // Set events as an attribute in the request
            request.setAttribute("events", events);
            
            // Forward the request to the view (JSP)
            RequestDispatcher dispatcher = request.getRequestDispatcher("/events.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            // Handle exceptions appropriately
            e.printStackTrace(); // For debugging purposes
            response.sendRedirect("error.jsp"); // Redirect to an error page
        }
    }
}
