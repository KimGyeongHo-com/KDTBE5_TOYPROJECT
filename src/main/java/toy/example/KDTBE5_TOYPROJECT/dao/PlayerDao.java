package toy.example.KDTBE5_TOYPROJECT.dao;

import db.DBConnection;
import toy.example.KDTBE5_TOYPROJECT.model.Player;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlayerDao {
private Connection connection;
    private static final PlayerDao instance = new PlayerDao();

    private PlayerDao() {
        connection = DBConnection.getInstance();
    }

    public static PlayerDao getInstance() {
        return instance;
    }

    // 선수 등록
    public int insert(Player player) throws SQLException {
        String query = "INSERT INTO player (team_id, name, position, created_at) VALUES (?, ?, ?, NOW())";
        int result = 0;
        try (PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, player.getTeamId());
            statement.setString(2, player.getName());
            statement.setString(3, player.getPosition());
            result = statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PlayerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
