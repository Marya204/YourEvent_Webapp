package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/airlinedb";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";

    private static final String INSERT_TICKET_SQL = "INSERT INTO billet (Eventid, Inviteid, Price, Status) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_TICKET_SQL = "UPDATE billet SET Eventid = ?, Inviteid = ?, Price = ?, Status = ? WHERE Id = ?";
    private static final String DELETE_TICKET_SQL = "DELETE FROM billet WHERE Id = ?";
    private static final String SELECT_ALL_TICKETS_SQL = "SELECT * FROM billet";

    public TicketDAO() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
    }

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    public void insertTicket(Ticket ticket) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TICKET_SQL)) {
            preparedStatement.setInt(1, ticket.getEventid());
            preparedStatement.setInt(2, ticket.getInviteid());
            preparedStatement.setDouble(3, ticket.getPrice());
            preparedStatement.setString(4, ticket.getStatus());
            preparedStatement.executeUpdate();
        }
    }

    public boolean updateTicket(Ticket ticket) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_TICKET_SQL)) {
            statement.setInt(1, ticket.getEventid());
            statement.setInt(2, ticket.getInviteid());
            statement.setDouble(3, ticket.getPrice());
            statement.setString(4, ticket.getStatus());
            statement.setInt(5, ticket.getId());
            return statement.executeUpdate() > 0;
        }
    }

    public boolean deleteTicket(int id) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_TICKET_SQL)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        }
    }

    public List<Ticket> getAllTickets() throws SQLException {
        List<Ticket> tickets = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_TICKETS_SQL);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("Id");
                int eventId = resultSet.getInt("Eventid");
                int inviteId = resultSet.getInt("Inviteid");
                float price = resultSet.getFloat("Price");
                String status = resultSet.getString("Status");

                Ticket ticket = new Ticket(id, eventId, inviteId, price, status);
                tickets.add(ticket);
            }
        }

        return tickets;
    }
    public Ticket getTicketById(int id) throws SQLException {
        Ticket ticket = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM billet WHERE Id = ?");
        ) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int eventId = resultSet.getInt("Eventid");
                int inviteId = resultSet.getInt("Inviteid");
                float price = resultSet.getFloat("Price");
                String status = resultSet.getString("Status");

                ticket = new Ticket(id, eventId, inviteId, price, status);
            }
        }

        return ticket;
    }
}
