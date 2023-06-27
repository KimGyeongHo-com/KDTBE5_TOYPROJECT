package toy.example.KDTBE5_TOYPROJECT.dao;

import db.DBConnection;
import lombok.Getter;
import toy.example.KDTBE5_TOYPROJECT.model.Team;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TeamDao {
//    private Connection connection = DBConnection.getInstance();
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
        String query = "INSERT INTO team_tb (stadium_id, name, created_at) VALUES (?, ?, NOW())";
        int result = 0;
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            statement.setInt(1, team.getStadiumId());
            statement.setString(2, team.getName());
            result =  statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                team.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TeamDao.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        return result;
    }
}
