package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketsDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/airlinedb";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";

    private static final String INSERT_TICKET_SQL = "INSERT INTO Billet (Eventid, Inviteid, Prix, Status) VALUES (?, ?, ?, ?)";
    private static final String SELECT_TICKET_BY_ID = "SELECT Id, Eventid, Inviteid, Prix, Status FROM Billet WHERE Id = ?";
    private static final String SELECT_ALL_TICKETS = "SELECT * FROM Billet";
    private static final String DELETE_TICKET_SQL = "DELETE FROM Billet WHERE Id = ?";
    private static final String UPDATE_TICKET_SQL = "UPDATE Billet SET Eventid = ?, Inviteid = ?, Prix = ?, Status = ? WHERE Id = ?";

    public TicketsDAO() {
    }

    protected Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void insertTicket(Ticket ticket) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TICKET_SQL)) {
            preparedStatement.setInt(1, ticket.getEventid());
            preparedStatement.setInt(2, ticket.getInviteid());
            preparedStatement.setDouble(3, ticket.getPrice());
            preparedStatement.setString(4, ticket.getStatus());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Ticket selectTicket(int ticketId) {
        Ticket ticket = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TICKET_BY_ID)) {
            preparedStatement.setInt(1, ticketId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                int eventId = rs.getInt("Eventid");
                int inviteId = rs.getInt("Inviteid");
                double price = rs.getDouble("Prix");
                String status = rs.getString("Status");
                
                ticket = new Ticket(ticketId, eventId, inviteId, price, status);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return ticket;
    }

    public List<Ticket> selectAllTickets() {
        List<Ticket> tickets = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TICKETS)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("Id");
                int eventId = rs.getInt("Eventid");
                int inviteId = rs.getInt("Inviteid");
                double price = rs.getDouble("Prix");
                String status = rs.getString("Status");

                Ticket ticket = new Ticket(id, eventId, inviteId, price, status);
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return tickets;
    }

    public boolean deleteTicket(int ticketId) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_TICKET_SQL)) {
            statement.setInt(1, ticketId);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateTicket(Ticket ticket) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_TICKET_SQL)) {
            statement.setInt(1, ticket.getEventid());
            statement.setInt(2, ticket.getInviteid());
            statement.setDouble(3, ticket.getPrice());
            statement.setString(4, ticket.getStatus());
            statement.setInt(5, ticket.getId());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
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