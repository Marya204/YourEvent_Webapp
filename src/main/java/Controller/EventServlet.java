package Controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import Model.Event;
import Model.EventDAO;
import java.util.List;


@WebServlet("/events")
public class EventServlet extends HttpServlet {
    
	private static final long serialVersionUID = 1L;

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
