package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InviteDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/events";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";

    private static final String INSERT_INVITE_SQL = "INSERT INTO invite (Name, Email, Eventid) VALUES (?, ?, ?)";
    private static final String SELECT_INVITE_BY_ID = "SELECT Inviteid, Name, Email, Eventid FROM invite WHERE Inviteid = ?";
    private static final String SELECT_ALL_INVITE = "SELECT * FROM invite";
    private static final String DELETE_INVITE_SQL = "DELETE FROM invite WHERE Inviteid = ?";
    private static final String UPDATE_INVITE_SQL = "UPDATE invite SET Name = ?, Email = ?, Eventid = ? WHERE Inviteid = ?";

    public InviteDAO() {
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

    public void insertInvite(Invite invite) throws SQLException {
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INVITE_SQL)) {
            preparedStatement.setString(1, invite.getName());
            preparedStatement.setString(2, invite.getEmail());
            preparedStatement.setInt(3, invite.getEventid());
            preparedStatement.executeUpdate();
        }
    }

    public Invite selectInvite(int Inviteid) {
        Invite invite = null;
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_INVITE_BY_ID)) {
            preparedStatement.setInt(1, Inviteid);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String Name = rs.getString("Name");
                String Email = rs.getString("Email");
                int Eventid = rs.getInt("Eventid");
                invite = new Invite(Inviteid, Name, Email, Eventid);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return invite;
    }

    public List<Invite> selectAllInvite() {
        List<Invite> inviteList = new ArrayList<>();
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_INVITE)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int Inviteid = rs.getInt("Inviteid");
                String Name = rs.getString("Name");
                String Email = rs.getString("Email");
                int Eventid = rs.getInt("Eventid");
                inviteList.add(new Invite(Inviteid, Name, Email, Eventid));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return inviteList;
    }

    public boolean deleteInvite(int Inviteid) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(DELETE_INVITE_SQL)) {
            statement.setInt(1, Inviteid);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateInvite(Invite invite) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(UPDATE_INVITE_SQL)) {
            statement.setString(1, invite.getName());
            statement.setString(2, invite.getEmail());
            statement.setInt(3, invite.getEventid());
            statement.setInt(4, invite.getInviteid());
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
