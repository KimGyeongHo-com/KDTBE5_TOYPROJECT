package toy.example.KDTBE5_TOYPROJECT.dao;

import db.DBConnection;
import toy.example.KDTBE5_TOYPROJECT.model.Team;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TeamDao {
    private Connection connection;
    private static final TeamDao instance = new TeamDao();

    private TeamDao() {
        connection = DBConnection.getInstance();
    }

    public static TeamDao getInstance() {
        return instance;
    }

    // 팀 등록
    public int insert(Team team) throws SQLException {
        String query = "INSERT INTO team (stadium_id, name, created_at) VALUES (?, ?, NOW())";
        int result = 0;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, team.getStadiumId());
            statement.setString(2, team.getName());
            result = statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TeamDao.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        return result;
    }

    public Team findByStadiumId(int stadiumId) throws SQLException {
        String query = "SELECT * FROM team WHERE stadium_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, stadiumId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int teamId = resultSet.getInt("id");
                String teamName = resultSet.getString("name");
                Timestamp createdAt = resultSet.getTimestamp("created_at");

                Team team = new Team(teamId, stadiumId, teamName, createdAt);
                return team;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TeamDao.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        return null;
    }
}