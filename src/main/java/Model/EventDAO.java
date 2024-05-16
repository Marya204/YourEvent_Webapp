package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/events";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Method to establish database connection
    private static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Method to retrieve all events from the database
    public static List<Event> getAllEvents() throws SQLException, ClassNotFoundException {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT * FROM event";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Event event = new Event();
                event.setEventId(rs.getInt("EventId"));
                event.setTitle(rs.getString("Title"));
                event.setDescription(rs.getString("Description"));
                event.setDate(rs.getDate("Date"));
                event.setLieu(rs.getString("Location"));
                event.setType(rs.getString("Type"));
                event.setStatus(rs.getString("Status"));
                event.setPrix(rs.getFloat("Price"));
                event.setCapacite(rs.getInt("Capacity"));
                // Set other properties as needed
                events.add(event);
            }
        }
        return events;
    }

   
}
