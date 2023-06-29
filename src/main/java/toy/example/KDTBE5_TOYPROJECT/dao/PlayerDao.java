package toy.example.KDTBE5_TOYPROJECT.dao;

import db.DBConnection;
import toy.example.KDTBE5_TOYPROJECT.model.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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
        String query = "INSERT INTO player (teamId, name, position, created_at) VALUES (?, ?, ?, NOW())";
        int result = 0;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, player.getTeamId());
            statement.setString(2, player.getName());
            statement.setString(3, player.getPosition());
            result = statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PlayerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public List<Player> getPlayersByTeam(int teamId) {
        List<Player> playerList = new ArrayList<>();
        String sql = "SELECT * FROM player WHERE teamId = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, teamId); // 파라미터 위치와 값을 설정

            ResultSet playerSet = statement.executeQuery();
            while (playerSet.next()) {
                Player player = new Player(12,"null","null");
                player.setId(playerSet.getInt("id"));
                player.setTeamId(playerSet.getInt("teamId"));
                player.setPosition(playerSet.getString("position"));
                player.setName(playerSet.getString("name"));
                player.setCreated_at(playerSet.getTimestamp("created_at"));


                playerList.add(player);
            }

            playerSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(PlayerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return playerList;



    public void getPlayerByPosition() {
        String sql = "SELECT " +
                "    position, " +
                "    MAX(CASE WHEN teamId = '1' THEN name END) AS 해테, " +
                "    MAX(CASE WHEN teamId = '2' THEN name END) AS 기아, " +
                "    MAX(CASE WHEN teamId = '3' THEN name END) AS 삼성, " +
                "    MAX(CASE WHEN teamId = '4' THEN name END) AS NC " +
                "FROM Player " +
                "GROUP BY position";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();

            System.out.println("POSITION | 해테 | 기아 | 삼성 | NC");
            System.out.println("---------------------------------------");

            while (resultSet.next()) {
                String position = resultSet.getString("position");
                String 해테 = resultSet.getString("해테");
                String 기아 = resultSet.getString("기아");
                String 삼성 = resultSet.getString("삼성");
                String NC = resultSet.getString("NC");

                System.out.println(position + " | " + 해테 + " | " + 기아 + " | " + 삼성 + " | " + NC);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlayerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //메소드들 작성하시면 됩니다.
    public int findById(int id) {
        String query = "SELECT * FROM player WHERE id = ?";
        int rowCount = -1;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
                rowCount = resultSet.getInt("id");
        } catch (SQLException e) {
            Logger.getLogger("선수 select: " + e.getMessage());
        }
        return rowCount;
    }

    public int updatePlayer(int id) {
        String query = "UPDATE player SET team_id = ? WHERE id = ?";
        int rowCount = -1;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setNull(1, Types.INTEGER);
            statement.setInt(2, id);

            rowCount = statement.executeUpdate(); //0일때 처리를 해주면 더 구분이 될듯
        } catch (SQLException e) {
            Logger.getLogger("선수 update: " + e.getMessage());
        }
        return rowCount;
    }

}
