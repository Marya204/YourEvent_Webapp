package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ParticipantDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/events";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String INSERT_PARTICIPANT_SQL = "INSERT INTO participant (Name, Email, Eventid) VALUES (?, ?, ?)";
    private static final String SELECT_ALL_PARTICIPANT= "SELECT * FROM participant";
    private static final String SELECT_Participant_By_ID = "SELECT * FROM participant where ParticipantID = ? ";
        private static final String UPDATE_PARTICIPANT_SQL = "UPDATE participant SET Name = ?, Email = ?, Eventid = ? WHERE ParticipantID = ?";
    private static final String DELETE_Participant_SQL = "DELETE FROM `participant` WHERE `eventid` = ?;";

    public List<Participant> getAllParticipants() {
    List<Participant> listparticipant = new ArrayList<>();
    try (Connection conn = getConnection();
         PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ALL_PARTICIPANT)) {
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            int Eventid = rs.getInt("eventid");
            String Email = rs.getString("Email");
            String Name = rs.getString("Name");
            int ParticipantID = rs.getInt("ParticipantID");

            Participant participant = new Participant (Name, Email, Eventid);
            listparticipant.add(participant);
        }
    } catch (SQLException e) {
        printSQLException(e);
    }
    return listparticipant;
}


    public void addParticipant(Participant participant) throws SQLException {
        System.out.println(INSERT_PARTICIPANT_SQL);
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(INSERT_PARTICIPANT_SQL)) {
            pstmt.setString(1, participant.getEmail());
            pstmt.setString(2, participant.getName());
            pstmt.setInt(3, participant.getParticipantID());
           
            System.out.println(pstmt);
            pstmt.executeUpdate();
            
            conn.commit();
        } catch (SQLException e) {
            printSQLException(e);
            throw e;
        }
    }
 public Participant getParticipant(int ParticipantID) {
        Participant participant = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection(); // Step 2:Create a statement using connection object
                 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_Participant_By_ID);) {
            preparedStatement.setInt(1, ParticipantID);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();
            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String Name = rs.getString("Name");
                String Email = rs.getString("Email");
                int Eventid = rs.getInt("Eventid");
                
              participant= new  Participant (ParticipantID,Name, Email, Eventid);
                
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return participant;
    }
 
   public void updateParticipant(Participant participant) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(UPDATE_PARTICIPANT_SQL)) {
            
            pstmt.setInt(1, participant.getParticipantID());
            pstmt.setString(2, participant.getName());
            pstmt.setString(3, participant.getEmail());
            pstmt.setInt(4, participant.getEventid());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
   
    public boolean deleteParticipant(int ParticipantID) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_Participant_SQL);) {
            statement.setInt(1, ParticipantID);
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
