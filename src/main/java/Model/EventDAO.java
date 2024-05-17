package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/events";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String INSERT_EVENT_SQL = "INSERT INTO event (Titre, Description, Date, Lieu, Type, Status, Prix, Capacite) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_EVENTS = "SELECT * FROM event";
    private static final String SELECT_Event_By_ID = "SELECT * FROM event where Eventid = ? ";
        private static final String UPDATE_EVENT_SQL = "UPDATE event SET Titre = ?, Description = ?, Date = ?, Lieu = ?, Type = ?, Status = ?, Prix = ?, Capacite = ? WHERE Eventid = ?";
    private static final String DELETE_Event_SQL = "DELETE FROM event WHERE eventid = ?;";


    public List<Event> getAllEvents() {
    List<Event> listevent = new ArrayList<>();
    try (Connection conn = getConnection();
         PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ALL_EVENTS)) {
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            int eventid = rs.getInt("eventid");
            String titre = rs.getString("titre");
            String description = rs.getString("description");
            Date date = rs.getDate("date");
            String lieu = rs.getString("lieu");
            String type = rs.getString("type");
            String status = rs.getString("status");
            float prix = rs.getFloat("prix");
            int capacite = rs.getInt("capacite");

            Event event = new Event(eventid, titre, description, date, lieu, type, status, prix, capacite);
            listevent.add(event);
        }
    } catch (SQLException e) {
        printSQLException(e);
    }
    return listevent;
}


    public void addEvent(Event event) throws SQLException {
        System.out.println(INSERT_EVENT_SQL);
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(INSERT_EVENT_SQL)) {
            pstmt.setString(1, event.getTitre());
            pstmt.setString(2, event.getDescription());
            pstmt.setDate(3, new java.sql.Date(event.getDate().getTime()));
            pstmt.setString(4, event.getLieu());
            pstmt.setString(5, event.getType());
            pstmt.setString(6, event.getStatus());
            pstmt.setFloat(7, event.getPrix());
            pstmt.setInt(8, event.getCapacite());
            System.out.println(pstmt);
            pstmt.executeUpdate();
            
            conn.commit();
        } catch (SQLException e) {
            printSQLException(e);
            throw e;
        }
    }
 public Event getEvent(int eventid) {
        Event event = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection(); // Step 2:Create a statement using connection object
                 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_Event_By_ID);) {
            preparedStatement.setInt(1, eventid);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();
            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int eventId = rs.getInt("Eventid");
                String titre = rs.getString("Titre");
                String description = rs.getString("Description");
                Date date = rs.getDate("Date");
                String lieu = rs.getString("Lieu");
                String type = rs.getString("Type");
                String status = rs.getString("Status");
                float prix = rs.getFloat("Prix");
                int capacite = rs.getInt("Capacite");
                
              event = new Event(eventId, titre, description, date, lieu, type, status, prix, capacite);
                
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return event;
    }
 
   public void updateEvent(Event event) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(UPDATE_EVENT_SQL)) {
            pstmt.setString(1, event.getTitre());
            pstmt.setString(2, event.getDescription());
            pstmt.setDate(3, new java.sql.Date(event.getDate().getTime()));
            pstmt.setString(4, event.getLieu());
            pstmt.setString(5, event.getType());
            pstmt.setString(6, event.getStatus());
            pstmt.setFloat(7, event.getPrix());
            pstmt.setInt(8, event.getCapacite());
            pstmt.setInt(9, event.getEventid());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
   
    public boolean deleteEvent(int Eventid) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_Event_SQL);) {
            statement.setInt(1, Eventid);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
    
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
